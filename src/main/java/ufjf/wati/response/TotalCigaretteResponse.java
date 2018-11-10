/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.response;

/**
 *
 * @author fernanda
 */
public class TotalCigaretteResponse {
    private double economized;
    private double spent;
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
