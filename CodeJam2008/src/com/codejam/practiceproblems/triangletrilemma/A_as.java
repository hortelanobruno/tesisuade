package com.codejam.practiceproblems.triangletrilemma;

import java.io.*;
import java.util.*;

public class A_as implements Runnable {

    public void solve() throws IOException {
        Scanner in = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));

        int n = in.nextInt();
        for (int t = 0; t < n; t++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            int x3 = in.nextInt();
            int y3 = in.nextInt();

            int s = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
            if (s == 0) {
                out.printf("Case #%d: not a triangle\n", t + 1);
                continue;
            }
            int l1 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
            int l2 = (x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2);
            int l3 = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3);

            boolean isos = (l1 == l2) || (l2 == l3) || (l3 == l1);
            int[] a = new int[]{l1, l2, l3};
            Arrays.sort(a);
            if (a[0] + a[1] == a[2]) {
                out.printf("Case #%d: %s right triangle\n", t + 1, isos ? "isosceles" : "scalene");
            }
            if (a[0] + a[1] < a[2]) {
                out.printf("Case #%d: %s obtuse triangle\n", t + 1, isos ? "isosceles" : "scalene");
            }
            if (a[0] + a[1] > a[2]) {
                out.printf("Case #%d: %s acute triangle\n", t + 1, isos ? "isosceles" : "scalene");
            }
        }

        in.close();
        out.close();
    }

    public void run() {
        try {
            solve();
        } catch (IOException e) {
            System.exit(64);
        }
    }

    public static void main(String[] arg) throws IOException {
        new Thread(new A_as()).start();
    }
}
