package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.daos.SistemaDAO;
import com.acceso.wfcore.dtos.PaginaDTO;
import com.acceso.wfcore.dtos.SistemaDTO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ArchivDTO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@ViewScoped
public class SistemaBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/sistema/paginaSistemas.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/sistema/paginaSistemas.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/sistema/paginaRegSistema.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/sistema/paginaRegSistema.xhtml";

    public static final String BEAN_NAME = "sistemaBean";

    private List<SistemaDTO> sistemas;
    private SistemaDTO sistema;

    private boolean isregEditable;

    public SistemaBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Sistemas";
        this.sistema = new SistemaDTO();
        this.isregEditable = true;
    }

    public List<SelectItem> getComboSistema() {
        List<SelectItem> res = new ArrayList<>();
        selectDto();
        sistemas.stream().filter(s -> !s.getIl_sisfor()).forEach(sis -> {
            SelectItem item = new SelectItem();
            item.setLabel(sis.getNo_sistem());
            item.setDescription(sis.getDe_sistem());
            item.setValue(sis.getCo_sistem());
            res.add(item);
        });
        return res;
    }

    @PostConstruct
    public void loadModule() {
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);

        Log.info("[SistemaBean]->PostConstruct:");
        /*EXP-->TRANSFER*/
        Object obj = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("X64SIS");
        Log.info("[SistemaBean]obj = " + obj);

        if (obj != null) {
            this.sistema = (SistemaDTO) obj;
        }

    }

    @Override
    public String load() {
        System.out.println("load()");
        this.isregEditable = true;
        // LLENAR LOS BOTONES SECUNDARIOS
        //doListener();
        //CARGA INICIAL!!
//        selectDto();

        return URL_LISTA;
    }

    @Override
    public void doListener() {
        //acceder al manager y decirle toma
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedMenuButton(false);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).initBreadCumBar();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar(beanName, URL_LISTA);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(true);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setCurrentBean(this);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setDefaultActionNameButton("NUEVO");

        //ManagerBean -> Open!
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setOpenedModule(this.beanName);

        System.out.println("listener()");
    }

    @Override
    public String defaultAction() {
        // Para el nuevo registro
        this.sistema = new SistemaDTO();
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Registro", URL_EDITAR);
        //varias cosas para editar
        return URL_NEW;
    }

    @Override
    public String newRegist() {
        //Data la causistica el metodo nuevo esta encapsuado en defaultAction
        return null;
    }

    @Override
    public String updateRegist() {
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);

        return URL_EDITAR;
    }

    @Override
    public String deleteRegist() {
        deleteDto();

        return URL_LISTA;
    }

    @Override
    public String saveRegist() {
        saveDto();
        return URL_LISTA;
    }

    @Override
    public void selectDto() {
        SistemaDAO dao = new SistemaDAO();
        this.sistemas = dao.getSistemas();
        dao.close();
    }

    @Override
    public void saveDto() {
        SistemaDAO dao = new SistemaDAO();
        this.sistema = dao.grabarSistema(sistema);
        this.sistemas = dao.getSistemas();
//      Sistema.out.println("ConexionBean actualizarConexion = " + this.sistema);
        dao.close();
    }

    @Override
    public void updateDto() {
        SistemaDAO dao = new SistemaDAO();
        this.sistema = dao.grabarSistema(sistema);
        this.sistemas = dao.getSistemas();
//      Sistema.out.println("ConexionBean actualizarConexion = " + this.sistema);
        dao.close();
    }

    @Override
    public void deleteDto() {
        SistemaDAO dao = new SistemaDAO();
        String resultado = dao.deleteSistema(sistema);
        this.sistemas = dao.getSistemas();
        dao.close();
    }

    public void uploadImage(FileUploadEvent event) {
        try {
            UploadedFile uploadedFile = event.getFile();
            File file = Util.toFile(uploadedFile);
            System.out.println("file = " + file);

            //crear id_image
            ArchivDTO arcadj;

            Frawor4DAO dao = new Frawor4DAO(WFIOAPP.APP.dataSourceService.getManager("wfaio").getNativeSession());
            arcadj = dao.setArchiv(file.getName());
            dao.close();
            //seteo de valor
            sistema.setAr_logsis(arcadj.getCo_archiv());

            String fileName = Util.formatName(file.getName());
            String pre_url = WFIOAPP.APP.getDataSourceService().getValueOfKey("AIO_DATA_FILE");

            File archivo = new File(pre_url + File.separator + Util.formatDate1(arcadj.getFe_archiv()));
            System.out.println("archivo(1) = " + archivo);

            try {
                System.out.println("archivo(2) = " + archivo.exists());

                if (!archivo.exists()) {
                    archivo.mkdirs();
                }

                archivo = new File(pre_url + File.separator + Util.formatDate1(arcadj.getFe_archiv()) + File.separator + arcadj.getCo_archiv() + "." + Util.getFileExtension(fileName));
                System.out.println("archivo(3) = " + archivo);

                Files.copy(new FileInputStream(file), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("archivo(3?) = " + archivo.exists());

                WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).put("" + arcadj.getCo_archiv(), archivo);
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        } catch (Exception ep) {
            System.out.println("ep = " + ep);
        }
    }

    public List<SistemaDTO> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<SistemaDTO> sistemas) {
        this.sistemas = sistemas;
    }

    public SistemaDTO getSistema() {
        return sistema;
    }

    public void setSistema(SistemaDTO sistema) {
        this.sistema = sistema;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

}
