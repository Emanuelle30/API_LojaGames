package com.lojaGames.lojaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojaGames.lojaGames.model.categoriaGames;

@Repository
public interface categoriaGamesRepository extends JpaRepository<categoriaGames, Long>{
	public List<categoriaGames> findAllByDescricaoContainingIgnoreCase (String descricao);
}
