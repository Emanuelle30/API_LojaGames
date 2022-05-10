package com.lojaGames.lojaGames.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtoGames")
public class produtoGames {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotBlank(message = "O atributo nome produto é obrigatório e não pode conter espaços em branco!")
	@Size(min = 3, max = 100, message = "O atributo nome produto deve conter no mínimo 3 e no máximo 100 caracteres!")
    private String nomeProduto;

    @NotBlank(message = "O atributo descrição é obrigatório e não pode conter espaços em branco!")
    @Size(min = 5, max = 100, message = "O atributo descricao deve conter no mínimo 5 e no máximo 100 caracteres!")
    private String descricao;	

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive(message = "Digite um valor maior do que zero")
    private BigDecimal preco;

    @NotBlank(message = "O atributo foto é obrigatório!")
    private String foto;

    @NotBlank(message = "O atributo fornecedor é obrigatório!")
    private String fornecedor;

    @ManyToOne
    @JsonIgnoreProperties("produtoGames")
    private categoriaGames categoriaGames;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public categoriaGames getCategoriaGames() {
		return categoriaGames;
	}

	public void setCategoriaGames(categoriaGames categoriaGames) {
		this.categoriaGames = categoriaGames;
	}
}
	