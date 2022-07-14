package bless.leandro.Vendas.domain.repositorio;

import bless.leandro.Vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";

    @Transactional
    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return  cliente;
    }

    public List<Cliente> buscarRegistros(){
        return  jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public  Cliente mapRow(ResultSet resultSet,  int i) throws SQLException{
                return new Cliente(resultSet.getString("nome"));
            }
        });
    }

}
