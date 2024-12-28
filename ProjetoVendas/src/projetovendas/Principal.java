package projetovendas;

import Connect.ConexaoFactor;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class Principal {

    public static void main(String[] args) {
        
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            conn.getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
            
        } catch(Exception e){
            
             System.out.println("Erro de conexão com o banco (Main)!");
            
        }
        
    }
    
}
