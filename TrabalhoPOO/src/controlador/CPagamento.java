package controlador;

import java.util.List;
import modelo.DAO.PagamentoDAO;
import modelo.dominio.Pagamento;
import visao.PagamentoView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CPagamento {

    public void escolherAcao(int opcao) {
        switch(opcao){
            case 1:
                new PagamentoView().inserirPagamento();
                break;
            case 2:
                new PagamentoView().excluirPagamento();
                break;
            case 3:
                new PagamentoView().atualizarPagamento();
                break;
            
        }
    }

    public void inserirPagamento(Pagamento pagamento) {
        new PagamentoDAO().inserirPagamento(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return new PagamentoDAO().listarPagamentos();
    }
    
    public void listarPagamentoParaSelecao(){
        new PagamentoView().listarPagamento();
    }

    public void excluirPagamento(int id) {
        new PagamentoDAO().excluirPagamento(id);
    }

    public void atualizarPagamento(Pagamento pagamento) {
        new PagamentoDAO().atualizarPagamento(pagamento);
    }

    public int buscarMaxId() {
        return new PagamentoDAO().buscarMaxId();
    }

    public List<Pagamento> relatorioVendasPorPagamento() {
        return new PagamentoDAO().relatorioVendasPorPagamento();
    }
}
