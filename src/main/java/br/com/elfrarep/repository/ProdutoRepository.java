package br.com.elfrarep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elfrarep.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
