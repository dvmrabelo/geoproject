package br.com.geoproject.cadastro.managedbeans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import br.com.geoproject.cadastro.daos.CadastroDAO;
import br.com.geoproject.cadastro.daos.FormDAO;
import br.com.geoproject.cadastro.models.Cadastro;
import br.com.geoproject.cadastro.models.Form;

@Model
public class AdminFormBean {

	private String emailCode;
	private Integer estatos;
	private Form form = new Form();
	@Inject
	private FormDAO formDao = new FormDAO();
	@Inject
	private CadastroDAO cadDAO = new CadastroDAO();

	@Transactional
	
	//Se o estado ainda for 0, envia os dados para serem salvos
	public void save() throws Exception {
		try {
			Cadastro cad = new Cadastro();
			Integer est = 1;
			cad = cadDAO.consultarPorCode(emailCode);
			if (cad.getEstatos() == 1) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("link-erro.xhtml");
			} else {
				cad.setEstatos(est);
				cadDAO.update(cad);
				form.setId_cad(cad.getId());
				formDao.save(form);
			}
		} catch (NoResultException nre) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("erro.xhtml");
		}
	}
	
	
	public Form getForm() {
		return form;
	}

	//Faz a verificação antes de abrir a página
	public void verificacao() throws Exception {
		if (emailCode == null ||  cadDAO.consultarPorNomeCode(emailCode))
			FacesContext.getCurrentInstance().getExternalContext().redirect("erro.xhtml");

	}

	public String getEmailCode() {
		return emailCode;
	}


	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}


	public Integer getEstatos() {
		return estatos;
	}

	public void setEstatos(Integer estatos) {
		this.estatos = estatos;
	}
}
