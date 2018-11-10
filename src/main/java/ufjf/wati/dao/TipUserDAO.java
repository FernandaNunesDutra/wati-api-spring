package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.TipUser;
import ufjf.wati.model.TipUserPK;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class TipUserDAO {

    @PersistenceContext
    private EntityManager em;

    public void alter(long tipId, long userId, boolean like) {
        int likeInt = like ? 1 : 0;

        TipUser tipUser = em.find(TipUser.class, new TipUserPK(tipId, userId));

        if (tipUser == null) {
            tipUser = new TipUser(new TipUserPK(tipId, userId), (byte) likeInt, new Date());
        } else {
            tipUser.setLikeTip((byte) likeInt);
        }

        em.merge(tipUser);
    }
}
