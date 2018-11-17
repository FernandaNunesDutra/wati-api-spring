package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_tip", schema = "wati")
public class Tip {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "value")
    private Integer value;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Integer getValue() {
        return value;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
}
