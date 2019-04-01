import java.util.ArrayList;

public class Node {
	 String word;
	 int Lav_dist;
	 ArrayList<Node> children;
	 public Node(String word,int Lav_dist,ArrayList<Node> children) {
		this.word=word;
		this.Lav_dist=Lav_dist;
		this.children=children;
	}
/*	public String Get_Word() {
		return word;
	}
	public int Get_dist() {
		return Lav_dist;
	}
	public ArrayList<Node> Get_Children(){
		return children;
	}*/
}
