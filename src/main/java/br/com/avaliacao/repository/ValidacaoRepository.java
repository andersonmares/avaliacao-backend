package br.com.avaliacao.repository;

import br.com.avaliacao.model.Validacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidacaoRepository extends JpaRepository<Validacao, Long> {
}
