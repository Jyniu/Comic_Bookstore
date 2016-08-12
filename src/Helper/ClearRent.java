/**
 *  A class for clearing book rented fare
 *
 *  @author    Tianming Zhang
 *  @date	   9th May,2014
 */

package Helper;

import Function.CustAcc;
import Function.CustMan;
import Function.StoreAcc;

public class ClearRent {

	private final static String CUS_ACC="cus_account.txt";              //customer account filename
	private DBhelper db;
	private StoreAcc sa;
	private CustAcc ca;
	private CustMan cm;
	private DateSer ds;
	
	/**
	 *  A method for clearing book rented fare
	 *  @param uID  id of the user
	 *  @return whether this operation is success
	 */
	public boolean clear(int uID){
		boolean ifsuc=true;
		double mon=0;
		ca=new CustAcc();
		sa=new StoreAcc();
		cm=new CustMan();
		ds=new DateSer();
		db=new DBhelper();
		//get user information
		String[] res1=cm.query(uID);
		if(!res1[4].equals("0")){                       //if borrow first book
			if(res1[8].equals("0")){                    //if first clearing from rent date
				if(ds.getTD(res1[7])>1){
					mon=gradeConv(res1[6])*(ds.getTD(res1[7])-1);
				}
			}else{
				if(ds.getTD(res1[8])>1){
					mon=gradeConv(res1[6])*(ds.getTD(res1[8])-1);
				}
			}
			res1[8]=ds.getTime();
			ifsuc=db.update(CUS_ACC, uID + "", 1, res1);
			ca.subAcc(uID, mon);
			sa.addAcc(mon);
		}
		res1=cm.query(uID);
		if(!res1[9].equals("0")){                        //if borrow second book
			if(res1[13].equals("0")){                    //if first clearing from rent date
				if(ds.getTD(res1[12])>1){
					mon=gradeConv(res1[11])*(ds.getTD(res1[12])-1);
				}
			}else{
				if(ds.getTD(res1[13])>1){
					mon=gradeConv(res1[11])*(ds.getTD(res1[13])-1);
				}
			}
			res1[13]=ds.getTime();
			ifsuc=db.update(CUS_ACC, uID + "", 1, res1);
			ca.subAcc(uID, mon);
			sa.addAcc(mon);
		}
		if(!ifsuc)
			System.out.println ("Clearing failed!");
		return ifsuc;
	}
	
	/**
	 *  A method for calculating the rented fare per day of relatively grade
	 *  @param grade grade of the book
	 *  @return the rented fare per day
	 */
	//grade conversion
	private double gradeConv(String grade){
		switch(grade){
		case "A":
			return 0.5;
		case "B":
			return 2*0.5;
		case "C":
			return 3*0.5;
		case "D":
			return 4*0.5;
		case "E":
			return 5*0.5;
		case "F":
			return 6*0.5;
		case "G":
			return 7*0.5;
		case "H":
			return 8*0.5;
		case "I":
			return 9*0.5;
		case "J":
			return 10*0.5;
		default:
			return 0;
		}
	}
}
