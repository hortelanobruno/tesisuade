package com.codejam.practiceproblems.traintimetable;

import java.io.*;
import java.util.*;

public class B_as implements Runnable {
	
    class Event implements Comparable {
        int t;
        boolean dep;

        Event(int t, boolean b) {
            this.t = t;
            this.dep = b;
        }

        public int compareTo(Object o) {
            Event that = (Event) o;
            if (this.t == that.t) {
                if (this.dep && !that.dep) {
                    return 1;
                } else if (!this.dep && that.dep) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                return this.t - that.t;
            }
        }
    }

    public void solve() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("B1.in"));
        PrintWriter out = new PrintWriter(new File("B1.out"));

        int testn = Integer.parseInt(in.readLine());
        for (int test = 0; test < testn; test++) {
            int t = Integer.parseInt(in.readLine());
            String[] s = in.readLine().split(" ");
            int na = Integer.parseInt(s[0]);
            int nb = Integer.parseInt(s[1]);
            ArrayList<Event> aa = new ArrayList<Event>();
            ArrayList<Event> bb = new ArrayList<Event>();
            for (int i = 0; i < na; i++) {
                s = in.readLine().split(" ");
                int st = Integer.parseInt(s[0].substring(0, 2)) * 60 + Integer.parseInt(s[0].substring(3));
                int fn = Integer.parseInt(s[1].substring(0, 2)) * 60 + Integer.parseInt(s[1].substring(3)) + t;
                aa.add(new Event(st, true));
                bb.add(new Event(fn, false));
            }
            for (int i = 0; i < nb; i++) {
                s = in.readLine().split(" ");
                int st = Integer.parseInt(s[0].substring(0, 2)) * 60 + Integer.parseInt(s[0].substring(3));
                int fn = Integer.parseInt(s[1].substring(0, 2)) * 60 + Integer.parseInt(s[1].substring(3)) + t;
                bb.add(new Event(st, true));
                aa.add(new Event(fn, false));
            }

            Collections.sort(aa);
            Collections.sort(bb);

            int needA = 0;
            int needB = 0;
            int hasA = 0;
            int hasB = 0;
            for (Event e : aa) {
                if (e.dep) {
                    if (hasA == 0) {
                        hasA++;
                        needA++;
                    }
                    hasA--;
                } else {
                    hasA++;
                }
            }
            for (Event e : bb) {
                if (e.dep) {
                    if (hasB == 0) {
                        hasB++;
                        needB++;
                    }
                    hasB--;
                } else {
                    hasB++;
                }
            }

            out.println("Case #" + (test + 1) + ": " + needA + " " + needB);
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