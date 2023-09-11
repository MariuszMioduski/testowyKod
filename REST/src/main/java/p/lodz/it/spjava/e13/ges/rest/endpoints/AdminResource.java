package p.lodz.it.spjava.e13.ges.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import p.lodz.it.spjava.e13.ges.dto.accounts.AccountDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.AdminDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAccountCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAdminCmd;
import p.lodz.it.spjava.e13.ges.rest.converters.AccountConverter;
import p.lodz.it.spjava.e13.ges.rest.converters.AdminConverter;
import p.lodz.it.spjava.e13.ges.rest.managers.AccountService;
import p.lodz.it.spjava.e13.ges.rest.managers.AdminService;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.utils.ValidationMessages;
import p.lodz.it.spjava.e13.ges.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


@Path("/admins")
public class AdminResource {

private static Logger logger = Logger.getLogger(AdminResource.class.getName());
private AdminService adminService;

private HashGenerator hashGenerator;

@Inject
public AdminResource(AdminService adminService, HashGenerator hashGenerator) {
        this.adminService = adminService;
        this.hashGenerator = hashGenerator;
        }

@GET
@Produces(MediaType.APPLICATION_JSON)
public List<AdminDto> findAllAdmins(){
        return AdminConverter.toDto(adminService.findAll());
        }

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response createAdmin(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
@Valid CreateAdminCmd createAdminCmd) {
        createAdminCmd.setPassword(hashGenerator.generateHash(createAdminCmd.getPassword()));
        createAdminCmd.setWorkAlarmPhone(createAdminCmd.getWorkAlarmPhone());
        Admin newAdmin = AdminConverter.fromCreateAdminCmd(createAdminCmd);
        return Response.ok().entity(AdminConverter.toDto(adminService.addAdmin(newAdmin))).build();
        }

@GET
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public AccountDto findById(@PathParam("id") UUID id) { // Wrong id format results in 404!
        return AccountConverter.toDto(adminService.findById(id));
        }

        }