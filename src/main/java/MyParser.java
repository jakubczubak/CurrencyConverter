import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class MyParser {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        while(true) {
            Map<Object, Object> exchangeRate = new LinkedHashMap<Object,Object>();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse("eurofxref-daily.xml");
            NodeList cubeList = doc.getElementsByTagName("Cube");

            for (int i = 0 ; i<cubeList.getLength(); i++){
                Node c = cubeList.item(i);
                if(c.getNodeType() == Node.ELEMENT_NODE){
                    Element cube =  (Element) c;
                    String currency = cube.getAttribute("currency");
                    String rate = cube.getAttribute("rate");

                    if(!currency.isEmpty()){
                        exchangeRate.put(currency,rate);
                    }
                }
            }

            System.out.println("Welcome to the currency calculator!");

            Scanner scan = new Scanner(System.in);
            double euro;

            while (true) {
                System.out.println("Enter the amount in Euro...");
                try {
                    euro = scan.nextDouble();
                    break;  // get out of the loop
                } catch (InputMismatchException mme) {
                    System.out.println("Incorrect value! Try again!");
                    // clear the scanner input
                    scan.nextLine();
                }
            }
            System.out.println("The entered amount is " + euro + " EUR");

            while (true) {
                System.out.println("Choose the currency you want to convert to");

                List<String> keys = new ArrayList(exchangeRate.keySet());

                for (int i = 0; i < keys.size(); i++) {
                    String currency = keys.get(i);
                    System.out.println(i + " - " + currency);
                }
                try {
                    int currencyID = scan.nextInt();
                    if(currencyID >= 0 && currencyID < keys.size()){
                        String currency = keys.get(currencyID);
                        System.out.println("You chose: " + currency) ;
                        double rate  = Double.parseDouble((String) exchangeRate.get(currency));

                        Calculator calculator = new Calculator();
                        calculator.printResult(euro,rate,currency);
                        break;  // get out of the loop
                    }
                } catch (InputMismatchException mme) {
                    System.out.println("Incorrect value! Try again!");
                    // clear the scanner input
                    scan.nextLine();
                }
            }

        }

    }
}
