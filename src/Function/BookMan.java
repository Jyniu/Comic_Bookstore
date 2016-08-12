/**
 *  A class for book management
 *
 *  @author    Mingjie Yang 
 *  @date	   9th May,2014
 */

package Function;

import Helper.DBhelper;
import UI.NoticeUI;

public class BookMan {
	private final static String BOOK_INF="book_infor.txt";              //book information filename
	private DBhelper db;
	private NoticeUI n;
	
	/**
	 *  A method for change book price
	 *  @param ID  id of the book
	 *  @param newP new price of the book
	 *  @return whether this operation is success
	 */
	public boolean chanPrice(int ID,String newP){
		double newPrice;
		//check if price is a double
		try{
			newPrice = Double.parseDouble(newP);
		}catch(Exception e){
			n=new NoticeUI("The price must be a number!");
			return false;
		}
		//check if the price is not negative
		if(newPrice<0){
			n=new NoticeUI("The book price can not be negative!");
			return false;
		}	
		//get book information and replace the price item with new price
		db=new DBhelper();
		String[] res=db.query(BOOK_INF, ID + "", 1);
		res[3]=newPrice + "";
		if(db.update(BOOK_INF,ID + "", 1, res)){
			return true;
		}else{
			n=new NoticeUI("The book price changed failed!");
			return false;
		}
	}
	
	/**
	 *  A method for change book grade
	 *  @param ID  id of the book
	 *  @param grade new grade of the book
	 *  @return whether this operation is success
	 */
	public boolean chanGrade(int ID,String grade){
		//the grade could only within "A-J"
		switch(grade){
		case "A":
			break;
		case "B":
			break;
		case "C":
			break;
		case "D":
			break;
		case "E":
			break;
		case "F":
			break;
		case "G":
			break;
		case "H":
			break;
		case "I":
			break;
		case "J":
			break;
		default:
			n=new NoticeUI("The grade is not allowed!");
			return false;
		}
		//get book information and replace the grade item with new grade
		db=new DBhelper();
		String[] res=db.query(BOOK_INF, ID + "", 1);
		res[4]=grade;
		if(db.update(BOOK_INF,ID + "", 1, res)){
			return true;
		}else{
			n=new NoticeUI("The book grade changed failed!");
			return false;
		}
	}
	
	/**
	 *  A method for query the specific book information
	 *  @param bookname the name of the book for query
	 *  @return the book information as a String array 
	 */
	public String[] query(String bookname){
		db=new DBhelper();
		return db.query(BOOK_INF, bookname, 2);
	}
}
