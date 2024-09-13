package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_estoque")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Produto produto;

    private Integer quantidade;  // Quantidade de produtos disponíveis no estoque

    // Reduz o estoque ao realizar um pedido
    public boolean reduzirEstoque(int quantidadeSolicitada) {
        if (quantidadeSolicitada > this.quantidade) {
            return false;  // Quantidade insuficiente no estoque
        }
        this.quantidade -= quantidadeSolicitada;
        return true;
    }

    // Verifica se o produto ainda está disponível no estoque
    public boolean isDisponivel() {
        return this.quantidade > 0;
    }
}
