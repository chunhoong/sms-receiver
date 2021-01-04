package com.chunhoong.smsreceiver.rest;

import com.chunhoong.smsreceiver.service.IncomingSmsDto;
import com.chunhoong.smsreceiver.service.IncomingSmsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/incomingSms")
@Produces(MediaType.APPLICATION_JSON)
public class IncomingSmsResource {

    private static final Logger logger = LoggerFactory.getLogger(IncomingSmsResource.class);

    @Inject
    private IncomingSmsService incomingSmsService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveIncomingSms(IncomingSmsRequest request) {
        logger.info("Incoming Sms: {}", request);

        IncomingSmsDto incomingSmsDto = new IncomingSmsDto();
        incomingSmsDto.setContent(request.getContent());
        incomingSmsDto.setSender(request.getSender());

        incomingSmsService.saveIncomingSms(incomingSmsDto);

        return Response.status(Response.Status.CREATED).build();
    }

}
