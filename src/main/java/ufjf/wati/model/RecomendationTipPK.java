package ufjf.wati.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecomendationTipPK  implements Serializable {

    @Column(name = "id_tip")
    private long idTip;

    @Column(name = "id_user")
    private long idUser;

    public RecomendationTipPK() {

    }

    public RecomendationTipPK(long idTip, long idUser) {
        this.idTip = idTip;
        this.idUser = idUser;
    }

    public long getIdTip() {
        return idTip;
    }

    public long getIdUser() {
        return idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecomendationTipPK that = (RecomendationTipPK) o;
        return idTip == that.idTip &&
                idUser == that.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTip, idUser);
    }
}
