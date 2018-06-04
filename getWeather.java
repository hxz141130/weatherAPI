package weatherAPI;

import javax.servlet.ServletException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/getWeather")
public class getWeather {
	public static void main(String[] args) throws IOException, ServletException{
	String a = "";
	updateInformation(a);
	}

/*	public static void main(String[] args) throws IOException{
	getWeather app = new getWeather();
	}*/
	@POST
	@Path("/readFile/post")
	public static String updateInformation(@FormParam("stationID") String stationID) throws IOException, ServletException{
		//read the csv file
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\asdf\\workspace\\weatherAPI\\Book1.csv"));
		String line = "";
		String all ="unknowxx";
		String overall = "";
		List<String> lines = new ArrayList<>();
		int y = 0;
		while((line=reader.readLine())!=null)
		{
		lines.add(line);
		y++;
		}
		//find the width of a row
		int width = 0;
		String s = lines.get(0);//this only gets the width of first row
		for (int i = 0; i<s.length(); i++)
		{
		if (s.charAt(i) == ',')
		{
		width++;
		}
		}
		String[][] weatherInfo = new String[lines.size()][width+1];
		y=0;
		for (String l : lines)
		{
		String[] clone = new String[width+5];
		System.arraycopy(l.trim().split(","), 0, clone, 0, l.trim().split(",").length);
		weatherInfo[y] = clone;
		y++;
		}
		//converting all the null values in code to empty strings
		for (int i = 0; i < weatherInfo.length; i++)
		{
		for (int j = 0; j < width+5; j++)
		{
		if(weatherInfo[i][j] == null)
		{
		weatherInfo[i][j] = "";
		}
		}
		}


		//search the station id in the file and display 
		//all the weather information correspond to this station id
		String info1 = "";
		for (int j = 0; j<lines.size(); j++)
		{
		if (stationID.equals(weatherInfo[j][0]))
		    {
		                // change the format for the date

		try {
		Date date = new SimpleDateFormat("yyyyMMdd").parse(weatherInfo[j][1]);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String testDateString = df.format(date);
		info1 = "Date: " + testDateString;
		} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}

		// call the elements method to find out which elements should pop up
		    // for the correspond station ID entered by the user
		String SF = weatherInfo[j][6];
		String weather = weatherInfo[j][2].toString();
		String str = weatherInfo[j][3].toString();
		String MF = weatherInfo[j][4].toString();
		String QF = weatherInfo[j][5].toString();
		/*String X = weatherInfo[j][7]; not use yet*/
		// Don't know where to use yet: double value2 = Double.parseDouble(X);
		double value = Double.parseDouble(str);
		// print the rest information follow by the correspond station ID
        printInformation Info = new printInformation();
	    all = info1 + ", " + System.lineSeparator() + Info.elements(weather, value, SF) + ", " + System.lineSeparator() + Info.MFLAG1(MF) + ", " + System.lineSeparator() + Info.QFLAG1(QF) + ", " + System.lineSeparator() + Info.SFLAG1(SF);
		overall = overall + System.lineSeparator() + all;


		    }
		}
		reader.close();
		System.out.println(overall);
		return overall;
		
	}
	
	


}
