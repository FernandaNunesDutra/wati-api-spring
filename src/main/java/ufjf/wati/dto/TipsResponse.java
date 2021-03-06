/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ufjf.wati.model.Tip;

import java.util.List;

/**
 *
 * @author fernanda
 */
public class TipsResponse {

    @JsonProperty("tips")
    private List<Tip> tips;

    public TipsResponse(List<Tip> tips) {
        this.tips = tips;
    }

    public List<Tip> getTips() {
        return tips;
    }
}
