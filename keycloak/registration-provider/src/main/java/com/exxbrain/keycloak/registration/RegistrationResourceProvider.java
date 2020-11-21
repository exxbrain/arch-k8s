package com.exxbrain.keycloak.registration;

import org.keycloak.models.*;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.models.utils.RepresentationToModel;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RegistrationResourceProvider implements RealmResourceProvider {
    private final KeycloakSession session;

    public RegistrationResourceProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return this;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserToRegister userToRegister) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userToRegister.getUsername());
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEnabled(true);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userToRegister.getPassword());
        user.getCredentials().add(credentialRepresentation);
        RealmModel realm = session.getContext().getRealm();
        UserModel model = RepresentationToModel.createUser(session, realm, user);
        UserRepresentation representation =
                ModelToRepresentation
                        .toRepresentation(session, session.getContext().getRealm(), model);
        return Response.status(Response.Status.CREATED).entity(representation).build();
    }

    @Override
    public void close() {
    }
}
