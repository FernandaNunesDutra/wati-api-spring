package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_challenge", schema = "wati")
public class Challenge {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private Integer type;

    @Column(name = "value")
    private Integer value;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Integer getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
}

