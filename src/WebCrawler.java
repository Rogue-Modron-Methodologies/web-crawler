
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

public class WebCrawler {
	
	private static Queue<String> mLineQueue = new LinkedList<String>();	
	private static BufferedWriter mOutput;
	private static java.net.URL mURL;
	private static String mLine;
	private static String mDivID;
	
	public static void main(String[] args) {
		
		try{
			getUserInput();
			if(findDiv()){
				System.out.print(mDivID + " Found in URL");
			}
			else
				System.out.print(mDivID + " NOT found in URL");
		}
		catch (java.net.MalformedURLException ex){
			ex.printStackTrace();
		}
		catch (java.io.IOException ex){
			System.out.println("I/O Error: " + ex);
		}
		printQueue();

	}
	
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	// Checks to see if the DivID is anywhere on the main URL
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	public static boolean findDiv() throws java.io.IOException {
		boolean found = false;
		Scanner input = new Scanner(mURL.openStream());		
		while(input.hasNext()){
			mLine = input.nextLine();
			mLineQueue.add(mLine);
			if(mLine.contains(mDivID)){
				String cropLine = snipDiv();
				findEndOfDiv();
				System.out.println(cropLine);
				found = true;
			}
		}
		input.close();
		return found;
	}

	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	//  
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	private static String findEndOfDiv() {
		
		return null;
	}
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	// Removes all characters before the DivID
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	private static String snipDiv() {
		String cropLine = mLine.substring(mLine.indexOf(mDivID), mLine.length());
		return cropLine;
	}
	
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	// Gets the URL and DivID from the user and instantiates the URL
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	private static void getUserInput() throws MalformedURLException {
		Scanner divInput = new Scanner(System.in);	
		System.out.println("Enter a URL: https://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html");
		String urlString = "https://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html"; 		//divInput.next();			//"http://www.google.com/index.html"
		mURL =new java.net.URL(urlString); 
		System.out.println("Enter a div ID: Footer");
		String div = "Footer";																					//  divInput.next();						
		mDivID = "<div id=\"" + div + "\">";
		divInput.close();	
	}

	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	// Prints the contents of the queue
	// (¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯`'•.¸//(*_*)\\¸.•'´¯) 
	private static void printQueue() {
		int lineNum = 1;
		try {
			mOutput = new BufferedWriter(new FileWriter("output.txt"));
			while(mLineQueue.peek() != null){
				mOutput.write(lineNum++ + "   " + mLineQueue.poll());
				mOutput.newLine();
			}
			mOutput.close();
		} 
		catch (IOException ex) {
			System.out.println("I/O Error: " + ex);
		}
	}	
	
	
	
	
	
	

	

}



/*
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a URL: ");
    String url = input.nextLine(); 
    crawler(url); // Traverse the Web from the a starting url
  }

  public static void crawler(String startingURL) {
    ArrayList<String> listOfPendingURLs = new ArrayList<>();
    ArrayList<String> listOfTraversedURLs = new ArrayList<>();
    
    listOfPendingURLs.add(startingURL);
    while (!listOfPendingURLs.isEmpty() && 
        listOfTraversedURLs.size() <= 100) {
      String urlString = listOfPendingURLs.remove(0);
      if (!listOfTraversedURLs.contains(urlString)) {
        listOfTraversedURLs.add(urlString);
        System.out.println("Craw " + urlString);

        for (String s: getSubURLs(urlString)) {
          if (!listOfTraversedURLs.contains(s))
            listOfPendingURLs.add(s);
        }
      }
    }
  }
  
  public static ArrayList<String> getSubURLs(String urlString) {
    ArrayList<String> list = new ArrayList<>();
    
    try {
      java.net.URL url = new java.net.URL(urlString); 
      Scanner input = new Scanner(url.openStream());
      int current = 0;
      while (input.hasNext()) {
        String line = input.nextLine();
        current = line.indexOf("http:", current);
        while (current > 0) {
          int endIndex = line.indexOf("\"", current);
          if (endIndex > 0) { // Ensure that a correct URL is found
            list.add(line.substring(current, endIndex)); 
            current = line.indexOf("http:", endIndex);
          }
          else 
            current = -1;
        }
      } 
    }
    catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
    
    return list;
  }






*/