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

import com.lojaGames.lojaGames.Repository.categoriaGamesRepository;
import com.lojaGames.lojaGames.model.categoriaGames;

@RestController
@RequestMapping("/categoriaGames")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class categoriaGamesController {
	
	@Autowired
	private categoriaGamesRepository repository;
	
	@GetMapping
	public ResponseEntity<List<categoriaGames>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<categoriaGames> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<categoriaGames>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
		
	@PostMapping
	public ResponseEntity<categoriaGames> post(@Valid @RequestBody categoriaGames categoriaGames) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoriaGames));
	}

	@PutMapping
	public ResponseEntity<categoriaGames> put(@Valid @RequestBody categoriaGames categoriaGames) {
		return repository.findById(categoriaGames.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoriaGames)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<categoriaGames> categoria = repository.findById(id);

		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		repository.deleteById(id);
	}
}