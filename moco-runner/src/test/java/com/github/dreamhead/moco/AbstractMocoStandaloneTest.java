package com.github.dreamhead.moco;

import com.github.dreamhead.moco.bootstrap.StartArgs;
import com.github.dreamhead.moco.helper.MocoTestHelper;
import com.github.dreamhead.moco.runner.JsonRunner;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import org.junit.After;

import java.io.IOException;
import java.io.InputStream;

import static com.github.dreamhead.moco.RemoteTestUtils.port;
import static com.github.dreamhead.moco.runner.JsonRunner.newJsonRunnerWithStreams;

public class AbstractMocoStandaloneTest {
    protected final MocoTestHelper helper = new MocoTestHelper();
    private JsonRunner runner;

    @After
    public void teardown() {
        if (runner != null) {
            runner.stop();
        }
    }

    protected void runWithConfiguration(String... resourceNames) {
        try {
            runner = newRunner(resourceNames);
            runner.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonRunner newRunner(String[] resourceNames) throws IOException {
        ImmutableList.Builder<InputStream> builder = ImmutableList.builder();
        for (String resourceName : resourceNames) {
            builder.add(Resources.getResource(resourceName).openStream());
        }
        return newJsonRunnerWithStreams(builder.build(), new StartArgs(port(), null, null, null, null, null));
    }
}
