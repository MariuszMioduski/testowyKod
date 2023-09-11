package p.lodz.it.spjava.e13.ges.rest.managers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.security.enterprise.SecurityContext;
import p.lodz.it.spjava.e13.ges.rest.exceptions.AccountException;
import p.lodz.it.spjava.e13.ges.rest.exceptions.AppBaseException;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Account;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.repositories.AccountFacade;
import p.lodz.it.spjava.e13.ges.rest.repositories.AdminFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateful
@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
//@Interceptors({LoggingInterceptor.class, PerformanceInterceptor.class})
public class AccountManager extends AbstractManager implements AccountService {
    //
    @Inject
    private AccountFacade accountFacade;

    @Inject
    private AdminFacade adminFacade;



    @Inject
    private SecurityContext sctx;

    @Override
  // @RolesAllowed("ADMIN")
    public void editAccountById(UUID id, long originalVersion, Account accountModifications) {
        Account modifiedAccount = findAccountById(id);
        editAccount(originalVersion, accountModifications, modifiedAccount);
    }

    @Override
   // @RolesAllowed({"ADMIN","EMPLOYEE","CLIENT"}) // everyone logged in is permitted
    public void editSelfAccount(long originalVersion, Account accountModifications) {
        Account modifiedAccount = findAccountSelf();
        editAccount(originalVersion, accountModifications, modifiedAccount);
    }

    private void editAccount(long originalVersion, Account accountModifications, Account modifiedAccount) {
        if(originalVersion != modifiedAccount.getVersion())
            throw AppBaseException.createForOptimisticLock();

        if (null != accountModifications.getEmail()) {
            modifiedAccount.setEmail(accountModifications.getEmail());
        }

        if (null != accountModifications.getFirstName()) {
            modifiedAccount.setFirstName(accountModifications.getFirstName());
        }
        if (null != accountModifications.getLastName()) {
            modifiedAccount.setLastName(accountModifications.getLastName());
        }

        if(null != accountModifications.getPhoneNumber()){
            modifiedAccount.setPhoneNumber(accountModifications.getPhoneNumber());
        }
        // It's not required for the changes to be committed,
        // we just want to call flush() to be able to catch exceptions
        accountFacade.edit(modifiedAccount);
    }




    @Override
    public void deleteAccount(UUID id) {
        Optional<Account> foundAccount = accountFacade.find(id);
        // maybe some logic here?
        // However, if foundAccount is actually a Client and has any Rent,
        // the foreign key constraint should cause an exception
        foundAccount.ifPresent(account -> accountFacade.remove(account));
    }

    @Override
    public void activateAccount(UUID id) {
        setAccountActive(id, true);
    }

    @Override
    public void deactivateAccount(UUID id) {
        setAccountActive(id, false);
    }

    public void confirmedAccount(UUID id) {
        setAccountConfirmed(id, true);
    }

    private void setAccountActive(UUID id, boolean active) {
        Account modifiedAccount = findAccountById(id);
        modifiedAccount.setActive(active);
        // It's not required for the changes to be committed,
        // we just want to call flush() to be able to catch exceptions
        accountFacade.edit(modifiedAccount);
    }

    private void setAccountConfirmed(UUID id, boolean confirmed) {
        Account modifiedAccount = findAccountById(id);
        modifiedAccount.setConfirmed(confirmed);
        // It's not required for the changes to be committed,
        // we just want to call flush() to be able to catch exceptions
        accountFacade.edit(modifiedAccount);
    }



    @Override
    public void changeAccountPassword(UUID id, String hashedPassword) {
        Account modifiedAccount = findAccountById(id);
        modifiedAccount.setPassword(hashedPassword);
        accountFacade.edit(modifiedAccount);
    }

    @Override
  //  @RolesAllowed({"ADMIN","EMPLOYEE","CLIENT"}) // everyone logged in is permitted
    public void changeSelfPassword(String hashedOldPassword, String hashedNewPassword) {
        Account modifiedAccount = findAccountSelf();
        if(!modifiedAccount.getPassword().equals(hashedOldPassword))
            throw AccountException.createForOldPasswordMismatch();
        modifiedAccount.setPassword(hashedNewPassword);
        accountFacade.edit(modifiedAccount);
    }


    @Override
    public Admin addAdmin(Admin admin) {
        adminFacade.create(admin);
        return admin;
    }

    @Override
    public Account findAccountById(UUID id) {
        return accountFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }
    @Override
    public Account findAccountByLogin(String login) {
        return accountFacade.findByLogin(login).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    //@RolesAllowed({"ADMIN","EMPLOYEE","CLIENT"}) // everyone logged in is permitted
    public Account findAccountSelf() {
        // CallerPrincipal should be not null if @RolesAllowed is passed through
        return findAccountByLogin(sctx.getCallerPrincipal().getName());
    }
    @Override
    public Admin findAdminById(UUID id) {
        Optional<Admin> foundAccount = adminFacade.find(id);
        return foundAccount.orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountFacade.findAll();
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminFacade.findAll();
    }


}

