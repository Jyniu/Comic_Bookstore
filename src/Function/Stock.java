/**
 *  A class for stocking the book
 *
 *  @author    Mingjie Yang 
 *  @date	   8th May,2014
 */

package Function;

import Helper.DBhelper;
import Helper.IDmaker;
import UI.NoticeUI;

public class Stock {
	
	private final static String BOOK_INF="book_infor.txt";              //book information filename
	private String name;                                                //book name
	private int ID;                                                     //book ID
	private String author;                                              //book author
	private double price;                                               //book price
	private String grade;                                               //book grade
	private int num;                                                    //book number
	private DBhelper db;
	private NoticeUI n;
	
	/**
	 *  A method for getting book name and check if the book is a new book
	 *  @param name  book name
	 *  @return whether this book is a new book
	 */
	public boolean getName(String name){
		db=new DBhelper();
		this.name = name;
		if(db.isfile(BOOK_INF)){                          //check if the file is exist
			//try to query the book
			String[] res=null;
			res=db.query(BOOK_INF, name, 2);
			if(res!=null){                                //if book is exist
				ID=Integer.parseInt(res[0]);
				return false;
			}else{
				return true;
			}
		}else{ 
			db.createfile(BOOK_INF);
			return true;
		}
	}
	
	/**
	 *  A method for getting book author
	 *  @param author  book author
	 */
	public void getAuthor(String author){
		this.author = author;
	}
	
	/**
	 *  A method for getting book price and check if the price is legal
	 *  @param pri  book price
	 *  @return whether this operation is success
	 */
	public boolean getPrice(String pri){
		//check if the input is a double
		try{
			price = Double.parseDouble(pri);
		}catch(Exception e){
				n=new NoticeUI("The price must be a number!");
				return false;
		}
		//check if the price is not negative
		if(price<0){
				n=new NoticeUI("The price cannot be negative!");
				return false;
		}
		return true;
	}
	
	/**
	 *  A method for getting book grade and check if the grade is legal
	 *  @param grade  book grade
	 *  @return whether this operation is success
	 */
	public boolean getGrade(String grade){
		this.grade = grade;
		switch(grade){
		case "A":
			return true;
		case "B":
			return true;
		case "C":
			return true;
		case "D":
			return true;
		case "E":
			return true;
		case "F":
			return true;
		case "G":
			return true;
		case "H":
			return true;
		case "I":
			return true;
		case "J":
			return true;
		default:
			n=new NoticeUI("The grade is not allowed!");
			return false;
		}
	}

	/**
	 *  A method for get book ID
	 */
	public void getId(){
		IDmaker m=new IDmaker(2);
		ID=m.getID();
	}
	
	/**
	 *  A method for getting book number and check if the number is legal
	 *  @param number  book number
	 *  @return whether this operation is success
	 */
	public boolean getNum(String number){
		//check if the input is a number
		try{
			num = Integer.parseInt(number);
		}catch(Exception e){
			n=new NoticeUI("Book nmuber must be a number!");
			return false;
		}
		//check if the number is no less than one
		if(num<1){
			n=new NoticeUI("Book nmuber cannot less than one!");
			return false;
		}
		return true;
	}

	/**
	 *  A method for adding a new book to the system
	 */
	public void addNew(){
		db=new DBhelper();
		String values=ID + "/" + name + "/" + author + "/"             //book ID/book name/book author
					  + price + "/" + grade + "/"                      //book price/book grade/
				      + num + "/0/" + num;                             //book stock/book rented/book all
		db.insert(BOOK_INF, values);
		n=new NoticeUI("Adding new book successfully!");
	}
	
	/**
	 *  A method for adding an old book to the system
	 */
	public void addOld(){
		db=new DBhelper();
		String[] res=db.query(BOOK_INF, ID + "", 1);
		res[5]=(Integer.parseInt(res[5])+num) + "";                    //book stock
	    res[7]=(Integer.parseInt(res[7])+num) + "";                    //book all
		db.update(BOOK_INF, ID + "", 1, res);
		n=new NoticeUI("Adding old book successfully!");
	}

//  an item class of the structure of the data saved in BOOK_INF("book_infor.txt")		
//	private class Item{
//0	private int bID;                 //book ID
//1	private String b_name;           //book name
//2	private String b_author;         //book b_author
//3	private double b_price;          //book price
//4	private String b_grade;          //book b_grade
//5	private int b_stock;             //the number of the book available
//6	private int b_rented;            //the number of the book rented
//7	private int b_all;               //the number of the book that store has
//}	
}
