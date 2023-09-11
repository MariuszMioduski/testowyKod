package p.lodz.it.spjava.e13.ges.dto.farmlocations;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

public class CreateFarmLocationCmd extends FarmLocationDto{

    @Getter @Setter
    private String cityName;

    @Getter @Setter
    private String postalCode;


    public CreateFarmLocationCmd() {
        super();
    }
}
