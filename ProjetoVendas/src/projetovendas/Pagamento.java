package projetovendas;

import java.util.Scanner;
import Connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.*;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Pagamento {
    
    Scanner pag = new Scanner(System.in);
    
    private String nomeTipo;

    public Pagamento(){};
    
    public Pagamento(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
    
    public void inserirPagamento(){
    
        System.out.print("Digite o nome do novo Pagamento:");
        this.setNomeTipo(pag.nextLine());
        
        try{
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "INSERT INTO pagamento(nome_tipo) values(?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, getNomeTipo());
            
            stmt.execute();
            
            stmt.close();
            connection.close();
            
            System.out.println("Forma de pagamento cadastrada com sucesso!\n");
            
        } catch(Exception e){
            System.out.println("Erro ao inserir forma de pagamento\n");
        }
        
    }
    
    public void deletarPagamento(){
        
        try{
        
            System.out.println("Selecione uma das seguintes formas de pagamento para deletar:");
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "SELECT * FROM pagamento";
            
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
            
            System.out.print("ID: ");
            int x = pag.nextInt();
            
            try{
                
                ConexaoFactor conn2 = new ConexaoFactor();
                Connection connection2 = conn2.getConnection();
                
                String sql2 = "SELECT usado FROM pagamento WHERE id_pag = ?";
                
                PreparedStatement stmt2 = connection2.prepareStatement(sql2);
                
                stmt2.setInt(1, x);
                
                ResultSet rs2 = stmt2.executeQuery();
                
                if(rs2.next()){
                    int x2 = rs2.getInt("usado");
             
                    if(x2 == 1){
                        System.out.println("A forma de pagmento ja foi vinculada a uma venda, nao foi possivel deletar.\n");
                    }else{
                        System.out.println("Forma de pagamento deletada com sucesso.\n");
                        
                        String sql3 = "CALL delete_in_ID_pagamento(?)";

                        PreparedStatement stmt3 = connection2.prepareStatement(sql3);
                
                        stmt3.setInt(1, x);
                
                        stmt3.executeUpdate();
                        
                        stmt3.close();
                    }
                }
                
                stmt2.close();
                connection2.close();
                
            } catch(Exception e){
                 System.out.println("Erro ao esolher a forma de pagmento para deletar\n");
            }
            
            stmt.close();
            rs.close();
            connection.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao deletar forma de pagemento\n");
            return;
        }
    
    }
    
}
