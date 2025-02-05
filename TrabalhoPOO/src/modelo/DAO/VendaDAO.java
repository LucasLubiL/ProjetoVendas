package modelo.DAO;

import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.dominio.RelatorioVendasPorData;
import modelo.dominio.Venda;
import modelo.dominio.Venda_itens;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class VendaDAO {

    public void inserirVenda(Venda venda,List<Venda_itens> itens) {
        try{
            
            Connection connection = new ConexaoFactor().getConnection();
            String sql= "INSERT INTO venda(id_cliente,quant_prod,valor,id_pag) values (?,?,?,?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getQuant());
            stmt.setDouble(3, venda.getValor());
            stmt.setInt(4,venda.getIdPag());
            
            int linhasAfetadas = stmt.executeUpdate();
            int idGerado=0;

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGerado = rs.getInt(1); 
                    }
                }
            }
            stmt.close();

            
            
            
            sql= "INSERT INTO venda_itens(id_venda,id_prod,preco,preco_total,quant_itens) values (?,?,?,?,?)";
            PreparedStatement stmt2 = connection.prepareStatement(sql);
            
            stmt2.setInt(1, idGerado);
            
            for(Venda_itens v: itens){
                
                stmt2.setInt(2, v.getIdProd());
                stmt2.setDouble(3, v.getPreco());
                stmt2.setDouble(4, v.getPrecoTotal());
                stmt2.setInt(5, v.getQuantItens());
                stmt2.execute();
            }
            
            
            stmt2.close();
            connection.close();
            System.out.println("\nVenda Realizada com sucesso!!!\n");
        }catch(Exception e){
            System.out.println("Erro ao inserir a venda!!!");
        }
    }

    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        
        try {
        
             Connection connection = new ConexaoFactor().getConnection();
             
             String sql = "SELECT * FROM venda where status = 'Emitido'";
             PreparedStatement stmt =  connection.prepareStatement(sql);
             
             ResultSet rs = stmt.executeQuery();
             
             
             while(rs.next()){
                 Venda venda = new Venda();
                 venda.setIdVenda(rs.getInt("id_venda"));
                 venda.setIdPag(rs.getInt("id_pag"));
                 venda.setIdCliente(rs.getInt("id_cliente"));
                 venda.setQuant(rs.getInt("quant_prod"));
                 venda.setValor(rs.getDouble("valor"));
                 vendas.add(venda);
                 
             }
             
             rs.close();
             stmt.close();
             connection.close();
             
             
             
        } catch (Exception e){
        
            System.out.println("Erro ao gerar relatorio.");  
            
        }
        
        return vendas;
    }

    public void cancelarVenda(int id) {
        try{
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "UPDATE venda SET status = 'Cancelado' WHERE id_venda = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.execute();

            stmt.close();
            connection.close();
            System.out.println("Venda Cancelada com sucesso.\n");
            
        }catch(Exception e){
            System.out.println("Falha no cancelamento da venda");
        }
    }

    public List<RelatorioVendasPorData> relatorioEmitidas(String date1, String date2) {
        List<RelatorioVendasPorData> vendas = new ArrayList<>();
        
        try{
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "CALL relatorio_vendas(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            
            ResultSet rs = stmt.executeQuery();
            
             while(rs.next()){
                 RelatorioVendasPorData venda = new RelatorioVendasPorData();
                 venda.setIdVenda(rs.getInt("id_venda"));
                 venda.setNome_cliente(rs.getString("nome_cliente"));
                 venda.setNome_tipo(rs.getString("nome_tipo"));
                 venda.setQuant_prod(rs.getInt("quant_prod"));
                 venda.setValor(rs.getDouble("valor"));
                 vendas.add(venda);
                 
             }
             rs.close();
             stmt.close();
             connection.close();
            
        }catch(Exception e){
            System.out.println("Erro ao gerar relatório");
        }
        
        return vendas;
    }

    public List<RelatorioVendasPorData> relatorioCanceladas(String date1, String date2) {
        List<RelatorioVendasPorData> vendas = new ArrayList<>();
        
        try{
            Connection connection = new ConexaoFactor().getConnection();
            
            String sql = "CALL relatorio_vendas_canceladas(?, ?)";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            
            ResultSet rs = stmt.executeQuery();
            
             while(rs.next()){
                 RelatorioVendasPorData venda = new RelatorioVendasPorData();
                 venda.setIdVenda(rs.getInt("id_venda"));
                 venda.setNome_cliente(rs.getString("nome_cliente"));
                 venda.setNome_tipo(rs.getString("nome_tipo"));
                 venda.setQuant_prod(rs.getInt("quant_prod"));
                 venda.setValor(rs.getDouble("valor"));
                 vendas.add(venda);
                 
             }
            rs.close();
            stmt.close();
            connection.close();
            
        }catch(Exception e){
            System.out.println("Erro ao gerar relatório");
        }
        
        return vendas;
    }
    
}
