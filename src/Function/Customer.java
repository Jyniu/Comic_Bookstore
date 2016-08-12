/**
 *  A class for modeling customer operation
 *
 *  @author    Jingzhi Liu 
 *  @date	   8th May,2014
 */

package Function;

import Helper.DBhelper;
import UI.NoticeUI;

public class Customer implements User{

	private final static String BOOK_INF="book_infor.txt";              //book information filename
	private DBhelper db;
	private BookMan bm;
	private StoreAcc sa;
	private RecSystem rs;
	private NoticeUI n;
	
	/**
	 *  A method for customer buy book by cash without using account
	 *  @param uID  id of the user,it would be zero for customer without account
	 *  @param book name of the book to buy
	 *  @param num number of the book to buy
	 *  @return whether this operation is success
	 */
	public boolean buyBook(int uID,String book,int num) {
		//ID for user without account or subscriber buying book by cash
		uID=0;
		boolean ifsuc=false;	           
		db=new DBhelper();
		bm=new BookMan();
		sa=new StoreAcc();
		rs=new RecSystem();
		//get book information
		String[] res=bm.query(book);
		 //check if there are enough books available
		if(Integer.parseInt(res[5])<num){                    
			n=new NoticeUI("Book only available: " + res[5]);
			ifsuc=false;
		}else{
			res[5]=(Integer.parseInt(res[5])-num) + "";        //book stock
			res[7]=(Integer.parseInt(res[7])-num) + "";        //book all
			if(!db.update(BOOK_INF, book, 2, res)){            //if update failed
				n=new NoticeUI("Buy book failed");
				ifsuc=false;
			}else{
				sa.addAcc(Double.parseDouble(res[3])*(double)(num));
				rs.addBuyr(uID, book, num);                     //add buy book record
				n=new NoticeUI("The trade is successfully!");
				ifsuc=true;
			}
		}
		return ifsuc;
	}

	/**
	 *  A method for customer selling book to the store(for future expansion)
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @return whether this operation is success
	 */
	public double sellBook(int uID,String book){
		return 0;
	}
	
	/**
	 *  A method for customer changing book with the store(for future expansion)
	 *  @param ubook book name of the user book
	 *  @param sbook book name of the store book
	 *  @return whether this operation is success
	 */ 
	public boolean chanBook(String ubook,String sbook) {
		return false;
	}
	
}
