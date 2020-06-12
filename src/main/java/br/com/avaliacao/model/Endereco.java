package br.com.avaliacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String cep;

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String cidade;

    @NotEmpty
    private String uf;

    private String complemento;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public void setCep(String cep) {
        // Remove mascara
        this.cep = cep == null ? null : cep.replace(".", "").replace("-", "");
    }

    public String getCepMascara() {
        return cep != null && cep.length() == 8 ? cep.substring(0, 5) + "-" + cep.substring(5, 8) : cep;
    }

}
