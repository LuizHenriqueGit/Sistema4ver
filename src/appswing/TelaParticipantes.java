package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import regras_negocio.Fachada;


public class TelaParticipantes {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button_1;
	private JLabel label;
	private JLabel lblData;
	private JLabel lblMedia;
	private JLabel label_4;
	private JTextField textField_1;
	private JButton button;

	private JTextField textField;
	private JButton button_4;
	private JTextField textField_2;
	private JTable table_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaParticipantes window = new TelaParticipantes();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	
	/**
	 * Create the application.
	 */
	public TelaParticipantes() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		frame.setTitle("Participantes");
		frame.setBounds(250, 495, 632, 447);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 11, 510, 172);
		frame.getContentPane().add(scrollPane);
		
				table = new JTable() {
					public boolean isCellEditable(int rowIndex, int vColIndex) {
						return false;
					}
					
					
				};
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (table.getSelectedRow() >= 0) 
							label.setText("Participante selecionado (CPF) = "+ table.getValueAt( table.getSelectedRow(), 0));
						
						String cpf = (String) table.getValueAt(table.getSelectedRow(), 0);
						Participante participante = Fachada.repositorio.localizarParticipante(cpf);
						
						DefaultTableModel model = new DefaultTableModel();
						model.addColumn("Código");
						model.addColumn("Telefone");
						for(Ingresso p : participante.getIngressos()) {
							model.addRow(new Object[]{  p.getCodigo(), p.getTelefone()});
							}
						
						table_1.setModel(model);
						
						table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
						table_1.getColumnModel().getColumn(1).setMaxWidth(50);	
						table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
							
					}
				});
				table.setGridColor(Color.BLACK);
				table.setRequestFocusEnabled(false);
				table.setFocusable(false);
				table.setBackground(Color.WHITE);
				table.setFillsViewportHeight(true);
				table.setRowSelectionAllowed(true);
				table.setFont(new Font("Tahoma", Font.PLAIN, 12));
				table.setBorder(new LineBorder(new Color(0, 0, 0)));
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setShowGrid(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
	

		button_1 = new JButton("Apagar Selecionado");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 0);
						//confirma��o
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão do CPF: "+ cpf + "?", "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarParticipante(cpf);
							label.setText("Exclusão realizada com sucesso.");
							listagem();
						}
					}
					else
						label.setText("Selecione uma linha.");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_1.setBounds(446, 240, 160, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(new Color(33, 150, 243));
		label.setBounds(10, 385, 321, 14);
		frame.getContentPane().add(label);

		lblData = new JLabel("CPF:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(10, 206, 71, 28);
		frame.getContentPane().add(lblData);

		lblMedia = new JLabel("Nascimento:");
		lblMedia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia.setBounds(10, 244, 71, 14);
		frame.getContentPane().add(lblMedia);
		
		JLabel lblMedia_1 = new JLabel("Empresa:");
		lblMedia_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia_1.setBounds(10, 275, 71, 14);
		frame.getContentPane().add(lblMedia_1);

		label_4 = new JLabel("Selecione uma linha");
		label_4.setFont(new Font("Gadugi", Font.BOLD, 12));
		label_4.setBounds(10, 188, 315, 14);
		frame.getContentPane().add(label_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(80, 213, 105, 20);
		frame.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 242, 105, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(80, 271, 105, 20);
		frame.getContentPane().add(textField_2);

		button = new JButton("Criar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() ||  textField_1.getText().isEmpty())  {
						label.setText("Algum campo vazio.");
						return;
					}
					String cpf = textField.getText();
					String nascimento = textField_1.getText();
					String empresa = textField_2.getText();
					if (!textField_2.getText().isEmpty()) {
						Fachada.criarConvidado(cpf, nascimento, empresa);
					} else {
						Fachada.criarParticipante(cpf, nascimento);
					}
					label.setForeground(new Color(76,175,80));
					label.setText("Participante adicionado.");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					listagem();
				}
				catch(Exception ex) {
					label.setForeground(new Color(244,67,54));
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(34, 300, 95, 23);
		frame.getContentPane().add(button);
		
		button_4 = new JButton("Listar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(446, 209, 160, 23);
		frame.getContentPane().add(button_4);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(195, 213, 243, 172);
		frame.getContentPane().add(scrollPane_2);
		
		table_1 = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		scrollPane_2.setViewportView(table_1);
		table_1.setShowGrid(true);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setRowSelectionAllowed(true);
		table_1.setRequestFocusEnabled(false);
		table_1.setGridColor(Color.BLACK);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table_1.setFocusable(false);
		table_1.setFillsViewportHeight(true);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBackground(Color.WHITE);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		lblNewLabel = new JLabel("Listagem dos Ingressos");
		lblNewLabel.setBounds(250, 194, 145, 14);
		frame.getContentPane().add(lblNewLabel);
		
	}

	public void listagem () {
		try{
			List<Participante> lista = Fachada.listarParticipantes();
			
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("CPF");
			model.addColumn("Nascimento");
			model.addColumn("Idade");
			model.addColumn("Empresa");
			for(Participante p : lista) {
				if (p instanceof Convidado) {
					Convidado convidado = (Convidado) p;
					model.addRow(new Object[]{  convidado.getCpf(), convidado.getNascimento(), convidado.calcularIdade(), convidado.getEmpresa() });
				} else {
					model.addRow(new Object[]{  p.getCpf(), p.getNascimento(), p.calcularIdade(), "N/A", });
					}
				}
			table.setModel(model);
			label_4.setText("Resultados: " + lista.size()+ " linhas. Selecione uma linha.");
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(1).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setForeground(new Color(244,67,54));
			label.setText(erro.getMessage());
		}
	}
}
