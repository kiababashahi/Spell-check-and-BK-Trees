import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class cheker {
	public static void main(String[] args) {
		ArrayList<String> sentence; // below you shoul enter the paths 
		String sentence_path="C:\\Users\\Bro_Kia\\eclipse-workspace\\SpelCheck\\test cases\\test cases\\testcase2\\sentence.txt";
		String Dictionary_path="C:\\Users\\Bro_Kia\\eclipse-workspace\\SpelCheck\\test cases\\test cases\\testcase2\\vocab.txt";
		String Dist_path="C:\\Users\\Bro_Kia\\eclipse-workspace\\SpelCheck\\test cases\\test cases\\testcase2\\MaxDistance.txt";		
		ReadInput read=new ReadInput(sentence_path,Dictionary_path,Dist_path);
		sentence=read.Sentence_holder();
		ArrayList<String>dictionary;
		dictionary=read.Dictonary();// dictionary
		SearchMethods s=new SearchMethods(dictionary);
		ArrayList<String> typos;
		typos=s.Find_typo(sentence);
		int distance=read.Distance();
		long LINEARstartTime = System.nanoTime();		
		Execute e=new Execute();
		e.Run_Linear(distance, s); //running the linear search 
		//if you just wish to test the BK tree please comment lines 22 until 25
		long LINEARendTime = System.nanoTime();
		long LINEARduration = (LINEARendTime-LINEARstartTime);
		System.out.println("the Linear search time is :"+ LINEARduration);
		long TreeBUIlDstartTime = System.nanoTime();
		e.Creat_tree(dictionary);// building the tree 
		// if you wish to check the linear plz comment lines 26 until 39
		long TreeBUIlDendTime = System.nanoTime();
		long TreeBUIlDduration = (TreeBUIlDendTime-TreeBUIlDstartTime);
		System.out.println("the Build Tree time is :"+ TreeBUIlDduration);
		long BKstartTime = System.nanoTime();
		e.Run_BK_Search(distance, s);
		long BKendTime = System.nanoTime();
		long BKduration = (BKendTime-BKstartTime);
		System.out.println("the BK search time is :"+ BKduration);
		if(BKduration<LINEARduration) {
			System.out.println("BK is faster");
		}
		else {
			System.out.println("Linear search is faster");
		}
	}	
}
