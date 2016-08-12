/**
 *  A class for customer management
 *
 *  @author    Jingzhi Liu
 *  @date	   9th May,2014
 */

package Function;

import Helper.DBhelper;
import UI.NoticeUI;

public class CustMan {
	
	private final static String CUS_ACC="cus_account.txt";              //customer account filename
	private DBhelper db;
	private final static String CUS_PW="cus_pw.txt";                    //customer password filename
	private NoticeUI n;
	
	/**
	 *  A method for change user name
	 *  @param oldname old name of the user
	 *  @param newname new name of the user
	 *  @return whether this operation is success
	 */
	public boolean chanName(String oldname,String newname){
		db=new DBhelper();
		if((db.query(CUS_ACC, newname, 2))==null){
			if(db.replace(CUS_ACC, oldname, 2, newname)){
				return true;
			}else{
				n=new NoticeUI("The user name changed failed!");
				return false;
			}
		}else{
			n=new NoticeUI("The user name already exists!");
			return false;
		}
	}
	
	/**
	 *  A method for change phone number
	 *  @param ID  id of the user
	 *  @param newphone new phone number of the user
	 *  @return whether this operation is success
	 */
	public boolean chanPhone(String ID,String newphone){
		//get user information
		db=new DBhelper();
		String[] res=db.query(CUS_ACC, ID, 1);
		//check if the new phone number is a number,and save it if it is
		try{
			res[3]=Integer.parseInt(newphone)+"";
		}catch(Exception e){
			n=new NoticeUI("The phone number must be a number!");
			return false;
		}
		//update the user information
		if(db.update(CUS_ACC,ID + "", 1, res)){
			return true;
		}else{
			n=new NoticeUI("The user phone number changed failed!");
			return false;
		}
	}
	
	/**
	 *  A method for change user password
	 *  @param ID id of the user
	 *  @param newpw new password of the user
	 *  @return whether this operation is success
	 */
	public boolean chanPw(int ID,String newpw){
		//get user password information and change it to new password
		db=new DBhelper();
		String[] res=db.query(CUS_PW, ID + "", 1);
		res[1]=newpw;
		if(db.update(CUS_PW, ID + "", 1, res)){
			n=new NoticeUI("Password changed successfully!");
			return true;
		}else{
			n=new NoticeUI("Password changed failed!");
			return false;
		}
	}
	
	/**
	 *  A method for delete the user
	 *  @param ID  id of the user
	 *  @return whether this operation is success
	 */
	public boolean delete(String ID){
		db=new DBhelper();
		//delete the customer account in both two file about information and password
		return db.delete(CUS_ACC, ID, 1)&db.delete(CUS_PW, ID, 1);
	}
	
	/**
	 *  A method for query the specific information of the user
	 *  @param ID  id of the user
	 *  @return information of the user in a String array
	 */
	//get specific customer's information
	public String[] query(int ID){
		db=new DBhelper();
		return db.query(CUS_ACC, ID + "", 1);
	}
	
	/**
	 *  A method for query the specific information of the user
	 *  @param name  name of the user
	 *  @return information of the user in a String array
	 */
	public String[] query(String name){
		db=new DBhelper();
		return db.query(CUS_ACC, name, 2);
	}
	
	/**
	 *  A method for reseting the user account,
	 *  that is clear the account balance if the balance is negative
	 *  @param uID  id of the user
	 *  @return whether this operation is success
	 */
	public boolean resAcc(int uID){
		CustAcc ca=new CustAcc();
		StoreAcc sa=new StoreAcc();
		boolean ifsuc=false;
		//get user account balance
		double bal=ca.showBal(uID);
		//check if store account is able to make this deal and money operation is success
		if((sa.showBal()+ bal)<0){
			n=new NoticeUI("The store account has not enough money!");
		}else if(ca.addAcc(uID, -bal)&sa.addAcc(bal)){
			ifsuc=true;
		}else{
			n=new NoticeUI("Reset failed!");
		}
		return ifsuc;
	}
}
