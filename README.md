# Problem description and the objective 
Your task is to write and test two simple spelling corrector algorithms. You are given as input three files. The first file is called vocab.txt, and is a dictionary of valid words, with each word on a separate line. The number of words is at most 400,000 and each word is at most 30 characters long. The second file is called sentence.txt, and contains a sentence of at most 20 words, each word being at most 30 characters long, and being separated by one or more empty spaces. The third file is called MaxDistance.txt and contains a single integer value k that lies between 0 and 5.

Your job is to break up the sentence into words, and for each word w in the sentence, that is not in the dictionary, 
flag it, and give a list of possible spelling corrections, that is, words in the dictionary that are at most Levenshtein distance k from the misspelled word. 

The Levenshtein distance between a word s and a word t is the number of additions, deletions, and substitutions of characters required to transform s to t. Write your output into a file called MisspelledWords.txt. On each line, write a mis-spelled word found in the sentence, followed by a colon, and then the list of possible corrections, separated by a comma and then a space. The last correction will be followed by nothing. Each misspelled word will be on a different line. If there are no misspelled words in the sentence, the output file should simply contain the number 0.
## Algorithms used: 
To find the list of spelling corrections, you should implement two algorithms:
1. A simple linear search for the current word w in the dictionary, checking for each word in the dictionary if it is distance<=k from the searched word w.
2. An algorithm using BK-trees.
## Reading the data 
Each word of the dictionary will be saved as a string element in the arraylist called dictionary and each word of the sentence will be saved as a string element in the array list called sentence. 

```ruby
				while(input1.hasNextLine()) {
					dictionary.add(input1.nextLine().toLowerCase());
				}

				while(input.hasNextLine()) {
					sentence.add(input.next().toLowerCase());
				}
				
````

## The Lavenshtein distance algorithm
Levenshtein distance (LD) : As stated before It is a similarity measure between two words: For example:
* LD(Pizza,Pizza)=0   as the two strings are identical
* LD(monkey,donkey)=1 as only one letter in the strings is different.  

The algorithm of the Lavenshtein distance and how to generate it can be accessed at: 

https://people.cs.pitt.edu/~kirk/cs1501/Pruhs/Spring2006/assignments/editdistance/Levenshtein%20Distance.htm
  
The Levenshtein distance algorithm has been used in:

* Spell checking
* Speech recognition
* DNA analysis
* Plagiarism detection

Borrowing and enhancing what was already in the website above I implemented the following block of code to generate the distance: 
```ruby
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
```
## Searching for recommendations and detecting the typos
First I will check if there are any typos in the sentence by matching the words in the sentence with the words in the dictionary and if there was a mismatch I would store it in an arraylist.


```ruby
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
```
### Naive Linear Search algorithm
The following block of code is in charge of generating the recommendations by using the naive linear search algorithm. I am using a hash map data structure for recommending by: Storing the typos as keys and the list of the words in the dictionary which lie on Lavenshtein distance k from the misspelled word as the values(stored in an array list). 

```ruby
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
```
## BK-Trees
 We would like to access the words in the dictionary that are very close to the misspelled word as fast as possible and to recommend them to the client. BK-trees are data structures that will help us improve our recommendation speed to another level compared to the naive linear approach.

By selecting the root to be any of the strings in the dictionary, the BK-tree is build recursively by adding sub-trees to the original tree such that all of the elements y of the k-th sub-tree have a Lavenshtein distance k from the root element , i.e., d(root,y)=k for all y &isin;Nodes(K-th sub-tee).

The following explanation has been borrowed from

 https://www.geeksforgeeks.org/bk-tree-introduction-implementation/

 The nodes in the BK Tree will represent the individual words in our dictionary and there will be exactly the same number of nodes as the number of words in our dictionary. The edge will contain some integer weight that will tell us about the edit-distance from one node to another. Lets say we have an edge from node u to node v having some edge-weight w, then w is the edit-distance required to turn the string u to v.

Consider our dictionary with words : { “help” , “hell” , “hello”}. Therefore, for this dictionary our BK Tree will look like the below one.

![](http://cdncontribute.geeksforgeeks.org/wp-content/uploads/17554971_1350416058376194_212983783_n.png)

Every node in the BK Tree will have exactly one child with same edit-distance. In case, if we encounter some collision for edit-distance while inserting, we will then propagate the insertion process down the children until we find an appropriate parent for the string node.

### Implementing BK-Trees
The data structure has been implemented in the class BK-tree by calling the Build_Tree() function which would result in calling the Add_child function which takes as input a node, a word from the dictionary and an object of the class search method in order to calculate the Lavenshtien distance.  
```ruby
 public void Add_child(Node root,String s,SearchMethods method) {
		 Node n;
		 boolean flag=false;
		 ArrayList<Node> childlist=new ArrayList<Node>();
		 if(root.children.size()==0) { //the stopping criteria
				n=new Node(s,method.Lavenshtien(root.word, s),childlist);
				root.children.add(n);
			}
			else {
				int dist=method.Lavenshtien(root.word, s);
				for(int j=0;j<root.children.size();j++) {
					if(dist==root.children.get(j).Lav_dist) {
						Add_child(root.children.get(j),s,method); //recurs 
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
```
The last two blocks of code that I will explain in this summary describe how to efficiently search the BK-tree to find the best set of recommendations with respect to the Lavenshtein distance k. After having built the tree, searching in it will be a trivial process. One only has to follow the values on the edges by recursively traversing the tree until they reach the tolerance, i.e., the maximum edit distance from our misspelled word to the correct words in our dictionary. 

which can be done following the block of code below.
```ruby
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
```
With that being explained I used the same HashMap data-structure to store the recommendations by applying : 

```ruby
public Map<String,ArrayList<String>> BK_reommneder(int k,Node root){
		 Map<String,ArrayList<String>> m=new HashMap<String,ArrayList<String>>();
		 for(int i=0;i<typos.size();i++) {
			 BKSearch(typos.get(i),root,k,BK_recommneded);
			 m.put(typos.get(i), BK_recommneded);
			 BK_recommneded=new ArrayList<String>();
		 }
		 return m;	  
  }
```
## Conclusion
I have tested both the linear search and the BK-tree search algorithms on different data-sets. In all cases, the BK-Tree search algorithm was faster than the linear search algorithm. The effect of the length of the searched word is very bold on the computational time. Another parameter that affects the run time of the algorithm is the size of the dictionary which has an impact on the time it takes to build the tree as well as the time it takes to search in it. 

An increase in the maximum distance increases the search time in both algorithms. Again in all cases the BK Search algorithm outperforms the Linear search algorithm. However, there is a price that we need to pay for using the BK-Tree search and that is: We first need to build the tree which can be expensive depending on the size of our dictionary. For example, in one of our test cases, which we had around 370098 words, building the tree took a lot more time than searching in it. Hence the size of the dictionary can really affect the computational time required to build the tree. 

In some of my experiments, building the tree took even more time than searching for the recommendation via the linear search algorithm. But, the advantage of the BK-tree method is, if we build the tree once we can use it as many times as possible for searching for recommendations especially that the BK-Tree search algorithm is much more efficient in terms of time than the linear search algorithm. 
