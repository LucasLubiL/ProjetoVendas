package modelo.dominio;

import projetovendas.*;
import java.util.Scanner;
import connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.*;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Pagamento {
    
    private String nomeTipo;

    public Pagamento(){};

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
    
}
