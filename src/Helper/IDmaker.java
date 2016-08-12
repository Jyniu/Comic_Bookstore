/**
 *  A class for making ID for new user or book
 *
 *  @author    Yuying Song
 *  @date	   5th May,2014
 */

package Helper;

public class IDmaker {
	private int judge;                                              //judge  which kind of ID to make
	private final static String UFILE="UserID.txt";                 //user ID filename
	private final static String BFILE="BookID.txt";                 //book ID filename
	
	/**
 	 *  Constructor
 	 *  @param j whether make ID for new user or book, 1 for user, 2 for book
 	 */
	public IDmaker(int j){
		judge=j;
	}
	
	/**
	 *  A method for getting ID
	 *  @return the ID this method make
	 */
	public int getID() {
		int ID = 1;
		DBhelper db=new DBhelper();
		switch(judge){
		case 1:                               //if make a user ID
			if(!db.isfile(UFILE)){     
				db.insert(UFILE, "1");        //initiate the ID with 1
				ID=1;
			}else{
				String[] res=db.query(UFILE, null, 1);
				ID=Integer.parseInt(res[0]);
				ID++;                       //make new ID based on old ID plus one
				db.replace(UFILE, res[0], 1, ID + "");
			}
			break;
		case 2:                              //if make a book ID
			if(!db.isfile(BFILE)){
				db.insert(BFILE, "1");       //initiate the ID with 1
				ID=1;
			}else{
				String[] res=db.query(BFILE, null, 1);
				ID=Integer.parseInt(res[0]);
				ID++;                        //make new ID based on old ID plus one
				db.replace(BFILE, res[0], 1, ID + "");
			}
			break;
		default:
			break;
		}
		return ID;
	}
}
