
import java.util.Random;


public class Scheme {
 
    public Double SolveOperationManual(String operation){
        Double answer = 0.0;
        Double f1, f2;
        if(operation.charAt(5) == '0'){
            answer = 0.0;
        }

        // System.out.println((double)operation.charAt(3) + "   " +  operation.charAt(3) + "........." + (double)operation.charAt(5)+ "   " +  operation.charAt(5) );

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
