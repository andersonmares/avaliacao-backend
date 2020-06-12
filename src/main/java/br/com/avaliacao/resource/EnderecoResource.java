package br.com.avaliacao.resource;

import br.com.avaliacao.model.Endereco;
import br.com.avaliacao.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("${frontend}")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping(value = "enderecos/{cep}")
    public Endereco consultarEnderecoPorCep(@PathVariable String cep) {
        return enderecoRepository.buscarPorCep(cep);
    }

}
