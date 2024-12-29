package projetovendas;

import Connect.ConexaoFactor;
import java.util.Scanner;
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
                    
                    
                    
                    break;
                case 2:
                    System.out.println("Login para Estoquista:");
                    System.out.print("User: ");
                    user = acao.nextLine();
                    System.out.print("Senha: ");
                    senha = acao.nextLine();
                    
                    
                    break;
                case 3:
                    System.out.println("Login para Caixa:");
                    System.out.print("User: ");
                    user = acao.nextLine();
                    System.out.print("Senha: ");
                    senha = acao.nextLine();
                    
                    
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;

            }
        } while( x != 0);
        
        MenuEstoquista c = new MenuEstoquista();
        
        c.relatorioProdutos();
        
    }

   
    
    
    
    
    
     
}
