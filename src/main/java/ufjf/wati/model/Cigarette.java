package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_cigarette", schema = "wati")
public class Cigarette {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "pack_cigarettes_price")
    private Double packCigarettesPrice;

    @Column(name = "economized")
    private Double economized;

    @Column(name = "spent")
    private Double spent;

    @Column(name = "num_cigarette")
    private Integer numCigarette;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "id_user")
    private Long userId;

    public Cigarette() {
    }

    public Cigarette(Double packCigarettesPrice, Double economized, Double spent, Integer numCigarette,
                     Date dateCreation, Long userId) {
        this.packCigarettesPrice = packCigarettesPrice;
        this.numCigarette = numCigarette;
        this.dateCreation = dateCreation;
        this.economized = economized;
        this.spent = spent;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public Double getPackCigarettesPrice() {
        return packCigarettesPrice;
    }

    public Double getEconomized() {
        return economized;
    }

    public Double getSpent() {
        return spent;
    }

    public Integer getNumCigarette() {
        return numCigarette;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Long getUserId() {
        return userId;
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

    private String format(String number) {
        String output;

        output = number.replace(".", "");
        output = output.replace(',', '.');

        return output;
    }
}
