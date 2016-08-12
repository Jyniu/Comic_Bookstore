/**
 *  A class for providing date server
 *
 *  @author    Yuying Song
 *  @date	   7th May,2014
 */

package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//Date server
public class DateSer {
	
	/**
	 *  A method for getting current date 
	 *  @return current date 
	 */
	public String getTime(){
		Date now = new Date(); 
		DateFormat d = DateFormat.getDateInstance();
		return d.format(now); 
	}
	
	/**
	 *  A method for calculating time difference between current date and old date
	 *  @param oldtime  old time date
	 *  @return time difference between current date and old date in days
	 */
	public long getTD(String oldtime){
		Date d2 = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //set date format
		Date d1=null;
		try {
			d1 = format.parse(oldtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();                      //calculate the time difference
		return TimeUnit.MILLISECONDS.toDays(diff);                    //transfer the difference in day's format
	}
	
	/**
	 *  A method for calculating time difference between new date and old date
	 *  @param newtime  new time date
	 *  @param oldtime  old time date
	 *  @return time difference between new date and old date in days
	 */
	public long getTD(String newtime,String oldtime){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date d1=null;
		Date d2=null;
		try {
			d1 = format.parse(oldtime);
			d2 = format.parse(newtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.MILLISECONDS.toDays(diff); 
	}
}
