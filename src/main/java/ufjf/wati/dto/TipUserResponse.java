/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dto;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author fernanda
 */
public class TipUserResponse {
    @SerializedName("id_tip")
    private long tipId;
    @SerializedName("id_user")
    private long userId;

    public TipUserResponse(long tipId, long userId) {
        this.tipId = tipId;
        this.userId = userId;
    }

    public long getTipId() {
        return tipId;
    }

    public long getUserId() {
        return userId;
    }
}
