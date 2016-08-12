/**
 *  An interface for user
 *
 *  @author     Tianye Ma
 *  @date	   6th May,2014
 */

package Function;

public interface User {

	/**
	 *  A method for customer buy book 
	 *  @param uID  id of the user
	 *  @param book name of the book to buy
	 *  @param num number of the book to buy
	 *  @return whether this operation is success
	 */
	public boolean buyBook(int uID,String book,int num);
	
	/**
	 *  A method for customer selling book to the store(for future expansion)
	 *  @param uID  id of the user
	 *  @param book name of the book
	 *  @return whether this operation is success
	 */
	public double sellBook(int uID,String book);
	
	/**
	 *  A method for customer changing book with the store(for future expansion)
	 *  @param ubook book name of the user book
	 *  @param sbook book name of the store book
	 *  @return whether this operation is success
	 */ 
	public boolean chanBook(String ubook,String sbook);
	
}
