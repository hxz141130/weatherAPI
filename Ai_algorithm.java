public class Ai_algorithm {

    String fname, mname,lname;
    int age,tenure;
    double salary;
//    int pr; putting the most recent one in.
    
    public Ai_algorithm(String first, String middle, String last,int e_age, int e_tenure, double e_salary)
    {
        fname=first;
        lname=last;
        mname=middle;
        age=e_age;
        tenure=e_tenure;
        salary=e_salary;
//        pr=e_pr;
    }
    public Ai_algorithm()
    {
    	
    }
//     public Ai_algorithm(String f, String m, String l, String a, String t, String s)
//     {
//         fname=f;
//         lname=l;
//         mname=m;
//         age=Integer.parseInt(a);
//         tenure=Integer.parseInt(t);
//         salary=Double.parseDouble(s);
//     }
    public String getFirst()
    {
        return fname;
    }
    public String getMiddle()
    {
        return mname;
    }
    public String getLast()
    {
        return lname;
    }
    public double c_Age(int rank)
    {
    	double r=3;
//        if(age<26)
//        {
//            r=0;
//        }
//        switch(age){
//        case 30:case 31:
//            r=1.5;
//        case 26:case 27:
//            r=3;        
//        case 32:case 33:
//            r=4.5;
//        case 28:case 29:
//            r=6;
//        case 56:case 57:
//            r=7.5;
//        case 42:case 43:
//            r=9;
//        case 48:case 49:
//            r=10.5;
//        case 50:case 51:
//            r=12;
//        case 40:case 41:
//            r=13.5;
//        case 34:case 35:
//            r=15;
//        case 36:case 37:
//            r=16.5;
//        case 54:case 55:
//            r=18;
//        case 46:case 47:
//            r=19.5;        
//        case 52:case 53:
//            r=21;
//        case 44:case 45:
//            r=22.5;
//        case 38:case 39:
//            r=24;
//        case 58:case 59:
//            r=25.5;
//        case 60:case 61:
//            r=27;
//        }
        return r*rank;        
    }
    public double c_Tenure(int rank)
    {
        double r=4;
//        if (tenure < 500)
//            r = 1;
//if (tenure > 500 && tenure <= 1000)
//             r = 6;
//if (tenure > 1000 && tenure <= 1500)
//             r = 12;
//if (tenure > 1500 && tenure <= 2000)
//             r = 9;
//if (tenure > 2000 && tenure <= 2500)
//             r = 10;
//if (tenure > 2500 && tenure <= 3000)
//             r = 8;
//if (tenure > 3000 && tenure <= 3500)
//             r = 13;
//if (tenure > 3500 && tenure <= 4000)
//             r = 21;
//if (tenure > 4000 && tenure <= 4500)
//             r = 20;
//if (tenure > 4500 && tenure <= 5000)
//             r = 17;
//if (tenure > 5000 && tenure <= 5500)
//             r = 15;
//if (tenure > 5500 && tenure <= 6000)
//             r = 14;
//if (tenure > 6000 && tenure <= 6500)
//             r = 18;
//if (tenure > 6500 && tenure <= 7000)
//             r = 11;
//if (tenure > 7000 && tenure <= 7500)
//             r = 22;
//if (tenure > 7500 && tenure <= 8000)
//             r = 23;
//if (tenure > 8000 && tenure <= 8500)
//             r = 16;
//if (tenure > 8500 && tenure <= 9000)
//             r = 5;
//if (tenure > 9000 && tenure <= 9500)
//             r = 4;
//if (tenure > 9500 && tenure <= 10000)
//             r = 24;
//if (tenure > 10000 && tenure <= 10500)
//             r = 19;
//if (tenure > 10500 && tenure <= 11000)
//             r = 24;
//if (tenure > 11000 && tenure <= 11500)
//             r = 7;
//if (tenure > 11500 && tenure <= 12000)
//             r = 2;
//if (tenure > 12000 && tenure <= 12500)
//             r = 5;
//if (tenure > 12500 && tenure <= 13000)
//             r = 5;
//if (tenure > 13000 && tenure <= 13500)
//             r = 5;
//        if (tenure > 13500 && tenure <= 14000)
//             r = 3;
//        if (tenure > 14000 && tenure <= 14500)
//             r = 2;
//        if (tenure > 14500 && tenure <= 15000)
//             r = 2;

        return r*rank;
        
    }


public double c_Salary(int rank)
{
    double r = 3;
//    if(salary<30000)
//    {
//        r=0;
//    }
//        
//    if(salary>40000 && salary<=45000)
//        r = 2.3;
//    else if(salary>30000 && salary<=35000)
//        r = 4.6;
//    else if(salary>55000 && salary<=60000)
//        r = 6.9;
//    else if(salary>65000 && salary<=70000)
//        r = 9.2;
//    else if(salary>45000 && salary<=50000)
//        r = 11.5;
//    else if(salary>50000 && salary<=55000)
//        r = 13.8;
//    else if(salary>60000 && salary<=65000)
//        r = 16.1;
//    else if(salary>35000 && salary<=40000)
//        r = 18.4;
//    /*
//    else if(salary>100000 && salary<=105000)
//    r = 18.4;
//     */
//    else if(salary>75000 && salary<=80000)
//        r = 20.7;
//    else if(salary>80000 && salary<=85000)
//        r = 23;
//    else if(salary>90000 && salary<=95000)
//        r = 25.3;
//    else if(salary>85000 && salary<=90000)
//        r = 27.6;
//    else if(salary>95000 && salary<=100000)
//        r = 29.9;
//        
    return r*rank;
}

//    public double c_Pr()
//    {
//        double r=20;
//        if(pr==0)
//        {
//            r=r*.66;
//        }
//        else if(pr==-1)
//        {
//            r=r*.33;
//        }
//        return r;
//    }
    public double compute(int rankA,int rankT, int rankS)
    {
        double fin;
        fin=c_Tenure(rankT)+c_Salary(rankS)+c_Age(rankA);
        System.out.println(fin);
        return fin;
    
    }
}
