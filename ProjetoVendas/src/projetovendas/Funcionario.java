package projetovendas;

import java.util.Scanner;
import Connect.ConexaoFactor;
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
public class Funcionario extends Pessoa{
    
    Scanner fun = new Scanner(System.in);
    
    private double salario;
    private String cargo;
    private String nasc;
    private String login,senha;

    public Funcionario(){}
    
    public Funcionario(double salario, String cargo, String nasc, String nome, String cpf, String endereco, String telefone) {
        super(nome, cpf, endereco, telefone);
        this.salario = salario;
        this.cargo = cargo;
        this.nasc = nasc;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNasc() {
        return nasc;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void inserirFuncionario(){
    
        System.out.println("Preenchimento do Funcionario:");
        System.out.print("Nome: ");
        super.setNome(fun.nextLine());
        System.out.print("CPF: ");
        super.setCpf(fun.nextLine());
        System.out.print("Endereco: ");
        super.setEndereco(fun.nextLine());
        System.out.print("Telefone(OPCIONAL): ");
        super.setTelefone(fun.nextLine());
        System.out.print("Cargo: ");
        this.setCargo(fun.nextLine());
        System.out.print("Salario: ");
        this.setSalario(fun.nextDouble());
        fun.nextLine();
        System.out.print("Data de Nascimento: ");
        this.setNasc(fun.nextLine());
        
        LocalDate localDate = LocalDate.parse(this.nasc);
        Date sqlDate = Date.valueOf(localDate);
        
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sqlFunc = "INSERT INTO funcionario(nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) values(?, ?, ?, ?, ? ,? ,?)";
            
            PreparedStatement stmp = connection.prepareStatement(sqlFunc);
            
            stmp.setString(1, getNome());
            stmp.setString(2, getCpf());
            stmp.setString(3, getEndereco());
            stmp.setDouble(4, getSalario());
            stmp.setString(5, getCargo());
            stmp.setDate(6, sqlDate);
            stmp.setString(7, getTelefone());
            
            stmp.execute();
            
            stmp.close();
            connection.close();
            
        } catch(Exception e){
            System.out.println("Erro ao cadastrar funcionario no banco.");
            return;
        }
        
        System.out.println("\nCriacao do Usuario:");
        System.out.print("Login: ");
        this.login = fun.nextLine();
        System.out.print("Senha: ");
        this.senha = fun.nextLine();
        
        try{
        
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
            
            String sqlUser = "INSERT INTO usuario(id_func, login, senha, cargo) values(?, ?, ?, ?)";
            String sqlBusca = "SELECT id_func FROM funcionario WHERE cpf = ?";
            
            PreparedStatement stmt = connection.prepareStatement(sqlUser);
            PreparedStatement stmt2 = connection.prepareStatement(sqlBusca);
            
            stmt2.setString(1, getCpf());
            
            ResultSet resultSet2 = stmt2.executeQuery();
            
            if(resultSet2.next()){
               
               int id = resultSet2.getInt("id_func");
               stmt.setInt(1, id);
               stmt.setString(2, getLogin());
               stmt.setString(3, getSenha());
               stmt.setString(4, getCargo());

               stmt.execute();
               
            }else{
                System.out.println("Erro ao cadastrar Usuario.");
            }
            
            resultSet2.close();
            stmt.close();
            stmt2.close();
            connection.close();
            
            System.out.println("Funcionario e Usuario inserido com sucesso!\n");
            
            return;
                   
        }catch(Exception e){
            System.out.println("Erro ao criar Usuario.");
            return;
        }

    } 
    
    public void atualizarFuncionario(){
          
        try{
          
            System.out.println("\nA partir do ID do funcionario, selecione qual voce deseja atualizar:");
              
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
              
            String sql = "Select id_func, cpf, nome_func, cargo FROM funcionario";
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
            int x = fun.nextInt();
            fun.nextLine();
            
            System.out.println("Atualizacao do Funcionario:");
            System.out.print("Nome: ");
            super.setNome(fun.nextLine());
            System.out.print("CPF: ");
            super.setCpf(fun.nextLine());
            System.out.print("Endereco: ");
            super.setEndereco(fun.nextLine());
            System.out.print("Telefone(OPCIONAL): ");
            super.setTelefone(fun.nextLine());
            System.out.print("Cargo: ");
            this.setCargo(fun.nextLine());
            System.out.print("Salario: ");
            this.setSalario(fun.nextDouble());
            fun.nextLine();
            System.out.print("Data de Nascimento: ");
            this.setNasc(fun.nextLine());

            LocalDate localDate = LocalDate.parse(this.nasc);
            Date sqlDate = Date.valueOf(localDate);
            
            try{
                
                ConexaoFactor conn2 = new ConexaoFactor();
                Connection connection2 = conn2.getConnection();
                
                String sql2 = "UPDATE funcionario set nome_func = ?, cpf = ?, endereco = ?, salario = ?, cargo = ?, data_nasc = ?, telefone = ? WHERE id_func = ?";
  
                PreparedStatement stmt2 = connection2.prepareStatement(sql2);
                
                stmt2.setString(1, getNome());
                stmt2.setString(2, getCpf());
                stmt2.setString(3, getEndereco());
                stmt2.setDouble(4, getSalario());
                stmt2.setString(5, getCargo());
                stmt2.setDate(6, sqlDate);
                stmt2.setString(7, getTelefone());
                stmt2.setInt(8, x);
                
                stmt2.execute();

                stmt2.close();
                connection2.close();
                
            } catch(Exception e){
                System.out.println("Erro ao inserir dados atualizados Funcionario");
                return;
            }
            
            connection.close();
            resultSet.close();
            stmt.close();
            
            System.out.println("ATUALIZACAO CONCLUIDA COM SUCESSO!");
            System.out.println("Nome: " + super.getNome() + " | CPF: " + super.getCpf() + " | Endereco: " + super.getEndereco() + " | Salario: " + this.getSalario() + " | Cargo: " + this.getCargo() + " | Data de Nascimento: " + this.getNasc() + " | Telefone: " + super.getTelefone() + "\n");
            
            return;
            
          } catch(Exception e){
              System.out.println("Erro ao atualizar Funcionario.");
              return;
          }
    
    }
    
    public void deleteFuncionario(){
    
        System.out.println("Escolha o ID do funcionario para deletar.");
    
        try{
          
            System.out.println("\nA partir do ID do funcionario, selecione qual voce deseja deleter:");
              
            ConexaoFactor conn = new ConexaoFactor();
            Connection connection = conn.getConnection();
              
            String sql = "Select id_func, cpf, nome_func, cargo FROM funcionario";
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

            System.out.print("Escolha o ID do funcionario: ");
            int x = fun.nextInt();
            
            String sqlCall = "CALL delete_in_ID_funcionario(?)";
            String sqlCall2 = "CALL delete_in_ID_usuario(?)";
            
            PreparedStatement call = connection.prepareStatement(sqlCall);
            PreparedStatement call2 = connection.prepareStatement(sqlCall2);
            
            call.setInt(1, x);
            call2.setInt(1, x);
            
            call2.executeUpdate();
            call.executeUpdate();
            
            System.out.println("Funcionario deletado.\n");
            
            stmt.close();
            connection.close();
            resultSet.close();
            call.close();
            call2.close();
            
            return;
            
        } catch(Exception e){
            System.out.println("Erro ao deletar Funcionario.");
            e.printStackTrace();
            return;
        }
        
    }
    
}
