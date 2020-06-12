package br.com.avaliacao.resource;

import br.com.avaliacao.model.*;
import br.com.avaliacao.repository.ClienteRepository;
import br.com.avaliacao.repository.ValidacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("${frontend}")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ValidacaoRepository validacaoRepository;

    @GetMapping(value = "/clientes")
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping(value = "/clientes/{id}")
    public Cliente porId(@PathVariable("id") Long id) {
        Optional<Cliente> optCliente = clienteRepository.findById(id);
        return optCliente.orElse(null);
    }

    @PostMapping(value = "/clientes")
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        if (cliente.getEndereco() != null){
            cliente.getEndereco().setCliente(cliente);
        }
        if (cliente.getEmails() != null) {
            for (Email email : cliente.getEmails()) {
                email.setCliente(cliente);
            }
        }
        if (cliente.getTelefones() != null) {
            for (Telefone telefone : cliente.getTelefones()) {
                telefone.setCliente(cliente);
            }
        }

        TipoOperacao tipoOperacao= cliente.getId() == null ? TipoOperacao.INCLUSAO : TipoOperacao.ALTERACAO;

        validacaoRepository.save(new Validacao(tipoOperacao, cliente.getId(), cliente.getNome(), "admin"));

        clienteRepository.save(cliente);

        return cliente;
    }

    @DeleteMapping(value = "/clientes/{id}")
    public void excluir(@PathVariable("id") Long id){
        Cliente cliente = porId(id);

        clienteRepository.deleteById(id);

        Validacao validacao = new Validacao(TipoOperacao.EXCLUSAO, id, cliente.getNome(), "admin");

        validacaoRepository.save(validacao);

    }

}
