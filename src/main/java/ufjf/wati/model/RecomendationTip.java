package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_tip_user_recommendation", schema = "wati")
public class RecomendationTip {

    @EmbeddedId
    private RecomendationTipPK pk;

    @Column(name = "algorithm")
    private String algorithm;

    @Column(name = "metric")
    private Integer metric;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;


    public RecomendationTip() {

    }

    public RecomendationTipPK getPk() {
        return pk;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public Integer getMetric() {
        return metric;
    }

    public Date getDate() {
        return date;
    }
}

