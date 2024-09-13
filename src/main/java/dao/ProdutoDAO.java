package dao;

import domain.Produto;
import persistence.JPAUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    private final JPAUtil jpaUtil;

    public ProdutoDAO() {
        jpaUtil = new JPAUtil();
    }
    @Transactional
    public void save(Produto produto){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(produto);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();

    }
    public Produto produtoByID2(long id ){
        //jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Produto.class,id);
    }
    public Produto produtoByID(long id ){
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Produto.class,id);
    }

    public List<Produto> getAllProdutos(){

        //utilizando jpql
        jpaUtil.getEntityManager().getTransaction().begin();
        // String jpql = "select p from Produto p";
        var query = jpaUtil.getEntityManager()
                .createNamedQuery("produto.getAll");
        return query.getResultList();
    }

    public List getAllProdutosByPrice(BigDecimal valor) {


        jpaUtil.getEntityManager().getTransaction().begin();

        var query = jpaUtil.getEntityManager().createNamedQuery("produto.byPrice");
        query.setParameter("preco", valor);
        return query.getResultList();
    }

    public List<Produto> findByName(String nome){
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("produto.byName");
        query.setParameter("nome",nome);
        return query.getResultList();

    }

    public List<Produto> findByNameLike(String nome){
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("produto.byNameLike");
        query.setParameter("nome","%"+nome+"%");
        return query.getResultList();

    }
    public String delete(long id){
        var produtoParaExcluir = produtoByID(id);
        jpaUtil.getEntityManager().remove(produtoParaExcluir);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
        return produtoParaExcluir.getNome().concat(" \n excluido com sucesso!");
    }

    public String update(long id,String nome){
        var produtoParaAlterar = produtoByID(id);
        produtoParaAlterar.setNome(nome);
        jpaUtil.getEntityManager().merge(produtoParaAlterar);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
        return produtoParaAlterar.getNome().concat(" \n Alterado com sucesso!");
    }


}
