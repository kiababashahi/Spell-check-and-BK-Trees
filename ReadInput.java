	import java.io.File;
	import java.io.FileNotFoundException;
	import java.lang.IllegalStateException;
import java.lang.reflect.Array;
import java.util.ArrayList;
	import java.util.NoSuchElementException;
	import java.util.Scanner;
	public class ReadInput {
		private Scanner input;
		private Scanner input1;
		private Scanner input2;
		private String Sentence_path;
		private String Dictionary_path;
		private String K_path;
		private int distance=0;
		private ArrayList<String> sentence=new ArrayList<>();
		private ArrayList<String> dictionary=new ArrayList<String>();
		public ReadInput(String s1,String s2,String s3) {
			Sentence_path=s1;
			Dictionary_path=s2;
			K_path=s3;
		}
		public ArrayList<String> Sentence_holder() {
			open_Sentence_File(Sentence_path);
			sentence=read_Sentence_Records();
			close_Sentence_File();
			return sentence;
		}
		
		public void open_Sentence_File(String path) {
			try {
				input=new Scanner(new File(path));
			}
			catch(FileNotFoundException e) {
				System.err.println("Error opening file");
				System.exit(1);
			}
		}
		
		public ArrayList<String>read_Sentence_Records() {
				try {
				while(input.hasNextLine()) {
					sentence.add(input.next().toLowerCase());
				}
				
			}catch(NoSuchElementException e2) {
				System.err.println("file improperly formed");
				input.close();
				System.exit(1);
			}
			catch (IllegalStateException e1) {
				System.err.println("error reading file");
				System.exit(1);
			}
			return sentence;
		}
			public void close_Sentence_File() {
				if(input !=null)
					input.close();
			}
			////////////////
			public ArrayList<String> Dictonary() {
				open_Dictionary_File(Dictionary_path);
				dictionary=read_Dictionary_Records();
				close_Dictionary_File();
				return dictionary;
			}
			public void open_Dictionary_File(String path) {
				try {
					input1=new Scanner(new File(path));
				}
				catch(FileNotFoundException e) {
					System.err.println("Error opening file");
					System.exit(1);
				}
			}
			public ArrayList<String> read_Dictionary_Records() {
				try {
					while(input1.hasNextLine()) {
						//System.out.println("gooz");
						dictionary.add(input1.nextLine().toLowerCase());
					}
					
				}catch(NoSuchElementException e2) {
					System.err.println("file improperly formed1");
					input1.close();
					System.exit(1);
				}
				catch (IllegalStateException e1) {
					System.err.println("error reading file");
					System.exit(1);
				}
				return dictionary;
			}
				public void close_Dictionary_File() {
					if(input1 !=null)
						input1.close();
				}
			
				public int Distance() {
					open_Distance_File(K_path);
					distance=read_Distance_Records();
					close_Distance_File();
					return distance;
				}
			
				public void open_Distance_File(String path) {
					try {
						input2=new Scanner(new File(path));
					}
					catch(FileNotFoundException e) {
						System.err.println("Error opening file");
						System.exit(1);
					}
				}
				public int read_Distance_Records() {
					try {
						while(input2.hasNextInt()) {
							distance=input2.nextInt();
						}
						
					}catch(NoSuchElementException e2) {
						System.err.println("file improperly formed");
						input2.close();
						System.exit(1);
					}
					catch (IllegalStateException e1) {
						System.err.println("error reading file");
						System.exit(1);
					}
					return distance;
				}
				public void close_Distance_File() {
					if(input2 !=null)
						input2.close();
				}
		}



