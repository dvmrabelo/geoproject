package br.com.geoproject.cadastro.daos;

import java.io.IOException;

import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.geoproject.cadastro.models.Form;

@Dependent
public class FormDAO {

	@PersistenceContext
	private EntityManager manager;
	
	//Salva os dados do formulário
	public void save(Form form) throws IOException{
		manager.persist(form);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	
}
