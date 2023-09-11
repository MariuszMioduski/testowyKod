package p.lodz.it.spjava.e13.ges.rest.model.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.rest.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicemen")
@DiscriminatorValue("SERVICEMAN")
@NamedQueries({
        @NamedQuery(name = "Serviceman.findByLogin", query = "select s from Serviceman s where s.login = :login")
})
public class Serviceman extends Account {

//    @ManyToMany(mappedBy = "serviceman")
//    private List<ServiceRequest> serviceRequest=new ArrayList<>();

    @OneToMany(mappedBy = "serviceman") //trzeba dodać cascady
    @Getter @Setter //powinny tu być?
    private List<ServiceRequest> serviceRequestList = new ArrayList<>();

    @Column(nullable = false)
    @Getter @Setter
    private String department;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(nullable = false)
    private ServicemanType servicemanType = ServicemanType.ELECTRICAL_TECHNICIAN;

    @Column(name = "additional_working_persmissions")
    @Getter @Setter
    private String additionalWorkingPermissions;

    public Serviceman() {
        super();
    }

    public Serviceman(String login, String password, String email, String firstName, String lastName, String phoneNumber, String department, String additionalWorkingPermissions) {
        super(login, password, email, firstName, lastName, phoneNumber);
        this.department = department;
        this.additionalWorkingPermissions = additionalWorkingPermissions;
    }


    //Czy ten konstruktor powinien zawierać listę zgłoszeń
    public Serviceman(String login, String password, String email, String firstName, String lastName, String phoneNumber, List<ServiceRequest> serviceRequestList, String department, String additionalWorkingPermissions) {
        super(login, password, email, firstName, lastName, phoneNumber);
        this.serviceRequestList = serviceRequestList;
        this.department = department;
        this.additionalWorkingPermissions = additionalWorkingPermissions;
    }

    @Override
    public String toString() {
        return  super.toString() + "Serviceman{" +
                ", department='" + department + '\'' +
                ", servicemanType=" + servicemanType +
                ", additionalWorkingPermissions='" + additionalWorkingPermissions + '\'' +
                "} " ;
    }
}
