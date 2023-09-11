package p.lodz.it.spjava.e13.ges.rest.managers;

import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;

import java.util.List;
import java.util.UUID;

public interface AdminService extends AbstractService{

    Admin findById(UUID id);


    List<Admin> findAll();



    Admin findByLogin(String login);

    Admin findSelf();

    Admin addAdmin(Admin admin);
}
