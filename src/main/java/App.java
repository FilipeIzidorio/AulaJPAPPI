import dao.*;
import domain.*;
import persistence.JPAUtil;
import service.PedidoService;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        var jpaUtil = new JPAUtil();

// Criar uma instância de Produto
        var produto = Produto.builder()
                .categoria("Prato Principal")
                .descricao("Grega")
                .nome("Arroz")
                .preco(BigDecimal.valueOf(12))

                .build();

        // Criar uma instância de Estoque
        var  estoque = Estoque.builder()
                .produto(produto)
                .quantidade(30) // Defina a quantidade de estoque
                .build();




        ProdutoDAO produtoDAO = new ProdutoDAO();
        var estoqueDAO = new EstoqueDAO();
        //produtoDAO.save(produto);
       // estoqueDAO.save(estoque);




        //System.out.println(produtoDAO.produtoByID(1L));
        //System.out.println(produtoDAO.getAllProdutos());
        //System.out.println(produtoDAO.getAllProdutosByPrice(BigDecimal.valueOf(10)));
        //System.out.println(produtoDAO.findByNameLike("Batata"));
        //System.out.println(produtoDAO.delete(8L));
        //System.out.println(produtoDAO.update(9L,"Churros"));

        // Persistir Pessoa
        PessoaDAO pessoaDAO = new PessoaDAO(jpaUtil);
        Pessoa pessoa = Pessoa.builder()
                .endereco(Endereco.builder()
                        .bairro("Jardim")
                        .cidade("Cajazeiras")
                        .rua("Projetada")
                        .numero("50")
                        .build())
                .nome("Filipe")
                .cpf("41272119203")
                .email("f@.com")
                .build();

        //pessoaDAO.save(pessoa);


        // Recuperar todos os produtos e criar o Cardápio
        var cardapioDAO = new CardapioDAO();
        var produtos = produtoDAO.getAllProdutos();
        System.out.println(produtos);

        var cardapio = Cardapio.builder()
                /*.produtos(List.of(Produto.builder()
                        .categoria("Entrada")
                        .descricao("Oriental")
                        .preco(new BigDecimal("50.5"))
                        .nome("Sushi")
                        .build()))*/
                .produtos(produtos)
                .build();

        //cardapioDAO.save(cardapio);


        // Recuperar o Cardápio existente
//        var cardapioExistente = cardapioDAO.cardapio(1L); // Ajuste o ID conforme necessário
//        cardapioExistente.setProdutos(produtos);
//        cardapioDAO.update(cardapioExistente);


        // Criar Pedido
        Pessoa cliente = pessoaDAO.findById(1L); // Ajuste o ID conforme necessário


        var itensPedidos = new ArrayList<ItemCardapio>();


        var item1 = new ItemCardapio();
        item1.setQuantidade(10);
        item1.setProduto(produtoDAO.produtoByID2(12L));
        item1.setSubTotal(item1.calculaSubtotal().doubleValue());


        itensPedidos.add(item1);


        var pedidoService = new PedidoService();

        pedidoService.setCliente(cliente);
        pedidoService.setItens(itensPedidos);

        System.out.println("Cliente: " + cliente.getNome());

        itensPedidos.forEach(
                i -> System.out.println("Item: " + i.getProduto().getNome() + " \n" +
                        "Preco Unitario: " + i.getProduto().getPreco() + "\n " +
                        "Quantidade: " + i.getQuantidade() + "\n " +
                        "Subtotal: " + pedidoService.subtotal(i))
        );

        var total = new BigDecimal(BigInteger.ZERO);
        for (ItemCardapio i : itensPedidos) {

            total = total.add(pedidoService.subtotal(i));
        }
        System.out.println("Valor Total: " + total);

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .itens(itensPedidos)
                .total(total)
                .build();

        PedidoDAO pedidoDAO = new PedidoDAO();
        //pedidoDAO.save(pedido);
/*
        // Exibir informações
        System.out.println("Cliente: " + cliente.getNome());
        itensPedidos.forEach(i -> System.out.println("Item: " + i.getProduto().getNome() +
                ", Preço Unitário: " + i.getProduto().getPreco() +
                ", Quantidade: " + i.getQuantidade() +
                ", Subtotal: " + i.calculaSubtotal()));

        System.out.println("Valor Total: " + total);



*/





/*

        boolean retorna = false;
        do {
            String menu =
                    """
                                    MENU PRINCIPAL


                                   
                             1 - CADASTRAR PRODUTOS
                             2 - BUSCAR PELO O ID
                             3 - LISTAR TODOS OS PRODUTOS
                             4 - LISTAR PRODUTOS PELO O VALOR MAIOR QUER :
                             5 - LISTAR PTODUTOS POR NOME
                             6 - DELETAR PRODUTO
                             0 - SAIR
                            """;
            int i = 0;
            try {
                i = Integer.parseInt(JOptionPane.showInputDialog(menu));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "DIGITE O NUMERO INTEIRO");
            }

            switch (i){
                case 1 ->{
                    produto.setId(null);
                    produto.setNome(JOptionPane.showInputDialog("Digite o nome do Produto"));
                    produto.setCategoria(JOptionPane.showInputDialog("Digite a Categoria do Produto"));
                    produto.setDescricao(JOptionPane.showInputDialog("Digite a Descricao do Produto"));
                    double precoDouble = Double.parseDouble(JOptionPane.showInputDialog("Digite a Preço do Produto"));
                    try {
                        produto.setPreco(BigDecimal.valueOf(precoDouble));
                    }catch (NumberFormatException e ){
                        JOptionPane.showMessageDialog(null,"Entrada Errada. Por favor, digite um numero válido", "Erro",JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(null,"nome:" + produto.getNome()+"\n"+
                            "categoria:"+ produto.getCategoria()+"\n" +
                            "Descrição:"+produto.getDescricao() +"\n"+
                            "Preço: "+produto.getPreco());

                    int confirmacao= JOptionPane.showConfirmDialog(null, "Deseja salvar as seguintes informações?\n",
                            "Confirmação", JOptionPane.YES_NO_OPTION);

                    // Verificando a resposta do usuário
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        // Salvando o produto no banco de dados
                        produtoDAO.save(produto);
                        JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
                        JOptionPane.showMessageDialog(null, "ATE LOGO");
                    System.exit(0);

                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                    }


                }
                case 2 -> {


                }
                case 3 -> {


                }
                case 4 -> {}
                case 5 -> {}
                case 6 -> {}
                case 0 -> {

                    JOptionPane.showMessageDialog(null, "ATE LOGO");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "OPCAO INVALIDA"+"\n"+"DIGITE UMA OPCAO VALIDA");

            }

        }while(!retorna); // do


*/
    }
}


