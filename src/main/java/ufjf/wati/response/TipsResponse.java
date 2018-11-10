/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.response;

import ufjf.wati.model.Tip;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class TipsResponse {
    @SerializedName("tips")
    private List<Tip> tips;

    public TipsResponse(List<Tip> tips) {
        this.tips = tips;
    }
    
}
