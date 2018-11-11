package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CigaretteDto {

    @JsonProperty("pack_cigarettes_price")
    double packCigarettesPrice;

    @JsonProperty("date_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date date;

    @JsonProperty("num_cigarette")
    int numCigarette;

    @JsonProperty("economized")
    double economized;

    @JsonProperty("spent")
    double spent;

    @JsonProperty("id_user")
    Long userId;

    public double getPackCigarettesPrice() {
        return packCigarettesPrice;
    }

    public Date getDate() {
        return date;
    }

    public int getNumCigarette() {
        return numCigarette;
    }

    public double getEconomized() {
        return economized;
    }

    public double getSpent() {
        return spent;
    }

    public Long getUserId() {
        return userId;
    }
}
