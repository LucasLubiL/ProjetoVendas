package controlador;


import modelo.DAO.FuncionarioDAO;
import modelo.dominio.Funcionario;
import java.util.List;
import visao.FuncionarioView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CFuncionario {

    
    public int cadastrarFuncionario(Funcionario funcionario){
        return new FuncionarioDAO().inserirFuncionario(funcionario);
        
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        new FuncionarioDAO().atualizarFuncionario(funcionario);
    }

    public void deletarFuncionario(int idFuncionario){
        new FuncionarioDAO().deletarFuncionario(idFuncionario);
    }

    public List<Funcionario> listarFuncionarios(){
        return new FuncionarioDAO().listarFuncionarios();
    }
    
    public void listarFuncionariosParaSelecao(){
        new FuncionarioView().listarFuncionarios();
    }
   

    public void escolherOpcao(int opcao) {
        switch (opcao) {
            case 1:
                new FuncionarioView().adicionarFuncionario();
                break;
            case 2:
                new FuncionarioView().atualizarFuncionario();
                break;
            case 3:
                new FuncionarioView().removerFuncionario();
                break;
            case 4:
                new FuncionarioView().listarFuncionarios();
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

    
    
}