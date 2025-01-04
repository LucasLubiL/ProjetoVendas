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
public class Venda {
    
    Scanner ve = new Scanner(System.in);
    
    private int idCliente;
    private int quant;
    private double valor;
    private int idPag;
    private double precoUnitario;

    public Venda(){
         
        this.valor = 0.0;
        this.quant = 0;
        this.precoUnitario = 0.0;
    
    }
    
    public Venda(int quant, double valor, int idCliente, int idPag) {
        this.quant = quant;
        this.valor = valor;
        this.idCliente = idCliente;
        this.idPag = idPag;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdPag() {
        return idPag;
    }

    public void setIdPag(int idPag) {
        this.idPag = idPag;
    }
      
    public void realizarVenda(){
    
        ArrayList<VendaItens> ven = new ArrayList();
        
        int x = 1;
        int y, z;
        int cli;
              
        System.out.println("Selecione o(s) produto(s) para a venda\n\n.");
        
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
            
            System.out.print("ID produto (0 - voltar): ");
            x = ve.nextInt();
            if(x == 0){
                System.out.println("Voltando...\n");
                return;
            }
            System.out.println("Digite a quantidade desejada");
            y = ve.nextInt();
            
            while(x != 0){
        
                VendaItens venda = new VendaItens();
                
                String sql2 = "SELECT quant, preco FROM produto WHERE id_prod = ?";
                
                PreparedStatement stmt2 = connection.prepareStatement(sql2);
                
                stmt2.setInt(1, x);
                
                ResultSet rs2 = stmt2.executeQuery();
                
                if(rs2.next()){
                    z = rs2.getInt("quant");
                    if(z - y < 0){
                        System.out.println("Estoque insuficiente.\n");
                    }else{
                     
                        venda.setIdProd(x);
                        venda.setPreco(rs2.getDouble("preco"));
                        venda.setPrecoTotal(y * rs2.getDouble("preco"));
                        venda.setQuantItens(y);     
                        
                        ven.add(venda);
                        
                        this.quant ++;
                        this.valor += rs2.getDouble("preco") * y;
           
                    }
                }
                
                stmt2.close();
                rs2.close();
                
                System.out.print("ID produto: ");
                x = ve.nextInt();
                System.out.println("Digite a quantidade desejada");
                y = ve.nextInt();
                    
            }
            
            System.out.println("Selecao de produtos finalizada.\n");
            
            connection.close();
            stmt.close();
            rs.close();
            
            
            
        }catch(Exception e){
            System.out.println("Erro ao selecionar produto.\n");
        }
        
        try{
                    
            System.out.println("Digite o ID do cliente desejado.\n");
                    
            ConexaoFactor conn2 = new ConexaoFactor();
            Connection connection2 = conn2.getConnection();

            String sql3 = "SELECT id_cliente, nome_cliente, cpf FROM cliente";
                    
            PreparedStatement stmt3 = connection2.prepareStatement(sql3);
                    
            ResultSet rs3 = stmt3.executeQuery();

            ResultSetMetaData metaData2 = rs3.getMetaData();
            int columnCount2 = metaData2.getColumnCount();

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            while(rs3.next()){

                for(int i = 1; i <= columnCount2; i++){

                    String columnName2 = metaData2.getColumnName(i);
                    String value2 = rs3.getString(i);
                    if(value2 != null){
                        System.out.print(columnName2 + ": " + value2 + " | ");
                    }

                }
                System.out.println();

            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.print("ID: ");
            cli = ve.nextInt();
                    
            this.setIdCliente(cli);
                
            connection2.close();
            stmt3.close();
            rs3.close();
                             
        } catch(Exception e){
            System.out.println("Erro ao escolher cliente para a venda.\n");
        }
     
        try{
                    
            System.out.println("Digite o ID do pagamento desejado.\n");
                    
            ConexaoFactor conn3 = new ConexaoFactor();
            Connection connection3 = conn3.getConnection();

            String sql4 = "SELECT id_pag as 'Id Pagamento', nome_tipo as Nome FROM pagamento";
                    
            PreparedStatement stmt4 = connection3.prepareStatement(sql4);
                    
            ResultSet rs4 = stmt4.executeQuery();

            ResultSetMetaData metaData3 = rs4.getMetaData();
            int columnCount3 = metaData3.getColumnCount();

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            while(rs4.next()){

                for(int i = 1; i <= columnCount3; i++){

                    String columnName3 = metaData3.getColumnName(i);
                    String value3 = rs4.getString(i);
                    if(value3 != null){
                        System.out.print(columnName3 + ": " + value3 + " | ");
                    }

                }
                System.out.println();

            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.print("ID: ");
            cli = ve.nextInt();
                
            this.setIdPag(cli);
                
            connection3.close();
            stmt4.close();
            rs4.close();
                             
        } catch(Exception e){
             System.out.println("Erro ao escolher forma de pagamento para a venda.\n");
        }
            
        try{
        
            ConexaoFactor conn4 = new ConexaoFactor();
            Connection connection4 = conn4.getConnection();
            
            String sql5 = "INSERT INTO venda(id_cliente, quant_prod, valor, id_pag) values(?, ?, ?, ?)";
            
            PreparedStatement stmt5 = connection4.prepareStatement(sql5);
            
            stmt5.setInt(1, this.getIdCliente());
            stmt5.setInt(2, this.getQuant());
            stmt5.setDouble(3, this.getValor());
            stmt5.setInt(4, this.getIdPag());
            
            stmt5.execute();
            
            System.out.println("Venda realizada com sucesso!\n");
            
            connection4.close();
            stmt5.close();
            
        } catch(Exception e){
            System.out.println("Erro ao realizar a venda.\n");
        }
        
        try{
        
            ConexaoFactor conn5 = new ConexaoFactor();
            Connection connection5 = conn5.getConnection();
             
            String sql6 = "INSERT INTO venda_itens(id_venda, id_prod, preco, preco_total, quant_itens) values(?, ?, ?, ?, ?)";
            String sql7 = "SELECT MAX(id_venda) FROM venda";
             
            PreparedStatement stmt6 = connection5.prepareStatement(sql6);
            PreparedStatement stmt7 = connection5.prepareStatement(sql7);
             
            ResultSet rs = stmt7.executeQuery();
             
            int id = 0;
            
            if(rs.next()){
                id = rs.getInt(1);
            }
             
            for(VendaItens aux : ven){
             
                stmt6.setInt(1, id);
                stmt6.setInt(2, aux.getIdProd());
                stmt6.setDouble(3, aux.getPreco());
                stmt6.setDouble(4, aux.getPrecoTotal());
                stmt6.setInt(5, aux.getQuantItens());
                
                stmt6.execute();
             
            }
            
            stmt6.close();
            connection5.close();
            rs.close();
            stmt7.close();
            
            System.out.println("Venda_Itens sucesso.\n");
        
        } catch(Exception e){
            System.out.println("Erro ao inserir venda_itens da venda.\n");
        }
    }
    
    public void consultarVenda(){
    
        System.out.print("Digite a Data Inicial (ano/mes/dia): ");
        String date1 = ve.nextLine();
        System.out.print("Digite a Data Final (ano/mes/dia): ");
        String date2 = ve.nextLine();
        
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "CALL relatorio_vendas(?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            
            ResultSet rs = stmt.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            System.out.println("\nRelatório de Vendas");
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

            stmt.close();
            rs.close();
            connection.close();
            
        } catch(Exception e){
            System.out.println("Erro ao consultar venda.\n");
        }
    
    }
    
    public void cancelarVenda(){
    
        System.out.println("Selecione o ID da venda para cancelar.\n");
        
        try{
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "UPDATE venda SET status = 'Cancelado' WHERE id_venda = ?";
            String sql2 = "SELECT * FROM venda WHERE status = 'Emitido'";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            
            ResultSet rs = stmt2.executeQuery();
            
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
            int x = ve.nextInt();
            
            stmt.setInt(1, x);
            
            stmt.execute();
            
            System.out.println("Venda Cancelada com sucesso.\n");
            
            stmt.close();
            stmt2.close();
            rs.close();
            connection.close();
            
        } catch(Exception e){
            System.out.println("Erro ao cancelar venda.\n");
            
        }
    
    }
    
}

