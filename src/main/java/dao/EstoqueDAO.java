package dao;

import domain.Estoque;
import domain.Produto;
import persistence.JPAUtil;

import javax.transaction.Transactional;
import java.util.List;

public class EstoqueDAO {

    private final JPAUtil jpaUtil;

    public EstoqueDAO() {
        this.jpaUtil = new JPAUtil();
    }

    @Transactional
    public void save(Estoque estoque) {
        var em = jpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
        em.close();
    }

    public Estoque findById(Long id) {
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Estoque.class, id);
    }

    public List<Estoque> estoqueGetAll() {
        var em = jpaUtil.getEntityManager();
        em.getTransaction().begin();
        var query = em.createQuery("SELECT e FROM tb_estoque e", Estoque.class);
        return query.getResultList();
    }

    public Estoque findByProduto(Produto produto) {
        var em = jpaUtil.getEntityManager();
        var query = em.createQuery("SELECT e FROM tb_estoque e WHERE e.produto = :produto", Estoque.class);
        query.setParameter("produto", produto);
        return query.getSingleResult();
    }

    @Transactional
    public void updateQuantidade(Long id, int novaQuantidade) {
        var em = jpaUtil.getEntityManager();
        em.getTransaction().begin();
        Estoque estoque = findById(id);
        if (estoque != null) {
            estoque.setQuantidade(novaQuantidade);
            em.merge(estoque);
        }
        em.getTransaction().commit();
    }

    @Transactional
    public void reduzirQuantidade(Long id, int quantidade) {
        var em = jpaUtil.getEntityManager();
        em.getTransaction().begin();
        Estoque estoque = findById(id);
        if (estoque != null && estoque.getQuantidade() >= quantidade) {
            estoque.setQuantidade(estoque.getQuantidade() - quantidade);
            em.merge(estoque);
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente no estoque.");
        }
        em.getTransaction().commit();
    }

    @Transactional
    public void delete(Long id) {
        var em = jpaUtil.getEntityManager();
        em.getTransaction().begin();
        Estoque estoque = findById(id);
        if (estoque != null) {
            em.remove(estoque);
        }
        em.getTransaction().commit();
    }

    public boolean isDisponivel(Long id) {
        Estoque estoque = findById(id);
        return estoque != null && estoque.getQuantidade() > 0;
    }
}
