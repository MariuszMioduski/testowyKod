package p.lodz.it.spjava.e13.ges.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.ServiceManager;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Serviceman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_requests")
public class ServiceRequest extends AbstractEntity implements Serializable {

    @Column(nullable = false, name = "service_request_description")
    @Getter @Setter
    private String serviceRequestDescription;

 // Czy mają być dodatkowe pola dla utworzenia żądania serwisowego?
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column( nullable = false, updatable = false, name = "creation_date_service_request")
//    private Date creationDateServiceRequest;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "last_modification_date_service_request")
//    private Date lastModificationDateServiceRequest;


   // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "farm_location_id", nullable = false, updatable = false)
    @Getter @Setter
    private FarmLocation farmLocation;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "service_requests_servicemen",
//    joinColumns = {@JoinColumn(name = "service_request_id", referencedColumnName = "id",
//            nullable = false, updatable = false)},
//     inverseJoinColumns = {
//            @JoinColumn(name="serviceman_id", referencedColumnName = "id", nullable = false, updatable = false)})
//    private List<Serviceman> serviceman = new ArrayList<>();

  //  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "serviceman_id", nullable = true, updatable = true)
    @Getter @Setter //czy powinnien być getter i setter w relacjach?
    private Serviceman serviceman;

   // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne()
    @JoinColumn(name = "created_id", nullable = false, updatable = false)
    @Getter @Setter
    private ServiceManager serviceManager;


    @Column(nullable = false, name = "request_status")
    @Getter @Setter
    private String requestStatus;

    @Column(nullable = false)
    @Getter @Setter
    private String reported;

    @Column(nullable = false, name = "invidual_object_name")
    @Getter @Setter
    private String InvidualObjectName;

    @Column(name = "additional_remarks")
    @Getter @Setter
    private String additionalRemarks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = true, name = "realization_date")
    @Getter @Setter
    private Date realizationDate;


    public ServiceRequest() {
        super();
    }

    public ServiceRequest(String serviceRequestDescription,final FarmLocation farmLocation,final Serviceman serviceman,final ServiceManager serviceManager, String requestStatus, String reported, String invidualObjectName, String additionalRemarks, Date realizationDate) {
        this.serviceRequestDescription = serviceRequestDescription;
        this.farmLocation = farmLocation;
        this.serviceman = serviceman;
        this.serviceManager = serviceManager;
        this.requestStatus = requestStatus;
        this.reported = reported;
        InvidualObjectName = invidualObjectName;
        this.additionalRemarks = additionalRemarks;
        this.realizationDate = realizationDate;
    }

    public ServiceRequest(String serviceRequestDescription, FarmLocation farmLocation, Serviceman serviceman, ServiceManager serviceManager, String requestStatus, String reported, String invidualObjectName, Date realizationDate) {
        this.serviceRequestDescription = serviceRequestDescription;
        this.farmLocation = farmLocation;
        this.serviceman = serviceman;
        this.serviceManager = serviceManager;
        this.requestStatus = requestStatus;
        this.reported = reported;
        InvidualObjectName = invidualObjectName;
        this.realizationDate = realizationDate;
    }

    public ServiceRequest(String serviceRequestDescription, FarmLocation farmLocation, ServiceManager serviceManager, String requestStatus, String reported, String invidualObjectName, Date realizationDate) {
        this.serviceRequestDescription = serviceRequestDescription;
        this.farmLocation = farmLocation;
        this.serviceManager = serviceManager;
        this.requestStatus = requestStatus;
        this.reported = reported;
        InvidualObjectName = invidualObjectName;
        this.realizationDate = realizationDate;
    }

    public ServiceRequest(String serviceRequestDescription, FarmLocation farmLocation, ServiceManager serviceManager, Date realizationDate) {
        this.serviceRequestDescription = serviceRequestDescription;
        this.farmLocation = farmLocation;
        this.serviceManager = serviceManager;
        this.realizationDate = realizationDate;
    }

    //Pytanie o ten konstruktor
//    public ServiceRequest(String serviceRequestDescription, FarmLocation farmLocation, Serviceman serviceman, ServiceManager serviceManager, String requestStatus, String reported, String invidualObjectName, String additionalRemarks, Date realizationDate) {
//        this.serviceRequestDescription = serviceRequestDescription;
//        this.farmLocation = farmLocation;
//        this.serviceman = serviceman;
//        this.serviceManager = serviceManager;
//        this.requestStatus = requestStatus;
//        this.reported = reported;
//        InvidualObjectName = invidualObjectName;
//        this.additionalRemarks = additionalRemarks;
//        this.realizationDate = realizationDate;
//    }

//    public ServiceRequest(String serviceRequestDescription, FarmLocation farmLocation, Serviceman serviceman, ServiceManager serviceManager) {
//        this.serviceRequestDescription = serviceRequestDescription;
//        this.farmLocation = farmLocation;
//        this.serviceman = serviceman;
//        this.serviceManager = serviceManager;
//    }

//    public ServiceRequest(String serviceRequestDescription, FarmLocation farmLocation) {
//        this.serviceRequestDescription = serviceRequestDescription;
//        this.farmLocation = farmLocation;
//    }
}
