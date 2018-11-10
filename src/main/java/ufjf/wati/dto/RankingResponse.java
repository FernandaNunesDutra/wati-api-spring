package ufjf.wati.dto;

import ufjf.wati.model.CigarettesAverage;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RankingResponse {
    @SerializedName("user")
    private List<CigarettesAverage> userAverage;
    
    @SerializedName("average")
    private List<CigarettesAverage> average;

    public RankingResponse(List<CigarettesAverage> userAverage, List<CigarettesAverage> average) {
        this.userAverage = userAverage;
        this.average = average;
    }

    public List<CigarettesAverage> getUserAverage() {
        return userAverage;
    }

    public List<CigarettesAverage> getAverage() {
        return average;
    }
}
