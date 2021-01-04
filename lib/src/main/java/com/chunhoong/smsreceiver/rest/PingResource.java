package com.chunhoong.smsreceiver.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

    @GET
    public Response ping() {
        PingResponse responseBody = new PingResponse();
        responseBody.setMessage("Sms receiver is up and running");
        return Response.status(Response.Status.OK).entity(responseBody).build();
    }

}
