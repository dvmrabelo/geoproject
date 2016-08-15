package br.com.geoproject.cadastro.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Form {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_form;
	private String pergunta_1;
	private String pergunta_2;
	private String pergunta_3;
	private Integer id_cad;
	
	//Getters and Setters
	public Integer getId_form() {
		return id_form;
	}
	public void setId_form(Integer id_form) {
		this.id_form = id_form;
	}
	public String getPergunta_1() {
		return pergunta_1;
	}
	public void setPergunta_1(String pergunta_1) {
		this.pergunta_1 = pergunta_1;
	}
	public String getPergunta_2() {
		return pergunta_2;
	}
	public void setPergunta_2(String pergunta_2) {
		this.pergunta_2 = pergunta_2;
	}
	public String getPergunta_3() {
		return pergunta_3;
	}
	public void setPergunta_3(String pergunta_3) {
		this.pergunta_3 = pergunta_3;
	}
	public Integer getId_cad() {
		return id_cad;
	}
	public void setId_cad(Integer id_cad) {
		this.id_cad = id_cad;
	}
}
