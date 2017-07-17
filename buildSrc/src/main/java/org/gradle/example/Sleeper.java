package org.gradle.example;

import org.gradle.internal.impldep.com.google.common.base.Throwables;

import javax.inject.Inject;

/**
 * Very simple class that sleeps for n seconds, logging each second.
 */
public class Sleeper implements Runnable {

    private final int secondsToSleep;

    @Inject
    public Sleeper(int secondsToSleep) {
        this.secondsToSleep = secondsToSleep;
    }

    @Override
    public void run() {
        System.out.println("Sleeping for " + (secondsToSleep) + " second(s).");
        try {
            Thread.sleep(secondsToSleep * 1000);
        } catch (InterruptedException e) {
            Throwables.propagate(e);
        }
    }
}
