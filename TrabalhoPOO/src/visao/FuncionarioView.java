package visao;

import modelo.dominio.Funcionario;
import controlador.CFuncionario;
import controlador.CLogin;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class FuncionarioView {

    public void menu() {
        Scanner op = new Scanner(System.in);
        CFuncionario controlador = new CFuncionario();
        
        int opcao;
        do {
            System.out.println("\n=== Gerenciamento de Funcionários ===");
            System.out.println("0. Voltar");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Atualizar Funcionário");
            System.out.println("3. Remover Funcionário");
            System.out.println("4. Listar Funcionários");
            
            System.out.print("Escolha uma opção: ");
            opcao = op.nextInt();
            op.nextLine(); 
            
            controlador.escolherOpcao(opcao);
            
            
        } while (opcao != 0);
    }

    public void adicionarFuncionario(){
        Scanner scanner = new Scanner(System.in);
        CFuncionario controlador = new CFuncionario();
        
        System.out.println("\n=== Adicionar Funcionário ===");
        Funcionario funcionario = new Funcionario();
        
        System.out.print("Nome: ");
        funcionario.setNome(scanner.nextLine());
        System.out.print("CPF: ");
        funcionario.setCpf(scanner.nextLine());
        System.out.print("Endereço: ");
        funcionario.setEndereco(scanner.nextLine());
        System.out.print("Telefone: ");
        funcionario.setTelefone(scanner.nextLine());
        System.out.print("Salário: ");
        funcionario.setSalario(scanner.nextDouble());
        scanner.nextLine(); 
        System.out.print("Cargo: ");
        funcionario.setCargo(scanner.nextLine());
        System.out.print("Data de Nascimento (Ano-Mes-Dia): ");
        funcionario.setNasc(scanner.nextLine());

        int idGerado=controlador.cadastrarFuncionario(funcionario);
        if(idGerado>0){
            funcionario.setId(idGerado);
            new CLogin().cadastrarUsuario(funcionario);
        }
               
    }

    public void atualizarFuncionario(){
        Scanner scanner = new Scanner(System.in);
        CFuncionario controlador = new CFuncionario();
        Funcionario funcionario = new Funcionario();
        
        System.out.println("\n=== Atualizar Funcionário ===");
        controlador.listarFuncionariosParaSelecao();
        System.out.print("ID do Funcionário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        funcionario.setId(id);
        

        System.out.print("Novo Nome: ");
        funcionario.setNome(scanner.nextLine());
        System.out.print("Novo CPF: ");
        funcionario.setCpf(scanner.nextLine());
        System.out.print("Novo Endereço: ");
        funcionario.setEndereco(scanner.nextLine());
        System.out.print("Novo Telefone: ");
        funcionario.setTelefone(scanner.nextLine());
        System.out.print("Novo Salário: ");
        funcionario.setSalario(scanner.nextDouble());
        scanner.nextLine(); // Consumir quebra de linha
        System.out.print("Novo Cargo: ");
        funcionario.setCargo(scanner.nextLine());
        System.out.print("Nova Data de Nascimento (yyyy-MM-dd): ");
        funcionario.setNasc(scanner.nextLine());

        controlador.atualizarFuncionario(funcionario);
       
    }

    public void removerFuncionario() {
        Scanner scanner = new Scanner(System.in);
        CFuncionario controlador = new CFuncionario();
        
        
        System.out.println("\n=== Remover Funcionário ===");
        controlador.listarFuncionariosParaSelecao(); 
        
        System.out.print("ID do Funcionário a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        controlador.deletarFuncionario(id);
        
    }

    public void listarFuncionarios(){
        Scanner scanner = new Scanner(System.in);
        CFuncionario controlador = new CFuncionario();
        List<Funcionario> funcionarios = new CFuncionario().listarFuncionarios();
        
        System.out.println("\n=== Lista de Funcionários ===");
        System.out.println("______________________________");
        
        for(Funcionario f: funcionarios){
            System.out.println("ID: "+f.getId()+" | Nome: "+f.getNome()+" | CPF: "+ f.getCpf()+" | Endereço: "+f.getEndereco()+" | Salário: "+f.getSalario()+" | Cargo: "+ f.getCargo()+" | Data de nascimento: "+f.getNasc()+" | Telefone: "+f.getTelefone()+" |");                                                                             
        }
        
    }
}
