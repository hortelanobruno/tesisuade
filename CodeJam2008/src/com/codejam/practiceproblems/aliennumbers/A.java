package com.codejam.practiceproblems.aliennumbers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class A implements Runnable {

	@Override
	public void run() {
		try {
			solve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void solve() throws IOException {
		Scanner in = new Scanner(new File("A-large-practice.in"));
		PrintWriter out = new PrintWriter("A-large-practice.out");

		int tn = in.nextInt();
		String alienNumber, sourceLanguage, targetLanguage;
		for (int i = 1; i <= tn; i++) {
			alienNumber = in.next();
			sourceLanguage = in.next();
			targetLanguage = in.next();
			int result = fromLanguage(alienNumber, sourceLanguage);
			String re = toLanguage(targetLanguage, result);

			System.out.println("Case #" + i + ": " + re);
			out.println("Case #" + i + ": " + re);
		}
		in.close();
		out.close();
	}

	private String toLanguage(String targetLanguage, int num) {
		int size = targetLanguage.toCharArray().length;
		boolean salir = false;
		String result = "";
		if (num <= size - 1) {
			if (num == 0) {
				System.out.println("es 0 el num");
			} else {
				return "" + targetLanguage.toCharArray()[num];
			}
		} else {
			while(!salir){
				int aux = num/size;
				int rest = num%size;
				result = targetLanguage.toCharArray()[rest] + result;
				if(aux<size){
					salir=true;
					result = targetLanguage.toCharArray()[aux] + result;
				}else{
					num=aux;
				}
			}
		}
		return result;
	}

	private int fromLanguage(String alienNumber, String sourceLanguage) {
		int result = 0;
		int size = sourceLanguage.toCharArray().length;
		int sizeNumber = alienNumber.toCharArray().length;
		char num;
		int elevado = 0;
		int pos;
		for (int i = sizeNumber - 1; i >= 0; i--) {
			num = alienNumber.toCharArray()[i];
			pos = sourceLanguage.indexOf(num);
			result += elevarNumero(pos, size, elevado);
			elevado++;
		}
		return result;
	}

	private int elevarNumero(int pos, int size, int elevado) {
		int num = 1;
		if (elevado == 0) {
			return pos;
		} else {
			for (int i = 0; i < elevado; i++) {
				num *= size;
			}
		}
		return pos * num;
	}

	public static void main(String arga[]) {
		new Thread(new A()).start();
	}

}
