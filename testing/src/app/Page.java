package CS172.project.demo;

public class Page{
	public String title;
	public String content;
	public float score;
	public String link;
	
	public Page(String t, String c){
		title = t;
		content = c;
		score = 0;
		link = "";
	}
	
	public Page(String t, String c, float s){
		title = t;
		content = c;
		score = s;
		link = "";
	}
	
	public Page(String t, String c, String l){
		title = t;
		content = c;
		score = 0;
		link = l;
	}
	
	public Page(String t, String c, float s, String l){
		title = t;
		content = c;
		score = s;
		link = l;
	}
}