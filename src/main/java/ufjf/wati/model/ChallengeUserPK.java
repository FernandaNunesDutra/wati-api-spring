package ufjf.wati.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ChallengeUserPK implements Serializable {
    private int idChallenge;
    private long idUser;

    @Column(name = "id_challenge")
    @Id
    public int getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(int idChallenge) {
        this.idChallenge = idChallenge;
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
        ChallengeUserPK that = (ChallengeUserPK) o;
        return idChallenge == that.idChallenge &&
                idUser == that.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChallenge, idUser);
    }
}
