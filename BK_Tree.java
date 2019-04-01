import java.util.ArrayList;

public class BK_Tree {
	Node root;
	ArrayList<String> dictionary;
	SearchMethods s=new SearchMethods(dictionary);
	public BK_Tree(Node root,ArrayList<String> dictionary) {
		this.root=root;
		this.dictionary=dictionary;
	}
	public void Build_Tree() {		
		for(int i=1;i<dictionary.size();i++) {
			Add_child(root, dictionary.get(i), s);					
		}
	}
	 public void Add_child(Node root,String s,SearchMethods method) {
		 Node n;
		 boolean flag=false;
		 ArrayList<Node> childlist=new ArrayList<Node>();
		 if(root.children.size()==0) {
				n=new Node(s,method.Lavenshtien(root.word, s),childlist);
				root.children.add(n);
			}
			else {
				int dist=method.Lavenshtien(root.word, s);
				for(int j=0;j<root.children.size();j++) {
					if(dist==root.children.get(j).Lav_dist) {
						Add_child(root.children.get(j),s,method);
						flag=true;
					}
				}
				if(flag==false) {
					n=new Node(s,method.Lavenshtien(root.word, s),childlist);
					root.children.add(n);
				}
			}
		 flag=false;
	 }
}
