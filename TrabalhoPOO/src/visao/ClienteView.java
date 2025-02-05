// Pacote: visao
package visao;

import controlador.CCliente;
import modelo.dominio.Cliente;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class ClienteView {
    private CCliente controlador;

    public ClienteView() {
        this.controlador = new CCliente();
    }

    public void menu() {
        Scanner escolha = new Scanner(System.in);
        int opcao=1;
        do{
            System.out.println("\n=== Gerenciamento de Clientes ===");
            System.out.println("Digite sua opção:");
            System.out.println("0-Voltar");
            System.out.println("1-Adicionar Cliente");
            System.out.println("2-Atualizar Cliente");
            System.out.println("3-Listar Clientes");
            opcao=escolha.nextInt();
            if(opcao>3 || opcao<0 ){
                System.out.println("Opção inválida!!");
            }else{
                new CCliente().escolherAcao(opcao);
            }
            
        }while(opcao!=0);
        System.out.println("Voltando...");
    }

    public void cadastrarClientes() {
        
        Scanner cli = new Scanner(System.in);
        Cliente cliente = new Cliente();
        CCliente controlador = new CCliente();
        
        System.out.println("Digite os dados do cliente para cadastro:");
        
        System.out.print("Nome: ");
        cliente.setNome(cli.nextLine());
        
        System.out.print("CPF: ");
        cliente.setCpf(cli.nextLine());
        
        System.out.print("Endereço: ");
        cliente.setEndereco(cli.nextLine());
        
        System.out.print("Telefone: ");
        cliente.setTelefone(cli.nextLine());
        
        controlador.adicionarCliente(cliente);
        
    }

    public void atualizarClientes() {
        Scanner cli= new Scanner(System.in);
        CCliente controlador = new CCliente();
        Cliente cliente = new Cliente();
        
        
        System.out.println("Digite o ID do cliente a ser atualizado:");
        controlador.listarClientesParaSelecao();
        
        System.out.println("ID:");
        cliente.setId(cli.nextInt());
        cli.nextLine();
        
        
        System.out.println("Digite os dados do cliente para cadastro:");
        
        System.out.print("Nome: ");
        cliente.setNome(cli.nextLine());
        
        System.out.print("CPF: ");
        cliente.setCpf(cli.nextLine());
        
        System.out.print("Endereço: ");
        cliente.setEndereco(cli.nextLine());
        
        System.out.print("Telefone: ");
        cliente.setTelefone(cli.nextLine());
        
        controlador.atualizarCliente(cliente);
        

        
        
    }

    public void listarClientes() {
        System.out.println("|                                   Lista de clientes                                    |");
        System.out.println("|________________________________________________________________________________________|");
        List<Cliente> clientes = controlador.listarClientes();
        for (Cliente c : clientes) {
            
            System.out.println("ID: "+c.getId()+" |Nome: " + c.getNome() + " | CPF: " + c.getCpf() 
                               + " | Endereço: " + c.getEndereco() + " | Telefone: " + c.getTelefone());
            
        }
    }
    
}
