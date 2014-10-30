import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class GuiListaDir {
	JPanel aba;
	
	JLabel diretorioPRNLabel = new JLabel("Selecione a pasta de arquivos .PRN");;
	JTextField diretorioPRN = new JTextField("", 30);
	JLabel diretorioDATLabel  = new JLabel("Selecione o arquivo .DAT");
	JTextField diretorioDAT = new JTextField("", 30);
	JLabel destinoSalvarLabel = new JLabel("Selecione a pasta de Destino");
	JTextField destinoSalvar = new JTextField("", 30);
	
	JButton procurarPRN = new JButton("Procurar");
	JButton procurarDAT = new JButton("Procurar");
	JButton procurarDestinoSalvar = new JButton("Procurar");
	
	JButton limparaba1 = new JButton("Limpar");
	JButton limparaba2 = new JButton("Limpar");
	JButton limparaba3 = new JButton("Limpar");
	
	JButton gerarArquivos = new JButton("Gerar");
	JButton xls;
	JLabel inf;
	JFrame frame;
	JLabel status;
	JFileChooser fchooser;
	JTextArea tarea;
	List<File> listFilePRN = null;
	File fileDAT;
	JButton copiar;

	public void interfaceGui(Container pane) {
		
		// Definição de ABAS
		JTabbedPane tabs = new JTabbedPane();
		final String TAB1 = "Adicionar .PRN";
		final String TAB2 = "Adicionar .DAT";
		final String TAB3 = "Gerar Arquivo de Entrada";

		// ABA1
		JPanel panel1 = new JPanel();
		panel1.add(diretorioPRNLabel);
		panel1.add(diretorioPRN);
		panel1.add(procurarPRN);
		panel1.add(limparaba1);

		// ABA2
		JPanel panel2 = new JPanel();
		panel2.add(diretorioDATLabel);
		panel2.add(diretorioDAT);
		panel2.add(procurarDAT);
		panel2.add(limparaba2);

		// ABA3
		JPanel panel3 = new JPanel();
		panel3.add(destinoSalvarLabel);
		panel3.add(destinoSalvar);
		panel3.add(procurarDestinoSalvar);
		panel3.add(limparaba3);
		panel3.add(gerarArquivos);

		// Organização das abas
		tabs.add(panel1, TAB1);
		tabs.add(panel2, TAB2);
		tabs.add(panel3, TAB3);

		pane.add(tabs, BorderLayout.CENTER);
		
		procurarPRN.addActionListener(new FormActionListener(FormActionListener.PROCURAR_PRN,fchooser,frame, diretorioPRN));
		procurarDAT.addActionListener(new FormActionListener(FormActionListener.PROCURAR_DAT,fchooser,frame, diretorioDAT));
		procurarDestinoSalvar.addActionListener(new FormActionListener(FormActionListener.PROCURAR_DESTINO,fchooser,frame, destinoSalvar));
		
		//Limpar
		limparaba1.addActionListener(new FormActionListener(FormActionListener.LIMPAR,fchooser,frame, diretorioPRN));
		limparaba2.addActionListener(new FormActionListener(FormActionListener.LIMPAR,fchooser,frame, diretorioDAT));
		limparaba3.addActionListener(new FormActionListener(FormActionListener.LIMPAR,fchooser,frame, destinoSalvar));
		
		//Gerar
		gerarArquivos.addActionListener(new GuiListaDir.gerarArquivosAL());
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
		public void actionPerformed(ActionEvent e) {
			String teste = "";
			ValidaFormUtil formUtil =  new ValidaFormUtil(frame);
			
			if(formUtil.validaCampos(diretorioPRN.getText(), diretorioDAT.getText(),destinoSalvar.getText())){
				listFilePRN = new GeradorDAT().getListFile(diretorioPRN.getText());
				List<File> listFilePRNArquivos = new ArrayList<File>();
				
				fileDAT = new GeradorDAT().getFile(diretorioDAT.getText());
				String path = destinoSalvar.getText();
				int i = 1;
				
				for (File filePRN : listFilePRN) {
					
					/**	Verificação necessária para que o método não gere um arquivo DAT para um outro arquivo DAT 
					 ** que possa estar dentro da pasta que contém a lista de PRN **/
					if(formUtil.validaFilePRN(filePRN)){
						listFilePRNArquivos.add(filePRN);
						path = path+"/output"+i+".dat";
						GeradorDAT.gerarArquivosEntrada(frame, filePRN, fileDAT, path);
						path = destinoSalvar.getText();
						try {
							 teste = GeradorDAT.formataArquivosLista(listFilePRNArquivos,frame,path);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						i++;
					}
							
				}
				path = path+"/ARQUIVOS.dat";
				GeradorDAT.gerarListaArquivos(frame, path, teste);
				path = destinoSalvar.getText();
				/**	Variáveis precisam ser zeradas no final da execução para que os mesmos
				indices não fiquem em memória e gerem novos arquivos de conteúdo duplicados **/
				GeradorDAT.lista.clear();	
				
				JOptionPane.showMessageDialog(frame, "Arquivos gerados com sucesso na pasta: "+path);
			}
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
