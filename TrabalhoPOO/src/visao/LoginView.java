package visao;

import controlador.CLogin;
import java.util.Scanner;
import modelo.dominio.Funcionario;
import modelo.dominio.Login;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class LoginView {
   
    
    public void logar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu Login:");
        System.out.print("User: ");
        String user = (scanner.nextLine());
        System.out.print("Senha: ");
        String senha = (scanner.nextLine());
        
        Login login= new Login();
        login.setUser(user);
        login.setSenha(senha);
        
        CLogin seuLogin = new CLogin();
        seuLogin.infoLogin(login);
        
        
    }

    public void cadastrarUsuario(Funcionario funcionario) {
        
        Login login=new Login();
        Scanner log = new Scanner(System.in);
        CLogin controlador = new CLogin();
        
        login.setCargo(funcionario.getCargo());
        login.setIdFuncionario(funcionario.getId());
        System.out.println("/n===Agora vamos criar um novo usuário para seu novo funcionário===");
        System.out.println("Digite as informações necessárias");
        
        System.out.print("Usuário:");
        login.setUser(log.nextLine());
        System.out.print("Senha:");
        login.setSenha(log.nextLine());
        
        controlador.inserirUsuario(login);
    }
    
    
}
