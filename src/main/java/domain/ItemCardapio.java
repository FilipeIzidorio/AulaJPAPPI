package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity // falar para JPA que tem quer criar a classe no banco da dados
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Produto produto ;

    private Integer quantidade;

    private Double subTotal;

    public BigDecimal calculaSubtotal(){
        return  produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

}
