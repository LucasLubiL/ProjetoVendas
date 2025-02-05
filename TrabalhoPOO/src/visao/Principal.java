package visao;

import controlador.CLogin;
import java.util.Scanner;


/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Principal {
    
    public static void main(String[] args) {
        
       Scanner scan = new Scanner(System.in);
       
       int opcao=1; 
       do{
           System.out.println("Digite:");
           System.out.println("0-Sair do programa");
           System.out.println("1-Fazer login");
           opcao=scan.nextInt();
           scan.nextLine();
           if(opcao!=0 && opcao!=1){
               System.out.println("Escolha uma opção válida!!!");
               
           }else{
               CLogin logando = new CLogin();
               logando.realizarLogin(opcao);
           }
           
       }while(opcao!=0);
    }
    
}
