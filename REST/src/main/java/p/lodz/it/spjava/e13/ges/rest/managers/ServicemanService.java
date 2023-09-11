package p.lodz.it.spjava.e13.ges.rest.managers;

import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Serviceman;

import java.util.List;
import java.util.UUID;

public interface ServicemanService extends AbstractService{

    Serviceman findById(UUID id);


    List<Serviceman> findAll();



    Serviceman findByLogin(String login);

    Serviceman findSelf();

    Serviceman addServiceman(Serviceman serviceman);
}
