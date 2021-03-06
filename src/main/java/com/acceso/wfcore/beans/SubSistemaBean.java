package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.SistemaDAO;
import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.dtos.SistemaDTO;
import com.acceso.wfcore.dtos.SubSistemaDTO;
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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.bean.ViewScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@ViewScoped
public class SubSistemaBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/subsistema/paginaSubSistemas.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/subsistema/paginaSubSistemas.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/subsistema/paginaRegSubSistema.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/subsistema/paginaRegSubSistema.xhtml";

    public static final String BEAN_NAME = "subSistemaBean";

    private List<SubSistemaDTO> subsistemas;
    private SubSistemaDTO subsistema;

    private boolean isregEditable;
    private SelectItem[] si_sistema;

    public SubSistemaBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Sub Sistema";
        this.subsistema = new SubSistemaDTO();
        this.isregEditable = true;
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
            this.subsistema.setAr_logsub(arcadj.getCo_archiv());

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

    @PostConstruct
    public void loadModule() {
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);

        Object obj = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("X64SUB");
        Log.info("[SubSistemaBean]obj = " + obj);

        if (obj != null) {
            this.subsistema = (SubSistemaDTO) obj;
        }

        List<SistemaDTO> sistemas;
        SistemaDAO dao = new SistemaDAO();
        sistemas = dao.getSistemas();
        dao.close();

        List<SelectItem> all = sistemas.stream().filter(s -> !s.getIl_sisfor()).map(s -> {
            SelectItem si = new SelectItem();
            si.setLabel(s.getNo_sistem());
            si.setDescription(s.getDe_sistem());
            si.setValue(s.getCo_sistem());
            return si;
        }).collect(Collectors.toList());
        si_sistema = (SelectItem[]) all.toArray(new SelectItem[all.size()]);
    }

    @Override
    public String load() {
        System.out.println("load()");
        this.isregEditable = true;
        // LLENAR LOS BOTONES SECUNDARIOS
        //doListener();
        //CARGA INICIAL!!
        selectDto();

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
        this.subsistema = new SubSistemaDTO();
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
        SubSistemaDAO dao = new SubSistemaDAO();
        this.subsistemas = dao.getSubSistemas();
        dao.close();
    }

    @Override
    public void saveDto() {
        SubSistemaDAO dao = new SubSistemaDAO();
        System.out.println("!subsistema = " + this.subsistema);
        this.subsistema = dao.grabarSubSistema(this.subsistema);
        this.subsistemas = dao.getSubSistemas();
//      System.out.println("SubSistemaBean actualizarSubSistema = " + this.subsistema);
        dao.close();
    }

    @Override
    public void updateDto() {
        SubSistemaDAO dao = new SubSistemaDAO();
        this.subsistema = dao.grabarSubSistema(subsistema);
        this.subsistemas = dao.getSubSistemas();
//      System.out.println("SubSistemaBean actualizarSubSistema = " + this.subsistema);
        dao.close();
    }

    @Override
    public void deleteDto() {
        SubSistemaDAO dao = new SubSistemaDAO();
        String resultado = dao.deleteSubSistema(subsistema);
        this.subsistemas = dao.getSubSistemas();
        dao.close();
    }

    public List<SubSistemaDTO> getSubsistemas() {
        return subsistemas;
    }

    public void setSubsistemas(List<SubSistemaDTO> subsistemas) {
        this.subsistemas = subsistemas;
    }

    public SubSistemaDTO getSubsistema() {
        return subsistema;
    }

    public void setSubsistema(SubSistemaDTO subsistema) {
        this.subsistema = subsistema;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

    public SelectItem[] getSi_sistema() {
        return si_sistema;
    }

    public void setSi_sistema(SelectItem[] si_sistema) {
        this.si_sistema = si_sistema;
    }

}
