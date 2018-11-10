package ufjf.wati.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "tb_user", schema = "wati", catalog = "")
public class User {
    private long id;
    private Boolean authorizeData;
    private Date birth;
    private Date dtCadastro;
    private String email;
    private Integer experimentalGroups;
    private String gender;
    private String name;
    private byte[] password;
    private Boolean pesquisaEnviada;
    private String phone;
    private String preferedLanguage;
    private Boolean receiveEmails;
    private Integer recoverCode;
    private String token;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "authorize_data")
    public Boolean getAuthorizeData() {
        return authorizeData;
    }

    public void setAuthorizeData(Boolean authorizeData) {
        this.authorizeData = authorizeData;
    }

    @Basic
    @Column(name = "birth")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "dt_cadastro")
    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "experimental_groups")
    public Integer getExperimentalGroups() {
        return experimentalGroups;
    }

    public void setExperimentalGroups(Integer experimentalGroups) {
        this.experimentalGroups = experimentalGroups;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Basic
    @Column(name = "pesquisa_enviada")
    public Boolean getPesquisaEnviada() {
        return pesquisaEnviada;
    }

    public void setPesquisaEnviada(Boolean pesquisaEnviada) {
        this.pesquisaEnviada = pesquisaEnviada;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "prefered_language")
    public String getPreferedLanguage() {
        return preferedLanguage;
    }

    public void setPreferedLanguage(String preferedLanguage) {
        this.preferedLanguage = preferedLanguage;
    }

    @Basic
    @Column(name = "receive_emails")
    public Boolean getReceiveEmails() {
        return receiveEmails;
    }

    public void setReceiveEmails(Boolean receiveEmails) {
        this.receiveEmails = receiveEmails;
    }

    @Basic
    @Column(name = "recover_code")
    public Integer getRecoverCode() {
        return recoverCode;
    }

    public void setRecoverCode(Integer recoverCode) {
        this.recoverCode = recoverCode;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(authorizeData, user.authorizeData) &&
                Objects.equals(birth, user.birth) &&
                Objects.equals(dtCadastro, user.dtCadastro) &&
                Objects.equals(email, user.email) &&
                Objects.equals(experimentalGroups, user.experimentalGroups) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(name, user.name) &&
                Arrays.equals(password, user.password) &&
                Objects.equals(pesquisaEnviada, user.pesquisaEnviada) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(preferedLanguage, user.preferedLanguage) &&
                Objects.equals(receiveEmails, user.receiveEmails) &&
                Objects.equals(recoverCode, user.recoverCode) &&
                Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, authorizeData, birth, dtCadastro, email, experimentalGroups, gender, name, pesquisaEnviada, phone, preferedLanguage, receiveEmails, recoverCode, token);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
}
