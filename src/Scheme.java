
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
            rt = Runtime.getRuntime();
            commands = new String[]{"Path"};
            myPR = rt.exec(commands, null, new File("Path"));

            
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Double SolveOperationScheme(String operation) throws IOException{
        myPR = rt.exec(commands, null, new File("Path"));
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
        
        String answer = stdInput.readLine();
        int counter = 0;
        
        System.out.println("About to enter while " + stdInput.readLine());
        
        while ((answer = stdInput.readLine()) != null && counter < 2) {

            System.out.println(answer);
            System.out.println("COOL");
          
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
        return Double.parseDouble(answer);
    }
    
    public String GenerateRandomOperation(int min, int max){
        String myOperation = "(";
        Random r = new Random(System.currentTimeMillis());
        String[] operatorOptions = {"+", "-", "*", "/"};
        myOperation += operatorOptions[(r.nextInt(4))];

        myOperation += " ";
        
        
        
        String num1 = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        myOperation += num1;
        
        myOperation += " ";
        
        String num2 = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        myOperation += num2;
        myOperation += ")";
        
        return myOperation;
    }
}
