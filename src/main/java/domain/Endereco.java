package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // falar para JPA que tem quer criar a classe no banco da dados
@Table(name = "tb_endereco") // nome da classe para o banco de dados
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;
    private String rua;
    private String bairro;
    private String numero;




}
