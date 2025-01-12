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
import javax.swing.JOptionPane;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Produto {
    
    private String nome;
    private double preco;
    private int quant;
    private String marca;
    private String categoria;
    private String tamCamisa;
    private String tamCalca;
    private int numCalcado;
    
    public Produto(){}

    public Produto(String nome, double preco, int quant, String marca, String categoria, String tamCamisa, String tamCalca, int numCalcado) {
        this.nome = nome;
        this.preco = preco;
        this.quant = quant;
        this.marca = marca;
        this.categoria = categoria;
        this.tamCamisa = tamCamisa;
        this.tamCalca = tamCalca;
        this.numCalcado = numCalcado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamCamisa() {
        return tamCamisa;
    }

    public void setTamCamisa(String tamCamisa) {
        this.tamCamisa = tamCamisa;
    }

    public String getTamCalca() {
        return tamCalca;
    }

    public void setTamCalca(String tamCalca) {
        this.tamCalca = tamCalca;
    }

    public int getNumCalcado() {
        return numCalcado;
    }

    public void setNumCalcado(int numCalcado) {
        this.numCalcado = numCalcado;
    }
      
}
