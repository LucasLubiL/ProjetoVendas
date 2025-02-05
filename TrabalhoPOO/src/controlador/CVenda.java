package controlador;

import java.util.List;
import modelo.DAO.VendaDAO;
import modelo.dominio.RelatorioVendasPorData;
import modelo.dominio.Venda;
import modelo.dominio.Venda_itens;
import visao.VendaView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CVenda {

    public void escolherOpcao(int opcao) {
        switch(opcao){
            case 1:
                new VendaView().realizarVenda();
                break;
            case 2:
                new VendaView().cancelarVenda();
                break;
            
        }
    }

    public void inserirVenda(Venda venda,List<Venda_itens> itens) {
        new VendaDAO().inserirVenda(venda,itens);
    }

    public void listarVendasParaSelecao() {
        new VendaView().listarVendas();
    }
    
    public List<Venda> listarVendas(){
        return new VendaDAO().listarVendas();
    }

    public void cancelarVenda(int id) {
        new VendaDAO().cancelarVenda(id);
    }

    public List<RelatorioVendasPorData> relatorioEmitidas(String date1, String date2) {
        return new VendaDAO().relatorioEmitidas(date1,date2);
    }

    public List<RelatorioVendasPorData> relatorioCanceladas(String date1, String date2) {
        return new VendaDAO().relatorioCanceladas(date1,date2);
    }
    

    
}
