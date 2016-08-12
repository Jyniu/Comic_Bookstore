/**
 *  A class for modeling customer operation
 *
 *  @author    Mingjie Yang 
 *  @date	   10th May,2014
 */

package Function;

import Helper.DBhelper;
import UI.NoticeUI;

public class Distributor {

	private final static String BOOK_STO="book_stock.txt";              //book information filename
	private DBhelper db;
	private NoticeUI n;
	
	/**
	 *  A method for ordering book from the distributor
	 *  @param book name of the book ordering
	 *  @param num number of the book ordering
	 *  @return whether this operation is success
	 */
	public boolean ordBook(String book,int num){
		boolean ifsuc=false;
		//get book information in distributor stock 
		db=new DBhelper();
		String[] res=db.query(BOOK_STO, book, 1);
		//check if distributor has the book of the book is enough
		if(res==null){
			ifsuc=false;
			n=new NoticeUI("Distributor doesn't has this book!");
			return false;
		}
		if(Integer.parseInt(res[1])>=num){
			res[1]=(Integer.parseInt(res[1])-num) + "";        //book stock
			if(db.update(BOOK_STO, book, 1, res)){
				ifsuc=true;
				n=new NoticeUI("Order book successfully-distributor");
			}else{	
				ifsuc=false;
				n=new NoticeUI("Order book failed-distributor");
			}
		}else{
			ifsuc=false;
			n=new NoticeUI("Order book failed-distributor");
		}
		db=null;
		return ifsuc;
	}

//  an item class of the structure of the data saved in BOOK_STO("book_stock.txt")
//	class Item{
//		private String b_name;        //book name
//		private int b_stock;          //book stock
//	}
}
