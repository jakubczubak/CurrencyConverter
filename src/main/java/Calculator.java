public class Calculator {

    public Calculator() {
    }

    public void printResult(double euro, double rate, String currency){
        System.out.println(euro + " EUR " + " is " + rate*euro + " " + currency);
        System.out.println();
        System.out.println();
    }
}
