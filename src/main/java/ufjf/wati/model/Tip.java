package ufjf.wati.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_tip", schema = "wati", catalog = "")
public class Tip {
    private int id;
    private String description;
    private String title;
    private Integer value;
    private Date dateCreation;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Basic
    @Column(name = "date_creation")
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tip tip = (Tip) o;
        return id == tip.id &&
                Objects.equals(description, tip.description) &&
                Objects.equals(title, tip.title) &&
                Objects.equals(value, tip.value) &&
                Objects.equals(dateCreation, tip.dateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, value, dateCreation);
    }
}
