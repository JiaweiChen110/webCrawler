package app;
import java.awt.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;

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
        //System.out.println("Working");
        //System.out.println(index);
        //System.out.println(config);
        //System.out.println(analyzer);
    	org.jsoup.nodes.Document doc = Jsoup.connect("https://www.usa.gov/").get();
    	org.jsoup.nodes.Document doc2 = Jsoup.parse(doc.toString());
    	FileWriter tester= new FileWriter("writeTest.txt");
    	BufferedWriter writer = new BufferedWriter(tester);
//    	String t = "TESTING";
    	Elements hrefs = doc.select("[href]");
    	//System.out.println(hrefs);
    	
    	for(org.jsoup.nodes.Element t:hrefs) {
    		System.out.println(t.attr("href"));
    		writer.write(t.toString()+"\n");
    	}
//    	writer.write(doc.toString());
//    	writer.write(hrefs.toString());
    	writer.close();
//    	System.out.println(doc);
    }

}