package com.codejam.practiceproblems.thepriceiswrong;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class A implements Runnable {

	private List<Double> newLista;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new A()).start();
	}

	@Override
	public void run() {
		try {
			solve();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void solve() {
		try {
			Scanner in = new Scanner(new FileInputStream(
					"./files/thepriceiswrong/B-large-practice.in"));
			PrintWriter out = new PrintWriter(new FileOutputStream(
					"./files/thepriceiswrong/B-large-practice.out"));

			int n = Integer.parseInt(in.nextLine());
			for (int i = 1; i <= n; i++) {
				String arts = in.nextLine();
				String prices = in.nextLine();
				List<String> articulos = new ArrayList<String>();
				List<Integer> precios = new ArrayList<Integer>();
				for (String a : arts.split(" ")) {
					articulos.add(a);
				}
				for (String a : prices.split(" ")) {
					precios.add(Integer.parseInt(a));
				}
				int size = precios.size();
				ArrayList<String> result = null;

				result = calcularParaVariosArticulos(size, articulos, precios);
				System.out.println("Case #"
						+ i
						+ ": "
						+ result.toString().replaceAll("\\[", "").replaceAll(
								"\\]", "").replaceAll(",", ""));
				System.out.println("Vieja: " + precios.toString());
				System.out.println("Nueva: " + newLista.toString());
				out.println("Case #"
						+ i
						+ ": "
						+ result.toString().replaceAll("\\[", "").replaceAll(
								"\\]", "").replaceAll(",", ""));

				// aca hay que imprimir
			}

			in.close();
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error al leer archivo");
			e.printStackTrace();
		}
		// ArrayList<String> a = new ArrayList<String>();
		// a.add("A");
		// a.add("B");
		// a.add("C");
		// a.add("D");
		// ArrayList<ArrayList<String>> re = generateCombinations(a);
		// for(ArrayList<String> r : re){
		// if(r.size()==3){
		// for(String aa : r){
		// System.out.print(aa);
		// }
		// System.out.println();
		// }
		// }

	}

	private ArrayList<String> calcularParaVariosArticulos(int size,
			List<String> articulos, List<Integer> precios) {

		// Integer posPuntosDeInfleccion =
		// calcularCantPuntosDeInfleccion(precios);
		// ArrayList<String> result =
		// calcularResult(posPuntosDeInfleccion,articulos,precios);
		ArrayList<String> result = calcularResult(articulos, precios);

		return result;
	}

	private ArrayList<String> calcularResult(List<String> articulos,
			List<Integer> precios) {
		ArrayList<String> arts = new ArrayList<String>(articulos);
		Collections.sort(arts);
		ArrayList<ArrayList<String>> re = generateCombinations(arts);
		ArrayList<ArrayList<String>> combinaciones = new ArrayList<ArrayList<String>>();

		for (int i = 1; i < articulos.size(); i++) {
			combinaciones.clear();
			for (ArrayList<String> r : re) {
				if (r.size() == i) {
					if(!combinaciones.contains(r)){
						combinaciones.add(r);
					}
				}
			}
			for (ArrayList<String> com : combinaciones) {
				newLista = cambiarLista(com, articulos, precios);
				if (isSortAsc(newLista)) {
					return com;
				}
			}
		}

		return null;
	}

	private ArrayList<String> calcularResult(Integer posPuntosDeInfleccion,
			List<String> articulos, List<Integer> precios) {

		ArrayList<String> arts = new ArrayList<String>(articulos);
		Collections.sort(arts);
		ArrayList<ArrayList<String>> re = generateCombinations(arts);
		ArrayList<ArrayList<String>> combinaciones = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> r : re) {
			if (r.size() == posPuntosDeInfleccion) {
				combinaciones.add(r);
			}
		}

		for (ArrayList<String> com : combinaciones) {
			List<Double> newLista = cambiarLista(com, articulos, precios);
			if (isSortAsc(newLista)) {
				return com;
			}
		}

		return null;
	}

	private List<Double> cambiarLista(ArrayList<String> com,
			List<String> articulos, List<Integer> precios) {
		LinkedList<Double> newList = new LinkedList<Double>();
		for(Integer preci : precios){
			newList.add(preci.doubleValue());
		}
		List<Integer> indexes = new ArrayList<Integer>();
		for (String art : com) {
			indexes.add(articulos.indexOf(art));
		}
		Collections.sort(indexes);
		for (Integer index : indexes) {
			if (index == 0) {
				newList.set(0, 0D);
			} else if (index == newList.size() - 1) {
				newList.set(newList.size() - 1, 100D);
			} else {
				newList.set(index, newList.get(index - 1) + 0.1);
			}
		}
		return newList;
	}

	private boolean isSortAsc(List<Double> lista) {
		double aux = lista.get(0);
		for (int i = 1; i < lista.size(); i++) {
			if (aux < lista.get(i)) {
				aux = lista.get(i);
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método para generar las combinaciones con los elementos que recibe.
	 * 
	 * @param elements
	 *            Recibe los elementos.
	 * @return Devuelve el ArrayList con las combinaciones.
	 */
	private ArrayList<ArrayList<String>> generateCombinations(
			ArrayList<String> elements) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		int combinations = (int) Math.pow(2, elements.size()); // Número de
																// combinaciones
																// a generar.
		for (int i = combinations; i > 0; i--) {
			String binary = fillWithZeros(Integer.toBinaryString(i), elements
					.size());
			res.add(getCombination(binary, elements));
		}
		return res;
	}

	/**
	 * Método para generar una combinación dada.
	 * 
	 * @param binary
	 *            Recibe el String con un número en binario que dará el patrón
	 *            de generación.
	 * @param elements
	 *            Recibe los elementos.
	 * @return Devuelve la combinación.
	 */
	private ArrayList<String> getCombination(String binary,
			ArrayList<String> elements) {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < binary.length(); i++) {
			if (Integer.parseInt(Character.toString(binary.charAt(i))) == 1) {
				res.add(elements.get(i));
			}
		}
		return res;
	}

	/**
	 * Método para rellenar con ceros un número binario.
	 * 
	 * Si recibe por ejemplo "010" y queremos que sea expresado con cinco
	 * cifras, devolverá: "00010".
	 * 
	 * @param binaryString
	 *            Recibe el número en binario.
	 * @param length
	 *            Recibe el número de cifras que queremos que tenga el
	 *            resultado.
	 * @return Devuelve la cadena.
	 */
	private String fillWithZeros(String binaryString, int length) {
		String ret = "";
		int remain = length - binaryString.length();
		if (remain > 0) {
			for (int i = 0; i < remain; i++) {
				ret += "0";
			}
		}
		ret += binaryString;
		return ret;
	}

	private Integer calcularCantPuntosDeInfleccion(List<Integer> precios) {
		int aux = 0;
		int val = precios.get(0);
		int value = -1;
		for (int i = 1; i < precios.size(); i++) {
			if (precios.get(i) > val) {
				if (value == -1) {
					value = val;
				} else {
					if (value < precios.get(i)) {
						aux++;
					}
				}
			}
			val = precios.get(i);
		}
		return precios.size() - aux;
	}

	private String calcularPara2Articulos(List<String> articulos,
			List<Integer> precios) {
		Collections.sort(articulos);
		return articulos.get(0);
	}

}
