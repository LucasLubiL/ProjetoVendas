package modelo.DAO;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */


import java.sql.Connection;
import connect.ConexaoFactor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.dominio.Login;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class LoginDAO {

    public Login verificarLogin(Login usuario) {
        Login usuario2 = new Login();
        
        try{
                   
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();

            String sql = "SELECT login, senha, cargo FROM usuario WHERE login = ? and senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, usuario.getUser());
            statement.setString(2, usuario.getSenha());

            ResultSet resultSet = statement.executeQuery();
            
            

            if (resultSet.next()) {
                
                String log = resultSet.getString("login");
                String sen = resultSet.getString("senha");
                String car = resultSet.getString("cargo");
                
                usuario2.setUser(log);
                usuario2.setSenha(sen);
                usuario2.setCargo(car);


            } 

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Erro de conexao login ");
        }
                
        
        return usuario2;
    }

    public void inserirUsuario(Login login) {
        try{
        
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "INSERT INTO usuario(id_func, login, senha, cargo) values(?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, login.getIdFuncionario());
            stmt.setString(2, login.getUser());
            stmt.setString(3, login.getSenha());
            stmt.setString(4, login.getCargo());

            stmt.execute();
            stmt.close();
            connection.close();
            
            
            
            System.out.println("Usuario cadastrado com sucesso!\n");
            
        }catch(Exception e){
            System.out.println("Erro ao cadastrar Usuario.");
        }
    }
   
}
