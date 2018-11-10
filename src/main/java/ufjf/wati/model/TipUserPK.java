package ufjf.wati.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TipUserPK implements Serializable {
    private int idTip;
    private long idUser;

    @Column(name = "id_tip")
    @Id
    public int getIdTip() {
        return idTip;
    }

    public void setIdTip(int idTip) {
        this.idTip = idTip;
    }

    @Column(name = "id_user")
    @Id
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipUserPK that = (TipUserPK) o;
        return idTip == that.idTip &&
                idUser == that.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTip, idUser);
    }
}
