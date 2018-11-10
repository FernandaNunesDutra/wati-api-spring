/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.Challenge;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ChallengeDAO {

    @PersistenceContext
    private EntityManager em;
    
    public List<Challenge> getAll(){
        
        List<Challenge> challenges = new ArrayList<>();
        TypedQuery<Challenge> query = em.createQuery("Select c FROM Challenge c", Challenge.class);
        
        try{
        
            challenges = query.getResultList();
        
        }catch(Exception e){
            System.out.print("Erro ao buscar usuário no banco.");
        }
        
        return challenges;
    }
    
    public List<Challenge> getByDate(Date date){
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Challenge> criteriaQuery = criteriaBuilder.createQuery(Challenge.class);
        Root<Challenge> root = criteriaQuery.from(em.getMetamodel().entity(Challenge.class));
        
        ParameterExpression<Date> parameter = criteriaBuilder.parameter(Date.class);
        Predicate dateCreationPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("dateCreation"), parameter);
        Predicate dateCreationOrPredicate = criteriaBuilder.or(dateCreationPredicate, root.<Date>get("dateCreation").isNull());

        criteriaQuery.where(dateCreationOrPredicate);

        List<Challenge> challenges = em.createQuery(criteriaQuery)
                .setParameter(parameter, date, TemporalType.DATE)
                .getResultList();
        
        return challenges;
    }
}
