package ufjf.wati.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChallengeUserPK implements Serializable {

    @Column(name = "id_challenge")
    private int idChallenge;

    @Column(name = "id_user")
    private long idUser;


    public int getIdChallenge() {
        return idChallenge;
    }

    public long getIdUser() {
        return idUser;
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
