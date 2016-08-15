package br.com.geoproject.cadastro.managedbeans;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdminFormBeanTest {

	AdminFormBean admin;
	@Before
	public void inicialize(){
		admin.setEmailCode("");
	}
	
	@Test
	public void test() {
		try{
			admin.verificacao();
		}catch (Exception e){
			Assert.fail("");
		}
	}

}
