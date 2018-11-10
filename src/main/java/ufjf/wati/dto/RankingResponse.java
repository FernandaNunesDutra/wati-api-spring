package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RankingResponse {
    @JsonProperty("user")
    private List<CigarettesAverageDto> userAverage;
    
    @JsonProperty("average")
    private List<CigarettesAverageDto> average;

    public RankingResponse(List<CigarettesAverageDto> userAverage, List<CigarettesAverageDto> average) {
        this.userAverage = userAverage;
        this.average = average;
    }

    public List<CigarettesAverageDto> getUserAverage() {
        return userAverage;
    }

    public List<CigarettesAverageDto> getAverage() {
        return average;
    }
}
