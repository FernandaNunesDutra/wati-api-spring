package ufjf.wati.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class CigarettePK implements Serializable {

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "id_user")
    private Long userId;

    public CigarettePK() {

    }

    public CigarettePK(Date dateCreation, Long userId) {
        this.dateCreation = dateCreation;
        this.userId = userId;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CigarettePK that = (CigarettePK) o;
        return Objects.equals(dateCreation, that.dateCreation) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCreation, userId);
    }
}
