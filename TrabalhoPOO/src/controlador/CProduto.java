package controlador;

import java.util.List;
import modelo.DAO.ProdutoDAO;
import modelo.dominio.Produto;
import visao.ProdutoView;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class CProduto {

    public List<Produto> listarProdutos() {
        return new ProdutoDAO().listaProdutos();
    }

    public void mostrarProdutosParaSelecao(){
        new ProdutoView().listarProdutos();
    }
    
    public void escolherAcao(int opcao) {
        switch(opcao){
            case 1:
                new ProdutoView().inserirProduto();
                break;
            case 2:
                new ProdutoView().atualizarEstoque();
                break;
            case 3:
                new ProdutoView().excluirProduto();
                break;
            case 4:
                new ProdutoView().atualizarProduto();
                break;
        };
    }
    
    
    public void InserirNovoProduto(Produto produto) {
        
        new ProdutoDAO().regitrarProduto(produto);
    }

    public void attEstoque(int idProduto, int quant) {
        new ProdutoDAO().atualizarEstoque(idProduto,quant);
    }

    public void excluirProduto(int idProd) {
        new ProdutoDAO().excluirProduto(idProd);
    }

    public void atualizarProduto(Produto produto) {
        new ProdutoDAO().atualizarProduto(produto);
    }

    public Produto selecionarProduto(int id) {
        return new ProdutoDAO().selecionarProduto(id);
    }
    
    
    
}
