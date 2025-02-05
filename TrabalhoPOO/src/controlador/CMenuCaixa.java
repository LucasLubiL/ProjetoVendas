package controlador;


import visao.ClienteView;
import visao.VendaView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CMenuCaixa {

    public void fazerEscolha(int escolha) {
        switch(escolha){
            
            case 1:
                new ClienteView().menu();
                break;
            case 2:
                new VendaView().menu();
                break;
            
        };
    }
    
}
