package p.lodz.it.spjava.e13.ges.dto.farmlocations;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.dto.AbstractDto;
import p.lodz.it.spjava.e13.ges.dto.ValidationMessages;

import java.util.Date;
import java.util.UUID;

public class FarmLocationDto extends AbstractDto {

    @NotNull
    @Getter @Setter
    @Size(min=4, max=50, message = ValidationMessages.FarmLocation.CITY_NAME_LENGTH)
//    @Pattern(regexp = "[A-Za-z]+", message = ValidationMessages.FarmLocation.CITY_NAME_FORMAT)
    private String cityName;

    @NotNull
    @Getter @Setter
    @Size(min=5, max=6, message = ValidationMessages.FarmLocation.POSTAL_CODE_LENGTH )
    private String postalCode;

    @JsonbCreator
    public FarmLocationDto(@JsonbProperty("id") UUID id,
                           @JsonbProperty("version") long version,
                           @JsonbProperty("creationDate") Date creationDate,
                           @JsonbProperty("lastModificationDate") Date lastModificationDate,
                           @JsonbProperty("cityName") String cityName,
                           @JsonbProperty("postalCode") String postalCode){
        super(id, version, creationDate, lastModificationDate);
        this.cityName=cityName;
        this.postalCode=postalCode;
    }



    public FarmLocationDto(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "FarmLocationDto{" +
                "cityName='" + cityName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                "} ";
    }
}
