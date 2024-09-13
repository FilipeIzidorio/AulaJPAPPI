package domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Entity // falar para JPA que tem quer criar a classe no banco da dados
@Table(name = "tb_pessoa") // nome da classe para o banco de dados
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    @Size(min = 2,max = 100)
    private String nome;

    @NotBlank
    @CPF (message = "CPF invalido")
    @Column(unique = true)
    private String cpf;

    @Email(message = "E-mail invalido")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
