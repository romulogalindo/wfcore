package com.acceso.wfcore.daos;

import org.hibernate.StatelessSession;

public abstract class DAO {

    public StatelessSession session;

    public void close() {
        try {
            if (session != null)
                session.close();
        } catch (Exception ep) {
            try {
                session = null;
            } catch (Exception ep2) {
                System.out.println("[" + this.getClass() + "] session closed!");
            }

            System.out.println("[" + this.getClass() + "] session closed!");
            ep.printStackTrace();
        }
    }

    ;
}
