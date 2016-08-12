/**
 *  A class for building UI frame and entrance
 *
 *  @author    Anni Piao 
 *  @date	   12th May,2014
 */

package UI;

import javax.swing.JFrame;

public class UIall {

	JFrame container;                  //the frame that will not changed
	int height=500;                    //the height of the frame
	int width=800;                     //the weight of the frame
	
	/**
	 *  A method for running the program
	 *  @param args  input saved in String array
	 */
	public static void main(String[] args){
		UIall u=new UIall();
		u.build();
	}
	
	/**
	 *  A method for building the frame and pass to LoginUI
	 */
	private void build(){
		container=new JFrame();
		//pass to LoginUI
		LoginUI l=new LoginUI();
		l.build(container);
		//set frame parameters
		container.setSize(width,height);                              //set size
		container.setLocation(300,100);                               //set location
		container.setResizable(false);                                //forbid size change
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //exit when closed
		container.setVisible(true);                                   //set visible	
	}
}
