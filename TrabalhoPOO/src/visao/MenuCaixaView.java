package visao;


import controlador.CMenuCaixa;
import java.util.Scanner;


/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class MenuCaixaView implements Menu{
    Scanner scanner = new Scanner(System.in);

    private int acao;
    private int escolha;
    
    public void menu() {
       
        do{
            System.out.println("\nEscolha a aréa de operação:");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1: Clientes");
            System.out.println("2: Venda");
            System.out.print("Digite sua escolha: ");
            escolha = scanner.nextInt();
            
            if(escolha<0 || escolha>2){
                System.out.println("A opção selecionada é inválida!!!");
            }else{
                new CMenuCaixa().fazerEscolha(escolha);
            }
           
        }while(escolha != 0);
        System.out.println("\nVoltando\n");
    }
    
  
}
