package p.lodz.it.spjava.e13.ges.web.restclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import p.lodz.it.spjava.e13.ges.dto.AbstractDto;
import p.lodz.it.spjava.e13.ges.web.restclient.filters.ApplicationClientResponseFilter;
import p.lodz.it.spjava.e13.ges.web.restclient.filters.AuthenticationClientRequestFilter;

import java.util.List;
import java.util.UUID;

public abstract class AbstractRestClient <T extends AbstractDto> {

    private Class<T> entityClass;

    public AbstractRestClient(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private final Client restClient = ClientBuilder.newClient()
            .register(ApplicationClientResponseFilter.class)
            .register(AuthenticationClientRequestFilter.class); //Maybe we shall register some filters or other config here?

    private final WebTarget baseTarget = restClient.target("http://localhost:8080/REST-0.1-SNAPSHOT/api/");

    protected WebTarget getBaseTarget() {
        return baseTarget;
    }

    protected abstract WebTarget getTarget();

    // These methods are protected, because there's no "standard set" for every entity type
    // Should you need that method for your type, override and expose method in subclass

    protected void create(T entity) {
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(entity));
    }

    protected List<T> findAll() {
        return getTarget().request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<T>>() {});
    }

    protected T find(UUID id) {
        return getTarget().path(String.valueOf(id)).request(MediaType.APPLICATION_JSON)
                .get(entityClass);
    }

    protected void remove(UUID id) {
        getTarget().path(String.valueOf(id)).request()
                .delete();
    }


}

