/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.gestor;

import com.gestor.controller.Propiedades;
import com.gestor.entity.App;
import com.gestor.entity.Dialogo;
import com.gestor.entity.UtilArchivo;
import com.gestor.entity.UtilJSF;
import com.gestor.entity.UtilLog;
import com.gestor.entity.UtilMSG;
import com.gestor.gestor.controlador.GestorEvaluacionAdjuntos;
import com.gestor.modelo.Sesion;
import com.gestor.publico.EvaluacionAdjuntos;
import com.gestor.publico.EvaluacionAdjuntosPK;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiEvaluacionAdjuntos")
@SessionScoped
public class UIEvaluacionAdjuntos {

    private UploadedFile file;
    private SeccionDetalleItems sdiSeleccionado;
    private List<EvaluacionAdjuntos> evaluacionAdjuntosList = new ArrayList<>();

    public void adjuntarSoporte() {
        this.sdiSeleccionado = (SeccionDetalleItems) UtilJSF.getBean("varSeccionDetalleItems");
        Dialogo dialogo = new Dialogo("dialogos/adjuntar-soporte.xhtml", "Adjuntar Soportes", "clip");
        UtilJSF.setBean("dialogo", dialogo, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarAdjunto(FileUploadEvent event) {
        try {
            String ruta = Propiedades.getInstancia().getPropiedades().getProperty("rutaTemporal");
            File carpeta = new File(ruta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            UtilArchivo.guardarStream(ruta + File.separator + event.getFile().getFileName(), event.getFile().getInputstream());
            this.file = event.getFile();
        } catch (IOException ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }

    }

    public void guardarAdjunto() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            EvaluacionAdjuntos evaluacionAdjuntos = (EvaluacionAdjuntos) UtilJSF.getBean("evaluacionAdjuntos");
            evaluacionAdjuntos.setEvaluacionAdjuntosPK(new EvaluacionAdjuntosPK(sdiSeleccionado.getSeccionDetalle().getSeccion().getCiclo().getEvaluacion().getEvaluacionPK().getCodEvaluacion(),
                    sdiSeleccionado.getSeccionDetalle().getSeccion().getCiclo().getEvaluacion().getEstablecimiento().getCodigoEstablecimiento(),
                    sdiSeleccionado.getSeccionDetalle().getSeccion().getCiclo().getCodCiclo(), sdiSeleccionado.getSeccionDetalle().getSeccion().getSeccionPK().getCodSeccion(),
                    sdiSeleccionado.getSeccionDetalle().getSeccionDetallePK().getCodDetalle(), sdiSeleccionado.getSeccionDetalleItemsPK().getCodItem())
            );
            evaluacionAdjuntos.setArchivo(file.getFileName());
            evaluacionAdjuntos.setExtension(FilenameUtils.getExtension(file.getFileName()));
            evaluacionAdjuntos.setDocumentoUsuario(sesion.getUsuarios().getUsuariosPK().getDocumentoUsuario());

            GestorEvaluacionAdjuntos gestorEvaluacionAdjuntos = new GestorEvaluacionAdjuntos();
            evaluacionAdjuntos.setEstado(App.EVALUACION_ADJUNTOS_ESTADO_ACTIVO);
            evaluacionAdjuntos = gestorEvaluacionAdjuntos.validarEvaluacionAdjuntos(evaluacionAdjuntos);
            Properties p = Propiedades.getInstancia().getPropiedades();
            String ruta = p.getProperty("rutaAdjunto") + File.separator + App.ADJUNTO_PREFIJO + sdiSeleccionado.getSeccionDetalle().getSeccion().getCiclo().getEvaluacion().getEvaluacionPK().getCodEvaluacion();
            String rutaTemporal = p.getProperty("rutaTemporal") + File.separator + file.getFileName();

            File carpeta = new File(ruta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            ruta += File.separator + file.getFileName();
            UtilArchivo.copiar(rutaTemporal, ruta);
            UtilArchivo.borrar(rutaTemporal);

            gestorEvaluacionAdjuntos.procesarEvaluacionAdjuntos(evaluacionAdjuntos);
            UtilMSG.addSuccessMsg("Adjunto almacenado correctamente.");
            UtilJSF.setBean("evaluacionAdjuntos", new EvaluacionAdjuntos(), UtilJSF.SESSION_SCOPE);
        } catch (IOException ex) {
            UtilLog.generarLog(this.getClass(), ex);
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
            }
        }

    }

    /** 
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the sdiSeleccionado
     */
    public SeccionDetalleItems getSdiSeleccionado() {
        return sdiSeleccionado;
    }

    /**
     * @param sdiSeleccionado the sdiSeleccionado to set
     */
    public void setSdiSeleccionado(SeccionDetalleItems sdiSeleccionado) {
        this.sdiSeleccionado = sdiSeleccionado;
    }

    /**
     * @return the evaluacionAdjuntosList
     */
    public List<EvaluacionAdjuntos> getEvaluacionAdjuntosList() {
        return evaluacionAdjuntosList;
    }

    /**
     * @param evaluacionAdjuntosList the evaluacionAdjuntosList to set
     */
    public void setEvaluacionAdjuntosList(List<EvaluacionAdjuntos> evaluacionAdjuntosList) {
        this.evaluacionAdjuntosList = evaluacionAdjuntosList;
    }
}
