/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author fernanda
 */
@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public User login(String email, String password) {

        User user = null;

        TypedQuery<User> query = em.createQuery("Select u FROM User u WHERE u.email = :email and u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password.getBytes());

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            System.out.print("Erro ao buscar usuário no banco.");
        }

        return user;
    }

    public void updateToken(String token, long id) {

        try {

            Query query = em.createQuery("UPDATE User u SET u.token = :token WHERE u.id = :id");
            query.setParameter("token", token);
            query.setParameter("id", id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.print("Erro ao atualizar o token.");
        }
    }

    public boolean validate(String token) {

        User user = findByToken(token);

        try {

            if (user != null)
                return true;

        } catch (Exception e) {
            System.out.print("Erro ao validar usuário pelo token.");
        }

        return false;
    }

    public boolean logout(String token) {

        try {

            Query query = em.createQuery("UPDATE User u SET u.token = NULL WHERE u.token = :token");
            query.setParameter("token", token);
            query.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print("Erro ao deslogar usuário.");
        }

        return false;
    }

    public User findByToken(String token) {

        User user = null;

        TypedQuery<User> query = em.createQuery("Select u FROM User u WHERE u.token = :token", User.class);
        query.setParameter("token", token);

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            System.out.print("Erro ao buscar usuário pelo token no banco.");
        }

        return user;

    }


}
