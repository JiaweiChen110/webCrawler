package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class MultiThreads implements Runnable{
	String hLink="";
	
	MultiThreads(String l){
		hLink = l;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		FileWriter w = null;
		try {
			w = new FileWriter("writeTest.txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	BufferedWriter writer = new BufferedWriter(w);
    	
    	HashSet<String> tSet = new HashSet<String>();
    	tSet.add(hLink);
////    	tSet.add("https://www.state.gov");
    	try {
			HashSet<String> fSet = App.crawlWeb(tSet,0);
			for(String i:fSet) {
	//    		System.out.println(i);
	    		writer.write(i);
	    		writer.newLine();
	    	}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("testing" + Thread.currentThread().getId() +hLink);
	}
}
