package visao;


import controlador.CMenuGerente;
import java.util.Scanner;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class MenuGerenteView implements Menu {
    Scanner scanner = new Scanner(System.in);
    
    private int acao;
    private int escolha;
    
    public void menu() {
       
        do{
            System.out.println("\nEscolha a aréa de operação:");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1: Clientes");
            System.out.println("2: Produtos");
            System.out.println("3: Funcionario");
            System.out.println("4: Venda");
            System.out.println("5: Pagamentos");
            System.out.println("6: Relatorios");
            System.out.print("Digite sua escolha: ");
            escolha = scanner.nextInt();
            
            new CMenuGerente().fazerEscolha(escolha);


        }while(escolha != 0);
        
    }
    
}

