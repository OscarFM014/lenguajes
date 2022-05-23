
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Scheme {
    Process myPR;
    Runtime rt;
    String[] commands;
    public Scheme(){
        Runtime runtime = Runtime.getRuntime();
        try {
            //Runtime rt = Runtime.getRuntime();
            rt = Runtime.getRuntime();

            //String[] commands = {"C:\\Program Files\\Racket\\Racket.exe"};
            commands = new String[]{"C:\\Program Files\\Racket\\Racket.exe"};
            myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));

            /*SolveOperationScheme(myPR, "(+ 1 2)");
            //Double t1 = SolveOperationScheme(myPR, "(+ 5 8)");
            //t1 += 20;
            System.out.println("SORT OF : ");
            myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
            SolveOperationScheme(myPR, "(* 6 7)");
            myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
            SolveOperationScheme(myPR, "(/ 0 0)");
            myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
            SolveOperationScheme(myPR, "(/ 0 6)");*/
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //public Double SolveOperationScheme(Process pro, String operation) throws IOException{
    public Double SolveOperationScheme(String operation) throws IOException{
        myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
        if(operation.charAt(5) == '0'){
            System.out.println("IMAGINARY DIVISION : 0");
            return 0.0;
        }
        System.out.println("Entered solving scheme operation : " + operation);
        
        BufferedWriter bWriter = 
        new BufferedWriter(new OutputStreamWriter(myPR.getOutputStream()));

        System.out.println("Reached 1");
        
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(myPR.getInputStream())); 
        
        System.out.println("Reached 2");
        
        BufferedReader stdError = new BufferedReader(new InputStreamReader(myPR.getErrorStream()));
        
        System.out.println("Reached 3");
        
        String answer = null;
        int counter = 0;
        
        //System.out.println("About to enter while " + stdInput.readLine());
        
        while ((answer = stdInput.readLine()) != null && counter < 2) {
        //while ( stdInput.ready() && counter < 2) {
            //answer = stdInput.readLine();
            System.out.println(answer);
            System.out.println("COOL");
            //pr = rt.exec("help");
            //pr = rt.exec("5");
            //rt.exec("(+ 1 2)");
            //rt.exec("Hola");

            //p_stdin.write("( + 6 2)");
            //p_stdin.write(GenerateRandomOperation());
            String Operation = operation;
            System.err.println("CHECKING: " + Operation + "dONE");
            bWriter.write(Operation);

            bWriter.newLine();
            bWriter.flush();
            counter++;
        }
        System.out.println(answer.charAt(0));
        System.out.println("FINISHED SCHEME TEST");
        
        answer = answer.substring(1);
        Double.parseDouble(answer);
        System.out.println("Answer : " + Double.parseDouble(answer));
        //return 0.0;
        return Double.parseDouble(answer);
    }
    
    public String GenerateRandomOperation(){
        String myOperation = "(";
        Random r = new Random(System.currentTimeMillis());
        String[] operatorOptions = {"+", "-", "*", "/"};
        myOperation += operatorOptions[(r.nextInt(4))];
        r = new Random(System.currentTimeMillis());
        
        myOperation += " ";
                
        String[] operandOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        myOperation += operandOptions[(r.nextInt(10))];
        
        myOperation += " ";
        
        myOperation += operandOptions[(r.nextInt(10))];
        myOperation += ")";
        
        System.out.println(myOperation);
        return "";
    }
}
