package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.MetodHeuristico;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class vista extends JFrame implements Observer{

	private JPanel contentPane;
	private JTextArea textArea;
	private PanelEstrella pnlEstrella;
	
	MetodHeuristico puzzle= new MetodHeuristico();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista frame = new vista();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.getContentPane().setBackground(Color.decode("#4C5280"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etiqueta=new JLabel("Estrella Magica Matematica Heuristica");
		
		etiqueta.setHorizontalAlignment(JLabel.CENTER);
		
		etiqueta.setFont(new Font("Verdana", Font.BOLD, 16));
		etiqueta.setBounds(5,2,750,30);
		etiqueta.setForeground(Color.decode("#050a30"));
		add(etiqueta);
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 20));
		textArea.setBounds(60, 30, 350, 130);
		textArea.setBackground(Color.decode("#4C5280"));
		textArea.setForeground(Color.white);
		contentPane.add(textArea);
		
		pnlEstrella = new PanelEstrella(puzzle.M);
		pnlEstrella.setBounds(60, 160, 555, 400);
		contentPane.add(pnlEstrella);
		
		JButton btnJugarMaquinaCon = new JButton("Comenzar");
		btnJugarMaquinaCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Thread hilo= new Thread(puzzle);
				hilo.start();
			}
		});
		btnJugarMaquinaCon.setBounds(509, 58, 119, 23);
		btnJugarMaquinaCon.setBackground(Color.decode("#050a30"));
		btnJugarMaquinaCon.setForeground(Color.white);
		contentPane.add(btnJugarMaquinaCon);
		
		puzzle.addObserver(this);
	}

	@Override
	public void update(Observable puzzle, Object objeto) {
		int [][]M= (int[][])objeto;
		
		textArea.setText("");
		for (int f = 0; f < 5; f++) {
			String linea="";
			for (int c = 0; c < 7; c++) {
				linea=linea + "["+M[f][c]+"]";
			}
			textArea.append(linea+"\n");
		}
		pnlEstrella.setMatriz(M);
		pnlEstrella.repaint();
	}
}
