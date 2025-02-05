
package modelo.dominio;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */
public class Pagamento {
    private int id_pagamento;
    private String nomeTipo;
    private int quantVendas;

    public Pagamento() {
    }

    public Pagamento(int id_pagamento, String nomeTipo) {
        this.id_pagamento = id_pagamento;
        this.nomeTipo = nomeTipo;
    }

    
    
    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public int getQuantVendas() {
        return quantVendas;
    }

    public void setQuantVendas(int quantVendas) {
        this.quantVendas = quantVendas;
    }
    
    
    
}
