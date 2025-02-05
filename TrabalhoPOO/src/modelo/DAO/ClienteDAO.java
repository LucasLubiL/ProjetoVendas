package modelo.DAO;


import connect.ConexaoFactor;
import modelo.dominio.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class ClienteDAO {
   
    public void inserirCliente(Cliente cliente) {
        
        try {
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "INSERT INTO cliente(nome_cliente, cpf, endereco, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.execute();
            
            
            connection.close();
            stmt.close();
            
            System.out.println("Cliente cadastrado com sucesso!!!");
            
        } catch (Exception e) {
            System.out.println("Falha ao inserir cliente");
        }
    }

    public void atualizarCliente(Cliente cliente) {
        
        try {
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "UPDATE cliente SET nome_cliente = ?, cpf = ?, endereco = ?, telefone = ? WHERE id_cliente = ?";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
           
            
            connection.close();
            stmt.close();
            
            System.out.println("Cliente atualizado com sucesso");
            
        } catch (Exception e) {
            System.out.println("Falha ao atualizar cliente");
        }
    }

    public List<Cliente> listarClientes() {
        
        List<Cliente> clientes = new ArrayList<>();
        try{
            Connection connection = new ConexaoFactor().getConnection();
            String sql = "SELECT * FROM cliente";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs= stmt.executeQuery();
               
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                
                clientes.add(cliente);
            }
            
            connection.close();
            stmt.close();
            rs.close();
            
        } catch (Exception e) {
            System.out.println("Falha ao listar clientes");
        }
        return clientes;
    }

    public int buscarMaxId() {
        int maiorId=0;
        
        try{
            Connection connection = new ConexaoFactor().getConnection();
            String sql = "SELECT max(id_cliente) as id_cliente FROM cliente";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs= stmt.executeQuery();
               
            
            if (rs.next()) {
                maiorId=rs.getInt("id_cliente");
            }
            
            connection.close();
            stmt.close();
            rs.close();
            
        } catch (Exception e) {
            System.out.println("Falha na busca do ID do cliente");
        }
        
        return maiorId;
    }
    
}