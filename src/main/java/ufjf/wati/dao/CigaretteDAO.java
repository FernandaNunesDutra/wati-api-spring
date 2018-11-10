/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.Cigarette;
import ufjf.wati.model.CigarettesAverage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author fernanda
 */
@Repository
public class CigaretteDAO {

    @PersistenceContext
    private EntityManager em;

    public Cigarette getToday(Long userId) {
        Date creationDate;

        try {
            Date today = new Date();
            creationDate = new SimpleDateFormat("yyyyMMdd").parse(today.toString());

        } catch (Exception e) {
            return null;
        }

        return getByDate(creationDate, userId);
    }

    public Cigarette getByDate(Date date, Long userId) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            String query = String.format("SELECT * FROM tb_cigarette WHERE id_user = %d AND date_creation='%s'", userId, dateString, Cigarette.class);

            Cigarette dateCigarette = (Cigarette) em.createNativeQuery(query, Cigarette.class).getSingleResult();

            return dateCigarette;
        } catch (NoResultException ex) {
            return null;
        }

    }


    public void alter(Cigarette cigarette) {

        if (getByDate(cigarette.getDateCreation(), cigarette.getUserId()) == null) {
            insert(cigarette);
        } else {
            update(cigarette);
        }
    }

    public void insert(Cigarette cigarette) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(cigarette.getDateCreation());

        String query = String.format("INSERT INTO tb_cigarette (pack_cigarettes_price, num_cigarette, date_creation,id_user, economized, spent) "
                        + "         VALUES(%s , %d, '%s', %d, %s , %s)",
                cigarette.formatPricePack(),
                cigarette.getNumCigarette(),
                date,
                cigarette.getUserId(),
                cigarette.formatEconomized(),
                cigarette.formatSpent());

        em.createNativeQuery(query).executeUpdate();
    }

    public void update(Cigarette cigarette) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(cigarette.getDateCreation());

        String query = String.format("UPDATE tb_cigarette SET pack_cigarettes_price = %s, num_cigarette = %d, "
                        + " economized = %s, spent = %s WHERE id_user = %d AND date_creation = '%s'",
                cigarette.formatPricePack(),
                cigarette.getNumCigarette(),
                cigarette.formatEconomized(),
                cigarette.formatSpent(),
                cigarette.getUserId(),
                date);

        em.createNativeQuery(query).executeUpdate();
    }

    public double getTotalSpent(Long userId) {

        try {
            String query = String.format("SELECT SUM(c.spent) FROM tb_cigarette c WHERE c.id_user = %d", userId);

            double spent = (double) em.createNativeQuery(query)
                    .getSingleResult();
            return spent;
        } catch (NullPointerException e) {
            return 0.0;
        }

    }

    public double getTotalEconomized(Long userId) {

        try {

            String query = String.format("SELECT SUM(c.economized) FROM tb_cigarette c WHERE c.id_user = %d", userId);

            double economized = (double) em.createNativeQuery(query)
                    .getSingleResult();
            return economized;
        } catch (NullPointerException e) {
            return 0.0;
        }
    }

    public List<Cigarette> getAverageSmoked(Long userId) {

        return getSmokedBetweenDate(userId, oneMonthAgo(), new Date());
    }

    public List<CigarettesAverage> getAverageSmoked(Date firstDate, Date secondDate) {

        List<CigarettesAverage> cigarettes = new ArrayList<>();

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String first = formatter.format(firstDate);
            String second = formatter.format(secondDate);

            String query = String.format("SELECT SUM(num_cigarette) AS total_cigarette , COUNT(id) AS total_user, date_creation FROM tb_cigarette c WHERE date_creation BETWEEN '%s' AND '%s' GROUP BY date_creation", first, second);

            List<Object[]> averageByDay = em.createNativeQuery(query).getResultList();

            for (Object[] average : averageByDay) {
                int totalCigarette = Integer.parseInt(average[0].toString());
                int totalUser = Integer.parseInt(average[1].toString());
                Date day = formatter.parse(average[2].toString());

                cigarettes.add(new CigarettesAverage(totalCigarette, totalUser, day));
            }

        } catch (Exception e) {
        }

        return cigarettes;
    }

    public List<CigarettesAverage> getOneMonthAgoAverageSmoked() {
        return getAverageSmoked(oneMonthAgo(), new Date());
    }

    private Date oneMonthAgo() {
        Locale myLocale = Locale.getDefault();
        Calendar today = Calendar.getInstance(myLocale);
        today.add(Calendar.MONTH, -1);
        Date oneMonthAgo = today.getTime();

        return oneMonthAgo;
    }

    public List<CigarettesAverage> getOneMonthAgoSmoked(Long userId) {
        List<CigarettesAverage> cigarettesAverage = new ArrayList<>();
        List<Cigarette> cigarettes = getSmokedBetweenDate(userId, oneMonthAgo(), new Date());

        for (Cigarette cigarette : cigarettes) {
            cigarettesAverage.add(new CigarettesAverage(cigarette.getNumCigarette(), 1, cigarette.getDateCreation()));
        }

        return cigarettesAverage;
    }

    public List<Cigarette> getSmokedBetweenDate(Long userId, Date firstDate, Date secondDate) {
        List<Cigarette> cigarettes = new ArrayList<>();

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String first = formatter.format(firstDate);
            String second = formatter.format(secondDate);

            String query = String.format("SELECT * FROM tb_cigarette c WHERE c.id_user = %d AND date_creation BETWEEN '%s' AND '%s'", userId, first, second);

            cigarettes = em.createNativeQuery(query, Cigarette.class).getResultList();

        } catch (Exception e) {

        }

        return cigarettes;
    }

    public long getSmokedTotal(Long userId) {

        try {

            String query = String.format("SELECT SUM(c.num_cigarette) FROM tb_cigarette c WHERE c.id_user = %d", userId);

            Object smoked = em.createNativeQuery(query).getSingleResult();
            return Long.parseLong(smoked.toString());
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public long getAverage(Long userId) {

        try {

            long cigarette = getSmokedTotal(userId);
            Query query = em.createQuery("SELECT COUNT(c.id) FROM Cigarette c WHERE c.userId = :userId");
            query.setParameter("userId", userId);
            long day = (long) query.getSingleResult();

            if (day > 0) {
                long average = (cigarette / day);
                return average;
            }

        } catch (NullPointerException e) {
            return 0;
        }

        return 0;
    }
}
