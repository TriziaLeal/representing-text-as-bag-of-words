import java.util.*;
import java.io.*;

public class Word{
	String line;
	String output;
	Map<String, Integer> bagOfWords;
	int totalfreq;
	int totalwords;
	public Word(String line){
		this.line = line;
		bagOfWords = new HashMap<String, Integer>();
	}

	//loewrcase all lines and remove all non alphanumeric characters
	public void cleanLine(){
		this.line = this.line.toLowerCase();
		this.line = this.line.replaceAll("[^A-Za-z0-9\\s]", "");
	}

	public void createDictionary(){
		String[] arr = this.line.trim().split("\\s+");
		for ( String ss : arr) {
			//if key does not exists, adds new word to dictionary with value of 1 
			if (this.bagOfWords.get(ss) == null){
				this.bagOfWords.put(ss,1);
				System.out.println("first found");
			}
			//else, increments 1 to the value of the founded key
  			else{
  				this.bagOfWords.put(ss,1+this.bagOfWords.get(ss));
  				System.out.println(this.bagOfWords.get(ss));
  			}
  		}
  		//after creating dictionary, counts total number of words and total frequencies of all words
  		this.totalwords = this.bagOfWords.size();
  		totalFreq();
	}

	//computes for total frequency of words
	public void totalFreq(){
			for (Map.Entry<String, Integer> entry : this.bagOfWords.entrySet()){
			this.totalfreq += entry.getValue();
		}
	}


	public void fileWriting(){
		this.output = new String();
		System.out.println(this.bagOfWords.entrySet());

		//concats values and keyys to line
		for (Map.Entry<String, Integer> entry : this.bagOfWords.entrySet()){
	    	this.output += "word: " + entry.getKey() + "      freq: " + entry.getValue() + "\n";		
		}

		//creates file
		try{    
        	FileWriter fw=new FileWriter("output.txt");    
        	fw.write(this.output);    
        	fw.close();    
        }catch(Exception e){System.out.println(e);}    

	}	


}