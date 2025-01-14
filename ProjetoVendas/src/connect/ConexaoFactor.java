package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class ConexaoFactor {
    
    public Connection getConnection(){
        
        try{
        
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vendas_empresa", "root", "66uca_L#");
            
        } catch (SQLException e){
        
            JOptionPane.showMessageDialog(null, "Erro de conexao");
            return null;
            
        }
            
    };
    
}
