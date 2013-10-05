import java.util.Calendar;
import java.util.Locale;


public class CalendarTest {
	public static void main(String[] args){
		Calendar ca = Calendar.getInstance();
		System.out.println("Month:" + (ca.get(ca.MONTH) + 1));
		System.out.println("Day:" + ca.get(ca.DAY_OF_MONTH));
		System.out.println("Hour:" + ca.get(ca.HOUR_OF_DAY));
		System.out.println("Minute:" + ca.get(ca.MINUTE));
		
		
	}

}
