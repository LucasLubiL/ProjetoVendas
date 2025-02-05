package modelo.DAO;

import connect.ConexaoFactor;
import modelo.dominio.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class PagamentoDAO {

    public void inserirPagamento(Pagamento pagamento) {
        try{
            
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "INSERT INTO pagamento(nome_tipo) values(?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, pagamento.getNomeTipo());
            
            stmt.execute();
            
            stmt.close();
            connection.close();
            
            System.out.println("Forma de pagamento cadastrada com sucesso!\n");
            
        } catch(Exception e){
            System.out.println("Erro ao inserir forma de pagamento\n");
        }
    }

    public List<Pagamento> listarPagamentos() {
        
        List<Pagamento> pagamentos= new ArrayList<Pagamento>();
        
        try{
            
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "SELECT * FROM pagamento";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamento pagamento = new Pagamento();
                 
                pagamento.setId_pagamento(rs.getInt("id_pag"));
                pagamento.setNomeTipo(rs.getString("nome_tipo"));
                 
                pagamentos.add(pagamento);
             
            }
            
            stmt.close();
            connection.close();
            
        }catch(Exception e){
            
            System.out.println("Erro ao deletar forma de pagemento\n");
            
        }
        
        return pagamentos;
    }

    public void excluirPagamento(int id) {
        try{
                
                
                Connection connection = new ConexaoFactor().getConnection();
                
                String sql = "SELECT usado FROM pagamento WHERE id_pag = ?";
                
                PreparedStatement stmt = connection.prepareStatement(sql);
                
                stmt.setInt(1, id);
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs.next()){
                    int seUsado = rs.getInt("usado");
             
                    if(seUsado == 1){
                        System.out.println("A forma de pagmento ja foi vinculada a uma venda, nao foi possivel deletar.\n");
                    }else{
                        
                        
                        String sql2 = "CALL delete_in_ID_pagamento(?)";

                        PreparedStatement stmt2 = connection.prepareStatement(sql2);
                
                        stmt2.setInt(1, id);
                
                        stmt2.executeUpdate();
                        
                        System.out.println("Forma de pagamento deletada com sucesso.\n");
                        
                        stmt2.close();
                    }
                }
                
                stmt.close();
                connection.close();
                
            } catch(Exception e){
                 System.out.println("Erro ao esolher a forma de pagmento para deletar\n");
            }
    }

    public void atualizarPagamento(Pagamento pagamento) {
        try{
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "Update pagamento set nome_tipo=? WHERE id_pag = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,pagamento.getNomeTipo());
            stmt.setInt(2, pagamento.getId_pagamento());
            
            stmt.execute();
            
            stmt.close();
            connection.close();
            
            System.out.println("Pagamento atualizado com sucesso!!!");
        
        } catch(Exception e){
            System.out.println("Erro ao atualizar pagamento\n");
        }
    }

    public int buscarMaxId() {
        int maiorId=0;
        
        try{
            Connection connection = new ConexaoFactor().getConnection();
            String sql = "SELECT max(id_pag) as id_pag FROM pagamento";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs= stmt.executeQuery();
               
            
            if (rs.next()) {
                maiorId=rs.getInt("id_pag");
            }
            
            connection.close();
            stmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return maiorId;
    }

    public List<Pagamento> relatorioVendasPorPagamento() {
        List<Pagamento> pagamentos = new ArrayList<>();
        
        try{
            Connection connection = new ConexaoFactor().getConnection();
            String sql = "SELECT * FROM relatorio_venda_pag";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Pagamento pagamento = new Pagamento();
                pagamento.setId_pagamento(rs.getInt("Id Pagamento"));
                pagamento.setNomeTipo(rs.getString("Nome"));
                pagamento.setQuantVendas(rs.getInt("Quantidade Vendas"));
                pagamentos.add(pagamento);
            }
            rs.close();
            connection.close();
            stmt.close();
        }catch(Exception e){
            System.out.println("Erro ao gerar relatório de Vendas por Pagamento");
        }
        
        
        return pagamentos;
    }
    
}
