package visao;

import controlador.CPagamento;
import controlador.CRelatorio;
import controlador.CVenda;
import java.util.List;
import java.util.Scanner;
import modelo.dominio.Pagamento;
import modelo.dominio.RelatorioVendasPorData;
import modelo.dominio.Venda;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class RelatorioView {
    
    public void menu(){
        CRelatorio controlador = new CRelatorio();
        Scanner rel = new Scanner(System.in);
        int opcao=1;
        do{
            System.out.println("\n===Menu de relatórios===");
            System.out.println("0- Voltar a Pagina anterior");
            System.out.println("1- Emitir relatório de vendas Emitidas por data");
            System.out.println("2- Emitir relatório de vendas Canceladas por data");
            System.out.println("3- Emitir relatório de Produtos");
            System.out.println("4- Emitir relatório de Vendas por tipo de pagamento");
            System.out.print("Escolha a ação que deseja realizar:");
            opcao = rel.nextInt();
            rel.nextLine();
        
            controlador.escolherOpcao(opcao);
            
        }while(opcao!=0);
        
     
        
    }

    public void relatorioEmitidas() {
        Scanner rel = new Scanner(System.in);
        System.out.println("Escolha as datas para definir o intervalo no qual deseja visualizar as vendas Emitidas:");
        
        System.out.print("Digite a Data Inicial (ano/mes/dia): ");
        String date1 = rel.nextLine();
        
        System.out.print("Digite a Data Final (ano/mes/dia): ");
        String date2 = rel.nextLine();
        
        List<RelatorioVendasPorData> vendas = new CVenda().relatorioEmitidas(date1,date2);
        
        System.out.println("====        Relatório de vendas         ====");
        System.out.println("_____________________________________________");
        for(RelatorioVendasPorData v:vendas){
            System.out.println("ID: "+v.getIdVenda()+" | Nome do Cliente: "+v.getNome_cliente()+" | Quantidade de produtos: "+v.getQuant_prod()+" | Preço da compra: "+v.getValor()+" | Tipo de pagamento: "+v.getNome_tipo()+" |");
        }
    }

    public void relatorioCanceladas() {
        Scanner rel = new Scanner(System.in);
        System.out.println("Escolha as datas para definir o intervalo no qual deseja visualizar as vendas Canceladas:");
        
        System.out.print("Digite a Data Inicial (ano/mes/dia): ");
        String date1 = rel.nextLine();
        
        System.out.print("Digite a Data Final (ano/mes/dia): ");
        String date2 = rel.nextLine();
        
        List<RelatorioVendasPorData> vendas = new CVenda().relatorioCanceladas(date1,date2);
        
        System.out.println("====        Relatório de vendas         ====");
        System.out.println("_____________________________________________");
        for(RelatorioVendasPorData v:vendas){
            System.out.println("ID: "+v.getIdVenda()+" | Nome do Cliente: "+v.getNome_cliente()+" | Quantidade de produtos: "+v.getQuant_prod()+" | Preço da compra: "+v.getValor()+" | Tipo de pagamento: "+v.getNome_tipo()+" |");
        }
    }

    public void relatorioVendasPorPagamento() {
        List<Pagamento> pagamentos = new CPagamento().relatorioVendasPorPagamento(); 
        
        System.out.println("===      Relatório de Vendas por Tipo de Pagamento     ===");
        System.out.println("__________________________________________________________");
        
        for(Pagamento p: pagamentos){
            System.out.println("ID: "+p.getId_pagamento()+" | Tipo de pagamento: "+p.getNomeTipo()+" | Quantidade de vendas: "+p.getQuantVendas()+" |");
        }
    }
    
}
