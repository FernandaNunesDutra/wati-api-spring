package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class RecommendedTipsDto implements Serializable {

    @JsonProperty("tips")
    private List<Integer> tips;

    public RecommendedTipsDto() {
    }

    public RecommendedTipsDto(String teste) {
    }


    public RecommendedTipsDto(List<Integer> tips) {
        this.tips = tips;
    }

    public List<Integer> getTips() {
        return tips;
    }

    public void setTips(List<Integer> tips) {
        this.tips = tips;
    }
}
