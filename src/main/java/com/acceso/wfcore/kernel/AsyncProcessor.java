package com.acceso.wfcore.kernel;

import javax.servlet.AsyncContext;

public abstract class AsyncProcessor implements Runnable {
    AsyncContext asyncContext;
    int secs;

    public AsyncProcessor(AsyncContext asyncCtx, int secs) {
        this.asyncContext = asyncCtx;
        this.secs = secs;
    }
}
