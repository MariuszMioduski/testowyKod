package p.lodz.it.spjava.e13.ges.rest.managers;


import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import p.lodz.it.spjava.e13.ges.rest.exceptions.AppBaseException;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Serviceman;
import p.lodz.it.spjava.e13.ges.rest.repositories.AdminFacade;
import p.lodz.it.spjava.e13.ges.rest.repositories.ServicemanFacade;

import java.util.List;
import java.util.UUID;

@Stateful
//@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class ServicemanManager extends AbstractManager implements ServicemanService{

    @Inject
    private ServicemanFacade servicemanFacade;

    @Inject
    private SecurityContext sctx;


    @Override
    public Serviceman findById(UUID id) {
        return servicemanFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Serviceman> findAll() {
        return servicemanFacade.findAll();
    }

    @Override
    public Serviceman findByLogin(String login) {
        return servicemanFacade.findByLogin(login).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public Serviceman findSelf() {
        return findByLogin(sctx.getCallerPrincipal().getName());
    }

    @Override
    public Serviceman addServiceman(Serviceman serviceman) {
        servicemanFacade.create(serviceman);
        return serviceman;
    }






}
