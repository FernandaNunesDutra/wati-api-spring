package ufjf.wati.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_tip_user", schema = "wati", catalog = "")
@IdClass(TipUserPK.class)
public class TipUser {
    private int idTip;
    private long idUser;
    private byte likeTip;
    private Date date;

    @Id
    @Column(name = "id_tip")
    public int getIdTip() {
        return idTip;
    }

    public void setIdTip(int idTip) {
        this.idTip = idTip;
    }

    @Id
    @Column(name = "id_user")
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "like_tip")
    public byte getLikeTip() {
        return likeTip;
    }

    public void setLikeTip(byte likeTip) {
        this.likeTip = likeTip;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipUser tipUser = (TipUser) o;
        return idTip == tipUser.idTip &&
                idUser == tipUser.idUser &&
                likeTip == tipUser.likeTip &&
                Objects.equals(date, tipUser.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTip, idUser, likeTip, date);
    }
}
