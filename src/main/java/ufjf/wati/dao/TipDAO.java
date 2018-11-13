package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.RecomendationTip;
import ufjf.wati.model.Tip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TipDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Tip> getAll() {
        TypedQuery<Tip> query = em.createQuery("SELECT t FROM Tip t", Tip.class);
        return query.getResultList();
    }

    public Tip getById(long id){
        return em.find(Tip.class, id);
    }

    public List<Tip> getByDate(Date date) {
        TypedQuery<Tip> query = em.createQuery(
                "SELECT t FROM Tip t WHERE t.dateCreation >= :creationDate", Tip.class);
        return query
                .setParameter("creationDate", date, TemporalType.DATE)
                .getResultList();
    }

    public List<Tip> getByDateRecommender(Date date, long userId) {
        TypedQuery<RecomendationTip> query = em.createQuery("SELECT r FROM RecomendationTip r " +
                "WHERE r.pk.idUser = :idUser AND r.date >= :creationDate", RecomendationTip.class);

        List<RecomendationTip>  recommenderTips = query.setParameter("creationDate", date, TemporalType.DATE).getResultList();

        List<Tip> tips = new ArrayList<>();

        for (RecomendationTip recommender : recommenderTips) {
            tips.add(getById(recommender.getPk().getIdTip()));
        }

        return tips;
    }

}
