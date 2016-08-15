package br.com.geoproject.cadastro.daos;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.geoproject.cadastro.managedbeans.AdminCadastroBean;
import br.com.geoproject.cadastro.models.Cadastro;

public class CadastroDAOTest {
	
	private Cadastro cadastro;
	private CadastroDAO cadDAO;
	
	@Before
	public void inicia(){
		cadDAO = new CadastroDAO();
		cadastro = new Cadastro();
		cadastro.setId(1);
		cadastro.setNome("Diogenes");
		cadastro.setEmail("vikmais@hotmail.com");
		cadastro.setEstatos(0);
		//Email encriptado, feito separado.
		cadastro.setNomeCode("10331549187090583267104104369718658999");	
	}
	
	
	@Test
	public void testEncrypt() {
		try {
			assertEquals((cadDAO.encrypt(cadastro.getNome())), "10331549187090583267104104369718658999");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail("erro");
		}
	}
}
