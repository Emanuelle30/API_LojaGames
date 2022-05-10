package com.lojaGames.lojaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojaGames.lojaGames.model.produtoGames;

@Repository
public interface produtoGamesRepository extends JpaRepository<produtoGames, Long>{
	public List<produtoGames> findAllByNomeProdutoContainingIgnoreCase (String nomeProduto);
}
