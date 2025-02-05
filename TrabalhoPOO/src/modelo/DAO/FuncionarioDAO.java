package modelo.DAO;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

import connect.ConexaoFactor;
import modelo.dominio.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class FuncionarioDAO {

    public int inserirFuncionario(Funcionario funcionario) {
        int idGerado = -1; 
        try (Connection connection = new ConexaoFactor().getConnection()) {
            String sql = "INSERT INTO funcionario(nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEndereco());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getCargo());
            stmt.setString(6, funcionario.getNasc());
            stmt.setString(7, funcionario.getTelefone());


            int linhasAfetadas = stmt.executeUpdate();


            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGerado = rs.getInt(1); 
                    }
                }
            }
            stmt.close();
            System.out.println("Funcionário cadastrado com sucesso. ID: " + idGerado);
            
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar o funcionário");
        }
        
        return idGerado; // Retorna o ID gerado
    }


    public void atualizarFuncionario(Funcionario funcionario) {
        try (Connection connection = new ConexaoFactor().getConnection()) {
            String sql = "UPDATE funcionario SET nome_func = ?, cpf = ?, endereco = ?, salario = ?, cargo = ?, data_nasc = ?, telefone = ? WHERE id_func = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEndereco());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getCargo());
            stmt.setString(6, funcionario.getNasc());
            stmt.setString(7, funcionario.getTelefone());
            stmt.setInt(8, funcionario.getId());

            stmt.execute();
            stmt.close();
            System.out.println("Funcionário atualizado com sucesso");
        }catch(Exception e){
            System.out.println("Não foi possível atualizar o funcionário");
        }
    }

    public void deletarFuncionario(int idFuncionario){
        try (Connection connection = new ConexaoFactor().getConnection()) {
            
            String sql = "CALL delete_in_ID_usuario(?)";
            String sqlDeleteFunc = "CALL delete_in_ID_funcionario(?)";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement stmt = connection.prepareStatement(sqlDeleteFunc);
            
            
            statement.setInt(1, idFuncionario);
            stmt.setInt(1, idFuncionario);
            
            statement.executeUpdate();
            stmt.executeUpdate();
            
            statement.close();
            stmt.close();
            System.out.println("Funcionário deletado com sucesso");
        }catch(Exception e){
            System.out.println("Não foi possível deletar o funcionário");
        }
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = new ConexaoFactor().getConnection()) {
            
            String sql = "SELECT * FROM funcionario";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id_func"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setNasc(rs.getString("data_nasc"));
                funcionario.setNome(rs.getString("nome_func"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setTelefone(rs.getString("telefone"));
                
                funcionarios.add(funcionario);
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            System.out.println("Não foi possível listar os funcionário");
        }
        return funcionarios;
    }
}

