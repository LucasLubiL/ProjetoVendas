package projetovendas;

import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class MenuGerente implements Menu {
    Scanner scanner = new Scanner(System.in);
    
    private int acao;
    private int escolha;
    
    void gerente() {
       
        do{
            System.out.println("\n\n\nEscolha a aréa de operação:");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1: Clientes");
            System.out.println("2: Produtos");
            System.out.println("3: Funcionario");
            System.out.println("4: Venda");
            System.out.println("5: Pagamentos");
            System.out.println("6: Relatorios");
            System.out.print("Digite sua escolha: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("Voltando");
                    break;
                case 1:
                  //  menuClientes(scanner);
                    break;
                case 2:
                  //  menuProdutos(scanner);
                    break;
                case 3:
                  //  menuFuncionarios(scanner);
                    break;
                case 4:
                  //  menuVendas(scanner);
                    break;
                case 5:
                   // menuPagamentos(scanner);
                    break;
                case 6:
                    // logica pro relatorio
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
            System.out.println("3. Excluir cliente");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                
            }
        }while(acao != 0); 
        
    }

    @Override
    public void menuProdutos() {
        
        do{
            System.out.println("\n\n\nVocê escolheu a tabela Produtos.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Excluir Produto");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }while(acao != 0); 
        
    }

    @Override
    public void menuFuncionarios() {
      
        do{
            System.out.println("\n\n\nVocê escolheu a tabela Funcionario.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Cadastrar Funcionario");
            System.out.println("2. Atualizar Funcionario");
            System.out.println("3. Excluir Funcionario");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

                switch (acao) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
        }while(acao != 0);    }

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
                case 2:
                    break;
                case 3:
                    break;
            }
        }while(acao != 0);    }

    @Override
    public void menuPagamentos() {
       
        do{
            System.out.println("\n\n\nVocê escolheu a tabela Pagamento.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Inserir tipo de pagamento");
            System.out.println("2. Atualizar tipo de pagamento");
            System.out.println("3. Excluir tipo de pagamento");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch(acao){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }while(acao != 0);    
    }
    
    public void relatorios(){
        
    }
    
}

