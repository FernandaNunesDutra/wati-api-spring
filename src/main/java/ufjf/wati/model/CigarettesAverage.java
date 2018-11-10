/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 *
 * @author fernanda
 */
public class CigarettesAverage {
    
    @SerializedName("total_cigarette")
    private int totalCigarette;
    @SerializedName("tota_user")
    private int totalUser;
    @SerializedName("date")
    private Date day;

    public CigarettesAverage(int totalCigarette, int totalUser, Date day) {
        this.totalCigarette = totalCigarette;
        this.totalUser = totalUser;
        this.day = day;
    }

    public int getTotalCigarette() {
        return totalCigarette;
    }

    public void setTotalCigarette(int totalCigarette) {
        this.totalCigarette = totalCigarette;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    
    
    
}
