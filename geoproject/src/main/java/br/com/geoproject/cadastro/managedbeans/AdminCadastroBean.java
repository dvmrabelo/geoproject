package br.com.geoproject.cadastro.managedbeans;




import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.geoproject.cadastro.models.Cadastro;
import br.com.geoproject.cadastro.daos.CadastroDAO;

@Model
public class AdminCadastroBean {

	private Cadastro cad = new Cadastro();
	@Inject
	private CadastroDAO cadDao = new CadastroDAO();
	
	//Envia os dandos para serem salvos
	@Transactional
	public void save() throws Exception{
		cadDao.save(cad);
		}
	
	public Cadastro getCadastro(){
		return cad;
	}
	
	
}
