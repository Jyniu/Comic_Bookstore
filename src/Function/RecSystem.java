/**
 *  A class for recording operation
 *
 *  @author    Jingzhi Liu 
 *  @date	   10th May,2014
 */

package Function;

import Helper.DBhelper;
import Helper.DateSer;

public class RecSystem {
	
	//buy book record filename
	private final static String BUY_REC="buy_record.txt";    
	//rent book record filename
	private final static String RENT_REC="rent_record.txt";      
	//order book record filename
	private final static String ORD_REC="ord_record.txt";       
	//sell book record filename
//	private final static String SELL_REC="sell_record.txt";   
	//change book record filename
//	private final static String CHAN_REC="chan_record.txt";      
	private BookMan bm;
	private CustMan cm;
	private DBhelper db;
	private DateSer ds;
	
	/**
	 *  A method for adding a buy book record
	 *  @param uID ID of the user
	 *  @param book the book user buy
	 *  @param num the number of the book user buy
	 */
	public void addBuyr(int uID,String book,int num){
		bm=new BookMan();
		cm=new CustMan();
		db=new DBhelper();
		ds=new DateSer();
		//get book information
		String[] res2=bm.query(book);                               
		if(uID!=0){                                                     //if the user is a subscriber
			String[] res1=cm.query(uID);                                //get user information
			String values=res1[1] + "/" + res1[3] + "/"                 //user name/user phone number/
						  + res2[1] + "/" + res2[2] + "/"               //book name/book author/
						  + res2[3] + "/" + num  + "/"                  //book price/book number/
			              + ds.getTime();                               //buy date
			db.insert(BUY_REC, values);
		}else{                                                          //if the user is a normal customer
			String values="Customer/0/"                                 //user name/user phone number/
					      + res2[1] + "/" + res2[2] + "/"               //book name/book author/
					      + res2[3] + "/" + num + "/"                   //book price/book number/
					      + ds.getTime();                               //buy date
			db.insert(BUY_REC, values);
		}
	}
	
	/**
	 *  A method for adding a rent book record
	 *  @param uID ID of the user
	 *  @param book the book user rent
	 */
	public void addRentr(int uID,String book){
		bm=new BookMan();
		cm=new CustMan();
		db=new DBhelper();
		ds=new DateSer();
		String[] res2=bm.query(book);                               //get book information
		String[] res1=cm.query(uID);                                //get user information
		String values=res1[1] + "/" + res1[3] + "/"                 //user name/user phone number/
					  + res2[1] + "/" + res2[2] + "/"               //book name/book author/
					  + res2[4] + "/" + ds.getTime();               //book grade/rent date
		db.insert(RENT_REC, values);
	}
	
	/**
	 *  A method for adding a order book record
	 *  @param uID ID of the user
	 *  @param book the book user order
	 *  @param num the number of the book user order
	 */
	public void addOrdr(int uID,String book,int num){
		bm=new BookMan();
		cm=new CustMan();
		db=new DBhelper();
		ds=new DateSer();
		String[] res2=bm.query(book);                               //get book information
		String[] res1=cm.query(uID);                                //get user information
		String values=res1[1] + "/" + res1[3] + "/"                 //user name/user phone number/
					  + res2[1] + "/" + res2[2] + "/"               //book name/book author/
					  + res2[3] + "/" + num + "/"                   //book price/book number/
					  + ds.getTime();                               //order date
		db.insert(ORD_REC, values);
	}
	
	/**
	 *  A method for adding a user sell book record(for future expansion)
	 */
	public void addSellr(){}
	
	/**
	 *  A method for adding a user change book record(for future expansion)
	 */
	public void addChanr(){}
}
