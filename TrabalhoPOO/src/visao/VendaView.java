package visao;

import controlador.CCliente;
import controlador.CPagamento;
import controlador.CProduto;
import controlador.CVenda;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.dominio.Produto;
import modelo.dominio.Venda;
import modelo.dominio.Venda_itens;

/**
 *
 * @author Alexssander, Lucas, Pablo
 */

public class VendaView {
    
    public void menu(){
        int opcao=1;
        Scanner ven = new Scanner(System.in);
        
        do{
            System.out.println("\n=== Menu de Venda ===");
            System.out.println("0-Voltar a página anterior");
            System.out.println("1-Realizar venda");
            System.out.println("2-Cancelar venda");
            System.out.print("Escolha a ação que deseja realizar:");
            opcao = ven.nextInt();
            ven.nextLine();
            if(opcao<0 || opcao>2){
                System.out.println("Opção inválida");
            }else{
                new CVenda().escolherOpcao(opcao);
            }
            
            
        }while(opcao!=0);
    }

    public void realizarVenda() {
        Scanner ven = new Scanner(System.in);
        Venda venda= new Venda();
        ArrayList<Venda_itens> itens = new ArrayList<>();
        
        System.out.println("Selecione os produtos para a venda:");
        new CProduto().mostrarProdutosParaSelecao();
        
        int id;
        int contarProd=0;
        
        while(true){
            System.out.println("ID do produto(Digite 0 para confirmar os produtos):");
            id=ven.nextInt();
            
            if(id==0){
                break;
            }
            
            
            Venda_itens item= new Venda_itens();
            item.setIdProd(id);
            
            System.out.println("|Digite a quantidade do produto desejada:");
            int quantidade = ven.nextInt();
            item.setQuantItens(quantidade);
            
            Produto checando = new CProduto().selecionarProduto(id);
            
            if(quantidade>checando.getQuant()){
                System.out.println("Não temos a quantidade requisitada em estoque, só temos "+checando.getQuant()+" unidades disponíveis para venda");
            }else{
                contarProd++;
                venda.setValor(checando.getPreco()*quantidade);
                item.setPreco(checando.getPreco());
                item.setPrecoTotal(checando.getPreco()*quantidade);
                itens.add(item);
            }
            
            
        }
        venda.setQuant(contarProd);
        
        
        System.out.println("Digite o ID do Cliente que vai fazer a compra:");
        new CCliente().listarClientesParaSelecao();
        
        System.out.print("\nID:");
        int idCliente=ven.nextInt();
        while(idCliente<1 || idCliente> new CCliente().buscarMaxId()){
            System.out.println("Selecione um id válido para Cliente");
            System.out.print("ID:");
            idCliente=ven.nextInt();
            
        }
        
        venda.setIdCliente(idCliente);
        
        System.out.println("Digite o ID do pagamento para fazer a compra:");
        new CPagamento().listarPagamentoParaSelecao();
        System.out.print("ID:");
        int idPagamento=ven.nextInt();
        
        while(idPagamento<1 || idPagamento>new CPagamento().buscarMaxId()){
            System.out.println("Selecione um id válido para Pagamento");
            System.out.print("ID:");
            idPagamento=ven.nextInt();
        }
        
        venda.setIdPag(idPagamento);
        
        new CVenda().inserirVenda(venda,itens);
        
    }

    public void cancelarVenda() {
        Scanner ven = new Scanner(System.in);
        CVenda controlador = new CVenda();
        
        System.out.println("Digite o ID da venda para cancelá-la");
        controlador.listarVendasParaSelecao();
        
        System.out.print("ID:");
        int id=ven.nextInt();
        
        controlador.cancelarVenda(id);
        
    }
    
    public void listarVendas(){
        List<Venda> vendas = new ArrayList<>();
        vendas= new CVenda().listarVendas();
        
        System.out.println("\n|======Lista de Vendas Emitidas======|");
        System.out.println("|___________________________________|");
        for(Venda v: vendas){
            System.out.println("ID da Venda: "+v.getIdVenda()+" | ID do Cliente: "+v.getIdCliente() +" | Quantidade de produtos: "+v.getQuant()+ " | Valor da compra: "+v.getValor()+" | ID do pagamento: "+v.getIdPag()+" |");
        }
        
    }
    
    
}
