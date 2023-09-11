package p.lodz.it.spjava.e13.ges.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import p.lodz.it.spjava.e13.ges.dto.accounts.*;
import p.lodz.it.spjava.e13.ges.rest.converters.AccountConverter;
import p.lodz.it.spjava.e13.ges.rest.converters.AdminConverter;
import p.lodz.it.spjava.e13.ges.rest.converters.ServicemanConverter;
import p.lodz.it.spjava.e13.ges.rest.managers.AdminService;
import p.lodz.it.spjava.e13.ges.rest.managers.ServicemanService;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Serviceman;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.ServicemanType;
import p.lodz.it.spjava.e13.ges.rest.utils.ValidationMessages;
import p.lodz.it.spjava.e13.ges.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


@Path("/servicemen")
public class ServicemanResource {

    private static Logger logger = Logger.getLogger(ServicemanResource.class.getName());
    private ServicemanService servicemanService;

    private HashGenerator hashGenerator;

    @Inject
    public ServicemanResource(ServicemanService servicemanService, HashGenerator hashGenerator) {
        this.servicemanService = servicemanService;
        this.hashGenerator = hashGenerator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServicemanDto> findAllServicemen() {
        return ServicemanConverter.toDto(servicemanService.findAll());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createServiceman(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
                                     @Valid CreateServicemanCmd createServicemanCmd) {
        createServicemanCmd.setPassword(hashGenerator.generateHash(createServicemanCmd.getPassword()));
        createServicemanCmd.setDepartment(createServicemanCmd.getDepartment());
        createServicemanCmd.setAdditionalWorkingPermissions(createServicemanCmd.getAdditionalWorkingPermissions());
        Serviceman newServiceman = ServicemanConverter.fromCreateServicemanCmd(createServicemanCmd);
        return Response.ok().entity(ServicemanConverter.toDto(servicemanService.addServiceman(newServiceman))).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ServicemanDto findById(@PathParam("id") UUID id) { // Wrong id format results in 404!
        return ServicemanConverter.toDto(servicemanService.findById(id));
    }

    @GET
    @Path("self")
    @Produces(MediaType.APPLICATION_JSON)
    public ServicemanDto findSelf() {
        return ServicemanConverter.toDto(servicemanService.findSelf());
    }

}