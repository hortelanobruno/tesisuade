package com.codejam.practiceproblems.traintimetable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A implements Runnable {
	
	private int trainContratedInA;
	private int trainContratedInB;

	public A() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		try {
			solve();
		} catch (IOException e) {
			System.out.println("Error");
			System.exit(64);
		} catch (Exception e) {
			System.out.println("Err");
		}
	}

	public static void main(String[] arg) throws IOException {
		new Thread(new A()).start();
	}

	public void solve() throws IOException {
		Scanner in = new Scanner(new FileInputStream(
				"./files/savingtheuniverse/A-large-practice.in"));
		PrintWriter out = new PrintWriter(new FileOutputStream(
				"./files/savingtheuniverse/A-large-practice.out"));

		int n = Integer.parseInt(in.nextLine());

		for (int t = 1; t <= n; t++) {

			int turnAround = Integer.parseInt(in.nextLine());
			String trips = in.nextLine();
			int tripsAtoB = Integer.parseInt(trips.split(" ")[0]);
			int tripsBtoA = Integer.parseInt(trips.split(" ")[1]);
			List<String> timeTripsAtoB = new ArrayList<String>();
			List<String> timeTripsBtoA = new ArrayList<String>();
			for (int i = 0; i < tripsAtoB; i++) {
				timeTripsAtoB.add(in.nextLine());
			}
			for (int i = 0; i < tripsBtoA; i++) {
				timeTripsBtoA.add(in.nextLine());
			}
			trainContratedInA=0;
			trainContratedInB=0;
			String result = calculateTrains(turnAround, tripsAtoB, tripsBtoA,
					timeTripsAtoB, timeTripsBtoA);

			System.out.println("Case #" + t + ": " + result);
			out.println("Case #" + t + ": " + result);
		}

		in.close();
		out.close();
	}

	private String calculateTrains(int turnAround, int tripsAtoB,
			int tripsBtoA, List<String> timeTripsAtoB,
			List<String> timeTripsBtoA) {
		if ((tripsAtoB == 0) || (tripsBtoA == 0)) {
			return tripsAtoB + " " + tripsBtoA;
		} else {
			Collections.sort(timeTripsAtoB);
			Collections.sort(timeTripsBtoA);

			List<Long> freeTrainInA = new ArrayList<Long>();
			List<Long> freeTrainInB = new ArrayList<Long>();
			String sale;
			
			while ((!timeTripsAtoB.isEmpty()) || (timeTripsBtoA.isEmpty())) {
				if (getFechaInMinutes(timeTripsAtoB.get(0).split(" ")[0]) == getFechaInMinutes(timeTripsBtoA.get(0).split(" ")[0])) {

				} else {
					if (getFechaInMinutes(timeTripsAtoB.get(0).split(" ")[0]) < getFechaInMinutes(timeTripsBtoA.get(0).split(" ")[0])) {
						sale = timeTripsAtoB.get(0);
						timeTripsAtoB.remove(0);
						if(freeTrainInA.isEmpty()){
							trainContratedInA++;
							freeTrainInB.add(getFechaInMinutes(sale.split(" ")[1])+turnAround);
						}else{
							
						}
						
					}
				}
			}
			return trainContratedInA+" "+trainContratedInB;
		}
	}

	private long getFechaInMinutes(String fecha) {
		return Long.parseLong(fecha.split(":")[1])
				+ (Long.parseLong(fecha.split(":")[0]) * 60);
	}

}
