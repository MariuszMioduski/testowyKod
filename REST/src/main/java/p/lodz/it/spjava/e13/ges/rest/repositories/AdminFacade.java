package p.lodz.it.spjava.e13.ges.rest.repositories;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
@LocalBean
//@Interceptors({GeneralExceptionInterceptor.class, AccountFacadesExceptionInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AdminFacade extends AbstractEMFacade<Admin> {

    @PersistenceContext
    private EntityManager em;

    public AdminFacade() {
        super(Admin.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Admin entity) { super.create(entity); }

    @Override
    public Optional<Admin> find(UUID id) {
        return super.find(id);
    }

    @Override
    public List<Admin> findAll() {
        return super.findAll();
    }

    public Optional<Admin> findByLogin(String login) {
        TypedQuery<Admin> tq = em.createNamedQuery("Admin.findByLogin", Admin.class);
        tq.setParameter("login", login);
        try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }
}
