package networkEcho_III;

import java.io.Serializable;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Clara Anna
 */
public class Matriz implements Serializable{
	private int linhas;
	private int colunas;
	private int[][] matriz;
	private static final long serialVersionUID = 1L;

	
	
	


	public Matriz(int linhas, int colunas) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		 this.matriz = new int[linhas][colunas];
	        Random gerador = new Random();
	        for(int i = 0; i < matriz.length; i++){
	            for (int j = 0; j < matriz[0].length; j++){
	                this.matriz[i][j] = gerador.nextInt(10);
	            }
	        }			
	}
	
	public Matriz(int linhas, int colunas, int m) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		this.matriz = new int[linhas][colunas];		
	}
    
    public void imprimeMatriz(int [][] m){
        for(int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    
    public static int[][] matrizTansposta(int[][] matriz) {
        int[][] retorno = new int[matriz[0].length][matriz.length];//invertendo a linha com a coluna
        for (int lin = 0; lin < retorno.length; lin++) {
        	
            for (int col = 0; col < retorno[lin].length; col++) {
                retorno[lin][col] = matriz[col][lin];
                System.out.print("");
            }
            
        }
        return retorno;
    }
    
    /*public static void main(String[] args) {
    	Matriz m = new Matriz (3,2);
    	m.imprimeMatriz(m.matriz);
    	
    	System.out.print("\n\n");
    	
    	int[][] t = m.matrizTansposta(m.matriz);
    	m.imprimeMatriz(t);
    	
    }*/
}
    
