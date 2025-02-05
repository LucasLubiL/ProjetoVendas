package visao;

import controlador.CProduto;
import java.util.List;
import java.util.Scanner;
import modelo.dominio.Produto;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class ProdutoView {
    
    public void menu() {
        Scanner escolha = new Scanner(System.in);
        int opcao=1;
        do{
            System.out.println("\n=== Gerenciamento de Produtos ===");
            System.out.println("0-Voltar");
            System.out.println("1-Inserir Produto");
            System.out.println("2-Atualizar Estoque");
            System.out.println("3-Excluir Produto");
            System.out.println("4-Atualizar Produto");
            System.out.print("Digite sua opção:");
            opcao=escolha.nextInt();
            if(opcao>4 || opcao<0 ){
                System.out.println("Opção inválida!!");
            }else{
                new CProduto().escolherAcao(opcao);
            }
            
            
        }while(opcao!=0);
        System.out.println("Voltando...");
    }

    public void listarProdutos() {
        CProduto controlador = new CProduto();
        List<Produto> produtos = controlador.listarProdutos();
        System.out.println("|                                      Lista de produtos                                        |");
        System.out.println("|_______________________________________________________________________________________________|");
        for (Produto p : produtos) {
            if(p.getCategoria().equals("top")){
                
                System.out.println("ID: "+p.getId()+" | Nome: " + p.getNome() + " | Preço: " + p.getPreco() 
                + " | Estoque: " + p.getQuant() + " | Categoria: " + p.getCategoria()+" | Tamanho: " + p.getTamCamisa()+"|"); 
                
            }else if(p.getCategoria().equals("bottom")){
                
                System.out.println("ID: "+p.getId()+" | Nome: " + p.getNome() + " | Preço: " + p.getPreco() 
                + " | Estoque: " + p.getQuant() + " | Categoria: " + p.getCategoria()+" | Tamanho: " + p.getTamCalca()+"|"); 
                
            }else{
                
                System.out.println("ID: "+p.getId()+" | Nome: " + p.getNome() + " | Preço: " + p.getPreco() 
                + " | Estoque: " + p.getQuant() + " | Categoria: " + p.getCategoria()+" | Tamanho: "+p.getNumCalcado()+"|"); 
                
            }
                                            
        }
    }

    public void inserirProduto() {
        Scanner prod = new Scanner(System.in);
        Produto produto = new Produto();
        System.out.println("Preencha os dados do novo produto.");
        
        System.out.print("Nome:");
        produto.setNome(prod.nextLine());
        
        System.out.print("Preço:");
        produto.setPreco(prod.nextDouble());
        
        System.out.print("Quantidade de itens:");
        produto.setQuant(prod.nextInt());
        prod.nextLine();
        
        System.out.print("Categoria:");
        produto.setCategoria(prod.nextLine());
        
        System.out.print("Tamanho");
        if(produto.getCategoria().equals("top")){
            produto.setTamCamisa(prod.nextLine());
        }else if(produto.getCategoria().equals("botton")){
            produto.setTamCalca(prod.nextLine());
        }else{
            produto.setNumCalcado(prod.nextInt());
            prod.nextLine();
        }
        
        new CProduto().InserirNovoProduto(produto);
    }

    public void atualizarEstoque() {
        Scanner prod = new Scanner(System.in);
        CProduto cProd = new CProduto();
        System.out.println("Selecione o ID do produto que deseja atualizar o estoque:\n");
        cProd.mostrarProdutosParaSelecao();
        System.out.print("ID:");
        int idProduto = prod.nextInt();
        System.out.print("Digite quantos produtos serão adicionados ao estoque:");
        int quant = prod.nextInt();
        cProd.attEstoque(idProduto,quant);
        
    }

    public void excluirProduto() {
        Scanner prod = new Scanner(System.in);
        CProduto cProd = new CProduto();
        
        System.out.println("Selecione o ID do produto que deseja Excluir:\n");
        cProd.mostrarProdutosParaSelecao();
        System.out.print("ID:");
        int idProd=prod.nextInt();
        prod.nextLine();
        
        cProd.excluirProduto(idProd);

    }

    public void atualizarProduto() {
        Scanner prod = new Scanner(System.in);
        CProduto cProd = new CProduto();
        Produto produto = new Produto();
        
        System.out.println("Selecione o ID do produto que deseja Atualizar as informações:\n");
        cProd.mostrarProdutosParaSelecao();
        
        System.out.print("ID:");
        produto.setId(prod.nextInt());
        prod.nextLine();
        System.out.println("\n");
        
        
        System.out.println("Digite os dados atualizados do produto:");
        System.out.print("Nome:");
        produto.setNome(prod.nextLine());
        
        System.out.print("Preço:");
        produto.setPreco(prod.nextDouble());
        prod.nextLine();
                
        System.out.print("Categoria:");
        produto.setCategoria(prod.nextLine());
        
        System.out.print("Tamanho:");
        if(produto.getCategoria().equals("top")){
            produto.setTamCamisa(prod.nextLine());
        }else if(produto.getCategoria().equals("botton")){
            produto.setTamCalca(prod.nextLine());
        }else{
            produto.setNumCalcado(prod.nextInt());
            prod.nextLine();
        }
        
        cProd.atualizarProduto(produto);
    }
    
}
