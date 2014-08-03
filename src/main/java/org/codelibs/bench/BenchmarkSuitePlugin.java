package org.codelibs.bench;

import java.util.Collection;

import org.codelibs.bench.core.action.AbortBenchmarkAction;
import org.codelibs.bench.core.action.BenchmarkAction;
import org.codelibs.bench.core.action.BenchmarkModule;
import org.codelibs.bench.core.action.BenchmarkStatusAction;
import org.codelibs.bench.core.action.TransportAbortBenchmarkAction;
import org.codelibs.bench.core.action.TransportBenchmarkAction;
import org.codelibs.bench.core.action.TransportBenchmarkStatusAction;
import org.codelibs.bench.core.rest.RestBenchAction;
import org.codelibs.bench.service.BenchmarkInitService;
import org.elasticsearch.action.ActionModule;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.rest.RestModule;

public class BenchmarkSuitePlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "BenchmarkSuitePlugin";
    }

    @Override
    public String description() {
        return "This is a elasticsearch-benchmark plugin.";
    }

    // for Rest API
    public void onModule(final RestModule module) {
        module.addRestAction(RestBenchAction.class);
    }

    public void onModule(final ActionModule module) {
        module.registerAction(BenchmarkAction.INSTANCE,
                TransportBenchmarkAction.class);
        module.registerAction(AbortBenchmarkAction.INSTANCE,
                TransportAbortBenchmarkAction.class);
        module.registerAction(BenchmarkStatusAction.INSTANCE,
                TransportBenchmarkStatusAction.class);
    }

    // for Service
    @Override
    public Collection<Class<? extends Module>> modules() {
        final Collection<Class<? extends Module>> modules = Lists
                .newArrayList();
        modules.add(BenchmarkModule.class);
        return modules;
    }

    // for Service
    @SuppressWarnings("rawtypes")
    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        final Collection<Class<? extends LifecycleComponent>> services = Lists
                .newArrayList();
        services.add(BenchmarkInitService.class);
        return services;
    }
}
