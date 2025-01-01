package projetovendas;

import java.util.Scanner;
import Connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Cliente extends Pessoa{
    
    private int x;
    
    public Cliente(){};

    public Cliente(String nome, String cpf, String endereco, String telefone) {
        super(nome, cpf, endereco, telefone);
    }
    
    Scanner cli = new Scanner(System.in);
    
    public void inserirCliente(){
    
        System.out.println("Digite os dados do Cliente");
        System.out.print("Nome: ");
        super.setNome(cli.nextLine());
        System.out.print("CPF: ");
        super.setCpf(cli.nextLine());
        System.out.print("Endereco: ");
        super.setEndereco(cli.nextLine());
        System.out.print("Telefone(OPCIONAL): ");
        super.setTelefone(cli.nextLine());

        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "INSERT INTO cliente(nome_cliente, cpf, endereco, telefone) values(?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, super.getNome());
            stmt.setString(2, super.getCpf());
            stmt.setString(3, super.getEndereco());
            stmt.setString(4, super.getTelefone());
            
            stmt.execute();
            
            System.out.println("Cliente cadastrado com sucesso!");
            
            connection.close();
            stmt.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao inserir Cliente");
            return;
        }
        
    }
    
    public void atualizarCliente(){
    
        try{
        
            System.out.println("\nA partir do ID do cliente, selecione qual voce deseja atualizar:");
            
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sql = "SELECT * FROM cliente";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet resultSet = stmt.executeQuery();
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
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

            System.out.print("ID: ");
            x = cli.nextInt();
            cli.nextLine();
            
            System.out.println("Digite os dados atualizados do Cliente");
            System.out.print("Nome: ");
            super.setNome(cli.nextLine());
            System.out.print("CPF: ");
            super.setCpf(cli.nextLine());
            System.out.print("Endereco: ");
            super.setEndereco(cli.nextLine());
            System.out.print("Telefone(OPCIONAL): ");
            super.setTelefone(cli.nextLine());
            
            try{
            
                ConexaoFactor conn2 = new ConexaoFactor();
                Connection connection2 = conn2.getConnection();
                
                String sql2 = "UPDATE cliente SET nome_cliente = ?, cpf = ?, endereco = ?, telefone = ? WHERE id_cliente = ?";
                
                PreparedStatement stmt2 = connection.prepareStatement(sql2);
                
                stmt2.setString(1, super.getNome());
                stmt2.setString(2, super.getCpf());
                stmt2.setString(3, super.getEndereco());
                stmt2.setString(4, super.getTelefone());
                stmt2.setInt(5, x);
                
                stmt2.execute();
                
                stmt2.close();
                connection2.close();
                
                System.out.println("Cliente atualizado com sucesso!");
            
            } catch(Exception e){
                System.out.println("Erro ao inserir os dados atualizados no banco");
            }
            
            stmt.close();
            resultSet.close();
            connection.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao atualizar Cliente.");
            return;
        }
        
    }
    
}
