package srhang.practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class TestClass {
	private int m;

	public int inc() {
		return m + 1;
	}
	
	public static void debug_output(){
		int[] x = new int[6];
		Arrays.fill(x, 1);
		for (int i = 0; i < x.length; i++) {
			try {
				int j = System.in.read();
				System.out.println("j:"+j);
				System.out.println(x[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void debug_print(){
		PrintWriter pWriter = new PrintWriter(System.out,true);
		pWriter.println("11");
	}

	public static void main(String args[]) {
		//debug_print();
		Elvis sElvis = Elvis.INSTANCE;
		sElvis.test();
	}
}
