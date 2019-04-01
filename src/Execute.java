import java.util.ArrayList;
import java.util.Map;

public class Execute {
	Map<String,ArrayList<String>> suggestions;
	Map<String, ArrayList<String>> BK_MAp;
	Node root;
	public void Run_Linear(int distance, SearchMethods s) {
		suggestions=s.Recommender(distance);
		WriteOutput outPut=new WriteOutput();
		outPut.openfile("FoundbylinearSearch");
		outPut.wtire_To_File(suggestions);
		outPut.closefile();
	}
	public void Creat_tree(ArrayList<String> dictionary) {
		ArrayList<Node> children=new ArrayList<Node>();
		root=new Node(dictionary.get(0), 0, children);
		BK_Tree Btree=new BK_Tree(root, dictionary);
		Btree.Build_Tree();
	}
	public void Run_BK_Search(int distance,SearchMethods s) {
		BK_MAp=s.BK_reommneder(distance, root);
		WriteOutput BK_OutPut=new WriteOutput();
		BK_OutPut.openfile("FoundbyBKSearch");
		BK_OutPut.wtire_To_File(BK_MAp);
		BK_OutPut.closefile();
	}
}
