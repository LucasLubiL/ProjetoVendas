package projetovendas;

import java.util.Scanner;

/**
 *
 
@author Alexssander, Lucas, Pablo
*/

public class MenuCaixa implements Menu{
    Scanner scanner = new Scanner(System.in);

    private int acao;
    private int escolha;
    
     void caixa() {
       
        do{
            System.out.println("\nEscolha a aréa de operação:");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1: Clientes");
            System.out.println("2: Venda");
            System.out.print("Digite sua escolha: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("\nVoltando\n");
                    break;
                case 1:
                  //  menuClientes(scanner);
                    break;
                case 2:
                  //  menuVendas(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(escolha != 0);
        
    }
    
    @Override
    public void menuClientes() {
        
        do{
            System.out.println("\n\n\nVocê escolheu a tabela Cliente.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Inserir cliente");
            System.out.println("2. Atualizar cliente");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;

            }
        }while(acao != 0);
    }

    @Override
    public void menuProdutos() {
        System.out.println("Acesso negado!");
    }

    @Override
    public void menuFuncionarios() {
        System.out.println("Acesso negado!");
    }

    @Override
    public void menuVendas() {
        
        do{
            System.out.println("\n\n\nVocê escolheu a tabela Venda.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Realizar Venda");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    break;
                case 1:
                    break;
            }
        }while(acao != 0);
    }

    @Override
    public void menuPagamentos() {
        System.out.println("Acesso negado!");
    }
  
}
