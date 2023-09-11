package p.lodz.it.spjava.e13.ges.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import p.lodz.it.spjava.e13.ges.dto.accounts.AdminDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAdminCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateServicemanCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.ServicemanDto;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ServicemanRestClient extends AbstractRestClient<ServicemanDto> {

    public ServicemanRestClient() {
        super(ServicemanDto.class);
    }

    @Override
    protected WebTarget getTarget() {
        return super.getBaseTarget().path("servicemen");
    }


    @Override
    public List<ServicemanDto> findAll() {
        return super.findAll();
    }

    @Override
    public ServicemanDto find(UUID id) {
        return super.find(id);
    }

    public void createServiceman(CreateServicemanCmd newServiceman) { // Because we do NOT use AccountDTO for creating
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newServiceman));
    }

    public ServicemanDto findSelf() {
        return getTarget().path("self").request().get(ServicemanDto.class);
    }
}