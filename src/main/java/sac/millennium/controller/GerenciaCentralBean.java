package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.dao.impl.GerenciaCentralSqlserverDAOImpl;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.service.IGerenciaCentralService;
import sac.millennium.service.impl.GerenciaCentralServiceImpl;

@ManagedBean
@ApplicationScoped
public class GerenciaCentralBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IGerenciaCentralDAO daoGerenciaCentral = new GerenciaCentralSqlserverDAOImpl();
	private IGerenciaCentralService servGerenciaCentral = new GerenciaCentralServiceImpl(daoGerenciaCentral);

	private List<GerenciaCentral> listaGerenciaCentral = new ArrayList<>();
	private GerenciaCentral gerenciaCentralSeleccionado;

	public GerenciaCentralBean() {
		gerenciaCentralSeleccionado = new GerenciaCentral();
	}

	public void listarTodo() {
		listaGerenciaCentral = servGerenciaCentral.findAll();
	}

	public void registra() {
		System.out.println(
				"==>" + gerenciaCentralSeleccionado.getId() + "-" + gerenciaCentralSeleccionado.getCodigoPropio()+ "-"+
						gerenciaCentralSeleccionado.getDescripcion()+"-"+gerenciaCentralSeleccionado.getDescripcionCorta()+
						gerenciaCentralSeleccionado.getEstado());
		
		servGerenciaCentral.create(gerenciaCentralSeleccionado);
		
		listarTodo();
		limpiar();
	}

	public void limpiar() {
		gerenciaCentralSeleccionado = new GerenciaCentral();
		
	}

	public void leerCentral(String idCen) {
			
			gerenciaCentralSeleccionado = servGerenciaCentral.findById(idCen);

		}
		public void update() {
			servGerenciaCentral.update(gerenciaCentralSeleccionado);
			
			listarTodo();
		}
		public void elimina(String id) {
		//	System.out.println("delete" + id);
			
		
			//gerenciaCentralSeleccionado.setId(id);
		
		 servGerenciaCentral.delete(id);
			
			listarTodo();
		}




	public List<GerenciaCentral> getListaGerenciaCentral() {
		listaGerenciaCentral = servGerenciaCentral.findAll();
		return listaGerenciaCentral;
	}

	public void setListaGerenciaCentral(List<GerenciaCentral> listaGerenciaCentral) {
		this.listaGerenciaCentral = listaGerenciaCentral;
	}

	public GerenciaCentral getGerenciaCentralSeleccionado() {
		return gerenciaCentralSeleccionado;
	}

	public void setGerenciaCentralSeleccionado(GerenciaCentral gerenciaCentralSeleccionado) {
		this.gerenciaCentralSeleccionado = gerenciaCentralSeleccionado;
	}

}
