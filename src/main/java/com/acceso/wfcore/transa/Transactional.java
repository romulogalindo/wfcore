package com.acceso.wfcore.transa;

import com.acceso.wfcore.dtos.TransaDTO;
import com.acceso.wfcore.log.Log;
import java.util.List;

/**
 *
 * @author Rómulo Galindo
 */
public class Transactional {

    static final H2DB h2db;

    static {
//        Log.info("[h2]==>PRE-START");
        h2db = new H2DB();
//        Log.info("[h2]==>-START");
//        Log.info("[h2]==>PRE-CREATE");
//        h2db.create();
//        Log.info("[h2]==>CREATED");
    }

    public static Long insert(int type, long usuari, String query) {
//        Log.info("[h2]==>INSERT");
        return h2db.newTransa(type, usuari, query);
    }

    public static void update(long id) {
//        Log.info("[h2]==>UPDATE");
        h2db.updateTransa(id);
    }

    public static List<TransaDTO> get() {
//        Log.info("[h2]==>GETALL");
        return h2db.getAll();
    }
}
