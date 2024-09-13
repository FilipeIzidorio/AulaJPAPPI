package dao;

import domain.Cardapio;
import persistence.JPAUtil;

public class CardapioDAO {
    private final JPAUtil jpaUtil;

    public CardapioDAO() {
        jpaUtil = new JPAUtil();

    }

    public void save (Cardapio cardapio){
        jpaUtil.getEntityManager().getTransaction().begin(); //abrir a transação com o banco
        jpaUtil.getEntityManager().merge(cardapio); //
        jpaUtil.getEntityManager().getTransaction().commit(); // confimar a transação  e salva
        jpaUtil.getEntityManager().close();
    }

    public void update (Cardapio cardapio){

        jpaUtil.getEntityManager().merge(cardapio); //
        jpaUtil.getEntityManager().getTransaction().commit(); // confimar a transação  e salva
        jpaUtil.getEntityManager().close();
    }

    public Cardapio cardapio(long id){
        jpaUtil.getEntityManager().getTransaction().begin(); //abrir a transação com o banco
        return jpaUtil.getEntityManager().find(Cardapio.class, id); //


    }


}
