package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import regras_negocio.Fachada;

public class TelaPrincipal {
	private JFrame frame;
	private JMenu mnEventos;
	private JMenu mnParticipantes;
	private JMenu mnIngressos;
	private JLabel label;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try{
					Fachada.repositorio.carregarObjetos();
				}
				catch(Exception e){
					System.out.println("-->" + e.getMessage());
				}
			}
			});

		frame.setTitle("Sistema 4Ever");
		frame.setBounds(250, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("Inicializando...");
		label.setBounds(0, 0, 450, 249);
		ImageIcon imagem = new ImageIcon(getClass().getResource("/imagens/TESTE.png"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));//		label.setIcon(fotos);
		label.setIcon(imagem);
		frame.getContentPane().add(label);
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnEventos = new JMenu("Eventos");
		mnEventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaEventos tela = new TelaEventos();
			}
		});
		menuBar.add(mnEventos);

		mnParticipantes = new JMenu("Participantes");
		mnParticipantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaParticipantes tela = new TelaParticipantes();
			}
		});
		menuBar.add(mnParticipantes);
		
		mnIngressos = new JMenu("Ingressos");
		mnIngressos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaIngressos tela = new TelaIngressos();
			}
		});
		menuBar.add(mnIngressos);

		frame.setVisible(true);
		
		//----------timer----------------
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
				frame.setTitle("Sistema 4ver - "+ dt);
				
			}
		});
		timer.setRepeats(true);
		timer.setDelay(1000);	//1segundos
		timer.start();			//inicia o temporizador
	}
}
