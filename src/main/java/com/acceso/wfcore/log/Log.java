package com.acceso.wfcore.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Rómulo Galindo
 */
public class Log {

    private static final Logger LOG = LogManager.getLogger(Log.class);

    public static void info(Object log) {
        LOG.info(log);
    }

    public static void error(Object log) {
        LOG.error(log);
    }
}
