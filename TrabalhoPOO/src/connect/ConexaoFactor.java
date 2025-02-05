package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class ConexaoFactor {
    
    public Connection getConnection(){
        
        try{
        
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vendas_empresa", "Administrador", "Administrador132@");
            
        } catch (SQLException e){
        
            JOptionPane.showMessageDialog(null, "Erro de conexao");
            return null;
            
        }
            
    };
    
}
