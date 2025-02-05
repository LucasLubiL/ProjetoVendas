package modelo.dominio;


/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class Cliente extends Pessoa {
    public Cliente() {}

    public Cliente(int id, String nome, String cpf, String endereco, String telefone) {
        super(id, nome, cpf, endereco, telefone);
    }
}