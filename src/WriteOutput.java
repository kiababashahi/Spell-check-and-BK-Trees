import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Map;
import java.util.NoSuchElementException;
public class WriteOutput {
	Map<String, ArrayList<String>> map;
	private Formatter output;
	public void openfile(String s) {
		try {
			output=new Formatter("MisspelledWords"+s+".txt");
		}
		catch (SecurityException e) {
			System.err.println("access denied");
			System.exit(1);
		}catch (FileNotFoundException e1) {
			System.err.println("File not found");
			System.exit(1);
		}
			}
	public void wtire_To_File(Map m) {
		map=m;
		try{
			ArrayList<String> correction;
			for(String keys:map.keySet()) {
				output.format("%s: ",keys);
				correction=map.get(keys);
				for(int i=0;i<correction.size();i++)
					if(i!=correction.size()-1)
					output.format("%s, ",correction.get(i));
					else output.format("%s",correction.get(i));
				output.format("\n");
			}
		}
		catch (FormatterClosedException e) {
			System.err.println("errot writing to file");
		}
		catch (NoSuchElementException e1) {
			System.err.println("invalid input");
		}
	}
	public void closefile() {
		if(output!=null)
			output.close();
	}
}
