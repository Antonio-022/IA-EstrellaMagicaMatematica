package Presentacion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanelEstrella extends JPanel 
{
	int [][]M;
	
	public PanelEstrella(int [][]M) 
	{
		this.M = M;
	}
	
	public void setMatriz(int[][]M)
	{
		this.M= M;
	}
	
	
	//FUNCION QUE SE ENCARGA DE SACAR UN RADIOGRAFIA GRAFICA COMPLETA DE LA MATRIZ
	@Override
	public void paint(Graphics g) { 
		
		Graphics2D g2= (Graphics2D)g;
		
		Image imgTablero= Toolkit.getDefaultToolkit().getImage("fondo.png");
		g2.drawImage(imgTablero, 0, 0, this);
		
		for (int f = 0; f < 5; f++) {
			for (int c = 0; c < 7; c++) {
				if(M[f][c]!=-1)
				{
					Image imgFicha=null;
					if(M[f][c]==0) 
						imgFicha= Toolkit.getDefaultToolkit().getImage("0.png");
					if(M[f][c]==1)
						imgFicha= Toolkit.getDefaultToolkit().getImage("1.png");
					if(M[f][c]==2)
						imgFicha= Toolkit.getDefaultToolkit().getImage("2.png");
					if(M[f][c]==3)
						imgFicha= Toolkit.getDefaultToolkit().getImage("3.png");
					if(M[f][c]==4)
						imgFicha= Toolkit.getDefaultToolkit().getImage("4.png");
					if(M[f][c]==5)
						imgFicha= Toolkit.getDefaultToolkit().getImage("5.png");
					if(M[f][c]==6)
						imgFicha= Toolkit.getDefaultToolkit().getImage("6.png");
					if(M[f][c]==7)
						imgFicha= Toolkit.getDefaultToolkit().getImage("7.png");
					if(M[f][c]==8)
						imgFicha= Toolkit.getDefaultToolkit().getImage("8.png");
					if(M[f][c]==9)
						imgFicha= Toolkit.getDefaultToolkit().getImage("9.png");
					if(M[f][c]==10)
						imgFicha= Toolkit.getDefaultToolkit().getImage("10.png");
					if(M[f][c]==11)
						imgFicha= Toolkit.getDefaultToolkit().getImage("11.png");
					if(M[f][c]==12)
						imgFicha= Toolkit.getDefaultToolkit().getImage("12.png");
					if(imgFicha!=null)
						g2.drawImage(imgFicha, (c*75)+18, (f*75)+10, this);
						
				}
			}
		}
	}
	
	private void imprimirSolucion(int[][] m) {
		System.out.println();
		for (int f = 0; f < m.length; f++) {
			for (int c = 0; c < m.length; c++) {
				System.out.print("["+m[f][c]+"]");
			}
			System.out.println();
		}
	}

}
