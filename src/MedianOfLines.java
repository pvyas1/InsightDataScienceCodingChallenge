import java.io.*;
import java.util.*;


public class MedianOfLines {

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
		
		// Creating a MinHeap PriorityQueue to store the values in a heap
		PriorityQueue<Double> minHeap = new PriorityQueue<Double>();
	    // Creating a MaxHeap PriorityQueue to store the values in a heap in Max to Min order.
	    PriorityQueue<Double> maxHeap = new PriorityQueue<Double>(10, Collections.reverseOrder());
	    
		//Counter Variable to check if it is our first time in the below For Loop.
	    int counter = 0;
	    
	    //Opening the specific output folder where the output file would be stored.
		File outputFolder = new File(outputFilePaths);
		
		//Buffer variable to write into the file.
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(outputFolder));
		
		
		for(File fileItem: filesList){
			BufferedReader readerBuff = new BufferedReader(new FileReader(fileItem));
			String txtLine = null;
			while ((txtLine = readerBuff.readLine()) != null) {
				if(txtLine.length() != 0){
					counter = counter + 1;
					String[] word = txtLine.split("\\s+");
					//String updatedWord = null;
					
					if(counter == 1 || (word.length <= maxHeap.peek()))
						maxHeap.add((double) word.length);
					else
						minHeap.add((double) word.length);
					
					
					if (maxHeap.size() > minHeap.size() + 1)
				        minHeap.add(maxHeap.remove());
					else if(minHeap.size() > maxHeap.size() + 1)
						maxHeap.add(minHeap.remove());
					
					
					if(minHeap.size() == maxHeap.size())
						writer.write(String.valueOf(0.5 * (minHeap.peek() + maxHeap.peek())));
					else if(minHeap.size() > maxHeap.size())
						writer.write(String.valueOf(minHeap.peek()));
					else
						writer.write(String.valueOf(maxHeap.peek()));
					
					writer.newLine();
					
				}
			}
			readerBuff.close();
		}
		
		writer.close();

	}

}
