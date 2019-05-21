package pl.ejaksla.onlinejudge.domain.services.impl;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class RunJUnit5TestsFromJava {
    SummaryGeneratingListener listener = new SummaryGeneratingListener();
    private final String testCode;

    RunJUnit5TestsFromJava(final String testCode) {
        this.testCode = testCode;
    }

    void runOne() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(testCode))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
