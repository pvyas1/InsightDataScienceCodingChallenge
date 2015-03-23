import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class WordCount {

	public static void main(String[] args) throws IOException {
		
		/*
		Reading the input and the output path from the command line from the run.sh file
		*/
		String inputFilePath = args[0];
        String outputFilePath = args[1];
		
		//Creating a variable for the folder.
        File inputFolder = new File(inputFilePath);
		
		//Collecting all the file names in the array of files under the folder inputFolder
		File[] listOfFiles = inputFolder.listFiles();
		
		//Creating an ArrayList to store the file names, so that it can be sorted later on using the standard sort function
		ArrayList<File> filesList = new ArrayList<File>();
		
		//Loop to add all the file names into the above created ArrayList.
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        filesList.add(file);
		    }
		}
		
		//Sorting the ArrayList. This will enable us to access the file in alphabetical order.
		Collections.sort(filesList);
		
		//creating a HashMap variable which would be storing the words in String format as it's Key and would be storing the frequency of this word as its Value
		//Here the Frequency is a BigInteger data type, just in case if we have huge count of the words. Specially considering the Large Input file case.
		HashMap<String,BigInteger> wordCountMap = new HashMap<String, BigInteger>();
		
		
		/*
			Iterating through the list of files and storing the individual word and it's frequency in the HashMap variable created above.
		*/
		for(File fileItem: filesList){
		
			//Creating a Buffer Reader to read the data streams from the file.
			BufferedReader readerBuff = new BufferedReader(new FileReader(fileItem));
			
			//Creating a variable to store the line being read.
			String txtLine = null;
			
			//Iterating over all the lines in the file being currently read.
			while ((txtLine = readerBuff.readLine()) != null) {
				
				//Reading only if the line has some words.
				if(txtLine.length() != 0){
				
					//Splitting the line into array of words. Here, we are splitting by "Space Character"
					String[] word = txtLine.split("\\s+");
					
					//Variable to read each word in the line.
					String updatedWord = null;
					
					//Running through all the words using a for loop.
					for(String item: word){
					
						//The words might contain special characters. Thus removing those using a function. Function description below.
						updatedWord = removeSpecialCharacters(item);
						
						//Updating the HashMap with the values for the specific word. Here, we check if the word is already there in our HashMap.
						//If present, then we update, or else we just add for the first time. Thus using an If-Else clause here.
						if(wordCountMap.containsKey(updatedWord))
							wordCountMap.put(updatedWord, wordCountMap.get(updatedWord).add(new BigInteger("1")));
						else
							wordCountMap.put(updatedWord, new BigInteger("1"));
					}
				}
			}
			//Closing the Buffer.
			readerBuff.close();
		}
		
		//Opening the specific output folder where the output file would be stored.
		File outputFolder = new File(outputFilePaths);
		
		//Buffer variable to write into the file.
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(outputFolder));
		
		/*
			Using a Iterator to iterate over the HashMap values and Key, while simultaneously updating the output file.
		*/
		Set wordCountMapSet = wordCountMap.entrySet();
		Iterator loop = wordCountMapSet.iterator();
		
		while(loop.hasNext()) {
			Map.Entry mapValues = (Map.Entry)loop.next();
			writer.write(mapValues.getKey()+":\t"+mapValues.getValue());
			writer.newLine();
		}
		//End of the Iterator Loop.
		
		//Closing the write stream.
		writer.close();
	}
	
	//Function to remove the special characters present in the word using Regular Expression.
	public static String removeSpecialCharacters(String word){
		word = word.replaceAll("[-+.^:,\\/\"\';!@#$%&*()~`{}|<>?_]", "");
		return word;
	}

}
