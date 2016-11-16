package com.n26.backend.exceptions;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class GenericExceptionMapper implements ExceptionMapper<Exception>{

    public Response toResponse(Exception e) {
             return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{ 'error': Whops! Something went wrong. }")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
    }
}
