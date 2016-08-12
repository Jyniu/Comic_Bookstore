/**
 *  A class for modeling subscriber operation
 *
 *  @author    Jingzhi Liu
 *  @date	   10th May,2014
 */

package Function;

import javax.swing.JOptionPane;

import Helper.ClearRent;
import Helper.DBhelper;
import Helper.DateSer;
import UI.NoticeUI;

public class Subscriber implements User {

	private final static String BOOK_INF="book_infor.txt";              //book information filename
	private final static String CUS_ACC="cus_account.txt";              //customer account filename
	private DBhelper db;
	private BookMan bm;
	private StoreAcc sa;
	private RecSystem rs;
	private CustAcc ca;
	private CustMan cm;
	private DateSer ds;
	private ClearRent cr;
	private NoticeUI n;
	
	/**
	 *  A method for subscriber buy book
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @param num number of the book 
	 *  @return whether this operation is success
	 */
	public boolean buyBook(int uID, String book, int num) {
		boolean ifsuc=true;
		double price=0;
		int stock=0;
		db=new DBhelper();
		bm=new BookMan();
		sa=new StoreAcc();
		rs=new RecSystem();
		ca=new CustAcc();
		cr=new ClearRent();
		String[] res=bm.query(book);
		price=Double.parseDouble(res[3]);
		stock=Integer.parseInt(res[5]);
		ifsuc=cr.clear(uID);                 //clearing
		if(!ifsuc)
			return false;
		//check if the customer balance has enough money
		if(ca.showBal(uID)<(price*num)){                   
			n=new NoticeUI("The account balance is not enough!");
			return false;
		}
		 //check if there are enough books available
		if(stock<num){                      
			n=new NoticeUI("Book only available: " + res[5]);
			int reponse=JOptionPane.showConfirmDialog(null, "Whether order the books?", "Query", JOptionPane.YES_NO_OPTION);
			if(reponse==0){
				return ordBook(uID,book,price,num);
			}else{
				ifsuc=false;
			}			
		} 
		//check if account balance is enough
		if(ifsuc){
			res[5]=(stock-num) + "";                           //book stock
			stock-=num;
			res[7]=(Integer.parseInt(res[7])-num) + "";        //book all
			if(!db.update(BOOK_INF, book, 2, res)){            //if update failed
				n=new NoticeUI("Buy book failed");
				return false;
			}
			double number=(double)(num);
			n=new NoticeUI(number + "");
			sa.addAcc(price*number);
			ca.subAcc(uID, price*number);                     
			rs.addBuyr(uID, book, num);                        //add buy book record
			ifsuc=true;
		}
		return ifsuc;
	}
	
	/**
	 *  A method for subscriber borrow book
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @return whether this operation is success
	 */
	public boolean borBook(int uID,String book){
		cm=new CustMan();
		bm=new BookMan();
		db=new DBhelper();
		rs=new RecSystem();
		ds=new DateSer();
		cr=new ClearRent();
		if(!cr.clear(uID))                              //clearing
			return false;              
		String[] res=bm.query(book);
		String[] res1=cm.query(uID);
		//check if account balance is OK 
		if(Double.parseDouble(res1[2])<0){
			n=new NoticeUI("The account balance is negative!");
			return false;	
		}
		if(!res1[4].equals("0")&&!res1[9].equals("0")){  //if already borrow two books
			n=new NoticeUI("The subscriber had borrowed two books!");
			return false;
		}
		if((Integer.parseInt(res[5])-1)<0){
			n=new NoticeUI("The stock is not enough!");
			return false;
		}
		res[5]=(Integer.parseInt(res[5])-1) + "";        //book stock
		res[6]=(Integer.parseInt(res[6])+1) + "";        //book rented
		if(res1[4].equals("0")){                   //if first place is empty
			res1[4]=res[0];
			res1[5]=res[1];
			res1[6]=res[4];
			res1[7]=ds.getTime();
			if((db.update(BOOK_INF, book, 2, res))&(db.update(CUS_ACC, uID + "", 1, res1))){
				rs.addRentr(uID, book);                  //add a rent book record
				return true;
			}else{
				n=new NoticeUI("Borrow failed!");
				return false;
			}
		}else{                                          //if second place is empty
			res1[9]=res[0];
			res1[10]=res[1];
			res1[11]=res[4];
			res1[12]=ds.getTime();
			if((db.update(BOOK_INF, book, 2, res))&(db.update(CUS_ACC, uID + "", 1, res1))){
				rs.addRentr(uID, book);                  //add a rent book record
				return true;
			}else{
				n=new NoticeUI("Borrow failed!");
				return false;
			}
		}
	}
	
	/**
	 *  A method for subscriber return book
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @param ifFirst if return the first book
	 *  @return whether this operation is success
	 */
	public boolean retBook(int uID,String book,boolean ifFirst){
		boolean ifsuc=false;
		cm=new CustMan();
		bm=new BookMan();
		db=new DBhelper();
		cr=new ClearRent();
		if(!cr.clear(uID))                               //clearing 
			return false;                                  
		String[] res=bm.query(book);
		String[] res1=cm.query(uID);
		res[5]=(Integer.parseInt(res[5])+1) + "";        //book stock
		res[6]=(Integer.parseInt(res[6])-1) + "";        //book rented
		if(ifFirst){                                     //if return the first book
			res1[4]="0";
			res1[5]="0";
			res1[6]="0";
			res1[7]="0";
			res1[8]="0";
			if((db.update(BOOK_INF, book, 2, res))&(db.update(CUS_ACC, uID + "", 1, res1))){
				ifsuc=true;
			}else{
				n=new NoticeUI("Return failed!");
				ifsuc=false;
			}
		}else{
			res1[9]="0";
			res1[10]="0";
			res1[11]="0";
			res1[12]="0";
			res1[13]="0";
			if((db.update(BOOK_INF, book, 2, res))&(db.update(CUS_ACC, uID + "", 1, res1))){
				ifsuc=true;
			}else{
				n=new NoticeUI("Return failed!");
				ifsuc=false;
			}
		}
		return ifsuc;
	}
	
	/**
	 *  A method for subscriber order book
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @param price price of the book
	 *  @param num number of the book 
	 *  @return whether this operation is success
	 */
	private boolean ordBook(int uID,String book,double price,int num){
		sa=new StoreAcc();
		ca=new CustAcc();
		rs=new RecSystem();
		Distributor d=new Distributor();
		double number=(double)(num);
		//add money to store account
		if(!sa.addAcc(price*number)){
			n=new NoticeUI("Add store count money failed");
			return false;
		}
		//subtracted money from customer account
		if(!ca.subAcc(uID, price*number)){
			n=new NoticeUI("Subtract customer count money failed");
			return false;
		}
		//order book from distributor
		if(d.ordBook(book, num)){
			sa.subAcc(price*number*0.9);                            //earn 10% as order fee
			rs.addOrdr(uID,book,num);
			n=new NoticeUI("Order " + num + " " + book + " successfully");  //invoice
			return true;
		}else{                                                      //pay back
			sa.subAcc(price*number);
			ca.addAcc(uID, price*number);
			return false;
		}
	}
	
	/**
	 *  A method for customer selling book to the store(for future expansion)
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @return whether this operation is success
	 */
	public double sellBook(int uID, String book) {
		return 0;
	}

	/**
	 *  A method for customer changing book with the store(for future expansion)
	 *  @param ubook book name of the user book
	 *  @param sbook book name of the store book
	 *  @return whether this operation is success
	 */ 
	public boolean chanBook(String ubook, String sbook) {
		return false;
	}

}
