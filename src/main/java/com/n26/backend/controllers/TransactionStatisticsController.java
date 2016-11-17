package com.n26.backend.controllers;


import com.n26.backend.metrics.MetricSet;
import com.n26.backend.metrics.MetricsRepository;
import com.n26.backend.model.StatisticsResponse;
import com.n26.backend.model.TransactionRequest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TransactionStatisticsController {

    private final MetricsRepository metricsRepository;

    @Inject
    public TransactionStatisticsController(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    @POST
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTransaction(@Valid final TransactionRequest transactionRequest) {
        metricsRepository.registerMetric(transactionRequest.amount, transactionRequest.timestamp);
        return Response.noContent().build();
    }

    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics() {
        MetricSet metric = this.metricsRepository.getStatisticsForInterval();
        StatisticsResponse response = new StatisticsResponse(metric);

        return Response.ok().entity(response).build();
    }
}
