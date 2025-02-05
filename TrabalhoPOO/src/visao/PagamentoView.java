package visao;

import controlador.CPagamento;
import java.util.List;
import java.util.Scanner;
import modelo.dominio.Pagamento;

/**
 *
 * @author Alexssander
 */
public class PagamentoView {
    
    public void menu(){
        Scanner escolha = new Scanner(System.in);
        int opcao=1;
        do{
            System.out.println("\n=== Gerenciamento de Opções de Pagamento ===");
            System.out.println("0-Voltar");
            System.out.println("1-Inserir Pagamento");
            System.out.println("2-Excluir Pagamento");
            System.out.println("3-Atualizar Pagamento");
            System.out.print("Digite sua opção:");
            opcao=escolha.nextInt();
            if(opcao>3 || opcao<0 ){
                System.out.println("Opção inválida!!");
            }else{
                new CPagamento().escolherAcao(opcao);
            }
            
            
        }while(opcao!=0);
        System.out.println("Voltando...");
        
        
    }

    public void inserirPagamento() {
        Scanner pag = new Scanner(System.in);
        Pagamento pagamento = new Pagamento();
        
        System.out.print("Digite o nome do pagamento a ser adicionado:");
        pagamento.setNomeTipo(pag.nextLine());
        
        new CPagamento().inserirPagamento(pagamento);
    }

    public void excluirPagamento() {
        Scanner pag = new Scanner(System.in);
        CPagamento controlador = new CPagamento();
        
        System.out.println("Escolha o ID do pagamento a ser excluido:\n");
        controlador.listarPagamentoParaSelecao();
        
        System.out.print("ID:");
        int id = pag.nextInt();
        controlador.excluirPagamento(id);
        
    }

    public void atualizarPagamento() {
        Scanner pag = new Scanner(System.in);
        CPagamento controlador = new CPagamento();
        Pagamento pagamento = new Pagamento();
        
        
        System.out.println("Escolha o ID do pagamento a ser atualizado:\n");
        controlador.listarPagamentoParaSelecao();
        
        
        System.out.print("ID:");
        pagamento.setId_pagamento(pag.nextInt());
        pag.nextLine();
        
        System.out.print("Digite o novo nome do pagamento:");
        pagamento.setNomeTipo(pag.nextLine());
        
        controlador.atualizarPagamento(pagamento);
    }
    
    public void listarPagamento(){
        CPagamento controlador = new CPagamento();
        List<Pagamento> pagamentos = controlador.listarPagamentos();
        
        System.out.println("|                                    Lista de pagamentos                                        |");
        System.out.println("|_______________________________________________________________________________________________|");
        
        for (Pagamento p : pagamentos) {
            System.out.println("ID: "+p.getId_pagamento()+" | Tipo de pagamento: "+p.getNomeTipo()+"|");
        }
        
    }
    
}
