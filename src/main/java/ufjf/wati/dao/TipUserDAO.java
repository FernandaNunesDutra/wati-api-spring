/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dao;

import org.springframework.stereotype.Repository;
import ufjf.wati.model.TipUser;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fernanda
 */
@Repository
public class TipUserDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void alter(long tipId, long userId, boolean like){
     
        int l = (like) ? 1 : 0;
        Long count = count(tipId, userId);
        
        if(count == 0){
            insert(tipId, userId, l);
        }else{
           update(tipId, userId, l);
        }   
    }
    
    public Long count(long tipId, long userId)
    {
                
        try{
                        
            String query = String.format("SELECT COUNT(*) FROM tb_tip_user t WHERE t.id_user= %d AND t.id_tip = %d", userId, tipId);

            Long count = (Long)em.createNativeQuery(query).getSingleResult();
            
            return count;
                    
        }catch(Exception e){
            return Long.parseLong("0");
        }
    }
    
    public TipUser getById(long tipId, long userId)
    {
                
        try{
                        
            String query = String.format("SELECT * FROM tb_tip_user WHERE id_user = %d AND id_tip = %d", userId, tipId);

            TipUser tipUser = (TipUser) em.createNativeQuery(query)
                                            .getSingleResult();
            
            return tipUser;
        
        }catch(Exception e){
            return null;
        }
    }
        
    public void update(long tipId, long userId, int like){
         
         try{
             
            String query = String.format("UPDATE tb_tip_user SET like_tip = %d WHERE id_user= %d AND id_tip=%d", like, userId, tipId); 

            em.createNativeQuery(query).executeUpdate();
        
        }catch(Exception e){
            System.out.print("Erro ao atualizar gostei das dicas.");
        }
    } 
    
    public void insert(long tipId, long userId, int like){

        try {
            String query = String.format("INSERT INTO tb_tip_user (id_tip, id_user, like_tip, date) VALUES( %d , %d , %d , NOW() )", tipId, userId, like);
            em.createNativeQuery(query).executeUpdate();


        } catch (Exception ex) {
            Logger.getLogger(TipUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
