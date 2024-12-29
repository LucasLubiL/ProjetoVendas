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
            
            PreparedStatement stmp = connection.prepareStatement(sqlUser);
            PreparedStatement stmp2 = connection.prepareStatement(sqlBusca);
            
            stmp2.setString(1, getCpf());
            
            ResultSet resultSet2 = stmp2.executeQuery();
            
            if(resultSet2.next()){
               
               int id = resultSet2.getInt("id_func");
               stmp.setInt(1, id);
               stmp.setString(2, getLogin());
               stmp.setString(3, getSenha());
               stmp.setString(4, getCargo());

               stmp.execute();
               
            }else{
                System.out.println("Erro ao cadastrar Usuario.");
            }
            
            resultSet2.close();
            stmp.close();
            stmp2.close();
            connection.close();
                   
        }catch(Exception e){
            System.out.println("Erro ao criar Usuario.");
        }

    } 
    
}
