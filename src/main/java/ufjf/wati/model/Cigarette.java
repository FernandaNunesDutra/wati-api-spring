package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_cigarette", schema = "wati")
public class Cigarette {
    private int id;
    private Double packCigarettesPrice;
    private Double economized;
    private Double spent;
    private Integer numCigarette;
    private Date dateCreation;
    private Long userId;


    public Cigarette(){}

    public Cigarette(Double packCigarettesPrice, Double economized, Double spent, Integer numCigarette,
                     Date dateCreation, Long userId) {
        this.packCigarettesPrice = packCigarettesPrice;
        this.numCigarette = numCigarette;
        this.dateCreation = dateCreation;
        this.economized = economized;
        this.spent = spent;
        this.userId = userId;
    }


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_user")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "pack_cigarettes_price")
    public Double getPackCigarettesPrice() {
        return packCigarettesPrice;
    }

    public void setPackCigarettesPrice(Double packCigarettesPrice) {
        this.packCigarettesPrice = packCigarettesPrice;
    }

    @Basic
    @Column(name = "economized")
    public Double getEconomized() {
        return economized;
    }

    public void setEconomized(Double economized) {
        this.economized = economized;
    }

    @Basic
    @Column(name = "spent")
    public Double getSpent() {
        return spent;
    }

    public void setSpent(Double spent) {
        this.spent = spent;
    }

    @Basic
    @Column(name = "num_cigarette")
    public Integer getNumCigarette() {
        return numCigarette;
    }

    public void setNumCigarette(Integer numCigarette) {
        this.numCigarette = numCigarette;
    }

    @Basic
    @Column(name = "date_creation")
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String formatEconomized() {
        return format(String.valueOf(economized));
    }

    public String formatSpent() {
        return format(String.valueOf(spent));
    }

    public String formatPricePack() {
        return format(String.valueOf(packCigarettesPrice));
    }

    private String format(String number){
        String output;

        output = number.replace(".", "");
        output = output.replace(',', '.');

        return output;
    }
}
