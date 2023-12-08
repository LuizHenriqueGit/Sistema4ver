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
import regras_negocio.Fachada;


public class TelaEventos {
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
	private JTextField textField_3;
	private JLabel lblMedia_2;

	/**
	 * Launch the application.
	 */
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaEventos window = new TelaEventos();
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
	public TelaEventos() {
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
		frame.setTitle("Eventos");
		frame.setBounds(250, 495, 564, 393);
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
						int id = (Integer) table.getValueAt( table.getSelectedRow(), 0);
						//confirma��o
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirmar exclusão do evento "+ id + "?", "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarEvento(id);
							label.setText("exclus�o realizada");
							listagem();
						}
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_1.setBounds(380, 240, 160, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(141, 329, 641, 14);
		frame.getContentPane().add(label);

		lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(10, 206, 71, 28);
		frame.getContentPane().add(lblData);

		lblMedia = new JLabel("Descricao:");
		lblMedia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia.setBounds(10, 244, 71, 14);
		frame.getContentPane().add(lblMedia);
		
		JLabel lblMedia_1 = new JLabel("Capacidade:");
		lblMedia_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia_1.setBounds(10, 275, 71, 14);
		frame.getContentPane().add(lblMedia_1);
		
		lblMedia_2 = new JLabel("Preço:");
		lblMedia_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedia_2.setBounds(10, 304, 71, 14);
		frame.getContentPane().add(lblMedia_2);

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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(80, 300, 105, 20);
		frame.getContentPane().add(textField_3);

		button = new JButton("Criar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() ||  textField_1.getText().isEmpty() ||  textField_2.getText().isEmpty() ||  textField_3.getText().isEmpty()) {
						label.setText("Campo vazio.");
						return;
					}
					String data = textField.getText();
					String descricao = textField_1.getText();
					String capacidade = textField_2.getText();
					String preco = textField_3.getText();
					Fachada.criarEvento(data, descricao, Integer.parseInt(capacidade), Double.parseDouble(preco));
					label.setText("Evento criado!");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(36, 329, 95, 23);
		frame.getContentPane().add(button);

		/* 
		 
		
		button_2 = new JButton("Apagar evento selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						int id = (Integer) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.apagarEvento(id);
						label.setText("Cancelou evento de ID: " + id);
						listagem();
					}
					else
						label.setText("Selecione uma linha.");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}

			}
		});
		
		
		*/

		
		button_4 = new JButton("Listar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(380, 211, 160, 23);
		frame.getContentPane().add(button_4);
		
	}

	public void listagem () {
		try{
			List<Evento> lista = Fachada.listarEventos();
			
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Data");
			model.addColumn("Descrição");
			model.addColumn("Capacidade");
			model.addColumn("Preço");
			model.addColumn("Ttl Arrecadado");
			model.addColumn("Lotação");
			for(Evento p : lista) {
					model.addRow(new Object[]{  p.getId(), p.getData(), p.getDescricao(), 
							p.getCapacidade(), p.getPreco(), p.totalArrecadado(), p.lotado()});
					}

			table.setModel(model);
			label_4.setText("Resultados: " +lista.size()+ " linhas. Selecione uma linha.");
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(1).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
