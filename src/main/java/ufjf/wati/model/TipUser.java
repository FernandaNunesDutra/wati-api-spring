package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_tip_user", schema = "wati")
public class TipUser {

    @EmbeddedId
    private TipUserPK pk;

    @Basic
    @Column(name = "like_tip")
    private byte likeTip;

    @Basic
    @Column(name = "date")
    private Date date;

    public TipUser() {
        
    }

    public TipUser(TipUserPK pk, byte likeTip, Date date) {
        this.pk = pk;
        this.likeTip = likeTip;
        this.date = date;
    }

    public TipUserPK getPk() {
        return pk;
    }

    public byte getLikeTip() {
        return likeTip;
    }

    public void setLikeTip(byte likeTip) {
        this.likeTip = likeTip;
    }

    public Date getDate() {
        return date;
    }
}
