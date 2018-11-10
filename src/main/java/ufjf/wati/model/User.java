package ufjf.wati.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_user", schema = "wati")
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "token")
    private String token;

    public long getId() {
        return id;
    }

    public Date getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
