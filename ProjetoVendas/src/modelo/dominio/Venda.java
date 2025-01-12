package modelo.dominio;

import projetovendas.*;
import java.util.Scanner;
import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Venda {
    
    private int idCliente;
    private int quant;
    private double valor;
    private int idPag;

    public Venda(){}
    
    public Venda(int quant, double valor, int idCliente, int idPag) {
        this.quant = quant;
        this.valor = valor;
        this.idCliente = idCliente;
        this.idPag = idPag;
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
        this.valor = valor;
    }

    public int getIdPag() {
        return idPag;
    }

    public void setIdPag(int idPag) {
        this.idPag = idPag;
    }
    
}

