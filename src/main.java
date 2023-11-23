

/**
 * @author YashiDev556
 */

import java.io.*;
import java.util.Scanner;

public class main {

    public static void main (String[] args) throws IOException {



        WebScraper webScraper = new WebScraper();

        System.out.println("Zacks Stocks Webscraper      Author: YashiDev556 \n \n");

        Scanner input = new Scanner(System.in);
        System.out.println("What rating stocks would you like to search for? (1-5, with 1 being strong buy and 5 being strong sell): ");
        String desiredRating = input.nextLine();



        System.out.println("Minimum Price? (Enter an Integer value): ");
        int min = Integer.parseInt(input.nextLine());


        System.out.println("Maximum Price? (Enter an Integer value): ");
        int max = Integer.parseInt(input.nextLine());
        
        System.out.println("Do You wish to search for Average Volume? (y/N): ");
        String answertoVol = input.nextLine();
        
        long minVol = -1;
        
        long maxVol = -1;
        
        if((!answertoVol.equals("")) && (!answertoVol.equals("N")) && (!answertoVol.equals("n")))
        {
        	System.out.println("Average Volume Minimum? (Enter an Integer)");
        	minVol = Long.parseLong(input.nextLine());
        	System.out.println("Average Volume Maxmimum? (Enter an Integer)");
        	maxVol = Long.parseLong(input.nextLine());
        	
        }
        	
        



        System.out.println("Give us some time to search all of your desired Zacks stocks. Once we have them, you'll be good to go!");


        // Open the file
        FileInputStream fstream = new FileInputStream("StockTickers.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        //PrintStream out = new PrintStream(new FileOutputStream("GoodStocksAccordingToZacks.txt"));

        StringBuilder loadingAnim = new StringBuilder(".");

        File log = new File("StockApplicationOutput.txt");
        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
        }finally
        {
            ;
        }



        String ticker;

        //Read File Line By Line
        while ((ticker = br.readLine()) != null)   {
            // Print the content on the console

            System.out.print(loadingAnim);
            

            //System.out.println(ticker); //delete this statement, it's for debugging purposes
            //System.out.println(webScraper.grabPrice(ticker));
            
            if(minVol == -1)
            {
            	if(webScraper.zacksAnalystStatusGetter(ticker).equals(desiredRating) && (webScraper.grabPrice(ticker) >= min) && (webScraper.grabPrice(ticker) <= max))
	            {
	                System.out.println();
	                System.out.println("This is Valid: " + ticker + " Price: " + webScraper.grabPrice(ticker) + " Average Volume: " + webScraper.grabVolume(ticker));
	                try (PrintWriter out = new PrintWriter(new FileWriter("StockApplicationOutput.txt", true))) {
	                    out.append(ticker + " Price: " + webScraper.grabPrice(ticker) + " Average Volume: " + webScraper.grabVolume(ticker) + "\n");
	                }
	            }
            } else {
            	if(webScraper.zacksAnalystStatusGetter(ticker).equals(desiredRating) && (webScraper.grabPrice(ticker) >= min) && (webScraper.grabPrice(ticker) <= max)
            			&& (webScraper.grabVolume(ticker) >= minVol) && (webScraper.grabVolume(ticker) <= maxVol))
	            {
	                System.out.println();
	                System.out.println("This is Valid: " + ticker + " Price: " + webScraper.grabPrice(ticker) + " Average Volume: " + webScraper.grabVolume(ticker));
	                try (PrintWriter out = new PrintWriter(new FileWriter("StockApplicationOutput.txt", true))) {
	                	out.append(ticker + " Price: " + webScraper.grabPrice(ticker) + " Average Volume: " + webScraper.grabVolume(ticker) + "\n");
	                }
	            }
            }
	            





        }


        System.out.println("All done! Have a nice day! \n \n Github Profile Link: https://github.com/YashiDev556");
        System.out.println("Press Enter to exit");
        String quitter = input.nextLine();

        //System.setOut(out);

        //Close the input stream
        fstream.close();
    }
}