package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalCigaretteResponse {
    private double economized;
    private double spent;

    @JsonProperty("smoked_total")
    private long smokedTotal;

    private long average;

    public TotalCigaretteResponse(double economized, double spent, long smokedTotal, long average) {
        this.smokedTotal = smokedTotal;
        this.economized = economized;
        this.average = average;
        this.spent = spent;
    }

    public double getEconomized() {
        return economized;
    }

    public double getSpent() {
        return spent;
    }

    public long getSmokedTotal() {
        return smokedTotal;
    }

    public long getAverage() {
        return average;
    }
}
