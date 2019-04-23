package app;

import java.awt.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import javax.lang.model.element.Element;

import org.apache.lucene.demo.*;
import org.apache.lucene.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
//import org.jsoup.nodes.Document;

public class App {
    public static void main(String[] args) throws Exception {
    	//StandardAnalyzer analyzer = new StandardAnalyzer();
//        Directory index = new Directory();

        //IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        IndexWriter w = new IndexWriter(index, config);

//        w.close();
//    	HashSet<String> temp = new HashSet<String>();
//    	HashSet<String> t = parseWeb("https://www.usa.gov");
    	FileWriter w = new FileWriter("writeTest.txt",true);
    	BufferedWriter writer = new BufferedWriter(w);
//    	writer.write(Jsoup.connect("https://www.usa.gov").get().toString());
//    	writer.close();
    	
//    	Thread testing2 = new Thread(new MultiThreads());
    	
    	HashSet<String> tSet = new HashSet<String>();
    	tSet.add("https://www.usa.gov");
    	tSet.add("https://www.state.gov");
    	for(String i:tSet) {
    		Thread testing2 = new Thread(new MultiThreads(i));
    		testing2.start();
    	}
    	
//    	Thread testing2 = new Thread(new MultiThreads("https://www.usa.gov"));
//		testing2.start();
    	/*
    	HashSet<String> tSet = new HashSet<String>();
    	tSet.add("https://www.usa.gov");
    	tSet.add("https://www.state.gov");
    	HashSet<String> fSet = crawlWeb(tSet,0);
    	for(String i:fSet) {
//    		System.out.println(i);
    		writer.write(i);
    		writer.newLine();
    	}
    	writer.close();
    	*/
    }
    
    public static HashSet<String> crawlWeb(HashSet<String> h,int max) throws IOException{
    	if(h == null || max > 1) {
    		return null;
    	}
    	HashSet<String> temp = new HashSet<String>();
    	int count = max;
    	count++;
    	for(String i:h) {
//    		count++;
    		HashSet<String> parseI = parseWeb(i);
    		if(parseI != null) {
    			temp.addAll(parseI);
    		}
    	}
    	HashSet<String> next = crawlWeb(temp,count);
    	HashSet<String> result = new HashSet<String>();
    	if(next!=null) {
    		result.addAll(next);
    	}
    	result.addAll(temp);
    	return result;
    }
    public static boolean isHost(String web){
    	if(web.length()>4) {
    		if(web.lastIndexOf('/') == web.length()-1) {
    			if(web.charAt(web.length()-5)=='.'){
    				return true;
    			}
    		}else{
    			if(web.charAt(web.length()-4)=='.'){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public static HashSet<String> parseWeb(String web){
    	org.jsoup.nodes.Document doc = null;
		try {
			doc = Jsoup.connect(web).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
    	
    	Elements hrefs = doc.select("[href]");
    	HashSet<String> hs = new HashSet<String>();
    	for(org.jsoup.nodes.Element t:hrefs) {
    		String temp = preProcess(t.attr("href"));
    		if(temp!=null) {
    			if(temp.charAt(0) == '/') {
    				hs.add(web+temp);
    			}else{
    				int index = temp.indexOf("https");
    				if(index>0) {
    					hs.add(temp.substring(index));
    				}
    			}
    		}
    	}
    	return hs;
    }
    final static String[] NON_PARSER = {".png",".ico",".css","#"};
    public static String preProcess(String href) {
    	if(href.compareTo("/") == 0) {
    		return null;
    	}else{
	    	for(String i:NON_PARSER) {
	    		if(href.contains(i)) {
	    			return null;
	    		}
	    	}
	    	return href;
    	}
    }
    /*
    public static int distance(int[] a, int b[]){
    	int sum=0;
    	for(int i=0;i<a.length;i++) {
    		sum+= Math.pow(a[i]-b[i],2);
    	}
    	return sum;
    }
    
    public static void hashing(String t[]) {
    	Vector<String> bitArray = new Vector<String>();
//    	HashMap<String,Integer> weight = new HashMap<String,Integer>();
    	
    	for(int i=0;i<t.length;i++) {
//    		System.out.println("Weight");
    		int temp = hashing(t[i].toLowerCase())%256;
    		System.out.print(String.format("%8s",Integer.toBinaryString(temp)).replace(" ", "0"));
    		System.out.println("   word = "+t[i].toLowerCase());
    	}
    }
    public static int hashing(String t) {
    	int sum=0;
    	for(int i=0;i<t.length();i++) {
    		
    		sum+= t.charAt(i);
    	}
    	return sum;
    }
	*/
}