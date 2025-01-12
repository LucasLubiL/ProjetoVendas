package projetovendas;

import java.util.Scanner;
import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Principal {
    
    public static void main(String[] args) {
        
        Scanner acao = new Scanner(System.in);
        
        int x;
        String user, senha;
        String l,s;
        
        do{
            
            System.out.println("Escolha qual login deseja fazer");
            System.out.println("0 - Encerrar programa");
            System.out.println("1 - Gerente");
            System.out.println("2 - Estoquista");
            System.out.println("3 - Caixa");
            x = acao.nextInt();
            acao.nextLine();

            switch(x){

                case 0:
                    JOptionPane.showMessageDialog(null, "Programa Finalizado!");
                    break;
                case 1:
                    System.out.println("Login para Gerente:");
                    System.out.print("User: ");
                    user = acao.nextLine();
                    System.out.print("Senha: ");
                    senha = acao.nextLine();
                    
                    try{
                   
                        ConexaoFactor conn = new ConexaoFactor();
                        Connection connection = conn.getConnection();
   
                        String sql = "SELECT login, senha, cargo FROM usuario WHERE cargo = 'Gerente' and login = ? and senha = ?";           
                        PreparedStatement statement =  connection.prepareStatement(sql);

                        statement.setString(1, user);
                        statement.setString(2, senha);
   
                        ResultSet resultSet = statement.executeQuery();
                        
                        if(resultSet.next()){
                            l = resultSet.getString("login");
                            s = resultSet.getString("senha");
                              
                            if(l.equals(user) && s.equals(senha)){
                                System.out.println("Login realizado com sucesso!");
                                MenuGerente ger = new MenuGerente();
                                ger.gerente();
                            }
                              
                        }else{
                            System.out.println("Suas credenciais estao incorretas.");
                        }
                        
                        resultSet.close();
                        statement.close();
                        connection.close();
                        
                    } catch(Exception e){
                        System.out.println("Erro de conexao login Gerente.");
                    }
                    
                    break;
                case 2:
                    System.out.println("Login para Estoquista:");
                    System.out.print("User: ");
                    user = acao.nextLine();
                    System.out.print("Senha: ");
                    senha = acao.nextLine();
                    
                    try{
                    
                        ConexaoFactor conn = new ConexaoFactor();
                        Connection connection = conn.getConnection();
             
                        String sql = "SELECT login, senha, cargo FROM usuario WHERE cargo like 'Estoquista' and login like ? and senha like ?";
                        PreparedStatement statement =  connection.prepareStatement(sql);
                        
                        statement.setString(1, user);
                        statement.setString(2, senha);
                        
                        ResultSet resultSet = statement.executeQuery();
                        
                        if(resultSet.next()){
                            l = resultSet.getString("login");
                            s = resultSet.getString("senha");
                              
                            if(l.equals(user) && s.equals(senha)){
                                System.out.println("\nLogin realizado com sucesso!\n");
                                MenuEstoquista est = new MenuEstoquista();
                                est.estoquista();
                            }
                              
                        }else{
                            System.out.println("\nSuas credenciais estao incorretas.\n");
                        }
                        
                        resultSet.close();
                        statement.close();
                        connection.close();
                        
                    } catch(Exception e){
                        System.out.println("Erro de conexao login Estoquista.");
                    }
                    
                    break;
                case 3:
                    System.out.println("Login para Caixa:");
                    System.out.print("User: ");
                    user = acao.nextLine();
                    System.out.print("Senha: ");
                    senha = acao.nextLine();
                    
                    try{
                    
                        ConexaoFactor conn = new ConexaoFactor();
                        Connection connection = conn.getConnection();
             
                        String sql = "SELECT login, senha, cargo FROM usuario WHERE cargo like 'Caixa' and login like ? and senha like ?";
                        PreparedStatement statement =  connection.prepareStatement(sql);
                        
                        statement.setString(1, user);
                        statement.setString(2, senha);
                        
                        ResultSet resultSet = statement.executeQuery();
                        
                        if(resultSet.next()){
                            l = resultSet.getString("login");
                            s = resultSet.getString("senha");
                              
                            if(l.equals(user) && s.equals(senha)){
                                System.out.println("\nLogin realizado com sucesso!\n");
                                MenuCaixa cax = new MenuCaixa();
                                cax.caixa();
                            }
                              
                        }else{
                            System.out.println("\nSuas credenciais estao incorretas.\n");
                        }
                        
                        resultSet.close();
                        statement.close();
                        connection.close();
                        
                    } catch(Exception e){
                        System.out.println("Erro de conexao login Caixa.");
                    }
                    
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;

            }
        } while( x != 0);
            
    }
     
}
