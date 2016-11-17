package com.n26.backend;


import com.n26.backend.metrics.InMemoryMetricsRepository;
import com.n26.backend.metrics.MetricsRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new InMemoryMetricsRepository()).to(MetricsRepository.class);
    }
}
