import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
Problem Statement 

Process every 5 lines from this file trim the spaces if any,
map it to their hex value using lambda expressions print it 
in a result file as (key, value(hex)) using the 
executor framework of 5 threads in the pool .

*/

interface Hex{
	public String toHexa(int num);
}

class Multi implements Runnable{
	private int num;
	static Hex converter=(num)->Integer.toHexString(num);
	static File file = new File("C:\\Users\\dhruv.kansal\\eclipse-workspace\\Problem1\\src\\output.txt");
	public Multi(int num) {
		this.num=num;
	}
	@Override
	public void run() {
		String hex=converter.toHexa(num);
		System.out.println("("+num+","+hex+")");
	}
}


public class Prob1 {
	public static void main(String[] args) throws FileNotFoundException
	{
		ExecutorService pool = Executors.newFixedThreadPool(5);
		File file=new File("C:\\Users\\dhruv.kansal\\eclipse-workspace\\Problem1\\src\\prob.txt");
		Scanner fileScanner=new Scanner(file);
		while(fileScanner.hasNextLine()) {
			int data=fileScanner.nextInt();
			//Call thread for the number
			Thread t=new Thread(new Multi(data));
			pool.execute(t);
		}
		pool.shutdown();
		fileScanner.close();
	}
}