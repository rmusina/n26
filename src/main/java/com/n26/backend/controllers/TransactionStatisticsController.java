package com.n26.backend.controllers;


import com.n26.backend.statistics.Statistics;
import com.n26.backend.statistics.StatisticsRepository;
import com.n26.backend.model.StatisticsResponse;
import com.n26.backend.model.TransactionRequest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TransactionStatisticsController {

    private final StatisticsRepository statisticsRepository;

    @Inject
    public TransactionStatisticsController(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @POST
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTransaction(@Valid final TransactionRequest transactionRequest) {
        statisticsRepository.registerStatistic(transactionRequest.amount, transactionRequest.timestamp);
        return Response.noContent().build();
    }

    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics() {
        Statistics statistics = this.statisticsRepository.getStatisticsForInterval();
        StatisticsResponse response = new StatisticsResponse(statistics);

        return Response.ok().entity(response).build();
    }
}
