package CS172.project.demo;

import java.awt.TextField;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.lang.model.element.Element;

import org.apache.lucene.demo.*;
import org.apache.lucene.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
//import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
//import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class App {
	
	@GetMapping("/articles")
    public List<Page> searchArticles (
            @RequestParam(required=false, defaultValue="usa") String query) throws Exception{
		List<Page> matches = new ArrayList<>();
		
		// Check if no query
        if (query.isEmpty())
        	return matches;
		
        Path f = Paths.get("indexFolder");
    	StandardAnalyzer analyzer = new StandardAnalyzer();
    	IndexWriterConfig config = new IndexWriterConfig(analyzer);
    	Directory directory = FSDirectory.open(f);
    	
    	//	CLEAN THE INDEX FOLDER BEFORE STARTING
    	String indexLocation = System.getProperty("user.dir") + "/indexFolder";
    	File index = new File(indexLocation);
    	File[] files = index.listFiles();
    	for (File file : files) {
    		if (!file.delete()) { 
    			System.out.println("Failed to delete "+file);
    		}
    	}
    	
    	
        // GATHER ALL THE FILES FROM DOWNLOAD FOLDER AND INDEX THEM
    	IndexWriter indexWriter = new IndexWriter(directory, config);
    	List<Page> pages = new ArrayList<Page>();
    	String downloadLocation = System.getProperty("user.dir") + "/downloadFiles";
    	File folder = new File(downloadLocation);
    	String[] listOfFiles = folder.list();
    	if(listOfFiles == null) {
    		return matches;
    	}
		for (String name: listOfFiles) {
	    	    // System.out.println(name);
	    	    String data = new String(Files.readAllBytes(Paths.get("downloadFiles/" + name)));
	    	    org.jsoup.nodes.Document d = Jsoup.parse(data);
	    	    Page test = new Page(d.title(),d.body().text());
	    	    pages.add(test);
    	}
    	    	
        for (Page page: pages) {
            Document doc = new Document();
            doc.add(new org.apache.lucene.document.TextField("title", page.title, Field.Store.YES));
            doc.add(new org.apache.lucene.document.TextField("content", page.content, Field.Store.YES));
            indexWriter.addDocument(doc);
        }
        indexWriter.close();
    	
        
        // SCORE THE INDEXED FILES
        DirectoryReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        String[] fields = {"title", "content"};
        Map<String, Float> boosts = new HashMap<>();
        boosts.put(fields[0], 1.0f);
        boosts.put(fields[1], 0.5f);
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer, boosts);
        Query q = parser.parse(query);
        int topHitCount = 100;
        ScoreDoc[] hits = indexSearcher.search(q, topHitCount).scoreDocs;

        
        // Iterate through the results:
        for (int rank = 0; rank < hits.length; ++rank) {
            Document hitDoc = indexSearcher.doc(hits[rank].doc);
            Page p = new Page(hitDoc.get("title"), hitDoc.get("content"), hits[rank].score);
            matches.add(p);
        }
        indexReader.close();
		
        directory.close();
        return matches;
    }
}