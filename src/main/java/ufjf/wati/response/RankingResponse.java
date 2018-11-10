/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.response;

import ufjf.wati.model.CigarettesAverage;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class RankingResponse {
    @SerializedName("user")
    private List<CigarettesAverage> userAverage;
    
    @SerializedName("average")
    private List<CigarettesAverage> average;

    public RankingResponse(List<CigarettesAverage> userAverage, List<CigarettesAverage> average) {
        this.userAverage = userAverage;
        this.average = average;
    }   
    
}
