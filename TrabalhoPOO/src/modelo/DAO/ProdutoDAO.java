package modelo.DAO;

import java.util.List;
import modelo.dominio.Produto;
import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class ProdutoDAO {

    public List<Produto> listaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        
        try {
        
             Connection connection = new ConexaoFactor().getConnection();
             
             String sql = "SELECT * FROM produto";
             PreparedStatement statement =  connection.prepareStatement(sql);
             
             ResultSet resultSet = statement.executeQuery();
             
             
             while(resultSet.next()){
                 Produto produto = new Produto();
                 produto.setId(resultSet.getInt("id_prod"));
                 produto.setNome(resultSet.getString("nome"));
                 produto.setPreco(resultSet.getDouble("preco"));
                 produto.setQuant(resultSet.getInt("quant"));
                 produto.setMarca(resultSet.getString("marca"));
                 produto.setCategoria(resultSet.getString("categoria"));
                 produto.setTamCamisa(resultSet.getString("tam_camisa"));
                 produto.setTamCalca(resultSet.getString("tam_calca"));
                 produto.setNumCalcado(resultSet.getInt("tam_calcado"));
                 produtos.add(produto);
                 
             }
             
             resultSet.close();
             statement.close();
             connection.close();
             
             
             
        } catch (Exception e){
        
            System.out.println("Erro ao gerar relatorio.");  
            
        }
        
        return produtos;
    }

    public void regitrarProduto(Produto produto) {
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "INSERT INTO produto(nome, preco, quant, marca, categoria, tam_camisa, tam_calca, tam_calcado) values(?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuant());
            stmt.setString(4, produto.getMarca());
            stmt.setString(5, produto.getCategoria());
            stmt.setString(6, produto.getTamCamisa());
            stmt.setString(7, produto.getTamCalca());
            stmt.setInt(8, produto.getNumCalcado());
            
            stmt.execute();
            
            connection.close();
            stmt.close();
            
            System.out.println("Produto inserido com sucesso!");
          
            
            
        } catch(Exception e){
            System.out.println("Erro ao inserir Produto.\n");
        }
    }

    
    public void atualizarEstoque(int idProduto, int quant) {
        try{
                
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
                
            String sql = "CALL att_estoque(?, ?)";
                
            PreparedStatement stmt = connection.prepareStatement(sql);
                
            stmt.setInt(1, idProduto);
            stmt.setInt(2, quant);
                
            stmt.executeUpdate();
                
            connection.close();
            stmt.close();
                
            System.out.println("Estoque atualizado com sucesso\n.");
                
        } catch(Exception e){
                System.out.println("Erro ao atualizar estoque do produto selecionado por ID.\n");
        }
    }

    public void excluirProduto(int idProd) {
        try{
                
                ConexaoFactor conn = new ConexaoFactor();
                Connection connection = conn.getConnection();
                
                String sql = "SELECT usado FROM produto WHERE id_prod = ?";
                
                PreparedStatement stmt = connection.prepareStatement(sql);
                
                stmt.setInt(1, idProd);
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs.next()){
                    int foiUsado = rs.getInt("usado");
             
                    if(foiUsado == 1){
                        System.out.println("O produto ja foi vinculado a uma venda, nao foi possivel deletar.\n");
                    }else{
                        
                        
                        String sql2 = "CALL delete_in_ID_produto(?)";

                        PreparedStatement stmt2 = connection.prepareStatement(sql2);
                
                        stmt2.setInt(1, idProd);
                
                        stmt2.executeUpdate();
                        
                        System.out.println("Produto deletado com sucesso.\n");
                        
                        stmt2.close();
                    }
                }
                
                stmt.close();
                connection.close();
                rs.close();
                
            } catch(Exception e){
                 System.out.println("Erro ao esolher o produto para deletar\n");
            }
    }

    public void atualizarProduto(Produto produto) {
        try{
            
                ConexaoFactor conn = new ConexaoFactor();
                Connection connection = conn.getConnection();

                String sql = "UPDATE produto set nome = ?, preco = ?, marca = ?, categoria = ?, tam_camisa = ?, tam_calca = ?, tam_calcado = ? WHERE id_prod = ?";

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getPreco());
                stmt.setString(3, produto.getMarca());
                stmt.setString(4, produto.getCategoria());
                stmt.setString(5, produto.getTamCamisa());
                stmt.setString(6, produto.getTamCalca());
                stmt.setInt(7, produto.getNumCalcado());
                stmt.setInt(8, produto.getId());

                stmt.execute();
                
                System.out.println("PRODUTO ATUALIZADO COM SUCESSO!");
            
                connection.close();
                stmt.close();
                
            } catch(Exception e){
                System.out.println("Erro ao atualizar o produto buscado por ID\n");
            }   
    }

    public Produto selecionarProduto(int id) {
        Produto produto = new Produto();
        
        try{
            String sql ="Select quant,preco from produto where id_prod=?";
            Connection connection = new ConexaoFactor().getConnection();
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                produto.setQuant(rs.getInt("quant"));
                produto.setPreco(rs.getDouble("preco"));
            }
            
            connection.close();
            stmt.close();
            rs.close();
            
        }catch(Exception e){
            System.out.println("Erro ao resgatar produto pelo id");
        }
        
        return produto;
    }
    
    
}
