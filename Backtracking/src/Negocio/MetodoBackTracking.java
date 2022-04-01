package Negocio;

import java.awt.Point;
import java.util.Observable;
import java.util.Vector;

public class MetodoBackTracking extends Observable implements Runnable
{
	 public int[][] M = {{-1, -1, -1, 0, -1, -1, -1},
			    {0, -1, 0, -1, 0, -1, 0},
			    {-1, 0, -1, -1, -1, 0, -1},
			    {0, -1, 0, -1, 0, -1, 0},
			    {-1, -1, -1, 0, -1, -1, -1}};

			    public int[][] RM = {
			        {0, 0, 0, 3, 0, 0, 0},
			        {1, 0, 11, 0, 12, 0, 2},
			        {0, 8, 0, 0, 0, 5, 0},
			        {4, 0, 7, 0, 9, 0, 6},
			        {0, 0, 0, 10, 0, 0, 0}};
			    public boolean Encontrado = false;
				public int C = 0;
				public Point Casilla = null;
				public Vector<Integer> Usados = new Vector<Integer>();
	@Override
	public void run() 
	{
		Vector<Integer> lisPasados= new Vector<Integer>();
		M= backtracking(M, lisPasados,false);
	}
	
	public int[][] backtracking(int[][]m, Vector lisPasados, boolean pillado)
	{
		if (pillado == true) {

			Usados.add(m[Casilla.x][Casilla.y]);
		}
			if(esEstadoFinal(m))
			{
				System.out.println("llego al final");
				imprimir(m);
				return m;
			}
			else 
			{	
				int mayHeuristica=0;
				int l = 0;
				int cont = 0;
				int Almacen = 0;
				Vector soluciones= expandir(m);		
				System.out.print(soluciones.size() + "   ");
				int CantidadAumento = 12;
				if (pillado == true)
					l = C;
				int[][] caminoFavorable= (int[][])soluciones.get(0);
				for (int i = 0; i < soluciones.size(); i++) 
				{						
					int[][] solucX= (int[][])soluciones.get(l);
					try {
						Thread.sleep(500); 
					}
					catch(Exception e){}
					
					this.setChanged(); 
					this.notifyObservers(solucX); 
					int h= funcionHeuristica(solucX);
					
				    if(h > mayHeuristica && !listaPasados(solucX, lisPasados))
				    {				
						mayHeuristica = h;
						cont = l;
						caminoFavorable = solucX;
						C++;
						System.out.print(true);
						break;

					} else {
						Almacen += CantidadAumento;
						l = Almacen + C;
					}	
				}
				lisPasados.add(caminoFavorable);
			    System.out.println("matriz ");
				int [][]soluc= backtracking(caminoFavorable, lisPasados, true);
				return soluc!=null?soluc:null;
			}

			
	}
	
	private boolean listaPasados(int[][] m, Vector lisPasados) {


		for (int i = 0; i < lisPasados.size(); i++) {
			int [][]aux= (int[][])lisPasados.get(i);

			int cont=0;
			for (int f = 0; f < m.length; f++) {
				for (int c = 0; c < m[f].length; c++) {
					if(m[f][c] > 0)
					if(m[f][c]==aux[f][c])
						cont++;
				}
			}
			
				return cont==12?true:false;
		}
		return false;
	}

	private void imprimir(int[][] M) 
	{
		for (int f = 0; f < M.length; f++) {
			String linea="";
			for (int c = 0; c < M[f].length; c++) {
				linea=linea+"["+M[f][c]+"]";
			}
			System.out.println(linea);
		}
		System.out.println();
	}

	public boolean esEstadoFinal(int[][] M) 
	{
		int cont=0;
		
		int indice=0;
		for (int f = 0; f < M.length; f++) {
			for (int c = 0; c < M[f].length; c++) {
				indice++;
				if(M[f][c] > 0) {
				if(M[f][c]==RM[f][c])
					cont++;
				}
			}
		}
		return cont==12?true:false;
	}

	public int funcionHeuristica(int[][]m)
	{
	int Cambio = 0;
	int contH = 0;
	for (int f = 0; f < m.length; f++) {
		for (int c = 0; c < m[f].length; c++) {

			int ele = m[f][c];
			if (ele > 0) {
				contH = 0;

				if (ele == RM[f][c] && !Verificar(ele)) {

					Encontrado = true;
					Casilla = new Point(f, c);
					contH++;

				} else {
					Encontrado = false;
					contH = 0;

				}
				if (contH == 1) {
					Cambio = Cambio + 1;
				}
			}
		}
	}
	return Cambio;
		
	}
	private boolean Verificar(int Dato) {
		for (int i = 0; i < Usados.size(); i++) {
			if ((int) Usados.get(i) == Dato) {
				
				return true;
			}

		}
		return false;
	}
	public Vector expandir(int[][]m)
	{
		 Vector soluciones = new Vector();
	        for (int f = 0; f < m.length; f++) {
	            for (int c = 0; c < m[f].length; c++) {
	                if (m[f][c] == 0) {

	                    int[][] clonM1 = clonar(m);
	                    clonM1[f][c] = 1;
	                    soluciones.add(clonM1);

	                    int[][] clonM2 = clonar(m);
	                    clonM2[f][c] = 2;
	                    soluciones.add(clonM2);

	                    int[][] clonM3 = clonar(m);
	                    clonM3[f][c] = 3;
	                    soluciones.add(clonM3);

	                    int[][] clonM4 = clonar(m);
	                    clonM4[f][c] = 4;
	                    soluciones.add(clonM4);

	                    int[][] clonM5 = clonar(m);
	                    clonM5[f][c] = 5;
	                    soluciones.add(clonM5);

	                    int[][] clonM6 = clonar(m);
	                    clonM6[f][c] = 6;
	                    soluciones.add(clonM6);

	                    int[][] clonM7 = clonar(m);
	                    clonM7[f][c] = 7;
	                    soluciones.add(clonM7);

	                    int[][] clonM8 = clonar(m);
	                    clonM8[f][c] = 8;
	                    soluciones.add(clonM8);

	                    int[][] clonM9 = clonar(m);
	                    clonM9[f][c] = 9;
	                    soluciones.add(clonM9);

	                    int[][] clonM10 = clonar(m);
	                    clonM10[f][c] = 10;
	                    soluciones.add(clonM10);

	                    int[][] clonM11 = clonar(m);
	                    clonM11[f][c] = 11;
	                    soluciones.add(clonM11);

	                    int[][] clonM12 = clonar(m);
	                    clonM12[f][c] = 12;
	                    soluciones.add(clonM12);

	                }
	            }
	        }
	        return soluciones;
	}
	
	public int[][] clonar(int[][]M)
	{
		int[][] clonM= new int[5][7];
		for (int f = 0; f < 5; f++) {
			for (int c = 0; c < 7; c++) {
				clonM[f][c]= M[f][c];
			}				
		}
		return clonM;
	}
}
