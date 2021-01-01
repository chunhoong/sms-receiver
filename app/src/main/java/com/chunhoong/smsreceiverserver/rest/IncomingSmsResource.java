package com.chunhoong.smsreceiverserver.rest;

import com.chunhoong.smsreceiverserver.service.IncomingSmsDto;
import com.chunhoong.smsreceiverserver.service.IncomingSmsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    public IncomingSmsResponse saveIncomingSms(IncomingSmsRequest request) {
        logger.info("Incoming Sms: {}", request);

        IncomingSmsDto incomingSmsDto = new IncomingSmsDto();
        incomingSmsDto.setContent(request.getContent());
        incomingSmsDto.setSender(request.getSender());

        incomingSmsService.saveIncomingSms(incomingSmsDto);

        IncomingSmsResponse response = new IncomingSmsResponse();
        response.setStatus("Successful");

        return response;
    }

}
