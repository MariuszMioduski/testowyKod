package p.lodz.it.spjava.e13.ges.rest.model.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.rest.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="service_managers")
@DiscriminatorValue("SERVICE_MANAGER")
public class ServiceManager extends Account{

    //powinien tu być getter setter i w konstruktorze powinniśmy to ująć?
    @OneToMany(mappedBy = "serviceManager")
  private List<ServiceRequest> serviceRequestList = new ArrayList<>();

    @Column(nullable = false)
    @Getter @Setter
    private String department;

    public ServiceManager() {
        super();
    }

    public ServiceManager(String login, String password, String email, String firstName, String lastName, String phoneNumber, String department) {
        super(login, password, email, firstName, lastName, phoneNumber);
        this.department = department;
    }
}
