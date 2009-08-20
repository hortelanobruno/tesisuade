package com.codejam.practiceproblems.thepriceiswrong;

import java.io.*;
import java.util.*;

public class B_as implements Runnable {

    public void solve() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("./files/thepriceiswrong/B-small-practice.in"));
        PrintWriter out = new PrintWriter(new File("./files/thepriceiswrong/B-small-practice.out2"));

        int tn = Integer.parseInt(in.readLine());
        for (int t = 0; t < tn; t++) {
            String[] s = in.readLine().split(" ");
            int n = s.length;
            int[] a = new int[n];
            String[] aa = in.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(aa[i]);
            }

            String[] ans = null;
            int aval = 10;
            search:
            for (int i = 0; i < (1 << n); i++) {
                boolean[] u = new boolean[n];
                int val = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) != 0) {
                        u[j] = true;
                    } else {
                        val++;
                    }
                }

                int prev = -1;
                for (int j = 0; j < n; j++) {
                    if (u[j]) {
                        if (a[j] < prev) {
                            continue search;
                        }
                        prev = a[j];
                    }
                }

                if (val < aval) {
                    aval = val;
                    ans = new String[aval];
                    int k = 0;
                    for (int j = 0; j < n; j++) {
                        if (!u[j]) {
                            ans[k++] = s[j];
                        }
                    }
                    Arrays.sort(ans);
                } else if (val == aval) {
                    int k = 0;
                    String[] pans = new String[aval];
                    for (int j = 0; j < n; j++) {
                        if (!u[j]) {
                            pans[k++] = s[j];
                        }
                    }
                    Arrays.sort(pans);
                    boolean better = false;
                    for (int j = 0; j < aval; j++) {
                        if (ans[j].compareTo(pans[j]) < 0) {
                            continue search;
                        }
                        if (ans[j].compareTo(pans[j]) > 0) {
                            better = true;
                            break;
                        }
                        k++;
                    }
                    if (better) {
                        ans = pans;
                    }
                }
            }
            out.print("Case #" + (t + 1) + ":");
            for (int i = 0; i < aval; i++) {
                out.print(" " + ans[i]);
            }
            out.println();
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
        new Thread(new B_as()).start();
    }
}
