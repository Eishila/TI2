package exercicio;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;



public class Principal {

	DAO dao = new DAO();
	
	//dao.conectar();
    private static void menu() {
    	System.out.println("");
        System.out.println("       -------------------------------------------------------------------");
        System.out.println("       | C A D A S T R O   D E    C L I E N T E S                        |");                     
        System.out.println("       |     1 - CADASTRAR NOVO CLIENTE                                  |");
        System.out.println("       |     2 - ATUALIZAR INFORMAÇÕES DO CLIENTE                        |");
        System.out.println("       |     3 - REMOVER CLIENTE                                         |");
        System.out.println("       |     4 - LISTAR TODOS OS CLIENTES                                |");
        //System.out.println("       |     5 - LISTAR INFORMAÇÕES DO CLIENTE POR CODIGO                |");
        //System.out.println("       |     6 - SALVAR DADOS DOS CLIENTES EM ARQUIVO                    |");
        System.out.println("       -------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Collection<Cliente> listaDeClientes = new ArrayList<Cliente>();
        Cliente clientes = new Cliente();
        int opcao;

        do {
        	
            menu();
            System.out.println("Digite a opção desejada: ");
            opcao = entrada.nextInt();

            switch (opcao) {
            case 1:
            	
            	System.out.println("Digite um Código para o Cliente!");
                clientes.setCodigo(entrada.nextLong());
                
                System.out.println("Digite o login do Cliente! ");
                clientes.setLogin(entrada.next());
                entrada.nextLine();
              
                System.out.println("Senha: ");
                clientes.setSenha(entrada.next());
                entrada.nextLine();
                
                System.out.println("sexo");
                clientes.setSenha(entrada.next());
                entrada.nextLine();
                
          
                listaDeClientes.add(clientes);
                break;
                
            case 2:
            	
            	System.out.println("Digite o Código do Cliente para Atualizar: ");
            	Scanner c = new Scanner(System.in);
            	Long codigo = c.nextLong();
            	break;
            	
            case 3:
            	
            	System.out.println("Digite o Código do Cliente para Remover: ");
               
            	break;
            	
            case 4:
            	
            	System.out.println("Lista de todos os Clientes:  ");
                for (Cliente cliente : listaDeClientes) {
                    System.out.println(clientes);
                }

                break;
               
            }	
        } while (opcao != 0); 
    }

}