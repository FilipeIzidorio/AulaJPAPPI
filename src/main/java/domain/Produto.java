    package domain;

    import lombok.*;

    import javax.persistence.*;
    import java.math.BigDecimal;



    @Entity
    @Table(name = "tb_produto")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @NamedQueries({
            @NamedQuery(name = "produto.getAll", query = "select p from Produto p"),
            @NamedQuery(name = "produto.byPrice", query = "select p from Produto p where p.preco > :preco"),
            @NamedQuery(name = "produto.byName", query = "select p from Produto p where p.nome = :nome")
    })
    public class Produto  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String categoria;

        private String descricao;

        private String nome;

        private BigDecimal preco;



        // Verifica se o produto está disponível para ser exibido no cardápio

    }
