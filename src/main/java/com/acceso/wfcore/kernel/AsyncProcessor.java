package com.acceso.wfcore.kernel;

import javax.servlet.AsyncContext;

public abstract class AsyncProcessor implements Runnable {

    AsyncContext asyncContext;
    int secs;
    int type;

    public AsyncProcessor(AsyncContext asyncCtx, int secs, int type) {
        this.asyncContext = asyncCtx;
        this.secs = secs;
        this.type = type;
    }

}
