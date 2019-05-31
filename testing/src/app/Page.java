package CS172.project.demo;

public class Page{
	public String title;
	public String content;
	public float score;
	
	public Page(String t, String c){
		title = t;
		content = c;
		score = 0;
	}
	
	public Page(String t, String c, float s){
		title = t;
		content = c;
		score = s;
	}
}