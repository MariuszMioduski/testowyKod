package p.lodz.it.spjava.e13.ges.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import p.lodz.it.spjava.e13.ges.dto.accounts.AccountDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.UpdateAccountCmd;
import p.lodz.it.spjava.e13.ges.dto.auth.ChangePasswordDto;
import p.lodz.it.spjava.e13.ges.rest.converters.AccountConverter;
import p.lodz.it.spjava.e13.ges.rest.managers.AccountService;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Account;
import p.lodz.it.spjava.e13.ges.rest.utils.ValidationMessages;
import p.lodz.it.spjava.e13.ges.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/accounts")
public class AccountResource {

    private static Logger logger = Logger.getLogger(AccountResource.class.getName());
    private AccountService accountService;
    private HashGenerator hashGenerator;

    @Inject
    public AccountResource(AccountService accountService, HashGenerator hashGenerator) {
        this.accountService = accountService;
        this.hashGenerator = hashGenerator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDto> getAllAccounts() {
        return AccountConverter.toDto(accountService.findAllAccounts());
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAccount(@PathParam("id") UUID id,
                              @NotNull(message = ValidationMessages.ARGUMENT_NULL)
                              @Valid UpdateAccountCmd updateAccountCmd) {
        Account AccountModifications = AccountConverter.fromUpdateAccountCmd(updateAccountCmd);
        accountService.editAccountById(id, updateAccountCmd.getOriginalVersion(), AccountModifications);
    }
    @PUT
    @Path("self")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateSelfAccount(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
                                  @Valid UpdateAccountCmd updateAccountCmd) {
        Account AccountModifications = AccountConverter.fromUpdateAccountCmd(updateAccountCmd);
        accountService.editSelfAccount(updateAccountCmd.getOriginalVersion(), AccountModifications);
    }

    @DELETE
    @Path("{id}")
    public void deleteAccount(@PathParam("id") UUID id) {
        accountService.deleteAccount(id);
    }

    @POST
    @Path("{id}/activate")
    public void activateAccount(@PathParam("id") UUID id) {
        accountService.activateAccount(id);
    }
    @POST
    @Path("{id}/deactivate")
    public void deactivateAccount(@PathParam("id") UUID id) {
        accountService.deactivateAccount(id);
    }

    @POST
    @Path("{id}/confirmed")
    public void confirmedAccount(@PathParam("id") UUID id) {
        accountService.confirmedAccount(id);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto findById(@PathParam("id") UUID id) { // Wrong id format results in 404!
        return AccountConverter.toDto(accountService.findAccountById(id));
    }
    @GET
    @Path("self")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto findSelf() {
        return AccountConverter.toDto(accountService.findAccountSelf());
    }

    @PUT
    @Path("{id}/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changePassword(@PathParam("id") UUID id,
                               @NotNull(message = ValidationMessages.ARGUMENT_NULL)
                               @Valid ChangePasswordDto changePasswordDto) {
        accountService.changeAccountPassword(id, hashGenerator.generateHash(changePasswordDto.getNewPassword()));
    }

    @PUT
    @Path("self/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeSelfPassword(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
                                   @Valid ChangePasswordDto changePasswordDto) {
        accountService.changeSelfPassword(hashGenerator.generateHash(changePasswordDto.getOldPassword()),
                hashGenerator.generateHash(changePasswordDto.getNewPassword()));
    }

}