package com.codejam.practiceproblems.triangletrilemma;

public class Triangulo {

	private String vertice1;// ej 0;0
	private String vertice2;
	private String vertice3;
	private double ladoA;
	private double ladoB;
	private double ladoC;
	private String angulo;
	private String lados;
	private Boolean triangle = true;
	private String resultado;

	public Triangulo(String v1, String v2, String v3) {
		this.vertice1 = v1;
		this.vertice2 = v2;
		this.vertice3 = v3;
		calcularLados();
		angulo = calcularClasificacionAngulo();
		lados = calcularClasificacionLados();
		resultado = calcularResultado();
	}

	public String getResultado() {
		return resultado;
	}

	private String calcularResultado() {
		if (triangle) {
			return lados + " " + angulo + " triangle";
		} else {
			return "not a triangle";
		}
	}

	private String calcularClasificacionLados() {
		if ((ladoA != ladoB) && (ladoA != ladoC) && (ladoC != ladoB)) {
			return "scalene";
		} else {
			return "isosceles";
		}
	}

	private String calcularClasificacionAngulo() {
		if (triangle) {
			double result1 = calcularAngulo(ladoA, ladoB, ladoC);// 0=recto
			// 1=agudo
			// -1=obtuso
			double result2 = calcularAngulo(ladoB, ladoA, ladoC);// 0=recto
			// 1=agudo
			// -1=obtuso
			double result3 = calcularAngulo(ladoC, ladoA, ladoB);// 0=recto
			// 1=agudo
			// -1=obtuso
			if ((result1 == 0) || (result2 == 0) || (result3 == 0)) {
				triangle = true;
				return "right";
			} else if ((result1 == -1) || (result2 == -1) || (result3 == -1)) {
				triangle = true;
				return "obtuse";
			} else if ((result1 == 1) && (result2 == 1) && (result3 == 1)) {
				triangle = true;
				return "acute";
			} else {
				triangle = false;
				return null;
			}
		}
		return null;
	}

	private double calcularAngulo(Double lado1, Double lado2, Double lado3) {
		// 0=recto 1=agudo -1=obtuso
		// a^2 = b^2 + c^2
		double aux1 = Math.round(Math.pow(lado1, 2));
		double aux2 = Math.round(Math.pow(lado2, 2) + Math.pow(lado3, 2));
		if (aux1 == aux2) {
			return 0;
		} else {
			if (aux1 > aux2) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	private void calcularLados() {
		if ((vertice1.equalsIgnoreCase(vertice2))
				|| (vertice2.equalsIgnoreCase(vertice3))|| (vertice1.equalsIgnoreCase(vertice3))) {
			triangle = false;
		}
		if ((vertice1.split(";")[0].equalsIgnoreCase(vertice2.split(";")[0]))
				&& (vertice1.split(";")[0]
						.equalsIgnoreCase(vertice3.split(";")[0]))) {
			triangle = false;
		}
		if ((vertice1.split(";")[1].equalsIgnoreCase(vertice2.split(";")[1]))
				&& (vertice1.split(";")[1]
						.equalsIgnoreCase(vertice3.split(";")[1]))) {
			triangle = false;
		}

		int v1x = Integer.parseInt(vertice1.split(";")[0]);
		int v1y = Integer.parseInt(vertice1.split(";")[1]);

		int v2x = Integer.parseInt(vertice2.split(";")[0]);
		int v2y = Integer.parseInt(vertice2.split(";")[1]);

		int v3x = Integer.parseInt(vertice3.split(";")[0]);
		int v3y = Integer.parseInt(vertice3.split(";")[1]);

		ladoA = calcularLado(v1x, v1y, v2x, v2y);
		ladoB = calcularLado(v2x, v2y, v3x, v3y);
		ladoC = calcularLado(v3x, v3y, v1x, v1y);

	}

	private Double calcularLado(int vax, int vay, int vbx, int vby) {
		int auxLado1;
		if (Math.abs(vax) >= Math.abs(vbx)) {
			auxLado1 = Math.abs(vax) - Math.abs(vbx);
		} else {
			auxLado1 = Math.abs(vbx) - Math.abs(vax);
		}
		int auxLado2;
		if (Math.abs(vay) >= Math.abs(vby)) {
			auxLado2 = Math.abs(vay) - Math.abs(vby);
		} else {
			auxLado2 = Math.abs(vby) - Math.abs(vay);
		}
		return Math.sqrt(Math.pow(auxLado1, 2) + Math.pow(auxLado2, 2));
	}

}
