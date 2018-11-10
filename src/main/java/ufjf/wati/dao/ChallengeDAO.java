package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.Challenge;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class ChallengeDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Challenge> getAll() {
        TypedQuery<Challenge> query = em.createQuery("SELECT c FROM Challenge c", Challenge.class);
        return query.getResultList();
    }

    public List<Challenge> getByDate(Date date) {
        TypedQuery<Challenge> query = em.createQuery(
                "SELECT c FROM Challenge c WHERE c.dateCreation >= :creationDate", Challenge.class);
        return query
                .setParameter("creationDate", date)
                .getResultList();
    }
}
