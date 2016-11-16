package com.n26.backend.controllers;


import com.n26.backend.metrics.MetricsProvider;
import com.n26.backend.model.TransactionRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TransactionStatisticsController {

    private final MetricsProvider metricsProvider;

    @Inject
    public TransactionStatisticsController(MetricsProvider metricsProvider) {
        this.metricsProvider = metricsProvider;
    }

    @POST
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTransaction(@Valid final TransactionRequest transactionRequest) throws Exception {
        throw new Exception("caca");
        //return Response.noContent().build();
    }

    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public int getStatistics() {
        return this.metricsProvider.getMetric();
    }
}
