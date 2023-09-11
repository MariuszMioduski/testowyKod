package p.lodz.it.spjava.e13.ges.rest.managers;

import p.lodz.it.spjava.e13.ges.rest.model.accounts.Account;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;

import java.util.List;
import java.util.UUID;

public interface AccountService extends AbstractService {


    void editAccountById(UUID id, long originalVersion, Account accountModifications);

    void editSelfAccount(long originalVersion, Account accountModifications);

    void deleteAccount(UUID id);

    void activateAccount(UUID id);

    void confirmedAccount(UUID id);

    void deactivateAccount(UUID id);

    void changeAccountPassword(UUID id, String hashedPassword);

    void changeSelfPassword(String hashedOldPassword, String hashedNewPassword);

    Admin addAdmin(Admin admin);



    Account findAccountById(UUID id);

    Account findAccountByLogin(String login);

    Account findAccountSelf();

    Admin findAdminById(UUID id);


    List<Account> findAllAccounts();

    List<Admin> findAllAdmins();


}