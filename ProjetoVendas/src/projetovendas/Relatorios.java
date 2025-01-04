package projetovendas;

import java.util.Scanner;
import Connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Relatorios {
    
    Scanner rel = new Scanner(System.in);
    
    public void vendaEmitida(){
    
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "CALL relatorio_vendas(?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            System.out.print("Digite a Data Inicial (ano/mes/dia): ");
            String date1 = rel.nextLine();
            System.out.print("Digite a Data Final (ano/mes/dia): ");
            String date2 = rel.nextLine();
            
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            
            ResultSet rs = stmt.executeQuery();
             
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()){
            
                for(int i = 1; i <= columnCount; i++){
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    System.out.print(columnName + ": " + value + " | ");
                }
                System.out.println();  
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            
            connection.close();
            stmt.close();
            rs.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao gerar relatorio vendas emitidas.\n");
            return;
        }
    
    }
    
    public void vendaCancelada(){
    
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "CALL relatorio_vendas_canceladas(?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            System.out.print("Digite a Data Inicial (ano/mes/dia): ");
            String date1 = rel.nextLine();
            System.out.print("Digite a Data Final (ano/mes/dia): ");
            String date2 = rel.nextLine();
            
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            
            ResultSet rs = stmt.executeQuery();
             
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()){
            
                for(int i = 1; i <= columnCount; i++){
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    System.out.print(columnName + ": " + value + " | ");
                }
                System.out.println();  
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            
            connection.close();
            stmt.close();
            rs.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao gerar relatorio vendas emitidas.\n");
            return;
        }
        
    }
    
    public void vendaPagamento(){
        
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "SELECT * FROM relatorio_venda_pag";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
             
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()){
            
                for(int i = 1; i <= columnCount; i++){
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    System.out.print(columnName + ": " + value + " | ");
                }
                System.out.println();  
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            
            connection.close();
            stmt.close();
            rs.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao gerar relatorio vendas pagamento.\n");
            return;
        }
    
    }
    
    public void relatorioProduto(){
    
         try {
        
             ConexaoFactor conn = new ConexaoFactor();
             Connection connection = conn.getConnection();
             
             String sql = "SELECT * FROM relatorio_produto";
             PreparedStatement statement =  connection.prepareStatement(sql);
             
             ResultSet resultSet = statement.executeQuery();
             
             ResultSetMetaData metaData = resultSet.getMetaData();
             int columnCount = metaData.getColumnCount();
             
             System.out.println("\nRelatório de Produtos");
             System.out.println("--------------------------------------------------------------------------------------------------------------------------");
             while(resultSet.next()){
             
                 for(int i = 1; i <= columnCount; i++){
                     
                     String columnName = metaData.getColumnName(i);
                     String value = resultSet.getString(i);
                     if(value != null){
                        System.out.print(columnName + ": " + value + " | ");
                     }
                 }
                 System.out.println();
             }
             
             System.out.println("--------------------------------------------------------------------------------------------------------------------------");
             
             resultSet.close();
             statement.close();
             connection.close();
             
             return;
             
        } catch (Exception e){
        
            System.out.println("Erro ao gerar relatorio.");  
            return;
            
        }
    
    }
    
}
