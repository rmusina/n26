package com.n26.backend;


import com.n26.backend.statistics.InMemoryStatisticsRepository;
import com.n26.backend.statistics.StatisticsRepository;
import com.n26.backend.time.CurrentTimeIntervalProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new InMemoryStatisticsRepository(new CurrentTimeIntervalProvider(60))).to(StatisticsRepository.class);
    }
}
