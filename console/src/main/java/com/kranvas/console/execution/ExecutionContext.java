package com.kranvas.console.execution;

import com.kranvas.core.Canvas;

/**
 * The environment in which the commands are executed
 */
public class ExecutionContext {
    private Canvas canvas;
    private boolean exitRequested;
    private boolean printCanvasRequested;
    private boolean printUsageRequested;

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    boolean isExitRequested() {
        return exitRequested;
    }

    public void setExitRequested(boolean exitRequested) {
        this.exitRequested = exitRequested;
    }

    boolean isPrintCanvasRequested() {
        return printCanvasRequested;
    }

    public void setPrintCanvasRequested(boolean printCanvasRequested) {
        this.printCanvasRequested = printCanvasRequested;
    }

    boolean isPrintUsageRequested() {
        return printUsageRequested;
    }

    public void setPrintUsageRequested(boolean printUsageRequested) {
        this.printUsageRequested = printUsageRequested;
    }
}
