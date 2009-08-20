package com.codejam.practiceproblems.croptriangles;

import java.io.*;
import java.util.*;

public class A_as_dumb implements Runnable {
    public void solve() throws IOException {
        Scanner in = new Scanner(new File("A-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new File("A0.ouu"));

        int tn = in.nextInt();
        for (int test = 0; test < tn; test++) {
            int n = in.nextInt();
            long A = in.nextLong();
            long B = in.nextLong();
            long C = in.nextLong();
            long D = in.nextLong();

            long[] x = new long[n];
            long[] y = new long[n];
            x[0] = in.nextLong();
            y[0] = in.nextLong();

            long M = in.nextLong();

            for (int i = 1; i < n; i++) {
                x[i] = (A * x[i - 1] + B) % M;
                y[i] = (C * y[i - 1] + D) % M;
            }

            long[][] cnt = new long[3][3];

            for (int i = 0; i < n; i++) {
                cnt[(int) (x[i] % 3)][(int) (y[i] % 3)]++;
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if ((x[i] + x[j] + x[k]) % 3 == 0 && (y[i] + y[j] + y[k]) % 3 == 0) {
                            ans++;
                        }
                    }
                }
            }

            out.printf("Case #%d: %d\n", test + 1, ans);
        }

        in.close();
        out.close();
    }

    @Override
    public void run() {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] s) {
        Locale.setDefault(Locale.US);
        new Thread(new A_as_dumb()).start();
    }
}