/**
 *  A class for the operation on customer account
 *
 *  @author    Jingzhi Liu
 *  @date	   6th May,2014
 */

package Function;

import java.text.DecimalFormat;

import Helper.DBhelper;

public class CustAcc {
	
	private final static String CUS_ACC="cus_account.txt";              //customer account filename
	private DBhelper db;
	private double stobal=0;                                            //store balance
	
	/**
	 *  A method for display the customer account balance
	 *  @param ID  id of the user
	 *  @return the customer account balance
	 */
	public double showBal(int ID){
		db=new DBhelper();
		String[] res=db.query(CUS_ACC, ID + "", 1);
		stobal=Double.parseDouble(res[2]);
		return stobal;
	}
	
	/**
	 *  A method for display the customer account balance
	 *  @param name  user name
	 *  @return the customer account balance
	 */
	public double showBal(String name){
		db=new DBhelper();
		String[] res=db.query(CUS_ACC, name, 2);
		stobal=Double.parseDouble(res[2]);
		return stobal;
	}
	
	/**
	 *  A method for adding money to the customer account
	 *  @param ID  id of the user
	 *  @param money the money adding to the account
	 *  @return whether this operation is success
	 */
	public boolean addAcc(int ID,double money){
		db=new DBhelper();
		String[] res=db.query(CUS_ACC, ID + "", 1);
		stobal=Double.parseDouble(res[2]);                   //old value
		DecimalFormat format = new DecimalFormat( "######.00 "); 
		stobal=Double.parseDouble(format.format(stobal+money));                                       //new value
		return db.replace(CUS_ACC, res[2], 3, stobal + "");
	}
	
	/**
	 *  A method for subtracting money from the customer account
	 *  @param ID  id of the user
	 *  @param money the money subtracted from the account
	 *  @return whether this operation is success
	 */
	public boolean subAcc(int ID,double money){
		db=new DBhelper();
		String[] res=db.query(CUS_ACC, ID + "", 1);
		stobal=Double.parseDouble(res[2]);                   //old value
		DecimalFormat format = new DecimalFormat( "######.00 "); 
		stobal=Double.parseDouble(format.format(stobal-money));       
		return db.replace(CUS_ACC, res[2], 3, stobal + "");
	}
	
}
