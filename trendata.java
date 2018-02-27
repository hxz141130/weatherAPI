import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class trendata {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String fname, mname,lname;
        int age,tenure;
        double salary=0;
        int pr;

//        Scanner scan=new Scanner(System.in);
//        System.out.println("Enter the First Name of the Employee: ");
//        fname=scan.nextLine();
//        System.out.println("Enter the Middle Name of the Employee: ");
//        mname=scan.nextLine();
//        System.out.println("Enter the Last Name of the Employee: ");
//        lname=scan.nextLine();
//        System.out.println("Enter the age of the Employee: ");
//        age=scan.nextInt();
//        System.out.println("Enter the Tenure of the Employee: ");
//        tenure=scan.nextInt();
//        System.out.println("Enter the Salary of the Employee: ");
//        salary=scan.nextDouble();
//        System.out.println("Enter the changes in the Performance rating of the Employee (enter 1 for increase, 0 for stay, -1 for decrease): ");
//        pr=scan.nextInt();
//        Ai_algorithm ai= new Ai_algorithm(fname,mname,lname,age,tenure,salary);
        
//        System.out.println("The Employee "+ai.getFirst()+" "+ai.getMiddle()+" "+ai.getLast()+" has made a score of "+ai.compute());
        PrintWriter pw = new PrintWriter(new File("Test.csv"));
        BufferedReader reader = new BufferedReader(new FileReader("Nurses.csv"));
        String line = "";
        String [][] employee = new String[10000][10000];
        List<String> lines = new ArrayList<>();

        double [] score= new double[10000];
        int i=10;
        int y=0;
        while((line=reader.readLine())!=null){
//            System.out.println(line);
             lines.add(line);
            employee[y] =line.trim().split(",");
            y++;
        }
        
        
            for(int j=1;j<3000;j++)
            {
                Ai_algorithm a = new Ai_algorithm(employee[j][0],employee[j][1],employee[j][2],employee[j][8],employee[j][4],employee[j][3]);
                score[j]=a.compute();
                
            }

       for(int z=0;z<lines.size();z++)
       {
           lines.set(z, lines.get(z)+","+score[z]);
       }
       
       int t=0;
       while(t<lines.size())
       {
           pw.append(lines.get(t));
           pw.append(System.lineSeparator());
//           System.out.println(lines.get(t));
           t++;
       }
       
       pw.flush();
       pw.close();
       reader.close();
    }

}


