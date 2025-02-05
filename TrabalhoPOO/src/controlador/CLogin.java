package controlador;

import modelo.DAO.LoginDAO;
import modelo.dominio.Funcionario;
import modelo.dominio.Login;
import visao.LoginView;
import visao.MenuCaixaView;
import visao.MenuEstoquistaView;
import visao.MenuGerenteView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CLogin {

    public void realizarLogin(int opcao) {
        if(opcao==1){
            LoginView loginVisao = new LoginView();
            loginVisao.logar();
        }
    }
    
    public void infoLogin(Login login){
    
        LoginDAO verificarLogin = new LoginDAO();
        Login login2 = verificarLogin.verificarLogin(login);
        if(login.getUser().equals(login2.getUser()) && login.getSenha().equals(login2.getSenha())){
            
            switch(login2.getCargo()){
                case ("Caixa"):
                    new MenuCaixaView().menu();
                    break;
                case ("Estoquista"):
                    new MenuEstoquistaView().menu();
                    break;
                case ("Gerente"):
                    new MenuGerenteView().menu();
                    break;
                
            }
            
        }else{
            System.out.println("O usuário ou senha digitados estão incorretos");
        }
    
    }

    public void cadastrarUsuario(Funcionario funcionario) {
        new LoginView().cadastrarUsuario(funcionario);
    }

    public void inserirUsuario(Login login) {
        new LoginDAO().inserirUsuario(login);
    }
    
}
