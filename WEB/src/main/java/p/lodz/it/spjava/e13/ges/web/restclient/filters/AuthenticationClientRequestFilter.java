package p.lodz.it.spjava.e13.ges.web.restclient.filters;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import p.lodz.it.spjava.e13.ges.web.security.RestSecurityContext;

import java.io.IOException;

public class AuthenticationClientRequestFilter implements ClientRequestFilter {

    @Inject
    private RestSecurityContext securityContext;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (securityContext.isAuthenticated()) {
            requestContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION, "Bearer " + securityContext.getJwt());
        }
    }

}

