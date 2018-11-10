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
    public double economized;
    public double spent;
    public long smokedTotal;
    public long average;

    public TotalCigaretteResponse(double economized, double spent, long smokedTotal, long average) {
        this.smokedTotal = smokedTotal;
        this.economized = economized;
        this.average = average;
        this.spent = spent;
    }
}
