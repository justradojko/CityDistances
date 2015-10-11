
public class CityRecord implements Comparable<CityRecord>{
	private String cityName;
	private int timeInMinutes;

	public int compareTo(CityRecord o){
		return Integer.compare(this.timeInMinutes, o.timeInMinutes);
	}
	
	public CityRecord(String s, int t){
		cityName = s;
		timeInMinutes = t;
	}
	
	public String getCityName() {
		return cityName;
	}

	public int getTimeInMinutes() {
		return timeInMinutes;
	}	
}
