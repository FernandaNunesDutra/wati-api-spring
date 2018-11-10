package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_challenge_user", schema = "wati")
public class ChallengeUser {

    @EmbeddedId
    private ChallengeUserPK pk;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    public ChallengeUserPK getPk() {
        return pk;
    }

    public Date getDate() {
        return date;
    }

}
