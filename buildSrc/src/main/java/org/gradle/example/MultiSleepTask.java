package org.gradle.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.workers.IsolationMode;
import org.gradle.workers.WorkerExecutor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple task that runs its own actions in parallel
 */
public class MultiSleepTask extends DefaultTask {

    @Input
    protected List<Integer> sleepers = new ArrayList<>();

    public void sleep(Integer ...seconds) {
        sleepers.addAll(Arrays.asList(seconds));
    }

    @Inject
    protected WorkerExecutor getWorkerExecutor() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void goToSleep() {
        sleepers.forEach(secondsToSleep -> getWorkerExecutor().submit(Sleeper.class, config -> {
            //TODO: Why doesn't IDEA recognize Isolation mode?
            config.setIsolationMode(IsolationMode.NONE);
            config.setParams(secondsToSleep);
        }));
    }
}
