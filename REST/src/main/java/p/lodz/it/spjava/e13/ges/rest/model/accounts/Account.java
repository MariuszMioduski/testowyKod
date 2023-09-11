package p.lodz.it.spjava.e13.ges.rest.model.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.rest.model.AbstractEntity;

import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findByLogin", query = "select a from Account a where a.login = :login"),

        @NamedQuery(name = "Account.findByLoginAndPasswordAndActiveTrue", query = "select a from Account a where a.login = :login and a.password = :password and a.active = true")
})
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue("INVALID")
@SecondaryTable(name="personal_data")
public class Account extends AbstractEntity {

    @Column(updatable = false)
    @Getter
    private String role;

    @Column(nullable = false, updatable = false, unique = true)
    @Getter @Setter
    private String login;

    @Getter @Setter
    private String password;

    @Column(table="personal_data",nullable = false, unique = true)
    @Getter @Setter
    private String email;

    @Column(table = "personal_data", name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(table = "personal_data",name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(table = "personal_data", name = "phone_number")
    @Getter @Setter
    private String phoneNumber;

    @Column(nullable = false)
    @Getter @Setter
    private boolean active=false;

    @Getter @Setter
    @Column(nullable = false)
    private boolean confirmed = false;



//przeniesione do AbstracEntity
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false, updatable = false, name = "creation_date")
//    private Date creationDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="last_modification_date")
//    private Date lastModificationDate;

    public Account() {
        super();
    }

    public Account(String login, String password, String email, String firstName, String lastName, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  super.toString() +"Account{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", confirmed=" + confirmed +
                '}' ;
    }
}
