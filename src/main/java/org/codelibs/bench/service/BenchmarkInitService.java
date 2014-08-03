package org.codelibs.bench.service;

import org.codelibs.bench.core.action.BenchmarkService;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;

public class BenchmarkInitService extends
        AbstractLifecycleComponent<BenchmarkInitService> {

    private Client client;

    private BenchmarkService benchmarkService;

    @Inject
    public BenchmarkInitService(final Settings settings, Client client,
            BenchmarkService benchmarkService) {
        super(settings);
        this.client = client;
        this.benchmarkService = benchmarkService;
    }

    @Override
    protected void doStart() throws ElasticsearchException {
        logger.info("Initializing BenchmarkInitService");
        benchmarkService.initBenchmarkExecutor(client);
    }

    @Override
    protected void doStop() throws ElasticsearchException {
        logger.info("Stopping BenchmarkInitService");
    }

    @Override
    protected void doClose() throws ElasticsearchException {
        logger.info("Closing BenchmarkInitService");
    }

}
