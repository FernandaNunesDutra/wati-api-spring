package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.dto.CigarettesAverageDto;
import ufjf.wati.model.Cigarette;

import javax.persistence.*;
import java.util.*;

@Repository
public class CigaretteDAO {

    @PersistenceContext
    private EntityManager em;

    public Cigarette getToday(Long userId) {
        return getByDate(new Date(), userId);
    }

    private Cigarette getByDate(Date date, Long userId) {

        try {
            TypedQuery<Cigarette> query = em.createQuery(
                    "SELECT c FROM Cigarette c WHERE c.pk.userId = :userId AND c.pk.dateCreation = :creationDate",
                    Cigarette.class);
            query.setParameter("userId", userId);
            query.setParameter("creationDate", date, TemporalType.DATE);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }


    public void alter(Cigarette cigarette) {
        em.merge(cigarette);
    }

    private double getFieldSum(String field, Long userId) {
        String queryStr = String.format("SELECT SUM(c.%s) FROM Cigarette c WHERE c.pk.userId = :userId", field);
        TypedQuery<Double> query = em.createQuery(queryStr, Double.class);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }

    public double getTotalSpent(Long userId) {
        return getFieldSum("spent", userId);
    }

    public double getTotalEconomized(Long userId) {
        return getFieldSum("economized", userId);
    }

    private List<CigarettesAverageDto> getAverageSmoked(Date before, Date after) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT SUM(c.numCigarette), COUNT(c), c.pk.dateCreation" +
                        " FROM Cigarette c WHERE c.pk.dateCreation BETWEEN :before AND :after GROUP BY c.pk.dateCreation",
                Object[].class);
        query.setParameter("before", before, TemporalType.DATE);
        query.setParameter("after", after, TemporalType.DATE);

        List<Object[]> averageByDay = query.getResultList();

        List<CigarettesAverageDto> cigarettes = new ArrayList<>();

        for (Object[] average : averageByDay) {
            int totalCigarette = Integer.parseInt(average[0].toString());
            int totalUser = Integer.parseInt(average[1].toString());
            Date day = (Date) average[2];

            cigarettes.add(new CigarettesAverageDto(totalCigarette, totalUser, day));
        }

        return cigarettes;
    }

    public List<CigarettesAverageDto> getOneMonthAgoAverageSmoked() {
        return getAverageSmoked(oneMonthAgo(), new Date());
    }

    private Date oneMonthAgo() {
        Locale myLocale = Locale.getDefault();
        Calendar today = Calendar.getInstance(myLocale);
        today.add(Calendar.MONTH, -1);

        return today.getTime();
    }

    public List<CigarettesAverageDto> getOneMonthAgoSmoked(Long userId) {
        List<CigarettesAverageDto> cigarettesAverageDto = new ArrayList<>();
        List<Cigarette> cigarettes = getSmokedBetweenDate(userId, oneMonthAgo(), new Date());

        for (Cigarette cigarette : cigarettes) {
            cigarettesAverageDto.add(new CigarettesAverageDto(cigarette.getNumCigarette(), 1,
                            cigarette.getPk().getDateCreation()));
        }

        return cigarettesAverageDto;
    }

    private List<Cigarette> getSmokedBetweenDate(Long userId, Date before, Date after) {
        TypedQuery<Cigarette> query = em.createQuery(
                "SELECT c FROM Cigarette c WHERE c.pk.userId = :userId AND c.pk.dateCreation BETWEEN :before AND :after",
                Cigarette.class);
        query.setParameter("userId", userId);
        query.setParameter("before", before, TemporalType.DATE);
        query.setParameter("after", after, TemporalType.DATE);

        return query.getResultList();
    }

    public long getSmokedTotal(Long userId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT SUM(c.numCigarette) FROM Cigarette c WHERE c.pk.userId = :userId", Long.class);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }

    public long getAverage(Long userId) {
        long cigarette = getSmokedTotal(userId);

        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cigarette c WHERE c.pk.userId = :userId", Long.class);
        query.setParameter("userId", userId);

        long day = query.getSingleResult();

        return day > 0 ? cigarette / day : 0;
    }
}
