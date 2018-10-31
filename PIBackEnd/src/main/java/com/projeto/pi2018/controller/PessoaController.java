package com.projeto.pi2018.controller;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.pi2018.azure.Azure;
import com.projeto.pi2018.model.Pessoa;
import com.projeto.pi2018.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {
	
	
	@Autowired
	private PessoaService ps;
	
	@PostMapping("/criar")
	public void criar(Pessoa pessoa) {
		ps.inserir(pessoa);
	}
	@GetMapping("/buscar/{id}")
	public Pessoa buscar(@PathVariable("id") Long id) {
		
		return ps.buscar(id);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable("id") Long id) {
		ps.excluir(id);
	}
	
	@PutMapping("/alterar/{id}")
	public void alterar(@PathVariable("id") Long id) {
		criar(buscar(id));
		
	}
	@RequestMapping("/detectFace/{body}")
	public String detectFace(@PathVariable("body") String body) throws Exception {
		Azure azure = new Azure();
		return azure.reconhecerFace(body);
	}

	@RequestMapping("/criarPersonGroup")
	public void criarPersonGrou() throws Exception {
		Azure grupo = new  Azure();
		grupo.criarPersonGruop();
	}
	@RequestMapping("/criarPerson")
	public String criarPerson() throws Exception {
		Azure grupo = new  Azure();
		return grupo.criarPerson();
	}
	@RequestMapping("/addFace")
	public void addFace() throws Exception {
		Azure grupo = new  Azure();
		
		grupo.addFace("3fdb85d7-f3ad-4ae4-b103-3a0ece1ec223");
	}
	
	@RequestMapping("/binario")
	public String conversaoBinario(@RequestBody String base64) {
		String temp ="";
		byte[] decode = DatatypeConverter.parseBase64Binary(base64);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < decode.length; i++) {
			 temp = Integer.toBinaryString(decode[i]);
			 sb.append(String.format("%8s", temp).replace(" ", "0"));
		}
		System.out.println(sb.toString());
		return sb.toString() ;
	}
	
	
	/*@RequestMapping("/identify")
	public void identify() throws Exception {
		Azure azure = new Azure();
		azure.identify(detectFace());
	}*/
}
