package com.acceso.wfcore.transa;

import com.acceso.wfcore.dtos.TransaDTO;
import com.acceso.wfcore.log.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rómulo Galindo
 */
public class H2DB {

    protected Connection con;

    public H2DB() {
        try {
            Class.forName("org.h2.Driver");
            con = java.sql.DriverManager.
                    getConnection("jdbc:h2:mem:testdb", "sa", "");

            String sql = "create table tbtransa"
                    + "("
                    + "	id bigint auto_increment,"
                    + "	type int,"
                    + "	usuari bigint,"
                    + "	query varchar,"
                    + "	start timestamp default current_timestamp(),"
                    + "	finish timestamp,"
                    + "	caltime long,"
                    + "	constraint tbtransa_pk"
                    + "		primary key (id)"
                    + ");";
            Statement pst = con.createStatement();
            int rpta = pst.executeUpdate(sql);
//            Log.info("rpta:" + rpta);
            pst = con.createStatement();
//            ResultSet rpta2 = pst.executeQuery("select * from tbtransa");
//            while (rpta2.next()) {
//                String queryx = rpta2.getString("query");
//            }
//            pst.execute();
//            } catch (Exception ep) {
//                ep.printStackTrace();
//            }
        } catch (Exception ep) {
            con = null;
            ep.printStackTrace();
        }
    }

    public long newTransa(int type, long usuari, String query) {
        long id = -1;
        try {
            String sql = "insert into tbtransa(type, usuari, query) values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, type);
            pst.setLong(2, usuari);
            pst.setString(3, query);
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return id;
    }

    public void updateTransa(long id) {
        try {
            String sql = "update tbtransa set finish=current_time where id=" + id + ";";
            PreparedStatement pst = con.prepareStatement(sql);
            int rpta = pst.executeUpdate();
//            System.out.println("/updateTransa/rpta = " + rpta);
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public List getAll() {
        List all = new ArrayList();
        try {

            Statement pst = con.createStatement();
            ResultSet rpta = pst.executeQuery("select * from tbtransa order by 1 desc");
            while (rpta.next()) {
                TransaDTO transadto = new TransaDTO();
                transadto.setId(rpta.getLong(1));
                transadto.setType(rpta.getInt(2));
                transadto.setUsuari(rpta.getLong(3));
                transadto.setQuery(rpta.getString(4));
                transadto.setStart(rpta.getDate(5));
                transadto.setFinish(rpta.getDate(6));
                transadto.setCaltime(rpta.getLong(7));
                all.add(transadto);
            }
        } catch (Exception ep) {
            con = null;
            ep.printStackTrace();
        }
        return all;
    }
}
