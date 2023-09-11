package p.lodz.it.spjava.e13.ges.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import p.lodz.it.spjava.e13.ges.dto.accounts.AccountDto;
import p.lodz.it.spjava.e13.ges.dto.auth.ChangePasswordDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.UpdateAccountCmd;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AccountRestClient extends AbstractRestClient<AccountDto> {

    public AccountRestClient() {
        super(AccountDto.class);
    }

    @Override
    protected WebTarget getTarget() {
        return getBaseTarget().path("accounts");
    }

    public void activate(UUID id) {
        getTarget().path(String.valueOf(id)).path("activate").request()
                .post(Entity.entity(null, MediaType.TEXT_PLAIN_TYPE));
    }

    public void deactivate(UUID id) {
        getTarget().path(String.valueOf(id)).path("deactivate").request()
                .post(Entity.entity(null,MediaType.TEXT_PLAIN_TYPE));
    }

    public void confirmed(UUID id) {
        getTarget().path(String.valueOf(id)).path("confirmed").request()
                .post(Entity.entity(null,MediaType.TEXT_PLAIN_TYPE));
    }

    public void edit(UUID id, UpdateAccountCmd updateAccountCmd) {
        getTarget().path(String.valueOf(id)).request()
                .put(Entity.json(updateAccountCmd));
    }

    public void editSelf(UpdateAccountCmd updateAccountCmd) {
        getTarget().path("self").request()
                .put(Entity.json(updateAccountCmd));
    }

    public void changePassword(UUID id, ChangePasswordDto changePasswordDto) {
        getTarget().path(String.valueOf(id)).path("password").request()
                .put(Entity.json(changePasswordDto));
    }

    public void changeSelfPassword(ChangePasswordDto changePasswordDto) {
        getTarget().path("self").path("password").request()
                .put(Entity.json(changePasswordDto));
    }

    @Override
    public List<AccountDto> findAll() {
        return super.findAll();
    }

    @Override
    public AccountDto find(UUID id) {
        return super.find(id);
    }

    public AccountDto findSelf() {
        return getTarget().path("self").request().get(AccountDto.class);
    }

    @Override
    public void remove(UUID id) {
        super.remove(id);
    }

}
