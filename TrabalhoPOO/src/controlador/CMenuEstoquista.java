package controlador;

import visao.ProdutoView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CMenuEstoquista {


    public void escolherOpcao(int escolha) {
        if(escolha==1){
            new ProdutoView().menu();
        }else if(escolha==2){
            new ProdutoView().listarProdutos();
        }
    }
    
}
