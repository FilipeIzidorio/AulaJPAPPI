package dao;

import domain.Pessoa;
import domain.Produto;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import persistence.JPAUtil;

@RequiredArgsConstructor


public class PessoaDAO {

    private final JPAUtil jpaUtil;

    public PessoaDAO() {
        jpaUtil = new JPAUtil();


    }

    public void save (Pessoa pessoa){
        jpaUtil.getEntityManager().getTransaction().begin(); //abrir a transação com o banco
        jpaUtil.getEntityManager().persist(pessoa); //
        jpaUtil.getEntityManager().getTransaction().commit(); // confimar a transação  e salva
        jpaUtil.getEntityManager().close();


    }
    public Pessoa findById(Long id){
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Pessoa.class,id);

    }

    public void closeEntityManager(){
        jpaUtil.getEntityManager().close();
    }

}
