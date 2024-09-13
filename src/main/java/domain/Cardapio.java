package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity // falar para JPA que tem quer criar a classe no banco da dados
@Table(name = "tb_cardapio") // nome da classe para o banco de dados
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

}
