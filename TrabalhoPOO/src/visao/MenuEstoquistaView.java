package visao;


import java.util.Scanner;
import connect.ConexaoFactor;
import controlador.CMenuEstoquista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class MenuEstoquistaView implements Menu {
    
    Scanner scanner = new Scanner(System.in);

    private int acao;
    private int escolha;
    
    public void menu() {
       
        do{
            System.out.println("\nEscolha a aréa de operação:");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1: Produtos");
            System.out.println("2: Relatorio de produtos");
            System.out.print("Digite sua escolha: ");
            escolha = scanner.nextInt();
            if(escolha!=1 && escolha!=0 && escolha!=2){
                System.out.println("Opção inválida!!");
            }else{
                new CMenuEstoquista().escolherOpcao(escolha);
            }
            
            
        }while(escolha != 0);
        System.out.println("Voltando...");
    }



}
