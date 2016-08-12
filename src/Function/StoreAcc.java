/**
 *  A class for the operation on store account
 *
 *  @author    Jingzhi Liu
 *  @date	   9th May,2014
 */

package Function;

import java.text.DecimalFormat;

import Helper.DBhelper;

public class StoreAcc {

	private final static String STO_ACC="store_account.txt";        //store account filename
	private DBhelper db;
	private double stobal=0;
	
	/**
	 *  A method for display the store account balance
	 *  @return the store account balance
	 */
	public double showBal(){
		db=new DBhelper();
		if(!db.isfile(STO_ACC)){
			db.insert(STO_ACC, "-5000");                //initialize store account
			stobal=0;
		}else{
			String[] res=db.query(STO_ACC, null, 1);
			stobal=Double.parseDouble(res[0]);
		}
		return stobal;
	}

	/**
	 *  A method for adding money to the store account
	 *  @param money the money adding to the account
	 *  @return whether this operation is success
	 */
	public boolean addAcc(double money){
		db=new DBhelper();
		boolean ifsuc=false;
		if(!db.isfile(STO_ACC)){
			db.insert(STO_ACC, "-5000");               //initialize store account
			stobal=0;
		}else{
			String[] res=db.query(STO_ACC, null, 1);
			stobal=Double.parseDouble(res[0]);
			DecimalFormat format = new DecimalFormat( "######.00 "); 
			stobal=Double.parseDouble(format.format(stobal+money));       
			ifsuc=db.replace(STO_ACC, res[0], 1, stobal + "");
		}
		return ifsuc;
	}
	
	/**
	 *  A method for subtracting money from the store account
	 *  @param money the money subtracted from the account
	 *  @return whether this operation is success
	 */
	public boolean subAcc(double money){
		db=new DBhelper();
		boolean ifsuc=false;
		if(!db.isfile(STO_ACC)){
			db.insert(STO_ACC, "-5000");                //initialize store account
			stobal=0;
		}else{
			String[] res=db.query(STO_ACC, null, 1);
			stobal=Double.parseDouble(res[0]);
			DecimalFormat format = new DecimalFormat( "######.00 "); 
			stobal=Double.parseDouble(format.format(stobal-money));       
			ifsuc=db.replace(STO_ACC, res[0], 1, stobal + "");
		}
		return ifsuc;
	}
}
