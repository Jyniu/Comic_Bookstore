/**
 *  A class for user log in the system
 *
 *  @author    Mingjie Yang
 *  @date	   4th May,2014
 */

package Function;

import Helper.DBhelper;
import UI.NoticeUI;

public class Login {
	
	private final static String CUS_ACC="cus_account.txt";            //customer account filename
	private final static String CUS_PW="cus_pw.txt";                  //customer password filename
	private final static String STA_ACC="staff_account.txt";          //staff account filename
	private final static String AUTH="authority.txt";                 //authority filename
	private String name;                                              //user name
	private String password;                                          //user password
	private boolean ifstaff;                                          //check if the user is a staff of the store
	private DBhelper db;
	private NoticeUI n;
	
	/**
	 *  A method for getting input of the user name
	 *  @param name input of the user name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *  A method for getting input of the user password
	 *  @param password input of the user password
	 */
	public void setPw(String password){
		this.password=password;
	}
	
	/**
	 *  A method for getting the identification of current user
	 *  @return whether current user is store staff
	 */
	public boolean who(){
		return ifstaff;
	}
	
	/**
	 *  A method for check account validity
	 *  @return whether account is valid
	 */
	public boolean checkAcc(){
		db=new DBhelper();
		//check if the user is a staff
		String[] res=null;
		res=db.query(STA_ACC, name, 2);
		//if the user name is correct, check the password
		if(res!=null){
			ifstaff=true;
			if(res[2].equals(password)){
				if(!db.isfile(AUTH)){              //if authority file is not existed,then add data to a new file
					if(res[0].equals("1")){        //if the staff ID is 1,						                          
						db.insert(AUTH, "1");      //which means he/she is manager,then sign it
					}else{
						db.insert(AUTH, "0");
					}
				}else{
					String[] res1=db.query(AUTH, null, 1);
					String auth="0";
					if(res[0].equals("1")){
						auth="1";
					}
					db.replace(AUTH, res1[0], 1, auth);
				}
				return true;
			}else{
				n=new NoticeUI("Password is not correct!");
				return false;
			}
		}
		
		//check if the user is a customer
		res=db.query(CUS_ACC, name, 2);
		if(res!=null){
			ifstaff=false;
			String[] res1=null;
			res1=db.query(CUS_PW, res[0], 1);
			if(res1!=null){
				if(res1[1].equals(password)){
					return true;
				}else{
					n=new NoticeUI("Password is not correct!");
					return false;
				}
			}
		}
		n=new NoticeUI("The user is not exist!");
		return false;
	}	
}
