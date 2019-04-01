import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchMethods {
private ArrayList<String> dictionary;
private ArrayList<String> recommneded=new ArrayList<String>();
private ArrayList<String> BK_recommneded=new ArrayList<String>();

public SearchMethods(ArrayList<String> ar) {
	dictionary=ar;
}
ArrayList<String> typos=new ArrayList<String>();
boolean flag=false;	 
 public ArrayList<String> Find_typo(ArrayList<String> sentence) {
	 for(int i=0;i<sentence.size();i++) {
		 for(int j=0;j<dictionary.size();j++) {
			 if(sentence.get(i).equals(dictionary.get(j))) {
				 /*System.out.println("here");*/
				 flag=true;
				 break;
			 }
		 }
		 if(flag==false) {
			 typos.add(sentence.get(i));
		 }
		 flag=false;
	 }
	 return typos;
 }
 public Map<String, ArrayList<String>> Recommender(int k) {
	 Map<String,ArrayList<String>> m=new HashMap<String,ArrayList<String>>();
	 for(int i=0;i<typos.size();i++) {
		 recommneded=new ArrayList<String>();
		 for(int j=0;j<dictionary.size();j++) {
			 if(Lavenshtien(typos.get(i),dictionary.get(j))<=k) {
				 recommneded.add(dictionary.get(j));
				// System.out.println("pizza");
			 }
		 }
		 m.put(typos.get(i), recommneded);
	 }
	 return m;
 }
  public Map<String,ArrayList<String>> BK_reommneder(int k,Node root){
		 Map<String,ArrayList<String>> m=new HashMap<String,ArrayList<String>>();
		 for(int i=0;i<typos.size();i++) {
			 BKSearch(typos.get(i),root,k,BK_recommneded);
			 m.put(typos.get(i), BK_recommneded);
			 BK_recommneded=new ArrayList<String>();
		 }
		 return m;	  
  }
 
 public int Lavenshtien(String s1, String s2) {
	  int n=s1.length (); // length of the String s1
	  int m= s2.length ();; // length of the String s2
	  int value; // value is zero if the chars match and 1 otherwise
	 
	 //the empty case: if one string is empty the distance is the length of the other 
	    if(n==0){
	      return m;
	    }
	    if(m==0){
	      return n;
	    }
	    int[][] distance=new int[n+1][m+1];
	    
	    //initialization step first row and column 
	    for (int i =0;i<n+1;i++) {
	      distance[i][0]=i;
	    }
	    for (int j=0;j< m+1;j++) {
	      distance[0][j]=j;
	    }
	    //Finding the distance by constructing the Matrix
	    char temp_i;
	    char temp_j;
	    for(int i=1;i<n+1;i++) {
	      temp_i=s1.charAt(i-1);  //starting from the first element of the string
	      for(int j=1;j<m+1;j++) {
	        temp_j= s2.charAt(j-1);
	        if (temp_i == temp_j) {
	          value=0;
	        }
	        else {
	          value=1;
	        }
	        distance[i][j]=Find_min((distance[i-1][j]+1),(distance[i][j-1]+1),(distance[i-1][j-1]+ value));
	      }
	    } //the last element on the bottom right
	    return distance[n][m];
	  }
 public int Find_min(int a ,int b, int c) {
	 int min=a;
	 if(b<min)
		 min=b;
	 if(c<min)
		 min=c;
	 return min;
 }
//ArrayList<Node>solutions=new ArrayList<Node>();
 public void BKSearch(String s,Node n,int tollerance,ArrayList<String> solutions){
	 int lav_dis=Lavenshtien(s,n.word);
	 if(lav_dis<=tollerance) {
		 solutions.add(n.word);
		// System.out.println(n.word);
		 }
		 int lower=lav_dis-tollerance;
		 int upper=lav_dis+tollerance;
		 while(lower<=upper) {
			 for(int i=0;i<n.children.size();i++) {
			 if(n.children.get(i).Lav_dist==lower) {
				 BKSearch(s,n.children.get(i), tollerance,solutions);
			 }
				 }
			 lower++;

	 }

 }
 
 
	}

 
 
 

