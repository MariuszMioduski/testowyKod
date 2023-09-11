package p.lodz.it.spjava.e13.ges.rest.managers;


import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.security.enterprise.SecurityContext;
import p.lodz.it.spjava.e13.ges.rest.exceptions.AppBaseException;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.repositories.AdminFacade;

import java.util.List;
import java.util.UUID;

@Stateful
//@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class AdminManager extends AbstractManager implements AdminService{

    @Inject
    private AdminFacade adminFacade;

    @Inject
    private SecurityContext sctx;


    @Override
    public Admin findById(UUID id) {
        return adminFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Admin> findAll() {
        return adminFacade.findAll();
    }

    @Override
    public Admin findByLogin(String login) {
        return adminFacade.findByLogin(login).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public Admin findSelf() {
        return null;
    }

    @Override
    public Admin addAdmin(Admin admin) {
        adminFacade.create(admin);
        return admin;
    }






}
