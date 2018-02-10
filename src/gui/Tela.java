package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Processos.Clientes;
import Processos.Vendedores;

public class Tela extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField jtfQtdeVendedores;
	private JButton botaoExecutar;
	private JTextField jtfTempoAtendimento;
	private JButton botaoCriarCliente;

	public Tela(){
		initGUI();
	}
	
	private void initGUI(){
		{
			GridLayout gd = new GridLayout(0,1);
			this.setLayout(gd);
			this.setPreferredSize(new Dimension(250, 250));
			{
				jtfQtdeVendedores = new JTextField();
				this.add(jtfQtdeVendedores);
				jtfQtdeVendedores.setBorder(BorderFactory.createTitledBorder("Adicionar Vendedores"));
			}
			{
				botaoExecutar = new JButton();
				this.add(botaoExecutar);
				botaoExecutar.setText("Executar");
				botaoExecutar.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						eventoCriarVendedores(evt);
					}
				});
			}
			{
				jtfTempoAtendimento = new JTextField();
				this.add(jtfTempoAtendimento);
				jtfTempoAtendimento.setBorder(BorderFactory.createTitledBorder("Tempo de Atendimento"));
			}
			{
				botaoCriarCliente = new JButton();
				this.add(botaoCriarCliente);
				botaoCriarCliente.setText("Criar Cliente");
				botaoCriarCliente.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent evt) {
						eventoCriarClientes(evt);
					}
				});
			}
			
		}

	}
	
	private void eventoCriarVendedores(MouseEvent evt) {
		int num = Integer.parseInt(jtfQtdeVendedores.getText());
		
		for (int i = 0; i < num; i++) {
			new Vendedores();
		}
		//botaoExecutar.setEnabled(false);
		
		
	}
	

	private void eventoCriarClientes(MouseEvent evt) {
		int num = Integer.parseInt(jtfTempoAtendimento.getText());
		new Clientes(num);
	}

}
