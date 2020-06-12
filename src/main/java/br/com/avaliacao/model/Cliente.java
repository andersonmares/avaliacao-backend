package br.com.avaliacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;

    public Cliente(Long id){
        this.id = id;
    }

    public void setCpf(String cpf) {
        // Remove mascara
        this.cpf = cpf == null ? null : cpf.replace(".", "").replace("-", "");
    }

    public String getCpfMascara() {
        return cpf != null && cpf.length() == 11 ? cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11) : cpf;
    }

}
