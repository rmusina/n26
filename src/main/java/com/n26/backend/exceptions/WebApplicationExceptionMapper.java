package com.n26.backend.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    public Response toResponse(WebApplicationException e) {
        return  Response.status(e.getResponse().getStatus())
                .entity(String.format("{ 'error': '%s' }", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
