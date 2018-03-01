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
        String [][] employee = new String[10000][10];// Reader 2-D Array.
        List<String> lines = new ArrayList<>();

        double [] score= new double[10000];
        int i=10;
        int y=0;
        while((line=reader.readLine())!=null){
//            System.out.println(line);
             lines.add(line);
            employee[y] =line.trim().split(",");
            y++;
        }//parse lines into tokens into 2d array
        List<Double> Ary_age= new ArrayList<>();
        List<Double> Ary_tenure=new ArrayList<>();
        List<Double> Ary_salary=new ArrayList<>();
        
        demo[] dAge=analyze(Ary_age, employee,8);
        demo[] dTenure=analyze(Ary_tenure, employee,4);
        demo[] dSalary=analyze(Ary_salary, employee,3);
        double points=0;
        Ai_algorithm a = new Ai_algorithm();
        int ag=0,te=0,sa=0;
        for(int h=1; h<10000; h++)
        {
        	if(employee[h][0]==null)
        	{
        		break;
        	}
        	for(int l=0;l<10;l++)
        	{
        		if(Double.parseDouble(employee[h][8])<(dAge[l].getMax()) && Double.parseDouble(employee[h][8])>=(dAge[l].getMin()))
        		{
        			ag=dAge[l].getRank();
        			for(int o=0;o<10;o++)
        			{
        				if(Double.parseDouble(employee[h][4])<dTenure[l].getMax() && Double.parseDouble(employee[h][4])>=dTenure[l].getMin())
        				{
        					te=dTenure[l].getRank();
        				for(int q=0;q<10;q++)
        					if(Double.parseDouble(employee[h][3])<dSalary[l].getMax() && Double.parseDouble(employee[h][3])>=dSalary[l].getMin())
        						sa=dSalary[l].getRank();
        				}
        			}
        		
        		}
        		
        	}
        	score[h]=a.compute(ag,te,sa);
        }

        
        
//             for(int j=1;j<3000;j++)
//             {
//                 Ai_algorithm a = new Ai_algorithm(employee[j][0],employee[j][1],employee[j][2],employee[j][8],employee[j][4],employee[j][3]);
//                 score[j]=a.compute();
                
//             }

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
    public static demo[] analyze(List<Double> list, String[][] e, int i)
    {
    	
        for(int x=1; x<10000; x++)
        {
            if(e[x][i]==null)//8 is age 4 is tenure 3 is salary
            {
                break;
            }
            list.add(Double.parseDouble(e[x][i]));              
        }
        Collections.sort(list);
        
        //break list up into demos
        int t = list.size()/10;
        demo[] demoList = new demo[10];
        for (int f = 0; f < 10; f++) {
        	demoList[f] = new demo();
        }
        
        for (int j = 0; j < 10; j++) {
        	demoList[j].setMin(t*j);
        	demoList[j].setMax(t*(j+1));
        }
        
        //add population to each demo
        for (demo d : demoList) {
        	for (int a = 0; a < list.size(); a++) {
        		if (d.inRange(list.get(a))){
        			d.pop(); //add a value for each
        		}
        	}
        }
        
        //find terminated ratios
        for (int k = 0; k <10; k++) {
        	double[] term_list = find_Terminated(e, i, t*k, t*(k+1));
        	if (demoList[k].getPop() != 0) {
        		demoList[k].setPercent(term_list.length/demoList[k].getPop()); 	
        	}
        	else {
        		demoList[k].setPercent(0);
        	}
        }
        List<Double> demoL=new ArrayList<>();
        for (demo m:demoList)
        {
        	demoL.add(m.getPercent());
        }
        Collections.sort(demoL,Collections.reverseOrder());
        for(int c=0;c<10;c++)
        {
        	for(int d=0;d<10;d++)
        	{if(demoList[d].getPercent()==demoL.get(c))
        		demoList[d].setRank(c+1);
        			}
        }
        return demoList;
        //return list;
    }
    public static double[] find_Terminated(String[][] e, int x,double min, double max)//x is the index of column
    {
    	double[] temp= new double[10000];
    	int count=0;
    	for(int i=1;i<10000;i++){
    		if(e[i][x] == null)
    			break;
    		if(Integer.parseInt(e[i][9])==1 && Double.parseDouble(e[i][x])<max && Double.parseDouble(e[i][x])>min)
    		{
    			temp[count]=Double.parseDouble(e[i][x]);
    			count++;
    		}
    	}
    	return temp;
    }
    public static int rank(double[] t, List<Double> l)
    {
    	
		return 0;
    	
    }
    
}