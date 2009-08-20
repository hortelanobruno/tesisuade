package com.codejam.practiceproblems.triangletrilemma;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *	NO FUNCIONA !!!! PUTA MADRE 
 */

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
		Scanner in = new Scanner(new File("./files/triangletrilemma/A-small-practice.in"));
		PrintWriter out = new PrintWriter("./files/triangletrilemma/A-small-practice.out");
		//Scanner in = new Scanner(new File("./files/triangletrilemma/test.in"));
		//PrintWriter out = new PrintWriter("./files/triangletrilemma/test.out");

		int tn = in.nextInt();
		//x1, y1, x2, y2, x3, y3
		int x1, y1, x2, y2, x3, y3;
		Triangulo tri;
		for (int i = 1; i <= tn; i++) {
			x1 = in.nextInt();
			y1 = in.nextInt();
			x2 = in.nextInt();
			y2 = in.nextInt();
			x3 = in.nextInt();
			y3 = in.nextInt();
			tri = new Triangulo(x1+";"+y1, x2+";"+y2, x3+";"+y3);
			System.out.println("Case #" + i + ": " + tri.getResultado());
			out.println("Case #" + i + ": " + tri.getResultado());
		}
		in.close();
		out.close();
	}


	public static void main(String arga[]) {
		new Thread(new A()).start();
	}

}

