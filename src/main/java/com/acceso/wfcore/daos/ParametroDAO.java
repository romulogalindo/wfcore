package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ConparDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfcore.utils.Values;
import org.hibernate.StatelessSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */

public class ParametroDAO {
   StatelessSession session;

   public ParametroDAO() {
      session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
   }

   public List<ConparDTO> getParametros() {

      List<ConparDTO> parametros = new ArrayList<>();
      NQuery nQuery = new NQuery();

      try {

         nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PARAMETRO));

         System.out.println("[ParametroDAO:getParametros] Q = " + nQuery.getQueryString());
         parametros = nQuery.list();
         System.out.println("[ParametroDAO:getParametros] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

      } catch (Exception ep) {
         System.out.println("[ParametroDAO:getParametros] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
         ep.printStackTrace();
      }

      return parametros;
   }

//   public List<ConparDTO> getParametrosByContenedor(Integer contenedor) {
//
//      List<ConparDTO> parametros = new ArrayList<>();
//      NQuery nQuery = new NQuery();
//
//      try {
//
//         nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PARAMETRO_CONTEN));
//         nQuery.setInteger("co_conten", contenedor == null ? -1 : contenedor);
//
//         System.out.println("[ParametroDAO:getParametrosByContenedor] Q = " + nQuery.getQueryString());
//         parametros = nQuery.list();
//         System.out.println("[ParametroDAO:get ParametrosByContenedor] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//
//      } catch (Exception ep) {
//         System.out.println("[ParametroDAO:getParametrosByContenedor] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//         ep.printStackTrace();
//      }
//
//      return parametros;
//   }

   //    public ParametroDTO grabarParametro(ParametroDTO pagina) {
//
//        NQuery nQuery = new NQuery();
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", pagina.getCo_usuari() == null ? -1 : pagina.getCo_usuari());
//            nQuery.setString("co_usulog", pagina.getCo_usulog());
//            nQuery.setString("no_usuari", pagina.getNo_usuari());
//            nQuery.setString("pw_usuari", pagina.getPw_usuari());
//            nQuery.setString("ti_usuari", pagina.getTi_usuari());
//            nQuery.setInteger("co_person", pagina.getCo_person() == null ? -1 : pagina.getCo_person());
//            nQuery.setInteger("co_sistem", pagina.getCo_sistem());
//            nQuery.setInteger("co_subsis", pagina.getCo_subsis());
//
//
//            System.out.println("[ParametroDAO:grabarParametro] Q = " + nQuery.getQueryString());
//
//            pagina = (ParametroDTO) nQuery.list().get(0);
//
//            System.out.println("[ParametroDAO:grabarParametro] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//        } catch (Exception ep) {
//            System.out.println("[ParametroDAO:grabarParametro] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return pagina;
//    }
//
//    public String deleteParametro(ParametroDTO pagina) {
//
//        NQuery nQuery = new NQuery();
//
//        String resultado = null;
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", pagina.getCo_usuari());
//
//            System.out.println("[ParametroDAO:deleteParametro] Q = " + nQuery.getQueryString());
//
//            resultado = nQuery.list().toString();
//
//            System.out.println("[ParametroDAO:deleteParametro] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//            System.out.println("[ParametroDAO:deleteParametro] Q = " + nQuery.getQueryString() + " R = " + resultado);
//
//        } catch (Exception ep) {
//            System.out.println("[ParametroDAO:deleteParametro] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//        return resultado;
//    }
//
   public void close() {
      try {
         session.close();
      } catch (Exception ep) {
         session = null;
      }
   }

}
