package org.gradle.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.workers.WorkerExecutor;

import javax.inject.Inject;

/**
 * Simple task that can be run in parallel with itself (and any other task that submits its work)
 */
public class SleepTask extends DefaultTask {
    @Input
    protected int secondsToSleep;

    @Inject
    protected WorkerExecutor getWorkerExecutor() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void sleep() {
        getWorkerExecutor().submit(Sleeper.class, config -> {
            config.setParams(secondsToSleep);
        });
    }
}
