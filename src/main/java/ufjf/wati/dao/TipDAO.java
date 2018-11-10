package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.Tip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public List<Tip> getByDate(Date date) {
        TypedQuery<Tip> query = em.createQuery(
                "SELECT t FROM Tip t WHERE t.dateCreation >= :creationDate", Tip.class);
        return query
                .setParameter("creationDate", date)
                .getResultList();
    }
}
