package com.codejam.practiceproblems.savingtheuniverse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class A implements Runnable{
	private int line=0;
	public A() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
        try {
            solve();
        } catch (IOException e) {
        	System.out.println("Error");
            System.exit(64);
        } catch(Exception e){
        	System.out.println("Linea: "+line+". "+e.getMessage());
        }
    }

    public static void main(String[] arg) throws IOException {
        new Thread(new A()).start();
    }
    
    public void solve() throws IOException {
        Scanner in = new Scanner(new FileInputStream("./files/savingtheuniverse/A-large-practice.in"));
        PrintWriter out = new PrintWriter(new FileOutputStream("./files/savingtheuniverse/A-large-practice.out"));
        
        int n = Integer.parseInt(in.nextLine());
        line++;
        List<String> searchEngine = new ArrayList<String>();
        List<String> querys = new ArrayList<String>();
        
        
        for (int t = 1; t <= n; t++) {
        	searchEngine.clear();
        	querys.clear();
        	line++;
            int searchEngineSize = Integer.parseInt(in.nextLine());
            for(int i=0;i<searchEngineSize;i++){
            	line++;
            	searchEngine.add(in.nextLine());
            }
            line++;
            int querysSize = Integer.parseInt(in.nextLine());
            for(int i=0;i<querysSize;i++){
            	line++;
            	querys.add(in.nextLine());
            }
            int switches = calcularSwitches(searchEngine,querys);
            System.out.println("Case #" + t + ": " + switches);
			out.println("Case #" + t + ": " + switches);
        }

        in.close();
        out.close();
    }

	private int calcularSwitches(List<String> searchEngine, List<String> querys) {
		List<String> aux = new ArrayList<String>();
		int sizeSearchEngine = searchEngine.size();
		int switches=0;
		for(String query : querys){
			if(!aux.contains(query)){
				if(aux.size()==sizeSearchEngine-1){
					switches++;
					aux.clear();
				}
				aux.add(query);
			}	
		}
		return switches;
	}
}
