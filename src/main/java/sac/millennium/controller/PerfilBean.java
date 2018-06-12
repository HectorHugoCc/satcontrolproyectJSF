package sac.millennium.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import sac.millennium.dao.IPerfilDAO;
import sac.millennium.dao.impl.PerfilSqlserverDAOImpl;
import sac.millennium.model.Perfil;
import sac.millennium.service.IPerfilService;
import sac.millennium.service.impl.PerfilServiceImpl;

@ManagedBean
@ApplicationScoped
public class PerfilBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IPerfilDAO daoPerfil = new PerfilSqlserverDAOImpl();
	private IPerfilService servPerfil = new PerfilServiceImpl(daoPerfil);

	private Perfil perfilSeleccionado;
	private UIData dtFilaP;

	private String id;
	List<Perfil> listaPerfil;

	@PostConstruct
	public void init() {
		listarTodo();
		perfilSeleccionado = new Perfil();
	}

	/*
	 * public String buscar(String id) {
	 * 
	 * @SuppressWarnings("unused") Perfil personaseleccionada =
	 * servPerfil.findById(id); return null; }
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PerfilBean() {
		perfilSeleccionado = new Perfil();
	}

	public void limpiar() {

		perfilSeleccionado = new Perfil();
	}
	public void registra() {

		System.out.println("Guardando......" + perfilSeleccionado.getId() + "--" + perfilSeleccionado.getDescripcion()
				+ "--" + perfilSeleccionado.getDescripcionCorta() + "--" + perfilSeleccionado.getEstado());

		servPerfil.create(perfilSeleccionado);
		
		listarTodo();
		limpiar();
	}

	public void editPerfil(String idPer) {
	
		
		System.out.println("mostrar: "+ perfilSeleccionado.getId() + "--" + perfilSeleccionado.getDescripcion()
		+ "--" + perfilSeleccionado.getDescripcionCorta() + "--" + perfilSeleccionado.getEstado());
		
		
		perfilSeleccionado = servPerfil.findById(idPer);

		//perfilSeleccionado.setId(idPer);
		
		// return DatabaseOperation.editStudentRecordInDB(studentId);
	}
	public void update() {
		servPerfil.update(perfilSeleccionado);
		
		//listarTodo();
	}
	public void elimina(String id) {
		
		servPerfil.delete(id);
		
		listarTodo();
	}

	public void listarTodo() {
		listaPerfil = servPerfil.findAll();
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public Perfil getPerfilSeleccionado() {
		return perfilSeleccionado;
	}

	public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
		this.perfilSeleccionado = perfilSeleccionado;
	}
	//
	// public void onRowEdit(RowEditEvent event) {
	// servPerfil.update((Perfil) event.getObject());
	// FacesMessage msg = new FacesMessage("Perfil editado", ((Perfil)
	// event.getObject()).getId());
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	//
	// }
	//
	// public void onRowCancel(RowEditEvent event) {
	// FacesMessage msg = new FacesMessage("Edit Cancelado", ((Perfil)
	// event.getObject()).getId());
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// }
	//
	// public void onCellEdit(CellEditEvent event) {
	// Object oldValue = event.getOldValue();
	// Object newValue = event.getNewValue();
	//
	// if (newValue != null && !newValue.equals(oldValue)) {
	// FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell
	// Changed",
	// "Old: " + oldValue + ", New:" + newValue);
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// }
	// }

	public UIData getDtFilaP() {
		return dtFilaP;
	}

	public void setDtFilaP(UIData dtFilaP) {
		this.dtFilaP = dtFilaP;
	}

}
