import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GuiListaDir {

	// Variaveis
	JPanel aba;
	JTextField diretorioPRN;
	JTextField diretorioDAT;
	JTextField destinoSalvar;
	JButton procurarPRN;
	JButton procurarDAT;
	JButton procurarDestinoSalvar;
	JButton limparaba1;
	JButton limparaba2;
	JButton limparaba3;
	JButton gerarArquivos;
	JButton xls;
	JLabel inf;
	JFrame frame;
	JLabel status;
	JFileChooser fchooser;
	JTextArea tarea;
	List<File> listFilePRN;
	File fileDAT;
	JButton copiar;

	public void interfaceGui(Container pane) {
		// Definição de ABAS
		JTabbedPane tabs = new JTabbedPane();
		final String TAB1 = "Adicionar .PRN";
		final String TAB2 = "Adicionar .DAT";
		final String TAB3 = "Gerar Arquivo de Entrada";

		// JTextField definição do valor dos campos.
		diretorioPRN = new JTextField("Selecionar a pasta com arquivos .PRN", 30);
		diretorioDAT = new JTextField("Selecionar o arquivo .DAT", 30);
		destinoSalvar = new JTextField("Pasta de Destino", 30);

		// //JTextField definição do valor dos botões
		procurarPRN = new JButton("Procurar");
		procurarDAT = new JButton("Procurar");
		procurarDestinoSalvar = new JButton("Procurar");
		limparaba1 = new JButton("Limpar");
		limparaba2 = new JButton("Limpar");
		limparaba3 = new JButton("Limpar");
		gerarArquivos = new JButton("Gerar");
		

		// ABA1
		JPanel panel1 = new JPanel();
		panel1.add(diretorioPRN);
		panel1.add(procurarPRN);
		panel1.add(limparaba1);

		// ABA2
		JPanel panel2 = new JPanel();
		panel2.add(diretorioDAT);
		panel2.add(procurarDAT);
		panel2.add(limparaba2);

		// ABA3
		JPanel panel3 = new JPanel();
		panel3.add(destinoSalvar);
		panel3.add(procurarDestinoSalvar);
		panel3.add(limparaba3);
		panel3.add(gerarArquivos);

		// Organização das abas
		tabs.add(panel1, TAB1);
		tabs.add(panel2, TAB2);
		tabs.add(panel3, TAB3);

		pane.add(tabs, BorderLayout.CENTER);
		
		//Metodos
		
		//Procurar
		this.procurarPRN.addActionListener(new GuiListaDir.bprocurarPRN());
		this.procurarDAT.addActionListener(new GuiListaDir.bprocurarDAT());
		this.procurarDestinoSalvar.addActionListener(new GuiListaDir.bprocurarDestinoSalvar());
		
		//Limpar
		this.limparaba1.addActionListener(new GuiListaDir.blimparAba1());
		this.limparaba2.addActionListener(new GuiListaDir.blimparAba2());
		this.limparaba3.addActionListener(new GuiListaDir.blimparAba3());
		
		//Gerar
		this.gerarArquivos.addActionListener(new GuiListaDir.gerarArquivosAL());
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (aba.getLayout());
		cl.show(aba, (String) evt.getItem());
	}

	private static void executarSistema() {
		JFrame frame = new JFrame(":: GERADOR DO ARQUIVO DE ENTRADA ::");
		frame.setDefaultCloseOperation(3);
		frame.setLocation(420, 250);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GuiListaDir exe = new GuiListaDir();
		exe.interfaceGui(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);
	}
	
	public class gerarArquivosAL implements ActionListener {
		public gerarArquivosAL() {}

		public void actionPerformed(ActionEvent e) {

			String path = null;
			
			if(GuiListaDir.this.diretorioPRN.getText() == null || GuiListaDir.this.diretorioPRN.getText().equals("") ||  GuiListaDir.this.diretorioPRN.getText().equals("Selecionar a pasta com arquivos .PRN"))
			{	
				JOptionPane.showMessageDialog(frame,
				    "Favor selecionar o arquivo/pasta com o(s) arquivo(s) .PRN",
				    "Mensagem de Erro",
				    JOptionPane.ERROR_MESSAGE);
				}
			else{
				GuiListaDir.this.listFilePRN = new GeradorDeDiretorio().getListFile(GuiListaDir.this.diretorioPRN.getText());
			}
			
			if(GuiListaDir.this.diretorioDAT.getText() == null || GuiListaDir.this.diretorioDAT.getText().equals("") ||  GuiListaDir.this.diretorioDAT.getText().equals("Selecionar o arquivo .DAT"))
			{	
				JOptionPane.showMessageDialog(frame,
				    "Favor selecionar o arquivo .DAT",
				    "Mensagem de Erro",
				    JOptionPane.ERROR_MESSAGE);
				}
			else{
			GuiListaDir.this.fileDAT = new GeradorDeDiretorio().getFile(GuiListaDir.this.diretorioDAT.getText());
			}
			
			if(GuiListaDir.this.destinoSalvar.getText() == null || GuiListaDir.this.destinoSalvar.getText().equals("") ||  GuiListaDir.this.destinoSalvar.getText().equals("Pasta de Destino"))
			{	
				JOptionPane.showMessageDialog(frame,
				    "Favor selecionar a pasta de destino",
				    "Mensagem de Erro",
				    JOptionPane.ERROR_MESSAGE);
				}
			else{
			path = GuiListaDir.this.destinoSalvar.getText();
			}
			for (File filePRN : GuiListaDir.this.listFilePRN) {
				try {
					GeradorDeDiretorio.gerarArquivosEntrada(frame, filePRN, GuiListaDir.this.fileDAT, path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public class bprocurarPRN implements ActionListener {
		public bprocurarPRN() {}

		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos .PRN","prn");
			GuiListaDir.this.fchooser = new JFileChooser();
			GuiListaDir.this.fchooser.setFileFilter(filter);
			GuiListaDir.this.fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			GuiListaDir.this.fchooser.enableInputMethods(true);
			GuiListaDir.this.fchooser.showSaveDialog(GuiListaDir.this.frame);

			GuiListaDir.this.diretorioPRN.setText(GuiListaDir.this.fchooser
					.getSelectedFile().getAbsolutePath());
		}
	}

	public class bprocurarDAT implements ActionListener {
		public bprocurarDAT() {}

		public void actionPerformed(ActionEvent e) {

			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos .DAT","dat");
			GuiListaDir.this.fchooser = new JFileChooser();
			GuiListaDir.this.fchooser.setFileFilter(filter);
			GuiListaDir.this.fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			GuiListaDir.this.fchooser.enableInputMethods(true);
			GuiListaDir.this.fchooser.showSaveDialog(GuiListaDir.this.frame);

			GuiListaDir.this.diretorioDAT.setText(GuiListaDir.this.fchooser
					.getSelectedFile().getAbsolutePath());
		}
	}

	public class bprocurarDestinoSalvar implements ActionListener {
		public bprocurarDestinoSalvar() {}

		public void actionPerformed(ActionEvent e) {

			GuiListaDir.this.fchooser = new JFileChooser();
			GuiListaDir.this.fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			GuiListaDir.this.fchooser.enableInputMethods(true);
			GuiListaDir.this.fchooser.showSaveDialog(GuiListaDir.this.frame);

			GuiListaDir.this.destinoSalvar.setText(GuiListaDir.this.fchooser
					.getSelectedFile().getAbsolutePath());
		}
	}
	
	public class blimparAba1 implements ActionListener {
		public blimparAba1() {}

		public void actionPerformed(ActionEvent e) {
			GeradorDeDiretorio.lista.clear();

			GuiListaDir.this.diretorioPRN.setText("");
		}
	}
	
	public class blimparAba2 implements ActionListener {
		public blimparAba2() {}

		public void actionPerformed(ActionEvent e) {
			GeradorDeDiretorio.lista.clear();

			GuiListaDir.this.diretorioDAT.setText("");
		}
	}
	
	public class blimparAba3 implements ActionListener {
		public blimparAba3() {}

		public void actionPerformed(ActionEvent e) {
			GeradorDeDiretorio.lista.clear();

			GuiListaDir.this.destinoSalvar.setText("");
		}
	}
	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				executarSistema();
			}
		});

	}
}
