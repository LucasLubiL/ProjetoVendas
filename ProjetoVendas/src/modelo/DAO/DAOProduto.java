package projetovendas;

import java.util.Scanner;
import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Produto {
    
    Scanner pro = new Scanner(System.in);
    
    public void inserirProduto(){
    
        System.out.println("Digite os dados do Produto:");
        System.out.print("Nome: ");
        this.setNome(pro.nextLine());
        System.out.print("Preco: ");
        this.setPreco(pro.nextDouble());
        System.out.print("Quantidade: ");
        this.setQuant(pro.nextInt());
        pro.nextLine();
        System.out.print("Marca: ");
        this.setMarca(pro.nextLine());
        System.out.print("Categoria(calçado, top, bottom): ");
        this.setCategoria(pro.nextLine());
        
        while(!this.getCategoria().equals("top") && !this.getCategoria().equals("calçado") && !this.getCategoria().equals("bottom")){
            System.out.println("Categoria Invalida, digite novamente (calçado, top, bottom)");
            this.setCategoria(pro.nextLine());
        }
        
        if(this.getCategoria().equals("calçado")){
            System.out.print("Numero Calcado: ");
            this.setNumCalcado(pro.nextInt());
        }else if(this.getCategoria().equals("top")){
            System.out.print("Tamanho camisa: ");
            this.setTamCamisa(pro.nextLine());
        }else if(this.getCategoria().equals("bottom")){
            System.out.print("Tamanho calca: ");
            this.setTamCalca(pro.nextLine());
        }
        
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "INSERT INTO produto(nome, preco, quant, marca, categoria, tam_camisa, tam_calca, tam_calcado) values(?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, this.getNome());
            stmt.setDouble(2, this.getPreco());
            stmt.setInt(3, this.getQuant());
            stmt.setString(4, this.getMarca());
            stmt.setString(5, this.getCategoria());
            stmt.setString(6, this.getTamCamisa());
            stmt.setString(7, this.getTamCalca());
            stmt.setInt(8, this.getNumCalcado());
            
            stmt.execute();
            
            connection.close();
            stmt.close();
            
            System.out.println("Produto inserido com sucesso!");
            System.out.println("Nome: " + this.getNome() + " | Preco: " + this.getPreco() + " | Quantidade: " + this.getQuant() + " | Marca: " + this.getMarca() + " | Categoria: " + this.getCategoria() + " | Tamanho Camisa: " + this.getTamCamisa() + " | Tamanho Calca: " + this.getTamCalca() + " | Numero Calcado: " + this.getNumCalcado() + "\n");
          
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao inserir Produto.\n");
            return;
        }
        
    }
    
    public void atualizarProduto(){
    
        try{
            
            System.out.println("\nA partir do ID do produto, selecione qual voce deseja atualizar:");
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "SELECT * FROM produto";
            
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
            int x = pro.nextInt();
            pro.nextLine();
            
            System.out.print("Nome: ");
            this.setNome(pro.nextLine());
            System.out.print("Preco: ");
            this.setPreco(pro.nextDouble());
            pro.nextLine();
            System.out.print("Marca: ");
            this.setMarca(pro.nextLine());
            System.out.print("Categoria(calçado, top, bottom): ");
            this.setCategoria(pro.nextLine());

            while(!this.getCategoria().equals("top") && !this.getCategoria().equals("calçado") && !this.getCategoria().equals("bottom")){
                System.out.println("Categoria Invalida, digite novamente (calçado, top, bottom)");
                this.setCategoria(pro.nextLine());
            }

            if(this.getCategoria().equals("calçado")){
                System.out.print("Numero Calcado: ");
                this.setNumCalcado(pro.nextInt());
            }else if(this.getCategoria().equals("top")){
                System.out.print("Tamanho camisa: ");
                this.setTamCamisa(pro.nextLine());
            }else if(this.getCategoria().equals("bottom")){
                System.out.print("Tamanho calca: ");
                this.setTamCalca(pro.nextLine());
            }
            
            try{
            
                ConexaoFactor conn2 = new ConexaoFactor();
                Connection connection2 = conn2.getConnection();

                String sql2 = "UPDATE produto set nome = ?, preco = ?, marca = ?, categoria = ?, tam_camisa = ?, tam_calca = ?, tam_calcado = ? WHERE id_prod = ?";

                PreparedStatement stmt2 = connection2.prepareStatement(sql2);

                stmt2.setString(1, this.getNome());
                stmt2.setDouble(2, this.getPreco());
                stmt2.setString(3, this.getMarca());
                stmt2.setString(4, this.getCategoria());
                stmt2.setString(5, this.getTamCamisa());
                stmt2.setString(6, this.getTamCalca());
                stmt2.setInt(7, this.getNumCalcado());
                stmt2.setInt(8, x);

                stmt2.execute();
                
                System.out.println("PRODUTO ATUALIZADO COM SUCESSO!");
                System.out.println("Nome: " + this.getNome() + " | Preco: " + this.getPreco() + " | Marca: " + this.getMarca() + " | Categoria: " + this.getCategoria() + " | Tamanho Camisa: " + this.getTamCamisa() + " | Tamanho Calca: " + this.getTamCalca() + " | Numero Calcado: " + this.getNumCalcado() + "\n");
            
                connection2.close();
                stmt2.close();
                
            } catch(Exception e){
                System.out.println("Erro ao atualizar o produto buscado por ID\n");
            }   
                
            connection.close();
            stmt.close();
            rs.close();
            
        } catch(Exception e){
            System.out.println("Erro ao atualizar produto.\n");
            return;
        }
    
    }
    
    public void deleteProduto(){
    
        System.out.println("Escolha o ID do produto para deletar.");
    
        try{
            
            System.out.println("\nA partir do ID do produto, selecione qual voce deseja atualizar:");
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "SELECT * FROM produto";
            
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
            int x = pro.nextInt();
            pro.nextLine();
                
            try{
                
                ConexaoFactor conn2 = new ConexaoFactor();
                Connection connection2 = conn2.getConnection();
                
                String sql2 = "SELECT usado FROM produto WHERE id_prod = ?";
                
                PreparedStatement stmt2 = connection2.prepareStatement(sql2);
                
                stmt2.setInt(1, x);
                
                ResultSet rs2 = stmt2.executeQuery();
                
                if(rs2.next()){
                    int x2 = rs2.getInt("usado");
             
                    if(x2 == 1){
                        System.out.println("O produto ja foi vinculado a uma venda, nao foi possivel deletar.\n");
                    }else{
                        System.out.println("Produto deletado com sucesso.\n");
                        
                        String sql3 = "CALL delete_in_ID_produto(?)";

                        PreparedStatement stmt3 = connection2.prepareStatement(sql3);
                
                        stmt3.setInt(1, x);
                
                        stmt3.executeUpdate();
                        
                        stmt3.close();
                    }
                }
                
                stmt2.close();
                connection2.close();
                rs2.close();
                
            } catch(Exception e){
                 System.out.println("Erro ao esolher o produto para deletar\n");
            }
            
            connection.close();
            stmt.close();
            rs.close();
            
        } catch(Exception e){
            System.out.println("Erro ao deletar o produto.\n");
        }
        
    }
    
    public void atualizarEstoque(){
    
        System.out.println("Selecione o ID do produto para atualizar o estoque:");
        
        try{
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "SELECT * FROM relatorio_produto";
            
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
            int x = pro.nextInt();
            System.out.print("Quantidade: ");
            int y = pro.nextInt();
            
            try{
                
                ConexaoFactor conn2 = new ConexaoFactor();
                Connection connection2 = conn2.getConnection();
                
                String sql2 = "CALL att_estoque(?, ?)";
                
                PreparedStatement stmt2 = connection2.prepareStatement(sql2);
                
                stmt2.setInt(1, x);
                stmt2.setInt(2, y);
                
                stmt2.executeUpdate();
                
                connection2.close();
                stmt2.close();
                
                System.out.println("Estoque atualizado com sucesso\n.");
                
            } catch(Exception e){
                System.out.println("Erro ao atualizar estoque do produto selecionado por ID.\n");
            }
            
            connection.close();
            stmt.close();
            rs.close();
            
        } catch(Exception e){
            System.out.println("Erro ao atualizar estoque do produto.\n");
        }
        
    }
      
}
