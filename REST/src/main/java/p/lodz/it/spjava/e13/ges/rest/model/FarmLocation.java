package p.lodz.it.spjava.e13.ges.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "farm_locations")
@NamedQueries({
        @NamedQuery(name="FarmLocation.findByCity", query = "SELECT f FROM FarmLocation f WHERE f.cityName= :cityName")
})
public class FarmLocation extends AbstractEntity implements Serializable {

@OneToMany(mappedBy = "farmLocation")
private List<ServiceRequest> serviceRequestList = new ArrayList<>();

    @Column(nullable = false, name = "city_name")
    @Getter @Setter
    private String cityName;

    @Column(nullable = false, name = "postal_code")
    @Getter @Setter
    private String postalCode;

    public FarmLocation() {
        super();
    }

    public FarmLocation(UUID id, String cityName, String postalCode) {
        super(id);
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    public FarmLocation(String cityName, String postalCode) {
        this.cityName = cityName;
        this.postalCode = postalCode;
    }
}
