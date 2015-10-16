import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CITY_DISTANCES")
public class CloseCities {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String time;
	private String firstCity;
	private String secondCity;
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result
				+ ((firstCity == null) ? 0 : firstCity.hashCode());
		result = result
				+ ((secondCity == null) ? 0 : secondCity.hashCode());		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;		
		CloseCities other = (CloseCities) obj;				
		if (firstCity == null) {
			if (other.firstCity != null)
				return false;
		} else if (! (firstCity.equals(other.firstCity) || firstCity.equals(other.secondCity)))
			return false;
		if (secondCity == null) {
			if (other.secondCity != null)
				return false;
		} else if (! (secondCity.equals(other.secondCity) || secondCity.equals(other.firstCity) ))
			return false;
		return true;
	}

	public CloseCities(){
		super();
	}
	
	public CloseCities(String t, String s1, String s2){
		time = t;
		firstCity = s1;
		secondCity = s2;
	}

	public String getTime() {
		return time; 
	}

	public String getFirstCity() {
		return firstCity;
	}

	public String getSecondCity() {
		return secondCity;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	public void setFirstCity(String firstCity) {
		this.firstCity = firstCity;
	}

	public void setSecondCity(String secondCity) {
		this.secondCity = secondCity;
	}	
}
