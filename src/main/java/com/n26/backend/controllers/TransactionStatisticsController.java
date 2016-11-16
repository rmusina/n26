package com.n26.backend.controllers;


import com.n26.backend.metrics.MetricsProvider;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Response postTransaction() {
        return Response.noContent().build();
    }

    @GET
    @Path("/statistics")
    public int getStatistics() {
        return this.metricsProvider.getMetric();
    }
}
