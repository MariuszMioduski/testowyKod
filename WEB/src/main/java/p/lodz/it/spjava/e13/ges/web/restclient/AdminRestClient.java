package p.lodz.it.spjava.e13.ges.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import p.lodz.it.spjava.e13.ges.dto.accounts.AccountDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.AdminDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAccountCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAdminCmd;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AdminRestClient extends AbstractRestClient<AdminDto> {

    public AdminRestClient() {
        super(AdminDto.class);
    }

    @Override
    protected WebTarget getTarget() {
        return super.getBaseTarget().path("admins");
    }


    @Override
    public List<AdminDto> findAll() {
        return super.findAll();
    }

    @Override
    public AdminDto find(UUID id) {
        return super.find(id);
    }

    public void createAdmin(CreateAdminCmd newAdmin) { // Because we do NOT use AccountDTO for creating
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newAdmin));
    }
}