package com.lojaGames.lojaGames.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lojaGames.lojaGames.Repository.produtoGamesRepository;
import com.lojaGames.lojaGames.model.produtoGames;

@RestController
@RequestMapping(value = "/produtoGames")
@CrossOrigin("*")
public class produtoGamesController {
	
	@Autowired
	private produtoGamesRepository repository;
	
	@GetMapping
	public ResponseEntity<List<produtoGames>> GetAll(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<produtoGames> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nomeProduto/{nomeProduto}")
	public ResponseEntity<List<produtoGames>> getByNome(@PathVariable String nomeProduto) {
		return ResponseEntity.ok(repository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}

	@PostMapping
	public ResponseEntity<produtoGames> post(@Valid @RequestBody produtoGames produtoGames) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoGames));
	}

	@PutMapping
	public ResponseEntity<produtoGames> put(@Valid @RequestBody produtoGames produtoGames) {
		return repository.findById(produtoGames.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoGames)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<produtoGames> produto = repository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		repository.deleteById(id);
	}
}