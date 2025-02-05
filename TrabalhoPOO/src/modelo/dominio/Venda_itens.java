package modelo.dominio;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class Venda_itens {
    private int idProd;
    private double preco;
    private double precoTotal;
    private int quantItens;

    public Venda_itens() {
    }

    public Venda_itens(int idProd, double preco, double precoTotal, int quantItens) {
        this.idProd = idProd;
        this.preco = preco;
        this.precoTotal = precoTotal;
        this.quantItens = quantItens;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getQuantItens() {
        return quantItens;
    }

    public void setQuantItens(int quantItens) {
        this.quantItens = quantItens;
    }
    
    
}
