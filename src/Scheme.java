
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
    String FilePath = "";
    String RacketExecPath = "";
    public Scheme(){
        Runtime runtime = Runtime.getRuntime();
        try {
            rt = Runtime.getRuntime();
            //commands = new String[]{RacketExecPath};
            //myPR = rt.exec(commands, null, new File(this.FilePath));
            commands = new String[]{"C:\\Program Files\\Racket\\Racket.exe"};
            myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));

            
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        
        
        //return 5.0;
    }
    
        public Double SolveOperationManual(String operation)throws IOException{
        Double answer = 0.0;
        Double f1, f2;
        if(operation.charAt(5) == '0'){
            answer = 0.0;
        }
        
        System.out.println((double)operation.charAt(3) + "   " +  operation.charAt(3) + "........." + (double)operation.charAt(5)+ "   " +  operation.charAt(5) );
        /*if(operation.charAt(1) == '*'){
            answer = (double)operation.charAt(3) * (double)operation.charAt(5);
        }else if(operation.charAt(1) == '/'){
            answer = (double)operation.charAt(3) / (double)operation.charAt(5);
        }else if(operation.charAt(1) == '+'){
            answer = (double)operation.charAt(3) + (double)operation.charAt(5);
        }else if(operation.charAt(1) == '-'){
            answer = (double)operation.charAt(3) - (double)operation.charAt(5);
        }*/
        
        if(operation.charAt(1) == '*'){
            answer = Double.parseDouble(operation.charAt(3)+"") * Double.parseDouble(operation.charAt(5)+"");
        }else if(operation.charAt(1) == '/'){
            answer = Double.parseDouble(operation.charAt(3)+"") / Double.parseDouble(operation.charAt(5)+"");
        }else if(operation.charAt(1) == '+'){
            answer = Double.parseDouble(operation.charAt(3)+"") + Double.parseDouble(operation.charAt(5)+"");
        }else if(operation.charAt(1) == '-'){
            answer = Double.parseDouble(operation.charAt(3)+"") - Double.parseDouble(operation.charAt(5)+"");
        }
        
        return answer;
    }
    
    public String GenerateRandomOperation(int min, int max){
        //String myOperation = "(exact->inexact (";
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
        //myOperation += "))";
        myOperation += ")";
        
        return myOperation;
        //return "(* 5 5)";
    }
}
