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
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
@LocalBean
//@Interceptors({AccountFacadesExceptionInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AccountFacade extends AbstractEMFacade<Account> {

    @PersistenceContext
    private EntityManager em;

    public AccountFacade() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(Account entity) { super.edit(entity); }

    @Override
    public void remove(Account entity) { super.remove(entity); }

    @Override
    public Optional<Account> find(UUID id) {
        return super.find(id);
    }

    @Override
    public List<Account> findAll() {
        return super.findAll();
    }

    public Optional<Account> findByLogin(String login) {
        TypedQuery<Account> tq = em.createNamedQuery("Account.findByLogin", Account.class);
        tq.setParameter("login", login);
        try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

}
