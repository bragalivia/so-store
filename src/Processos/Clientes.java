package Processos;

import gui.Principal;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Clientes implements Runnable {

	public  static  Semaphore mutex = new Semaphore(1);
	public  static  Semaphore cliente = new Semaphore(0);
	public  static  Semaphore vendedor = new Semaphore(0);
	public  static  Semaphore print    = new Semaphore(1);
	public  static  Semaphore controleFila = new Semaphore(1);
	
	private int idCliente;
	private int ta;
	private int senha;
	private static int idGeneratorCliente = 1;
	private static int idGeneratorSenha = 1;
	private int status = 1;
	private int idVendedor = -1;
	private Thread t;
	private JLabel l,l1;
	private JPanel pc;
	
	public Clientes(){}
	
	public Clientes(int ta){
		this.ta = ta;
		this.idCliente = idGeneratorCliente;
		this.senha = idGeneratorSenha;
		idGeneratorCliente++;
		idGeneratorSenha++;
		Compartilhada.clientes.addLast(this);
		Principal.addTexto("Cliente "+idCliente+" criado");
		l1 = new JLabel(new ImageIcon (getClass().getResource("/imagens/usuarios.png")));
		l = new JLabel("Cliente "+idCliente+" criado");
		pc = new JPanel();
		pc.setBackground(Color.WHITE);
		pc.add(l1);
		pc.add(l);
		Principal.getPanel2().add(pc);
		Principal.getPanel2().updateUI();
		Runnable r = this;
	    t = new Thread(r);
		t.setPriority(1);
		t.start();
	}
	
	public void run(){
			try {
				antesAtendimento();
				mutex.acquire();    //down
				cliente.release();  //up
				mutex.release();    //down
				vendedor.acquire(); //down
				sendoAtendido();
							
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public void sendoAtendido(){
		try {
			long temp = System.currentTimeMillis();
			print.acquire();
			this.setStatus(2);
			Principal.addTexto("Cliente " +idCliente + " sendo atentido pelo vendedor " +idVendedor);
			l.setText(("Cliente " +idCliente + " sendo atentido pelo vendedor " +idVendedor));
		    System.out.println("Cliente " +idCliente + " sendo atentido pelo vendedor " +idVendedor);
			print.release();
            while(System.currentTimeMillis() < temp +getTa()*1000){
            	LabelCliente(); 
			}
            this.setStatus(3);
            l.setText((getMessage()));
            System.out.println(getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void antesAtendimento(){
		Principal.addTexto(getMessage());
		l.setText(getMessage());
		System.out.println(getMessage());
		System.out.println();
	}
private void LabelCliente(){
		
		switch (new Random().nextInt(2)){
		case 0:
			l1.setIcon(new ImageIcon (getClass().getResource("/imagens/usuarios.png")));
			break;
		case 1:
			l1.setIcon(new ImageIcon (getClass().getResource("/imagens/usuarios1.png")));
			break;
		}
        }
	public String getMessage(){
		String message = "";
		switch (this.getStatus()) {
		case 1:
			message +=" Cliente "+ this.idCliente+" na fila";
			break;
		case 2:
			message+=this.idCliente+" sendo atendido";
			break;
		
		case 3:
			message+="  Cliente "+this.idCliente+" foi atendido";
			break;
		}
		return message;
	}
	
	// GET e SET
	
	public int getTa() {
		return ta;
	}
	public void setTa(int ta) {
		this.ta = ta;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public static int getIdGeneratorCliente() {
		return idGeneratorCliente;
	}
	
	public static void setIdGeneratorCliente(int idGeneratorCliente) {
		Clientes.idGeneratorCliente = idGeneratorCliente;
	}
	
	public static int getIdGeneratorSenha() {
		return idGeneratorSenha;
	}
	
	public static void setIdGeneratorSenha(int idGeneratorSenha) {
		Clientes.idGeneratorSenha = idGeneratorSenha;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	
	
}