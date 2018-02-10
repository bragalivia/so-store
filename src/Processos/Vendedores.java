package Processos;

import gui.Principal;
import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vendedores implements Runnable {
	private int id;
	private int status = 1;
	private static int idGenerator = 1;
	private Clientes meuCliente = null;
	private JLabel l;
	private JLabel l1;
	private JPanel pc;

	public Vendedores() {
		this.id = idGenerator++;
		l1 = new JLabel(new ImageIcon (getClass().getResource("/imagens/usuarios.png")));
		l = new JLabel("Vendedor "+id+" criado");
		Principal.addTexto("Vendedor "+id+" criado");
		pc = new JPanel();
		pc.setBackground(Color.WHITE);
		pc.add(l1);
		pc.add(l);
		Principal.getPanel1().add(pc);
		Principal.getPanel1().updateUI();
		Runnable r = this;
		Thread t = new Thread(r);
		t.setPriority(1);
		t.start();
		
		
		Compartilhada.vendedores.addLast(this);	
	}

	public void run() {
		while (true) {
			try {
				estadoDoVendedor();
				Clientes.cliente.acquire();
				Clientes.mutex.acquire();
				//checar por clientes 
				if(Compartilhada.clientes.size() > 0){ // tem clientes 
					//atende o primeiro da fila
					meuCliente = Compartilhada.clientes.get(0);
					meuCliente.setIdVendedor(id);
					Compartilhada.removerCliente(meuCliente);
				}
				Clientes.vendedor.release();
				Clientes.mutex.release();
				if(meuCliente != null) atendendoCliente(meuCliente);
				meuCliente = null;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void estadoDoVendedor() {
		this.setStatus(1);
		try {
			Clientes.print.acquire();
			Principal.addTexto(getMessage());
			l.setText(getMessage());
			System.out.println(getMessage());
			Clientes.print.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void atendendoCliente(Clientes c) {
		try {
			long temp = System.currentTimeMillis();
			this.setStatus(2);
			Clientes.print.acquire();
			Principal.addTexto(getMessage() + " " +  c.getIdCliente());
			l.setText(getMessage() + " " +  c.getIdCliente());
			System.out.println("--> " +getMessage() + "Cliente " +  c.getIdCliente());
			Clientes.print.release();
			while (System.currentTimeMillis() < temp + c.getTa()*1000) {
				Label();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	private void Label(){
		switch (new Random().nextInt(2)){
		case 0:
			l1.setIcon(new ImageIcon (getClass().getResource("/imagens/usuarios1.png")));
			break;
		case 1:
			l1.setIcon(new ImageIcon (getClass().getResource("/imagens/usuarios.png")));
			break;
		}
        }

	
	public String getMessage() {
		String message = "";
		switch (this.getStatus()) {
		case 1:
			message += " Vendedor " + this.id + " disponível";
			break;
		case 2:
			message += "Vendedor " + this.id + " atendendo ";
			break;
		}
		return message;
	}

	// GET e SET
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static int getIdGenerator() {
		return idGenerator;
	}

	public static void setIdGenerator(int idGenerator) {
		Vendedores.idGenerator = idGenerator;
	}

	public Clientes getMeuCliente() {
		return meuCliente;
	}

	public void setMeuCliente(Clientes meuCliente) {
		this.meuCliente = meuCliente;
	}
	
	

}