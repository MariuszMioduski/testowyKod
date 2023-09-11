package p.lodz.it.spjava.e13.ges.rest.model.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;




@Entity
@NamedQueries({
        @NamedQuery(name = "Admin.findByLogin", query = "select a from Admin a where a.login = :login")
})
@Table(name = "admins")
@DiscriminatorValue("ADMIN")
public class Admin extends Account {

    @Column(nullable = false, name = "work_alarm_phone")
    @Getter @Setter
    private String workAlarmPhone;

    public Admin() {
        super();
    }

    public Admin(String login, String password, String email, String firstName, String lastName, String phoneNumber, String workAlarmPhone) {
        super(login, password, email, firstName, lastName, phoneNumber);
        this.workAlarmPhone = workAlarmPhone;
    }



    @Override
    public String toString() {
        return super.toString() +"Admin{" +
                "workAlarmPhone='" + workAlarmPhone + '\'' +
                "} " ;
    }
}
