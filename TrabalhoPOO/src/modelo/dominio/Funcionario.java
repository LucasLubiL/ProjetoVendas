package modelo.dominio;


/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Funcionario extends Pessoa {

    private double salario;
    private String cargo;
    private String nasc;
    private String login;
    private String senha;

    public Funcionario() {}

    public Funcionario(int id, double salario, String cargo, String nasc, String nome, String cpf, String endereco, String telefone) {
        super(id, nome, cpf, endereco, telefone);
        this.salario = salario;
        this.cargo = cargo;
        this.nasc = nasc;
    }

    

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNasc() {
        return nasc;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

