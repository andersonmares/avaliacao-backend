package br.com.avaliacao.repository;

import br.com.avaliacao.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    default Endereco buscarPorCep(String cep) {

        CepRepository cepRepository = new CepRepository();
        CepRepository.CepVO cepVo = cepRepository.consultar(cep);

        assert cepVo != null;

        Endereco endereco = new Endereco();
        endereco.setCep(cepVo.getCep());
        endereco.setUf(cepVo.getUf());
        endereco.setCidade(cepVo.getLocalidade());
        endereco.setBairro(cepVo.getBairro());
        endereco.setLogradouro(cepVo.getLogradouro());
        endereco.setComplemento(cepVo.getComplemento());

        return endereco;

    }

}
