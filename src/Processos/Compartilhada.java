package Processos;

import java.util.LinkedList;

public class Compartilhada {
	

	private static Compartilhada singleton = null;
	public static Compartilhada getInstancia(){
		if(singleton == null) singleton = new Compartilhada();
		return singleton;
	}
	
	private Compartilhada(){
		
	}
	
	// lista de clientes
	public static LinkedList <Clientes> clientes = new LinkedList<Clientes>();
	// lista de vendedores
	public static LinkedList <Vendedores> vendedores = new LinkedList<Vendedores>();
	
	public static void removerCliente(Clientes meuCliente) {
		clientes.remove(meuCliente);
	}
	
	

}
