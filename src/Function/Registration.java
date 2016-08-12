/**
 *  A class for user registration
 *
 *  @author    Mingjie Yang,Jingzhi Liu
 *  @date	   5th May,2014
 */

package Function;

import Helper.DBhelper;
import Helper.IDmaker;
import UI.NoticeUI;

public class Registration{
	
	private final static String CUS_ACC="cus_account.txt";              //customer account filename
	private final static String CUS_PW="cus_pw.txt";                    //customer password filename
	private String name;                                                //user name
	private String password;                                            //user password
	private double paid;                                                //user paid
	private long phone;                                                 //user phone
	private int ID;                                                     //user ID
	private NoticeUI n;
	private DBhelper db;
		
	/**
	 *  A method for getting user name and check if the name is unique in the system
	 *  @param name  user name
	 *  @return whether this operation is success
	 */
	public boolean getName(String name){
		db=new DBhelper();
		this.name = name;
		//check the uniqueness of the name in the system
		if(db.isfile(CUS_ACC)&db.query(CUS_ACC, name, 2)!=null){  
			n=new NoticeUI("The user name already exists!");
			return false;
		}
		return true;
	}
	
	/**
	 *  A method for getting user password and check if the password is of high security
	 *  @param p1 first input of the password
	 *  @param p2 second input of the password
	 *  @return whether this operation is success
	 */
	public boolean getPw(String p1,String p2){
		db=new DBhelper();
		if(!p1.equals(p2)){              //check if two input are the same
				n=new NoticeUI("Twice input passwords are not equal!");
				return false;
		}
		if(p1.length()<6){               //check if password is long enough
				n=new NoticeUI("Password must be no less than 6 charaters!");
				return false;
		}
		password=p1;	
		return true;
	}
	
	/**
	 *  A method for getting user paid money and check if the money is enough
	 *  @param pay money paid
	 *  @return whether this operation is success
	 */
	public boolean getMoney(String pay){
		//check if the input is a number
		try{
			paid = Double.parseDouble(pay);
		}catch(Exception e){
			n=new NoticeUI("Please enter a number for money!");
			return false;
		}
		//check if the money no less than 15 pounds
		if(paid<15){
			n=new NoticeUI("The least money paid first is 15 pounds!");
			return false;
		}
		return true;
	}
	
	/**
	 *  A method for getting user phone number and check if the input is legal
	 *  @param p  phone number
	 *  @return whether this operation is success
	 */
	public boolean getPhone(String p){
		//check if the input is a number
		try{
			phone = Long.parseLong(p);
		}catch(Exception e){
			n=new NoticeUI("Please enter a number for phone!");
			return false;
		}
		return true;
	}

	/**
	 *  A method for add the registration information to account
	 */
	//add new user account
	public void addAcc(){	
		db=new DBhelper();
		//make user ID
		IDmaker idmaker = new IDmaker(1);
		ID = idmaker.getID();
		//save user information
		String values=ID + "/" + name + "/"             //user ID/user name/
					  + paid + "/" + phone + "/"        //user account balance/user phone number/
	//first rented book ID/first rented book name/first rented book grade/first book rented date/first book last clearing time/
	//second rented book ID/second rented book name/second rented book grade/second book rented date/second book last clearing time 
					  + "0/0/0/0/0/0/0/0/0/0";             
		db.insert(CUS_ACC, values);
		//save password
		if(!db.isfile(CUS_PW))
			db.createfile(CUS_PW);
		values=ID + "/" + password;
		db.insert(CUS_PW, values);
		n=new NoticeUI("Welcome " + name + " to be one of our member!");
	}
	
//  an item class of the structure of the data saved in CUS_ACC("cus_account.txt")	
//	private class Item{
//	0	private int uID;                 //user ID
//	1	private String u_name;           //user name
//	2	private double accbal;           //account balance
//	3	private long phnum;              //phone number
//	4	private int f_bID;               //first rented book ID
//	5	private String f_bname;          //first rented book name
//	6	private String f_grade;          //first rented book grade
//	7	private String f_rdate;          //first book rented date 
//  8   private String f_cdate;          //first book last clearing time
//	9	private int s_bID;               //second rented book ID
//	10	private String s_bname;          //second rented book name
//	11	private String s_grade;          //second rented book grade
//	12	private String s_rdate;          //second book rented date 
//  13  private String s_cdate;          //second book last clearing time
//	}
	
//  an item class of the structure of the data saved in CUS_PW("cus_pw.txt")	
//	private class Item{
//		private int uID;                 //user ID
//		private String pw;               //user password
//	}
	
}