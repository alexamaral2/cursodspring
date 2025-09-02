package com.alexj.produtosapi.repository;

import com.alexj.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
