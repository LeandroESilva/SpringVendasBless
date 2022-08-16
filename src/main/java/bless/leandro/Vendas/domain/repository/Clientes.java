package bless.leandro.Vendas.domain.repository;

import bless.leandro.Vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
public interface  Clientes extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos (@Param("id") Integer id);







    // EXEMPLOS------------------------------------------------------------------------------------------------
    //Query Metods do JpaRepository ===========================================================================
    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    boolean existsByNome(String nome);
    // ==========================================================================================================

    // Consultas por injeção de HQL =================================================================
    // ==============================================================================================
    @Query(value = "select c from clientes where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    @Query("delete from Cliente c where c.nome = :nome")
    @Modifying //utilização do hql necessita que seja utilizado a anotação quando for alterar a tabela
    void deleteByNome(String nome);


    // Metodos de persistencia por EntityMeneger ====================================================
    // ==============================================================================================
    /*@Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return  cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente){
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarRegistros(String nome){
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }*/
}
