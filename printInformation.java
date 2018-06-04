package weatherAPI;
import javax.ws.rs.*;

@Path("/printInformation")
public class printInformation {
	//find out which element it is and print out the value correspond to the element
	@POST
	public String elements(String C_column, double D_column, String G_column)
	{

	if(C_column.equals("AWDR"))
	{
	    return "Average daily wind direction: " + D_column + " degrees";
	}
	else if(C_column.equals("PRCP"))
	{
		return "Precipitation: " + D_column/10 + " mm";

	}
	else if(C_column.equals("SNOW"))
	{
		return "Snowfall: " + D_column + " mm";
	}
	else if(C_column.equals("SNWD"))
	{
	    return "Snow depth: " + D_column + " mm";

	}
	else if(C_column.equals("TMAX"))
	{
		return "Maximum temperature: " + D_column/10 + " C degrees";
	}
	else if(C_column.equals("TMIN"))
	{
		return "Minimum temperature: " + D_column/10 + " C degrees";

	}
	else if(C_column.equals("TAVG"))
	{
	if (G_column.equals("S"))
	{
		return "Average temperature: " + D_column/10 + " C degrees  Note: the average for the period ending at 2400 UTC rather than local midnight";
	}
	else
	{
		return "Average temperature: " + D_column/10 + " C degrees";
	}
	}
	else if(C_column.equals("TOBS"))
	{
		return "Temperature at the time of observation: " + D_column/10 + " C degrees";
	}
	else if(C_column.equals("TSUN"))
	{
		return "Daily total sunshine: " + D_column + " minutes";

	}
	else if(C_column.equals("WDF2"))
	{
        return "Direction of fastest 2-minute wind: " + D_column + " degrees";
	}
	else if(C_column.equals("WDF3"))
	{
		return "Direction of fastest 3-minute wind: " + D_column + " degrees";
	}
	else if(C_column.equals("WDF5"))
	{
		return "Direction of fastest 5-second wind: " + D_column + " degrees";
	}
	else if(C_column.equals("WDFG"))
	{
		return "Direction of peak wind gust: " + D_column + " degrees";
	}
	else if(C_column.equals("WDMV"))
	{
		return "24-hour wind movement: " + D_column + " km";
	}
	else if(C_column.equals("WESD"))
	{
		return "Water equivalent of snow on the ground: " + D_column/10 + " mm";
	}
	else if(C_column.equals("WESF"))
	{
		return "Water equivalent of snowfall: " + D_column/10 + " mm";
	}
	else if(C_column.equals("WSF2"))
	{
		return "Fastest 2-minute wind speed: " + D_column/10 + " m/s";
	}
	else if(C_column.equals("WSF5"))
	{
		return "Fastest 5-second wind speed: " + D_column/10 + " m/s";
	}
	else if(C_column.equals("WSFG"))
	{
		return "Peak gust wind speed: " + D_column/10 + " m/s";
	}
	else if(C_column.equals("WSFI"))
	{
		return "Highest instantaneous wind speed: " + D_column/10 + " m/s";
	}
	else if(C_column.equals("WT01"))
	{
		return "This area has fog, ice fog, or freezing fog";
	}
	else if(C_column.equals("WT02"))
	{
		return "This area has heavy fog or heaving freezing fog";
	}
	else if(C_column.equals("WT03"))
	{
		return "This area has Thunder";
	}
	else if(C_column.equals("WT04"))
	{
		return "This area has ice pellets, sleet, snow pellets, or small hail";
	}
	else if(C_column.equals("WT05"))
	{
		return "This area has hail";
	}
	else if(C_column.equals("WT06"))
	{
		return "This area has glaze or rime";
	}
	else if(C_column.equals("WT07"))
	{
		return "This area has Dust, volcanic ash, blowing dust, blowing sand, or blowing obstruction";
	}
	else if(C_column.equals("WT08"))
	{
		return "This area has smoke or haze";
	}
	else if(C_column.equals("WT09"))
	{
		return "This area has blowing or drifting snow";
	}
	else if(C_column.equals("WT11"))
	{
		return "This area has high or damaging winds";
	}
	else if(C_column.equals("WT15"))
	{
		return "This area has freezing drizzle";
	}
	else if(C_column.equals("WT17"))
	{
		return "This area has freezing rain";
	}
	else if(C_column.equals("WT18"))
	{
		return "This area has Snow, snow pellets, snow grains, or ice crystals";
	}
	else
	{
		return "This information missing";
	}
	}
	//print out the correspond measurement flag for the first day of the month.
	public String MFLAG1(String E_col)
	{ 
	//convert E_col from string to char type
	if (!E_col.equals(""))
	{
	char c = E_col.charAt(0);
	switch (c)
	{
	case 'B':
	return "precipitation total formed from two 12-hour totals";
	case 'D':
	return "precipitation total formed from four six-hour totals";
	default:
	break;
	}
	}
	return "no measurement information applicable";
	}
	//print out the correspond quality flag for the first day of the month.
	public String QFLAG1(String F_col)
	{
	if (F_col.equals(""))
	return "did not fail any quality assurance check";
	//convert E_col from string to char type
	else
	{
	char d = F_col.charAt(0);
	switch (d)
	{
	case 'D':
	return "failed duplicate check";
	case 'G':
	return "failed gap check";
	case 'I':
	return "failed internal consistency check";
	case 'K':
	return "failed streak/frequent-value check";
	case 'L':
	return "failed check on length of multiday period";
	case 'M':
	return "failed megaconsistency check";
	case 'N':
	return "failed naught check";
	case 'O':
	return "failed climatological outlier check";
	case 'R':
	return "failed lagged range check";
	case 'S':
	return "failed spatial consistency check";
	case 'T':
	return "failed temporal consistency check";
	case 'W':
	return "temperature too warm for snow";
	case 'X':
	return "failed bounds check";
	case 'Z':
	return "flagged as a result of an official Datzilla investigation";
	default:
	break;
	}
	}
	return "This information missing";
	}
	//print out the source flag for the first day of the month.
	public String SFLAG1(String G_col)
	{
	switch (G_col)
	{
	case "7":
	return "U.S. Cooperative Summary of the Day -- Transmitted via WxCoder3 (NCDC DSI-3207)";
	case "a":
	return "Australian data from the Australian Bureau of Meteorology";
	case "0":
	return "U.S. Cooperative Summary of the Day (NCDC DSI-3200)";
	case "6":
	return "CDMP Cooperative Summary of the Day (NCDC DSI-3206)";
	case "A":
	return "U.S. Automated Surface Observing System (ASOS) \r\n" + 
	"                   real-time data (since January 1, 2006)";
	case "B":
	return "U.S. ASOS data for October 2000-December 2005 (NCDC \r\n" + 
	"                   DSI-3211)";
	case "b":
	return "Belarus update";
	case "C":
	return "Environment Canada";
	case "D":
	return "Short time delay US National Weather Service CF6 daily \r\n" + 
	"             summaries provided by the High Plains Regional Climate\r\n" + 
	"     Center";
	case "E":
	return "European Climate Assessment and Dataset (Klein Tank \r\n" + 
	"             et al., 2002)";
	case "F":
	return "U.S. Fort data";
	case "G":
	return "Official Global Climate Observing System (GCOS) or \r\n" + 
	"                   other government-supplied data";
	case "H":
	return "High Plains Regional Climate Center real-time data";
	case "I":
	return "International collection (non U.S. data received through\r\n" + 
	"             personal contacts)";
	case "K":
	return "U.S. Cooperative Summary of the Day data digitized from\r\n" + 
	"             paper observer forms (from 2011 to present)";
	case "M":
	return "Monthly METAR Extract (additional ASOS data)";
	case "N":
	return "Community Collaborative Rain, Hail,and Snow (CoCoRaHS)";
	case "Q":
	return "Data from several African countries that had been \r\n" + 
	"             \"quarantined\", that is, withheld from public release\r\n" + 
	"     until permission was granted from the respective \r\n" + 
	"             meteorological services";
	case "R":
	return "NCEI Reference Network Database (Climate Reference Network\r\n" + 
	"             and Regional Climate Reference Network)";
	case "r":
	return "All-Russian Research Institute of Hydrometeorological \r\n" + 
	"             Information-World Data Center";
	case "S":
	return "Global Summary of the Day (NCDC DSI-9618)\r\n" + 
	"                   NOTE: \"S\" values are derived from hourly synoptic reports\r\n" + 
	"                   exchanged on the Global Telecommunications System (GTS).\r\n" + 
	"                   Daily values derived in this fashion may differ significantly\r\n" + 
	"                   from \"true\" daily data, particularly for precipitation\r\n" + 
	"                   (i.e., use with caution).";
	case "s":
	return "China Meteorological Administration/National Meteorological Information Center/\r\n" + 
	"             Climatic Data Center (http://cdc.cma.gov.cn)";
	case "T":
	return "SNOwpack TELemtry (SNOTEL) data obtained from the U.S. \r\n" + 
	"             Department of Agriculture's Natural Resources Conservation Service";
	case "U":
	return "Remote Automatic Weather Station (RAWS) data obtained\r\n" + 
	"             from the Western Regional Climate Center";
	case "u":
	return "Ukraine update";
	case "W":
	return "WBAN/ASOS Summary of the Day from NCDC's Integrated \r\n" + 
	"             Surface Data (ISD).";
	case "X":
	return "U.S. First-Order Summary of the Day (NCDC DSI-3210)";
	case "Z":
	return "Datzilla official additions or replacements";
	case "z":
	return "Uzbekistan update";
	}
	return "This SFLAG1 information missing";
	}

	}
