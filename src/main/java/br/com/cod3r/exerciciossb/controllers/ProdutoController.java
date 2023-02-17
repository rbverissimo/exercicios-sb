package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exerciciossb.model.entities.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@PostMapping
	public Produto novoProduto(@RequestParam String nome) {
		Produto p = new Produto(nome);
		return p;
	}

}
