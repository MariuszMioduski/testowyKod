package p.lodz.it.spjava.e13.ges.rest.repositories;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import p.lodz.it.spjava.e13.ges.dto.accounts.ServicemanDto;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Serviceman;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
@LocalBean
//@Interceptors({GeneralExceptionInterceptor.class, AccountFacadesExceptionInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ServicemanFacade extends AbstractEMFacade<Serviceman> {

    @PersistenceContext
    private EntityManager em;

    public ServicemanFacade() {
        super(Serviceman.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Serviceman entity) { super.create(entity); }

    public void remove(Serviceman serviceman) {
        super.remove(serviceman);
    }

    public void edit(Serviceman serviceman) { super.edit(serviceman); }

    @Override
    public Optional<Serviceman> find(UUID id) {
        return super.find(id);
    }

    public Optional<Serviceman> findAndRefresh(UUID id) {
        return super.findAndRefresh(id);
    }

    @Override
    public List<Serviceman> findAll() {
        return super.findAll();
    }

    @Override
    public void forceVersionIncrement(Serviceman entity) {
        super.forceVersionIncrement(entity);
    }

    public Optional<Serviceman> findByLogin(String login) {
        TypedQuery<Serviceman> tq = em.createNamedQuery("Serviceman.findByLogin", Serviceman.class);
        tq.setParameter("login", login);
        try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }
}
