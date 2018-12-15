package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.exception.UnauthorizedUserException;
import ufjf.wati.model.User;

import javax.persistence.*;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public User login(String email, byte[] password) {

        TypedQuery<User> query = em.createQuery(
                "Select u FROM User u WHERE u.email = :email and u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new UnauthorizedUserException();
        }
    }

    public void updateToken(String token, long id) {
        Query query = em.createQuery("UPDATE User u SET u.token = :token WHERE u.id = :id");
        query.setParameter("token", token);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void validateUserToken(String token) {
        findByToken(token);
    }

    public void logout(String token) {
        Query query = em.createQuery("UPDATE User u SET u.token = NULL WHERE u.token = :token");
        query.setParameter("token", token);
        query.executeUpdate();
    }

    public User findByToken(String token) {
        TypedQuery<User> query = em.createQuery("Select u FROM User u WHERE u.token = :token", User.class);
        query.setParameter("token", token);

        try {

            return query.getSingleResult();

        } catch (NoResultException e) {
            throw new UnauthorizedUserException();
        }
    }
}
