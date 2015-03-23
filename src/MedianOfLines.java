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
					
					//Incrementing the counter variable.
					counter = counter + 1;
					
					//Splitting the line into array of words. Here, we are splitting by "Space Character"
					String[] word = txtLine.split("\\s+");
					
					//Adding the first time to the MaxHeap. Or else adding to the one with the least value of the root.
					if(counter == 1 || (word.length <= maxHeap.peek()))
						maxHeap.add((double) word.length);
					else
						minHeap.add((double) word.length);
					
					
					//Moving elements from the one having greater size to the one having the lower size.
					if (maxHeap.size() > minHeap.size() + 1)
				        minHeap.add(maxHeap.remove());
					else if(minHeap.size() > maxHeap.size() + 1)
						maxHeap.add(minHeap.remove());
					
					
					//Calculating the Median value from the heap.
					if(minHeap.size() == maxHeap.size())
						writer.write(String.valueOf(0.5 * (minHeap.peek() + maxHeap.peek())));
					else if(minHeap.size() > maxHeap.size())
						writer.write(String.valueOf(minHeap.peek()));
					else
						writer.write(String.valueOf(maxHeap.peek()));
					
					//Entering a new line.
					writer.newLine();
					
				}
			}
			//Closing the reader buffer.
			readerBuff.close();
		}
		//Closing the write stream.
		writer.close();

	}

}
