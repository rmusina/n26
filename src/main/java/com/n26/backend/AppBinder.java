package com.n26.backend;


import com.n26.backend.configuration.AppConfig;
import com.n26.backend.statistics.InMemoryStatisticsRepository;
import com.n26.backend.statistics.StatisticsRepository;
import com.n26.backend.time.CurrentTimeIntervalProvider;
import com.n26.backend.time.TimeIntervalProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


class AppBinder extends AbstractBinder {

    private final AppConfig config;

    AppBinder(AppConfig config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        TimeIntervalProvider timeIntervalProvider = new CurrentTimeIntervalProvider(config.intervalSizeSeconds);

        bind(timeIntervalProvider).to(TimeIntervalProvider.class);
        bind(new InMemoryStatisticsRepository(timeIntervalProvider)).to(StatisticsRepository.class);
    }
}
