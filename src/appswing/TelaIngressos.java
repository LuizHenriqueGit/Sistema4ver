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

import modelo.Evento;
import modelo.Ingresso;
import regras_negocio.Fachada;


public class TelaIngressos {
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

	/**
	 * Launch the application.
	 */
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaIngressos window = new TelaIngressos();
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
	public TelaIngressos() {
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
		frame.setTitle("Ingressos");
		frame.setBounds(250, 495, 597, 372);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 11, 455, 172);
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
							label.setText("selecionado="+ table.getValueAt( table.getSelectedRow(), 0));
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
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		button_1 = new JButton("Apagar Selecionado");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String codigo = (String) table.getValueAt( table.getSelectedRow(), 0);
						//confirma��o
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma exclus�o "+ codigo, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarIngresso(codigo);
							label.setText("Exclusão realizada!");
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
		button_1.setBounds(411, 240, 160, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(new Color(33, 150, 243));
		label.setBounds(141, 302, 700, 14);
		frame.getContentPane().add(label);

		lblData = new JLabel("ID evento:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(10, 206, 71, 28);
		frame.getContentPane().add(lblData);

		lblMedia = new JLabel("CPF:");
		lblMedia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia.setBounds(10, 244, 71, 14);
		frame.getContentPane().add(lblMedia);
		
		JLabel lblMedia_1 = new JLabel("Telefone");
		lblMedia_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia_1.setBounds(10, 275, 71, 14);
		frame.getContentPane().add(lblMedia_1);

		label_4 = new JLabel("Selecione uma linha");
		label_4.setFont(new Font("Gadugi", Font.BOLD, 12));
		label_4.setBounds(53, 183, 315, 14);
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
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String id = textField.getText();
					String cpf = textField_1.getText();
					String telefone = textField_2.getText();
					Fachada.criarIngresso(Integer.parseInt(id), cpf, telefone);
					label.setForeground(new Color(76,175,80));
					label.setText("Ingresso criado para o evento de ID " + id + ".");
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
		button.setBounds(36, 300, 95, 23);
		frame.getContentPane().add(button);

		button_4 = new JButton("Listar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(411, 209, 160, 23);
		frame.getContentPane().add(button_4);
			
	}

	public void listagem () {
		try{
			List<Ingresso> lista = Fachada.listarIngressos();
			
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Código");
			model.addColumn("Telefone");
			model.addColumn("Preço Ingresso");
			model.addColumn("Preço Evento");
			model.addColumn("Data Evento");
			model.addColumn("Idade");
			for(Ingresso p : lista) {
					model.addRow(new Object[]{  p.getCodigo(), p.getTelefone(), p.calcularPreco(), 
							p.getEvento().getPreco(), p.getEvento().getData(), p.getParticipante().calcularIdade()});
					}

			table.setModel(model);
			label_4.setText("Resultados: " +lista.size()+ " linhas. Selecione uma linha.");
			
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