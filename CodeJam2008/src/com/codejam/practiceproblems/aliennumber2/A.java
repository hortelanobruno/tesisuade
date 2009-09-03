package com.codejam.practiceproblems.aliennumber2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


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
		Scanner in = new Scanner(new File("./files/aliennumber2/A-small-attempt3.in"));
		PrintWriter out = new PrintWriter("./files/aliennumber2/A-small-attempt3.out");

		String cases = in.nextLine();//1 size of word, 2 cant of words, 3 cant of test cases
		int tn = Integer.parseInt(cases.split(" ")[2]);
		int sizeOfWords = Integer.parseInt(cases.split(" ")[0]);
		int cantOfWords = Integer.parseInt(cases.split(" ")[1]);
		List<String> words = new ArrayList<String>();
		for(int i=0 ; i < cantOfWords ; i++){
			words.add(in.nextLine());
		}
		String testCase;
		for (int i = 1; i <= tn; i++) {
			testCase = in.nextLine();
			int re = calcularResult2(testCase,words,sizeOfWords,i);
			System.out.println("Case #" + i + ": " + re);
			if(i==tn){
				out.print("Case #" + i + ": " + re);
			}else{
				out.println("Case #" + i + ": " + re);
			}
		}
		in.close();
		out.close();
	}

	private int calcularResult(String testCase, List<String> words,
			int sizeOfWords, int i) {
		
		
		if(chequearCantLetras(testCase,sizeOfWords)){
			List<String> combinaciones = armarCombinaciones(testCase,sizeOfWords,words);
			if(combinaciones!=null){
				if(i==3){
					Collections.sort(combinaciones);
					for(String comb: combinaciones){
							System.out.println(comb);
					}
				}
				int aux = 0;
				for(String comb : combinaciones){
					if(words.contains(comb)){
						aux++;
					}
				}
				
				return aux;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	private int calcularResult2(String testCase, List<String> words,
			int sizeOfWords, int i) {
		
		
		if(chequearCantLetras(testCase,sizeOfWords)){
			int aux = armarCombinaciones2(testCase,sizeOfWords,words);
			return aux;
		}else{
			return 0;
		}
	}

	private boolean chequearCantLetras(String testCase, int sizeOfWords) {
		if(testCase.contains("(")){
			int aux=0;
			char letra;
			boolean b =false;
			for(int i=0 ; i<testCase.length() ; i++){
				letra = testCase.charAt(i);
				if(letra == '('){
					aux++;
					b=true;
				}else{
					if(letra == ')'){
						b=false;
					}else{
						if(!b){
							aux++;
						}
					}
				}
			}
			if(aux == sizeOfWords){
				return true;
			}else{
				return false;
			}
		}else{
			if(testCase.length() == sizeOfWords){
				return true;
			}else{
				return false;
			}
		}
	}
	
	private int armarCombinaciones2(String testCase, int sizeOfWords,List<String> words) {
		if(testCase.contains("(")){
			List<String> words2 = new ArrayList<String>(words);
			List<String> words3;
			for(int i=0 ; i<sizeOfWords ; i++){
				if(!words2.isEmpty()){
					words3 = new ArrayList<String>(words2);
					String ar = obtenerTestCase(testCase,i);
					if(ar.length()==1){
						for(String wor : words3){
							if(wor.charAt(i) != ar.charAt(0)){
								words2.remove(wor);
							}
						}
					}else{
						for(String wor : words3){
							int aa1=0;
							for(int a=0 ; a<ar.length() ; a++){
								if(wor.charAt(i) == ar.charAt(a)){
									aa1++;
								}
							}
							if(aa1==0){
								words2.remove(wor);
							}
						}
						
					}
				}else{
					return 0;
				}
			}	
			return words2.size();
		}else{
			if(words.contains(testCase)){
				return 1;
			}else{
				return 0;
			}
		}
	}

	private String obtenerTestCase(String testCase, int indice) {
		if(testCase.contains("(")){
			int aux=0;
			char letra;
			boolean b =false;
			int indiceparentesis=-1;
			for(int i=0 ; i<testCase.length() ; i++){
				letra = testCase.charAt(i);
				if(letra == '('){
					indiceparentesis=i+1;
					b=true;
				}else{
					if(letra == ')'){
						aux++;
						if(aux-1 == indice){
							return testCase.substring(indiceparentesis, i);
						}
						b=false;
					}else{
						if(!b){
							aux++;
							if(aux-1 == indice){
								return testCase.substring(i, i);
							}
						}
					}
				}
			}
			return "";
		}else{
			return testCase.substring(indice, indice);
		}
	}

	private List<String> armarCombinaciones(String testCase, int sizeOfWords,List<String> words) {
		Set<String> combis = new HashSet<String>();
		List<String> aux = new ArrayList<String>();
		if(testCase.contains("(")){
			for(String a : testCase.split("\\(")){
				for(String b : a.split("\\)")){
					if(!b.equalsIgnoreCase(""))
						aux.add(b);
				}
			}
			for(String a : aux){
				if(a.length()!=1){
					cagregarCombinacionesEnLista(combis,a);
				}else{
					cargarEnLista(combis,a);
				}
				int aux3=0;
				for(String cb : combis){
					for(String word : words){
						if(word.startsWith(cb)){
							aux3++;
							break;
						}
					}
					if(aux3>0){
						break;
					}
				}
				if(aux3==0){
					return null;
				}
			}	
			return new ArrayList<String>(combis);
		}else{
			List<String> lista = new ArrayList<String>();
			lista.add(testCase);
			return lista;
		}
	}

	private void cagregarCombinacionesEnLista(Set<String> combis, String a) {
		if(combis.isEmpty()){
			char letra;
			for(int i=0 ; i<a.length() ; i++){
				letra = a.charAt(i);
				combis.add(""+letra);
			}
		}else{
			char letra;
			//Set<String> aux = new HashSet<String>();
			List<String> aux = new ArrayList<String>();
			for(int i=0 ; i<a.length() ; i++){
				letra = a.charAt(i);
				for(String comb : combis){
					if(!aux.contains(comb+letra)){
						aux.add(comb+letra);
					}
				}	
			}
			combis.clear();
			combis.addAll(aux);
		}
	}

	private void cargarEnLista(Set<String> combis, String a) {
		if(combis.isEmpty()){
			combis.add(a);
		}else{
			Set<String> aux = new HashSet<String>();
			for(String pal : combis){
				aux.add(pal+a);
			}
			combis.clear();
			combis.addAll(aux);
		}
	}

	

}
