package modelo.dominio;

import projetovendas.*;
import java.util.Scanner;
import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Cliente extends Pessoa{
    
    public Cliente(){};

    public Cliente(String nome, String cpf, String endereco, String telefone) {
        super(nome, cpf, endereco, telefone);
    }
    
}
