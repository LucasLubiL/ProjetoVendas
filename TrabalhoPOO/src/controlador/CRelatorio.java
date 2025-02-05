package controlador;

import visao.RelatorioView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CRelatorio {

    public void escolherOpcao(int opcao) {
        switch(opcao){
            case 0:
                break;
            case 1:
                new RelatorioView().relatorioEmitidas();
                break;
            case 2:
                new RelatorioView().relatorioCanceladas();
                break;
            case 3:
                new CProduto().mostrarProdutosParaSelecao();
                break;
            case 4:
                new RelatorioView().relatorioVendasPorPagamento();
                break;
            default:
                System.out.println("Opção inválida, tente novamente!!!");
        };
    }
    
    
    
}
