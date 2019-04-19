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
    	HashSet<String> temp = new HashSet<String>();
    	HashSet<String> t = parseWeb("https://www.usa.gov",temp);
    	for(String i:t) {
    		System.out.println(i);
    	}
    }
    public static HashSet<String> parseWeb(String web, HashSet<String> h) throws IOException {
    	org.jsoup.nodes.Document doc = Jsoup.connect(web).get();
    	
    	Elements hrefs = doc.select("[href]");
    	HashSet<String> hs = new HashSet<String>();
    	hs = h;
    	for(org.jsoup.nodes.Element t:hrefs) {
    		String temp = preProcess(t.attr("href"));
    		if(temp!=null) {
    			if(temp.charAt(0) == '/') {
    				hs.add(web+temp);
//    				System.out.println(web+temp);
    			}else {
    				hs.add(temp);
//    				System.out.println(temp);
    			}
//    			System.out.println(t.attr("href"));
    		}
//    		writer.write(t.toString()+"\n");
    	}
//    	System.out.println(hs);
//    	for(String i:hs) {
//    		System.out.println(i);
//    	}
    	return hs;
    }
    final static String[] NON_PARSER = {".png",".ico",".css","#"};
    public static String preProcess(String href) {
    	if(href.compareTo("/") == 0) {
    		return null;
    	}else {
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