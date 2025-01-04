package projetovendas;

import java.util.Scanner;
import Connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class VendaItens {
    
    private int idVenda;
    private int idProd;
    private double preco;
    private double precoTotal;
    private int quantItens;
    
    public VendaItens(){};

    public VendaItens(int idVenda, int idProd, double preco, double precoTotal, int quantItens) {
        
        this.idVenda = idVenda;
        this.idProd = idProd;
        this.preco = preco;
        this.precoTotal = precoTotal;
        this.quantItens = quantItens;
        
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int id_venda) {
        this.idVenda = id_venda;
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
