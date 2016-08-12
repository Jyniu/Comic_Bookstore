/**
 *  A class for providing facility to access database(file)
 *
 *  @author    Jiayue Niu,Jingzhi Liu
 *  @date	   6th May,2014
 */

package Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DBhelper {

	private ArrayList<String> data;                      
	
	/**
	 *  A method for checking if file is exist
	 *  @param filename name of file
	 *  @return if the file is exist
	 */
	public boolean isfile(String filename){
		File myFile = new File("File" + File.separator + filename);
		return myFile.exists();
	}
	
	/**
	 *  A method for creating new file
	 *  @param filename name of file
	 */
	public void createfile(String filename){
		File file1 = new File("File");                     //path
		File myFile = new File("File" + File.separator + filename);
		try{
			file1.mkdir();                                 //make path first
			myFile.createNewFile();                        //make file in the path 
		}catch (IOException ex){
			ex.printStackTrace();
			System.exit(1);
	    }	
	}
	
	/**
	 *  A method for inserting information into the file
	 *  @param filename name of file
	 *  @param values values to insert
	 */
	public void insert(String filename,String values){
		File file1 = new File("File");
		File myFile = new File("File" + File.separator + filename);
		try{
			if(!myFile.exists()){
				file1.mkdir();                                 //make path first
				myFile.createNewFile();                        //make file in the path 
			}
			FileWriter writer = new FileWriter(myFile,true);   //write after the content in the file
			writer.write(values + "\r\n");                     //insert new line
			writer.close();
		}catch (IOException ex){
			ex.printStackTrace();
			System.exit(1);                                    //exist the system
	    }
	}

	/**
	 *  A method for deleting information from the file
	 *  @param filename name of file
	 *  @param condition value in the record that need to delete
	 *  @param loc location of the value in the record
	 *  @return whether this operation is success
	 */
	public boolean delete(String filename,String condition,int loc){
		boolean ifdelete=false;
		File myFile = new File("File" + File.separator + filename);
		data=new ArrayList<String>();
		try{ 
			//read data and separate into String array
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line=null;
			String[] result=null;
			while((line=reader.readLine())!=null){              
				result=line.split("/");
				if(result[loc-1].equals(condition)){
					ifdelete=true;
				}else{	
					data.add(line);
				}
				result=null;
			}
			reader.close();
			//write back into the file without the record we want to delete
			FileWriter writer = new FileWriter(myFile);
			for(int i=0;i<data.size();i++){
				writer.write(data.get(i) + "\r\n" );        //insert new line
			}
			writer.close();
		}catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
		data=null;
		return ifdelete;
	}
	
	/**
	 *  A method for querying book information
	 *  @param filename name of the file
	 *  @param condition value in the record that need to query
	 *  @return specific data of the record in a String array
	 */
	public String[] query(String filename,String condition,int loc){
		String[] sresult=null;
		File myFile = new File("File" + File.separator + filename);
		try{ 
			//read the data from the file
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line=null;
			String[] result=null;
			while((line=reader.readLine())!=null){
				result=line.split("/");
				if(condition==null){           //if condition is null, return the value of the first record
					reader.close();
					sresult=result;
					return sresult;
				}
				if(result[loc-1].equals(condition))
					sresult=result;
				result=null;
			}
			reader.close();
		}catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
		return sresult;
	}

	/**
	 *  A method for updating information of the file
	 *  @param filename name of file
	 *  @param condition value in the record that need to update
	 *  @param loc location of the value in the record
	 *  @param newvalues new values that need to update
	 *  @return whether this operation is success
	 */
	public boolean update(String filename,String condition,int loc,String[] newvalues){
		boolean ifupdate=false;
		File myFile = new File("File" + File.separator + filename);
		data=new ArrayList<String>();
		try{ 
			//read the data from the file
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line=null;
			String[] result=null;
			while((line=reader.readLine())!=null){
				result=line.split("/");
				if(result[loc-1].equals(condition)){
					String str="";
					for(int i=0;i<newvalues.length;i++){
						str+=newvalues[i];
						if(i!=(newvalues.length-1))
							str+="/";
					}
					data.add(str);
					ifupdate=true;
				}else{	
					data.add(line);
				}
				result=null;
			}
			reader.close();
			//write back to the file with new values
			FileWriter writer = new FileWriter(myFile);
			for(int i=0;i<data.size();i++){
				writer.write(data.get(i) + "\r\n" );   //insert new values
			}
			writer.close();
		}catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
		data=null;
		return ifupdate;
	}

	/**
	 *  A method for replacing the value(condition) with new value at specific the location
	 *  @param filename name of file
	 *  @param condition value in the record that need to replace
	 *  @param loc location of the value in the record
	 *  @param newvalue new values that need to replace 
	 *  @return whether this operation is success
	 */ 
	public boolean replace(String filename,String condition,int loc,String newvalue){
		boolean ifreplace=false;
		File myFile = new File("File" + File.separator + filename);
		data=new ArrayList<String>();
		try{ 
			//read data from the file
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line=null;
			String[] result=null;
			while((line=reader.readLine())!=null){
				result=line.split("/");
				if(result[loc-1].equals(condition)){
					//save new data
					result[loc-1]=newvalue;
					//combine the values
					String str="";
					for(int i=0;i<result.length;i++){
						str+=result[i];
						if(i!=result.length-1)
							str+="/";
					}
					data.add(str);
					ifreplace=true;
				}else{	
					data.add(line);
				}
				result=null;
			}
			reader.close();
			//write back into the file with new data in specific record
			FileWriter writer = new FileWriter(myFile);
			for(int i=0;i<data.size();i++){
				writer.write(data.get(i) + "\r\n" );
			}
			writer.close();
		}catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
		data=null;
		return ifreplace;
	}

}
