package com.n26.backend;


import com.n26.backend.metrics.InMemoryMetricsProvider;
import com.n26.backend.metrics.MetricsProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new InMemoryMetricsProvider()).to(MetricsProvider.class);
    }
}
