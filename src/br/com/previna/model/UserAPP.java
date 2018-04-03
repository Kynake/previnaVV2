package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name= "USERS_APP")
@JsonIgnoreProperties(ignoreUnknown=true)

public class UserAPP implements Serializable {
    private static final long serialVersionUID = -789863172532826108L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private long id;

    @Email(message="E-mail invalido")
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name= "NAME")
    private String name;

    @Column(name= "PASSWORD")
    private String password;

    @Column(name= "PHONE")
    private String phone;

    @Column(name="PREVINA")
    private int previnas;

    @Column(name="UUID")
    private String uuid;

    /**
     * Construtor vazio.
     *
     **/
    public UserAPP() {

    }

    @Override
    public String toString() {
        return "UserAPP{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", previnas=" + previnas +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {


        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPrevinas() {
        return previnas;
    }

    public void setPrevinas(int previnas) {
        this.previnas = previnas;
    }
} 
