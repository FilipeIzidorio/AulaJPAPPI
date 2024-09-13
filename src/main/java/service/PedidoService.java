package service;

import domain.ItemCardapio;
import domain.Pessoa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private Pessoa cliente;
    private List<ItemCardapio> itens;

   /* public BigDecimal subtotal(ItemCardapio i) {
        return itens.stream()
                .map(item -> item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
*/

    public BigDecimal subtotal(ItemCardapio i ){
        BigDecimal subtotal = BigDecimal.ZERO;
        subtotal = subtotal.add(
                i.getProduto().getPreco().multiply(new BigDecimal(i.getQuantidade())));
        return subtotal;

    }
    public void setItens(ArrayList<ItemCardapio> itensPedido){}

    public void setCliente(Pessoa pessoa){}



}
