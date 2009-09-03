package com.codejam.practiceproblems.welcometocodejam;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class A implements Runnable {
	
	public static void main(String arga[]) {
		new Thread(new A()).start();
	}

	@Override
	public void run() {
		try {
			solve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void solve() throws IOException {
		Scanner in = new Scanner(new File("./files/welcometocodejam/C-small-attempt0.in"));
		PrintWriter out = new PrintWriter("./files/welcometocodejam/C-small-attempt0.out");

		
		int tn = Integer.parseInt(in.nextLine());
		String example;
		for (int i = 1; i <= tn; i++) {
			example = in.nextLine();
			if(i>=48){
				int re = calcularResult(example);
				String result = ""+re;
				if(result.length()==4){
					System.out.println("Case #" + i + ": " + result);
					out.println("Case #" + i + ": " + result);
				}else{
					int size = 4-result.length();
					for(int j=0 ; j<size ; j++){
						result = "0"+result;
					}
					if(result.length()!=4){
						System.out.println("ERRORRRRRRRRRRR");
					}else{
						System.out.println("Case #" + i + ": " + result);
						if(i==tn){
							out.print("Case #" + i + ": " + result);
						}else{
							out.println("Case #" + i + ": " + result);
						}
						
					}
				}
			}
		}
		in.close();
		out.close();
	}

	private int calcularResult(String example) {
		String frase = "welcome to code jam";
		if(chequearValidez(example,frase)){
			System.out.println("Valido");
			return calcularCantRepeticiones(example,frase);
		}else{
			System.out.println("No valido");
		}
		return 0;
	}

	private int calcularCantRepeticiones(String example, String frase) {
		int aux = 1;
		int aux2=0;
		int indice=0;
		char letra;
		char letraAnterior = 0;
		for(int i=0 ; i<example.length() ; i++){
			letra = frase.charAt(indice);
			if(example.charAt(i) == letraAnterior){
				aux2++;
			}else if(example.charAt(i) == letra){
				if(aux2 != 0){
					aux *= aux2;
					aux2 =0;
				}
				aux2++;
				letraAnterior=letra;
				indice++;
				if(indice==19){
					for(int j=1+i ; j<example.length() ; j++){
						if(example.charAt(j) == letra){
							aux2++;
						}
					}
					aux *= aux2;
					break;
				}
			}
		}
		if(indice==19){
			return aux;
		}else{
			return 0;
		}
	}

	private boolean chequearValidez(String example, String frase) {
		int indice=0;
		char letra;
		for(int i=0 ; i<example.length();i++){
			letra = frase.charAt(indice);
			if(example.charAt(i) == letra){
				indice++;
				if(indice==19){
					break;
				}
			}
		}
		if(indice==19){
			return true;
		}else{
			return false;
		}
	}



	

}