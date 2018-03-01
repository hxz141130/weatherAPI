
public class demo {

	double min=0;
	double max=0;
	int rank; //number of points it receives in this demo
	
	int population = 0;
	double percentage;
	
	public demo(){}
	
	public demo(double a, double b) {
		setMin(a);
		setMax(b);
		rank = 0;
	}
	
	void setMin(double i){
		min = i;
	}
	
	void setMax(double i) {
		max = i;
	}
	
	Boolean inRange(double i) {
		if (i > min && i < max) {
			return true;
		}
		return false;
	}
	
	public void setRank(int i) {
		rank = i;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void pop() {
		population++;
	}
	
	public int getPop() {
		return population;
	}
	
	public void setPercent(double p) {
		percentage = p;
	}
	
	public double getPercent() {
		return percentage;
	}
	public double getMin()
	{
		return min;
	}
	public double getMax()
	{
		return max;
	}
}
