package com.lojaGames.lojaGames.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_categoriaGames")
public class categoriaGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O atributo descricao é obrigatório e não pode utilizar espaços em branco!") 
   	@Size(min = 5, max = 100, message = "O atributo descricao deve conter no mínimo 5 e no máximo 100 caracteres!")
    private String descricao;

    @OneToMany(mappedBy = "categoriaGames", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoriaGames")
    private List<produtoGames> produtoGames;

    @ManyToOne
	@JsonIgnoreProperties("categoriaGames")
	private Usuario usuario;
	
    @UpdateTimestamp
	private LocalDateTime data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<produtoGames> getProdutoGames() {
		return produtoGames;
	}

	public void setProdutoGames(List<produtoGames> produtoGames) {
		this.produtoGames = produtoGames;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
 }