package projetovendas;

import java.util.Scanner;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class MenuGerente implements Menu {
    Scanner scanner = new Scanner(System.in);
    
    private int acao;
    private int escolha;
    
    void gerente() {
       
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

            switch (escolha) {
                case 0:
                    System.out.println("\nVoltando...\n");
                    break;
                case 1:
                  //  menuClientes(scanner);
                    menuClientes();
                    break;
                case 2:
                  //  menuProdutos(scanner);
                    menuProdutos();
                    break;
                case 3:
                  //  menuFuncionarios(scanner);
                    menuFuncionarios();
                    break;
                case 4:
                  //  menuVendas(scanner);
                    break;
                case 5:
                   // menuPagamentos(scanner);
                    menuPagamentos();
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
            System.out.println("\nVocê escolheu a tabela Cliente.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Inserir cliente");
            System.out.println("2. Atualizar cliente");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    System.out.println("\nVoltando...\n");
                    break;
                case 1:
                    Cliente cli = new Cliente();
                    cli.inserirCliente();
                    break;
                case 2:
                    Cliente cli2 = new Cliente();
                    cli2.atualizarCliente();
                    break;
                
            }
        }while(acao != 0); 
        
    }

    @Override
    public void menuProdutos() {
        
        do{
            System.out.println("\nVocê escolheu a tabela Produtos.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Excluir Produto");
            System.out.println("4. Atualizar Estoque");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    System.out.println("\nVoltando...\n");
                    break;
                case 1:
                    Produto pro = new Produto();
                    pro.inserirProduto();
                    break;
                case 2:
                    Produto pro2 = new Produto();
                    pro2.atualizarProduto();
                    break;
                case 3:
                    Produto pro3 = new Produto();
                    pro3.deleteProduto();
                    break;
                case 4:
                    Produto pro4 = new Produto();
                    pro4.atualizarEstoque();
            }
        }while(acao != 0); 
        
    }

    @Override
    public void menuFuncionarios() {
      
        do{
            System.out.println("\nVocê escolheu a tabela Funcionario.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Cadastrar Funcionario");
            System.out.println("2. Atualizar Funcionario");
            System.out.println("3. Excluir Funcionario");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();
            scanner.nextLine();

                switch (acao) {
                    case 0:
                        System.out.println("\nVoltando...\n");
                        break;
                    case 1:
                        Funcionario func = new Funcionario();
                        func.inserirFuncionario();
                        break;
                    case 2:
                        Funcionario func2 = new Funcionario();
                        func2.atualizarFuncionario();
                        break;
                    case 3:
                        Funcionario func3 = new Funcionario();
                        func3.deleteFuncionario();
                        break;
                }
        }while(acao != 0);    }

    @Override
    public void menuVendas() {
       
        do{
            System.out.println("\nVocê escolheu a tabela Venda.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Realizar Venda");
            System.out.println("2. Consultar Venda");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    System.out.println("\nVoltando...\n");
                    break;
                case 1:
                    Venda ven = new Venda();
                    ven.realizarVenda();
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
            System.out.println("\nVocê escolheu a tabela Pagamento.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Inserir tipo de pagamento");
            System.out.println("2. Excluir tipo de pagamento");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch(acao){
                case 0:
                    System.out.println("\nVoltando...\n");
                    break;
                case 1:
                    Pagamento pag = new Pagamento();
                    pag.inserirPagamento();
                    break;
                case 2:
                    Pagamento pag2 = new Pagamento();
                    pag2.deletarPagamento();
                    break;

            }
        }while(acao != 0);    
    }
    
    public void relatorios(){
        
    }
    
}

