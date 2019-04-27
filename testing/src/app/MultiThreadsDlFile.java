package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;

public class MultiThreadsDlFile implements Runnable{
	String link ="";
	String file ="";
	
	MultiThreadsDlFile(String t,String f){
		link = t;
		file = f;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(link);
		System.out.println(file);
//		FileWriter w = null;
////		FileReader r = null;
//		try {
//			w = new FileWriter(file);
////			r = new FileReader(link);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BufferedReader reader = new BufferedReader(r);
		org.jsoup.nodes.Document doc = null;
		
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			doc = null;
		}
		if(doc!=null) {
//			System.out.println("In");
			FileWriter w = null;
			try {
				w = new FileWriter(file);
//				System.out.println("RUN");
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				w = null;
			}
			if(w!=null) {
				BufferedWriter writer = new BufferedWriter(w);
	//			System.out.println(doc.html());
				try {
					writer.write(doc.toString());
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
//		try {
//			
//			BufferedWriter writer = new BufferedWriter(w);
//			System.out.println(doc.html());
//			writer.write(doc.toString());
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
////			doc = null;
//		}
//		System.out.println(doc.html());
//		if(doc!=null) {
//			System.out.println(doc.html());
//			
//		}
	}
	
}