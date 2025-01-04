package projetovendas;

import java.util.Scanner;
import Connect.ConexaoFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 *
 
@author Alexssander, Lucas, Pablo
*/
public class MenuEstoquista implements Menu {
    Scanner scanner = new Scanner(System.in);

    private int acao;
    private int escolha;
    
    void estoquista() {
       
        do{
            System.out.println("\nEscolha a aréa de operação:");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1: Produtos");
            System.out.println("2: Relatorios");
            System.out.print("Digite sua escolha: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("\nVoltando\n");
                    break;
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    relatorioProdutos();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(escolha != 0);
        
    }

    @Override
    public void menuClientes() {
        System.out.println("Acesso negado !");
    }
    
    @Override
    public void menuProdutos() {
        int acao;
        do{
            System.out.println("\nVocê escolheu a tabela Produtos.");
            System.out.println("0. Voltar a Pagina anterior");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Excluir Produto");
            System.out.print("Escolha uma ação: ");
            acao = scanner.nextInt();

            switch (acao) {
                case 0:
                    System.out.println("\nVoltando...\n");
                    break;
                case 1:
                    Produto pro = new Produto();
                    pro.inserirProduto();
                    break;
                case 2:
                    Produto pro2 = new Produto();
                    pro2.atualizarProduto();
                    break;
                case 3:
                    Produto pro3 = new Produto();
                    pro3.deleteProduto();
                    break;
            }
        }while(acao != 0);
    }

    @Override
    public void menuFuncionarios() {
        System.out.println("Acesso negado !");
    }

    @Override
    public void menuVendas() {
        System.out.println("Acesso negado !");
    }

    @Override
    public void menuPagamentos() {
        System.out.println("Acesso negado !");
    }


    public void relatorioProdutos(){
        
        try {
        
             ConexaoFactor conn = new ConexaoFactor();
             Connection connection = conn.getConnection();
             
             String sql = "SELECT * FROM relatorio_produto";
             PreparedStatement statement =  connection.prepareStatement(sql);
             
             ResultSet resultSet = statement.executeQuery();
             
             ResultSetMetaData metaData = resultSet.getMetaData();
             int columnCount = metaData.getColumnCount();
             
             System.out.println("\nRelatório de Produtos");
             System.out.println("--------------------------------------------------------------------------------------------------------------------------");
             while(resultSet.next()){
             
                 for(int i = 1; i <= columnCount; i++){
                     
                     String columnName = metaData.getColumnName(i);
                     String value = resultSet.getString(i);
                     if(value != null){
                        System.out.print(columnName + ": " + value + " | ");
                     }
                 }
                 System.out.println();
             }
             
             System.out.println("--------------------------------------------------------------------------------------------------------------------------");
             
             resultSet.close();
             statement.close();
             connection.close();
             
             return;
             
        } catch (Exception e){
        
            System.out.println("Erro ao gerar relatorio.");  
            return;
            
        }
        
    }

}
