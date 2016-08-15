package br.com.geoproject.cadastro.daos;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.geoproject.cadastro.infra.MailSender;
import br.com.geoproject.cadastro.models.Cadastro;

@Dependent
public class CadastroDAO {

	@PersistenceContext
	private EntityManager manager;
	@Inject
	private MailSender mailSender;

	//Salva os dados do cadastro, antes verificar se o email já está cadastrado
	public void save(Cadastro cad) throws Exception {
		
		try {
			String email;
			Query query = manager.createQuery("Select email From Cadastro where email = ?1");
			query.setParameter("1", cad.getEmail());
			email = (String) query.getSingleResult();
			FacesContext.getCurrentInstance().getExternalContext().redirect("link-erro.xhtml");
		} catch (NoResultException nre) {
			cad.setNomeCode(encrypt(cad.getEmail()));
			mailSender.send("geofusionproject@gmail.com", cad.getEmail(), "Bem-vindo", getMessage(cad.getNome(), cad.getEmail()));
			manager.persist(cad);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}
	}

	//Faz atualização em um cadastrado
	public boolean update(Cadastro cad) {
		try{
		manager.merge(cad);
		return true;
		} catch (Exception e){
			return false;
		}
	}

	//Criptografa o Email do cadastro para passar na url
	public static String encrypt(String email) throws Exception {
		MessageDigest name = MessageDigest.getInstance("MD5");
		name.update(email.getBytes(), 0, email.length());
		return new BigInteger(1, name.digest()).toString();
	}

	//Gera a mensagem a ser enviado ao email do cadastrado
	public String getMessage(String nome, String email) throws Exception {
		String br = System.getProperty("line.separator");
		String mainBody = "Olá senhor " + nome + "! " + br
				+ "Seja bem vindo, em breve entraremos em contato para notificá-lo do grande lançamento do nosso serviço."
				+ br
				+ "Nós estamos felizes com seu interesse, sendo assim, para termos um feedback, gostaríamos que você desse sua opinião rapidamente (2 a 3 minutos) respondendo a algumas perguntas sobre o que você espera do nosso produto."
				+ br + "É só acessar esse link abaixo:" + br + getUrl(encrypt(email));
		return mainBody;
	}

	//Gera a URL para o formulário
	private String getUrl(String email) throws Exception {

		String url = "http://localhost:8080/geoproject/form.xhtml?nome=" + email;
		return url;
	}

	//Verifica se o código criptografado pertence a algum cadastrado, retorna um boolean
	public boolean consultarPorNomeCode(String emailCode) {
		String pesq;
		int codigo;
		try {
			pesq = manager.createQuery("From Cadastro where nomeCode = :code", Cadastro.class).setParameter("code", emailCode).getSingleResult().getNomeCode();
			codigo = manager.createQuery("From Cadastro where nomeCode = :code", Cadastro.class).setParameter("code", emailCode).getSingleResult().getEstatos();
			if(pesq.equals(emailCode) && codigo == 0)
				return false;
			else
				return true;
		} catch (NoResultException e) {
			return true;
		}
	}
	
	//Verifica se o código criptografado pertence a algum cadastrado, retorna um Cadastro
	public Cadastro consultarPorCode(String emailCode) {
		Cadastro pesq;
		try {
			pesq = manager.createQuery("From Cadastro where nomeCode = :code", Cadastro.class).setParameter("code", emailCode).getSingleResult();
				return pesq;
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
