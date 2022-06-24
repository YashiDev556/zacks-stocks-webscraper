
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class WebScraper {


    //does not need a constructor yet...

    public String zacksAnalystStatusGetter(String ticker)
    {

        ArrayList<String> ratings = new ArrayList<String>();

        String rating = "";

        try {

            String url = "https://www.zacks.com/stock/quote/" + ticker + "?q=" + ticker;


            final Document document = Jsoup.connect(url).get();

            for(int i = 1; i <= 5; i++)
                ratings.add(document.getElementsByClass("rank_chip rankrect_" + i).text());


        } catch (IOException e) {
            //e.printStackTrace();
            ;
        }

        for(int j = 0; j < 5; j++)
        {
            if(ratings.size() != 0 && !ratings.get(j).equals(""))
                rating = ratings.get(j);
        }


        return rating;
    }

    public double grabPrice(String ticker) throws IOException {

        try {
            String url = "https://www.zacks.com/stock/quote/" + ticker;


            final Document document = Jsoup.connect(url).get();

            String price = document.getElementsByClass("last_price").text();

            price = price.replace("$", "");
            price = price.replace(" USD", "");

            //System.out.println(price);
            if(!price.isEmpty())
                return Double.parseDouble(price);
            return (-1);

        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Couldn't get the link to: " + ticker);

        }

        return -1;

    }






}
