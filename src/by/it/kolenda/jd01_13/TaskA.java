package by.it.kolenda.jd01_13;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Double> values = new ArrayList<>();
        String str="";
        while (!(str=scanner.nextLine()).equalsIgnoreCase("END")){
            try {
                Double value = Double.parseDouble(str);
                if (value<0) throw new ArithmeticException("Negative value of sqrt");
                values.add(Math.sqrt(value));
//                System.out.println(values);
                Double sum=0.0;
                for (Double val:values){
                    sum=sum+val;
                }
                System.out.printf("Summa korney sum=%f\n",sum);
            }
            catch (NumberFormatException e) {
                System.out.printf("Incorrect line input %s\n", str);
            }
            catch (ArithmeticException e) {
                System.out.printf("Arithmetic error %s\n", e.getMessage());
                StackTraceElement[] st=e.getStackTrace();
                System.out.println("Stack:");
                for (StackTraceElement el:st) {

                    System.out.printf("Source class: \"%s\" file \"%s\" method \"%s\" \n");
                    el.getClassName();
                    el.getFileName();
                    el.getMethodName();
                    el.getLineNumber();
                    if(el.getMethodName().equals("main"));
                    break;
                }
            }
        }
    }
}
