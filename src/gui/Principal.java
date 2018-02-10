package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;



import javax.swing.JFrame;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.ScrollPaneConstants;

public class Principal extends JFrame {
	
	{
		
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;
	static JPanel p1;
	static JPanel p2;
    private JScrollPane jsp,jsp1,logScroll;
    private static JTextArea logArea;
	private JPanel painelTexto;
	
	
	
	
	public static void main(String[] args) {
		new Principal();
	}

	public Principal() {
		
		Tela t = new Tela();
		JPanel painelResultado = new JPanel(new BorderLayout());
		JPanel painelLoja = new JPanel(new GridLayout(1,2));
		
		p1 = new JPanel();
		BoxLayout bl = new BoxLayout(p1, BoxLayout.PAGE_AXIS);
		p1.setBackground(Color.white);
		p1.setLayout(bl);
		jsp = new JScrollPane(p1);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBorder(null);
		
		
	    p2 = new JPanel();
	    BoxLayout bl1 = new BoxLayout(p2, BoxLayout.PAGE_AXIS);
		p2.setBackground(Color.white);
		p2.setLayout(bl1);
		jsp1 = new JScrollPane(p2);
		jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp1.setBorder(null);
		
        logArea = new JTextArea();
		
		logScroll = new JScrollPane(logArea);
		logScroll.setPreferredSize(new Dimension (250, 170));
		logScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		logScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		logScroll.setBorder(BorderFactory.createTitledBorder("Log"));
		
		painelTexto = new JPanel();
		painelTexto.setLayout(new BorderLayout());
		painelTexto.add(logScroll);

		painelResultado.add(t, BorderLayout.WEST);
		painelLoja.add(jsp);
		painelLoja.add(jsp1);
		painelResultado.add(painelLoja);
		painelResultado.add(painelTexto,BorderLayout.SOUTH);
		
		this.getContentPane().add(painelResultado);
		this.setSize(1000, 500);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Loja SO - Leandro e Livia");
	}
	public static JPanel getPanel1(){
		return p1;
	}
	public static JPanel getPanel2(){
		return p2;
	}
	public static void addTexto (String texto){
		logArea.append(texto + "\n");
	}

}
