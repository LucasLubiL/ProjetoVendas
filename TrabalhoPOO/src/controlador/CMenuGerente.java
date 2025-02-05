package controlador;

import visao.ClienteView;
import visao.FuncionarioView;
import visao.PagamentoView;
import visao.ProdutoView;
import visao.RelatorioView;
import visao.VendaView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CMenuGerente {

    public void fazerEscolha(int escolha) {
        switch (escolha) {
            case 0:
                System.out.println("\nVoltando...\n");
                break;
            case 1:
                new ClienteView().menu();
                break;
            case 2:
                new ProdutoView().menu();
                break;
            case 3:
                new FuncionarioView().menu();
                break;
            case 4:
                new VendaView().menu();
                break;
            case 5:
                new PagamentoView().menu();
                break;
            case 6:
                new RelatorioView().menu();
                break;
            default:
                System.out.println("Opção inválida.");
            }
    }
   
}
