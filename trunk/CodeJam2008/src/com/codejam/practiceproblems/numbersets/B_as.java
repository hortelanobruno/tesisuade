package com.codejam.practiceproblems.numbersets;

import java.io.*;
import java.util.*;

public class B_as implements Runnable {
    class DSU {
        int[] p;
        int[] r;

        DSU(int n) {
            p = new int[n];
            r = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        int get(int x) {
            if (p[x] != x) {
                p[x] = get(p[x]);
            }
            return p[x];
        }

        void union(int x, int y) {
            x = get(x);
            y = get(y);
            if (x == y) {
                return;
            }

            if (r[x] == r[y]) {
                r[x]++;
            }

            if (r[x] > r[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }

        int cnt() {
            int r = 0;
            for (int i = 0; i < p.length; i++) {
                if (p[i] == i) {
                    r++;
                }
            }
            return r;
        }
    }

    long gcd(long x, long y) {
        while (y != 0) {
            long t = x % y;
            x = y;
            y = t;
        }
        return x;
    }

    public void solve() throws IOException {
        Scanner in = new Scanner(new File("B-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("B-small-attempt0.out"));
        
        int tn = in.nextInt();


        for (int test = 0; test < tn; test++) {
            long a = in.nextLong();
            long b = in.nextLong();
            long p = in.nextLong();
            int n = (int) (b - a + 1);

            long[] primes = new long[n];
            int pn = 0;
            for (int i = 2; i <= n; i++) {
                boolean isp = true;
                for (int j = 0; j < pn; j++) {
                    if (i % primes[j] == 0) {
                        isp = false;
                        break;
                    }
                }
                if (isp) {
                    primes[pn++] = i;
                }
            }

            int sp = 0;
            while (primes[sp] < p) {
                sp++;
            }

            DSU dsu = new DSU((int) (b - a + 1));
            for (long i = a; i <= b; i++) {
                for (long j = i + 1; j <= b; j++) {
                    long d  = gcd(i, j);
                    for (int k = sp; k < pn; k++) {
                        if (d % primes[k] == 0) {
                            dsu.union((int) (i - a), (int) (j - a));
                            break;
                        }
                    }
                }
            }

            out.printf("Case #%d: %d\n", test + 1, dsu.cnt());
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
        new Thread(new B_as()).start();
    }
} 