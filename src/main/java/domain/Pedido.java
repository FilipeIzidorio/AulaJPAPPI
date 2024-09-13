package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pessoa cliente;


    @OneToMany(cascade = CascadeType.MERGE)
    private List<ItemCardapio> itens;

    private Integer quantidade;

    private BigDecimal total ;

//    public boolean adicionarItem(Produto produto, int quantidadeSolicitada) {
//        if (produto.getEstoque().reduzirEstoque(quantidadeSolicitada)) {
//            this.itens.add(produto);
//            this.quantidade += quantidadeSolicitada;
//            return true;
//        }
//        System.out.println("Quantidade solicitada maior que o disponível no estoque.");
//        return false;
//    }
}
