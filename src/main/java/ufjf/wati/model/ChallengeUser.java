package ufjf.wati.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_challenge_user", schema = "wati", catalog = "")
@IdClass(ChallengeUserPK.class)
public class ChallengeUser {
    private int idChallenge;
    private long idUser;
    private Date date;

    @Id
    @Column(name = "id_challenge")
    public int getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(int idChallenge) {
        this.idChallenge = idChallenge;
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
        ChallengeUser that = (ChallengeUser) o;
        return idChallenge == that.idChallenge &&
                idUser == that.idUser &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChallenge, idUser, date);
    }
}
