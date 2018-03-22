import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class trendata {
	
    static int DOBIndex = -1;
    static int salaryIndex = -1;
    static int hireIndex = -1;
    static int fireIndex = -1; //column for termination date
    static int voluntaryIndex = -1; //column for voluntary or involuntary termination
    
    static int ageIndex;
    static int tenureIndex;
    static int terminationIndex;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String fname, mname,lname;
        int age,tenure;
        double salary=0;
        int pr;
        
        //ArrayList lines is meant to find the exact dimensions of input. 
        //It is then propagated to Array employee with the correct dimensions.
        
        PrintWriter pw = new PrintWriter(new File("Test.csv"));
        BufferedReader reader = new BufferedReader(new FileReader("Test Data Set - 600.csv"));
        String line = "";
        List<String> lines = new ArrayList<>();

        double [] score= new double[10000];
        int y=0;
        while((line=reader.readLine())!=null){
             lines.add(line);
            //employee[y] =line.trim().split(",");
            y++;
        }//parse lines into tokens into 2d array
        
        int width = 1; //Use number of commas to find total width of a row
        String s = lines.get(0);
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == ',') {
        		width++;
        	}
        }
        
        //2d Array employee is the representation of the dataset.
        //Add space at the end for generated columns.
        //Column +1 will be age
        //Column +2 will be tenure
        
        String [][] employee = new String[lines.size()][width+5];
        y=0;
        for (String l : lines) {
        	String[] clone = new String[width+5];
        	System.arraycopy(l.trim().split(","), 0, clone, 0, l.trim().split(",").length);
        	employee[y] = clone;
        	//employee[y] =l.trim().split(",");
        	y++;
        }
       
        
        for (int i = 0; i < employee.length; i++) {
            for (int j = 0; j < width+5; j++) {
                if (employee[i][j] == null) {
                    employee[i][j] = "";
                }
            }
        }
        
        ageIndex = width+1;
        tenureIndex = width+2;
        terminationIndex = width+3;
        
        //3 available metrics for data analysis
        List<Double> Ary_age= new ArrayList<>();
        List<Double> Ary_tenure=new ArrayList<>();
        List<Double> Ary_salary=new ArrayList<>();
        
        //Finding indexes for relevant columns. List any that maybe exist for datasets, even if they may not be present.
        
        
        for (int i = 0; i < employee[0].length; i++ ) {
        	if (employee[0][i].equals("DOB")) {
        		DOBIndex = i;
        	}
        	else if (employee[0][i].equals("Employee Salary")) {
        		salaryIndex = i;
        	}
        	else if (employee[0][i].equals("Hire Date")) {
        		hireIndex = i;
        	}
        	else if (employee[0][i].equals("Termination Date")) {
        		fireIndex = i;
        	}
        	else if (employee[0][i].equals("Separation Type")) {
        		voluntaryIndex = i;
        	}
        }
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        Date d1 = null;
        Date d2 = new Date();
        
        for (int i = 1; i<lines.size(); i++) {
        	//Termination boolean
        	if (!employee[i][fireIndex].equals("")) {
        		employee[i][terminationIndex] = 1 + "";
        	}
        	else { employee[i][terminationIndex] = 0 + "";}

        	try {
				d1 = format.parse(employee[i][DOBIndex]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	employee[i][ageIndex] = (d2.getTime() - d1.getTime()) / (1000*60*60*24*365) + "";
        	

	        if (employee[i][terminationIndex].equals("1")){
        	try {
				d1 = format.parse(employee[i][hireIndex]);
				d2 = format.parse(employee[i][fireIndex]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        	employee[i][tenureIndex] = (d2.getTime() - d1.getTime()) / (1000*60*60*24*365) + "";
        	}
        	else {
            	try {
    				d1 = format.parse(employee[i][hireIndex]);
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}
        		employee[i][tenureIndex] = (d2.getTime() - d1.getTime()) / (1000*60*60*24*365) + "";
        	}
        }
        
        
        
        demo[] dAge=analyze(Ary_age, employee,ageIndex);
        demo[] dTenure=analyze(Ary_tenure, employee,tenureIndex);
        demo[] dSalary=analyze(Ary_salary, employee,salaryIndex);
        
        System.out.println("AGE");
        for (demo d : dAge) {
        	System.out.println("Demographic " + d.getRank() + ": " + d.getMin() + " to " + d.getMax() + " " + d.getPercent() + " " + d.getPop());
        }

    	System.out.println("TENURE");
        for (demo d : dTenure) {
        	System.out.println("Demographic " + d.getRank() + ": " + d.getMin() + " to " + d.getMax() + " " + d.getPercent()+ " " + d.getPop());
        }

    	System.out.println("SALARY");
        for (demo d : dSalary) {        	
        	System.out.println("Demographic " + d.getRank() + ": " + d.getMin() + " to " + d.getMax() + " " + d.getPercent()+ " " + d.getPop());
        }
        double points=0;
        Ai_algorithm a = new Ai_algorithm();
        int ag=0,te=0,sa=0;
        for(int h=1; h<employee.length; h++)
        {
        	if(employee[h][0]==null)
        	{
        		break;
        	}
        	for(int l=0;l<dAge.length;l++)
        	{
        		if(dAge[l].inRange(Double.parseDouble(employee[h][ageIndex])))
        		{
        			ag=dAge[l].getRank() * (1-((int)dAge[l].getPercent()*100));
        		}
        	}
        	for(int o=0;o<dTenure.length;o++) {
        		if(dTenure[o].inRange(Double.parseDouble(employee[h][tenureIndex])))
        		{
        			te=dTenure[o].getRank() * (1-((int)dTenure[o].getPercent()*100));
        		}
        	}
        	for(int q=0;q<dSalary.length;q++) {
        		if(dSalary[q].inRange(Double.parseDouble(employee[h][salaryIndex])))
        		{
        			sa=dSalary[q].getRank() * (1-((int)dSalary[q].getPercent()*100));
        		}
        	}
        	//System.out.println("The scores for employee " + h + " is ag = " + ag + " te =" + te + " sa = " + sa);
        	score[h]=a.compute(ag,te,sa);
        }

       for(int z=1;z<lines.size();z++)
       {
           lines.set(z, lines.get(z)+","+ employee[z][terminationIndex]+","+score[z]);
       }
       
       int t=1;
       pw.append(lines.get(0)+","+" Termination"+","+"Score");
       pw.append(System.lineSeparator());
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
    public static demo[] analyze(List<Double> list, String[][] e, int i)//i for index of column, whether it is age, tenure, or salary
    {

    	
        for(int x=1; x<e.length; x++)
        {
            if(e[x][i]==null)
            {
                break;
            }
//            if(e[x][i].charAt(0)=='$')
//            {
//            	e[x][i]=e[x][i].substring(1, e[x][i].length()-1);
//            	
//            }
//            System.out.println(e[x][i]);
            list.add(Double.parseDouble(e[x][i]));              
        }
        Collections.sort(list);
        
        //break list up into demos
//        demo[] demoList = new demo[20]; 20 hard coded categories for algorithm 0.1
        demo[] demoList = new demo[8];
        for (int f = 0; f < demoList.length; f++) {
        	demoList[f] = new demo();
        }
        double mid=find_Average(list);
        List<Double> high = new ArrayList<>(), low=new ArrayList<>(), lowL=new ArrayList<>(), lowH=new ArrayList<>(), 
        		highL=new ArrayList<>(),highH=new ArrayList<>(), lowLL=new ArrayList<>(), lowLH=new ArrayList<>(),
        		lowHL=new ArrayList<>(), lowHH=new ArrayList<>(), highLL=new ArrayList<>(), highLH=new ArrayList<>(),
        		highHL=new ArrayList<>(),highHH=new ArrayList<>();
        for(int p=0; p<list.size();p++)
        {
        	if(list.get(p)<=mid)
        	{
        		low.add(list.get(p));
        	}
        	else
        		high.add(list.get(p));
        }
        double lowM=find_Average(low), highM=find_Average(high);

        	
        	
        	
        	for(int p=0; p<low.size();p++)
            {
            	if(low.get(p)<=lowM)
            	{
            		lowL.add(low.get(p));
            	}
            	else
            		lowH.add(low.get(p));
            }
        	for(int p=0; p<high.size();p++)
            {
            	if(high.get(p)<=highM)
            	{
            		highL.add(high.get(p));
            	}
            	else
            		highH.add(high.get(p));
            }
        	double lowLM=find_Average(lowL), lowHM=find_Average(lowH),highLM=find_Average(highL),highHM=find_Average(highH);
//        	System.out.println(lowM+" "+highM+ " "+ mid);
        	demoList[0].setMin(0);
        	demoList[0].setMax(lowLM);
        	demoList[1].setMin(lowLM+1);
        	demoList[1].setMax(lowM);
        	demoList[2].setMin(lowM+1);
        	demoList[2].setMax(lowHM);
        	demoList[3].setMin(lowHM+1);
        	demoList[3].setMax(mid);
        	demoList[4].setMin(mid+1);
        	demoList[4].setMax(highLM);
        	demoList[5].setMin(highLM+1);
        	demoList[5].setMax(highM);
        	demoList[6].setMin(highM+1);
        	demoList[6].setMax(highHM);
        	demoList[7].setMin(highHM+1);
        	demoList[7].setMax(list.get(list.size()-1));

        	for(int p=0; p<lowL.size();p++)
            {
            	if(lowL.get(p)<=lowLM)
            	{
            		lowLL.add(lowL.get(p));
            	}
            	else
            		lowLH.add(lowL.get(p));
            }

        	for(int p=0; p<lowH.size();p++)
            {
            	if(lowH.get(p)<=lowHM)
            	{
            		lowHL.add(lowH.get(p));
            	}
            	else
            		lowHH.add(lowH.get(p));
            }

        	for(int p=0; p<highL.size();p++)
            {
            	if(highL.get(p)<=highLM)
            	{
            		highLL.add(highL.get(p));
            	}
            	else
            		highLH.add(highL.get(p));
            }

        	for(int p=0; p<highH.size();p++)
            {
            	if(highH.get(p)<=highHM)
            	{
            		highHL.add(highH.get(p));
            	}
            	else
            		highHH.add(highH.get(p));
            }
        demoList[0].setPopulation(lowLL.size());
        demoList[1].setPopulation(lowLH.size());
        demoList[2].setPopulation(lowHL.size());
        demoList[3].setPopulation(lowHH.size());
        demoList[4].setPopulation(highLL.size());
        demoList[5].setPopulation(highLH.size());
        demoList[6].setPopulation(highHL.size());
        demoList[7].setPopulation(highHH.size());
        //        if(i==4)//the first demo is set.
//    	{
//    		demoList[0].setMax(500);
//    		demoList[0].setMin(0);
//    	}
//        if(i==8)
//    	{
//    		demoList[0].setMax(25);
//    		demoList[0].setMin(0);    		
//    	}
//        if(i==3)
//    	{
//    		demoList[0].setMax(30000);
//   			demoList[0].setMin(0);
//    	}
//        int counter=0;//counter is where we start.
//        for(int ind=0;ind<list.size();ind++)
//        {
//        	if(i==4)
//        	{
//        		if(list.get(ind)<500)
//        		counter++;
//        	}
//        	if(i==8)
//        	{
//        		if(list.get(ind)<25)
//        			counter++;
//        	}
//        	if(i==3)
//        	{
//        		if(list.get(ind)<30000)
//        		counter++;
//        	}
//        }
//        int t = (list.size()-counter)/19;
//        for (int j = 1; j < 20; j++) {        	
//        	demoList[j].setMin(counter+(j-1)*t);
//        	
//        	demoList[j].setMax(counter+j*t);
//        	System.out.print(demoList[j].getMax()+" "+demoList[j].getMin()+" ");
//        }
        
        //add population to each demo
//        int co=0;
//        for (demo d : demoList) {
////        	for (int a = 0; a < list.size(); a++) {
////        		if (d.inRange(list.get(a))){
////        			d.pop(); //add a value for each
////        		}
//        	d.setPopulation(t);
//        		
//        	}       
        
        //find terminated ratios
        for (int k = 0; k <demoList.length; k++) {
//        	List<Double> term_list = find_Terminated(e, i, t*k, t*(k+1));
        	if (demoList[k].getPop() != 0) {
        		demoList[k].setPercent((double)(find_Terminated(e, i, demoList[k].getMin(), demoList[k].getMax()))/(double)(demoList[k].getPop()));
       		//System.out.println("Termination Ratio for demo " + k + " is " + demoList[k].getPercent());
        	}
        	else if (demoList[k].getPop() == 0) {
        		demoList[k].setPercent(0);
        	}
        }
        List<Double> demoL=new ArrayList<>();
        for (demo m:demoList)
        {
        	demoL.add(m.getPercent());
        }
        Collections.sort(demoL,Collections.reverseOrder());
        for(int c=0;c<demoList.length;c++)
        {
        	for(int d=0;d<demoList.length;d++)
        	{if(demoList[d].getPercent()==demoL.get(c))
        		demoList[d].setRank(c+1);
        			}
        }
        return demoList;
        //return list;
    }
    public static int find_Terminated(String[][] em, int x,double min, double max)//x is the index of column
    {
    	int count=0;
    	for(int i=1;i<em.length;i++){
    		if(em[i][x] == null)
    			break;
    		if(Integer.parseInt(em[i][terminationIndex])==1 && Double.parseDouble(em[i][x])<=max && Double.parseDouble(em[i][x])>min)
    		{
//    			temp.add(Double.parseDouble(em[i][x]));
    			count++;
    		}
    	}
    	return count;
    }
    public static double find_Average(List<Double> emp)
    {
    	double total=0;
    	for(int i=0; i<emp.size();i++)
    	{
    		total+=emp.get(i);
    	}
    	
    	return total/emp.size();
    }
}
