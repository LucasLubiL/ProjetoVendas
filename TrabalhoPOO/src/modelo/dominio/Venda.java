package modelo.dominio;

import java.util.ArrayList;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class Venda {
    private int idVenda;
    private int idCliente;
    private int quant;
    private double valor;
    private int idPag;
   

    public Venda() {
        this.valor=0;
        
    }

    
    public Venda(int idVenda, int idCliente, int quant, double valor, int idPag) {
        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.quant = quant;
        this.valor = valor;
        this.idPag = idPag;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = this.valor+valor;
    }

    public int getIdPag() {
        return idPag;
    }

    public void setIdPag(int idPag) {
        this.idPag = idPag;
    }

   
    
}
