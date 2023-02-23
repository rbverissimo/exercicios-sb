package br.com.cod3r.exerciciossb.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exerciciossb.model.entities.Produto;
import br.com.cod3r.exerciciossb.model.repositories.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Produto salvarProduto( @Valid Produto p) {
		produtoRepository.save(p);
		return p;	
	}
	
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping(path = "/nome/{parteNome}")
	public Iterable<Produto> obterProdutosPorNome(
			@PathVariable String parteNome) {
		return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
	}
	
	
	@GetMapping(path = "/pagina/{numeroPagina}")
	public Iterable<Produto> obterProdutosPorPagina(
			@PathVariable int numeroPagina, 
			@PathVariable int qntElementos){
		if(qntElementos >= 6) qntElementos = 6;
		Pageable page = PageRequest.of(numeroPagina, qntElementos);
		return produtoRepository.findAll(page);
	}
	
	@GetMapping(path="/{id}")
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
	}
	
	/*
	 * @PutMapping public Produto alterarProduto( @Valid Produto p) {
	 * produtoRepository.save(p); return p; }
	 */
	
	@DeleteMapping(path="/{id}")
	public void excluirProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}

}
