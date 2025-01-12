package modelo.dominio;

import projetovendas.*;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class VendaItens {
    
    private int idProd;
    private double preco;
    private double precoTotal;
    private int quantItens;
    
    public VendaItens(){};

    public VendaItens(int idProd, double preco, double precoTotal, int quantItens) {
        
        this.idProd = idProd;
        this.preco = preco;
        this.precoTotal = precoTotal;
        this.quantItens = quantItens;
        
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int id_prod) {
        this.idProd = id_prod;
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
