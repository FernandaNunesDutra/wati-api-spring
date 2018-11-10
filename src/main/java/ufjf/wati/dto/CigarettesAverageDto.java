package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CigarettesAverageDto {
    
    @JsonProperty("total_cigarette")
    private int totalCigarette;

    @JsonProperty("total_user")
    private int totalUser;

    @JsonProperty("date")
    private Date day;

    public CigarettesAverageDto(int totalCigarette, int totalUser, Date day) {
        this.totalCigarette = totalCigarette;
        this.totalUser = totalUser;
        this.day = day;
    }

    public int getTotalCigarette() {
        return totalCigarette;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public Date getDay() {
        return day;
    }
}
