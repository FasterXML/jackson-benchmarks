package com.fasterxml.jackson.perf;

import org.openjdk.jmh.annotations.AuxCounters;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.runner.IterationType;

/**
 * Simple container for additional state: size of input/output.
 * Notes
 *<ul>
 * <li>Type needs to be {@code EVENTS} so as not to be normalized to
 * runtime or iterations.<li>
 * <li>Ugly state-handling was the only way I could figure out how to
 *  avoid either accumulation of size values with {@code EVENTS} or
 *  normalization attempts with {@code OPERATIONS}.
 *  </li>
 *</ul>
 */
@AuxCounters(AuxCounters.Type.EVENTS)
@State(Scope.Thread)
public class AuxStateSize
{
    /**
     * Actual size we will report once (and only once!) during
     * the first run after warmup(s).
     */
    public int size;

    /**
     * Simple state to indicate last iteration type we saw
     */
    private IterationType lastType;

    /**
     * Flag that indicates that we should set size just once
     */
    private boolean shouldSetSize;

    // Choice is actually clear: "Invocation" way too often, but
    // "Trial" not often enough (since it is just once before Warmup).
    @Setup(Level.Iteration)
    public void clearSize(IterationParams iterParams) {
        size = 0;
        final IterationType nextType = iterParams.getType();

        // Transition...
        if (nextType != lastType) {
            if (nextType == IterationType.MEASUREMENT) {
                shouldSetSize = true;
            }
            lastType = nextType;
        }
    }

    public void set(int size) {
        if (shouldSetSize) {
            shouldSetSize = false;
            this.size = size;
        }
    }
}
