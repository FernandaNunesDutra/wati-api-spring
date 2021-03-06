/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ufjf.wati.model.Challenge;

import java.util.List;

/**
 *
 * @author fernanda
 */
public class ChallengesResponse {

    @JsonProperty("challenges")
    private List<Challenge> challenges;

    public ChallengesResponse(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
}
