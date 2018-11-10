package ufjf.wati.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RankingResponse {
    @SerializedName("user")
    private List<CigarettesAverageDto> userAverage;
    
    @SerializedName("average")
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
