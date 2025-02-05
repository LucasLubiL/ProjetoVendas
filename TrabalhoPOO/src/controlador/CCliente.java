package controlador;

import modelo.DAO.ClienteDAO;
import modelo.dominio.Cliente;
import java.util.List;
import visao.ClienteView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CCliente {
    
    public void adicionarCliente(Cliente cliente) {
        new ClienteDAO().inserirCliente(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        new ClienteDAO().atualizarCliente(cliente);
    }

    public List<Cliente> listarClientes() {
        return new ClienteDAO().listarClientes();
    }

    public void escolherAcao(int opcao) {
        switch (opcao) {
            case 1:
                new ClienteView().cadastrarClientes();
                break;
            case 2:
                new ClienteView().atualizarClientes();
                break;
            case 3:
                new ClienteView().listarClientes();
                break;
        };
    }

    public void listarClientesParaSelecao() {
        new ClienteView().listarClientes();
    }

    public int buscarMaxId() {
        return new ClienteDAO().buscarMaxId();
    }
}

