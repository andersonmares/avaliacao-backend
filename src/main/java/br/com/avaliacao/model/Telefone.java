package br.com.avaliacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @NotEmpty
    private String numero;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public void setNumero(String numero) {
        // Remove mascara
        this.numero = numero == null ? null : numero
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(" ", "");
    }
}
