/**
 *  A class for store balance user interface
 *
 *  @author    Tianming Zhang 
 *  @date	   14th May,2014
 */

package UI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Function.StoreAcc;

public class BalanceUI extends JPanel{

	private int width=800;                        //width of this panel
	private int height=440;	                      //height of this panel
	private JFrame container;                     //frame of the UI
	private StoreAcc s;
	private double money;                         //store balance
	
	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;                         //get frame
		//set content panel attributes
		this.setLayout(null);                    //default the layout
		this.setBackground(Color.white);         
		this.setSize(width,height);
		this.setLocation(0,0);
		contf.getContentPane().add(this);
		
		s=new StoreAcc();
		//add head panel to this panel
		HeadP headp=new HeadP();
		headp.setLocation(0, 0);
		headp.build();
		this.add(headp);
		//add content panel
		addCon();
	}
	
	
	/**
	 *  A method for adding content panel to this panel
	 */
	private void addCon(){

		//content panel
		JPanel contp=new JPanel();
		contp.setSize(width, 440);
		contp.setLocation(0,60);
		contp.setBackground(Color.pink);
		contp.setLayout(null);
		this.add(contp);
		
		//background panel
		JPanel backp=new JPanel();
		backp.setSize(600, 200);
		backp.setLocation(100, 100);
		backp.setBackground(new Color(245,222,125));
		backp.setLayout(null);
		contp.add(backp);
		
		money=s.showBal();
		//store account balance label
		JLabel accl = new JLabel("£ " + money);
		accl.setSize(600, 200);
		accl.setLocation(0, 0);
		Font font = new Font("Default",Font.PLAIN,80);
		accl.setFont(font);
		if(money<0){                           //if the money is negative, the number would be red
			accl.setForeground(Color.red);
		}else{                                 //if the money is positive, the number would be green
			accl.setForeground(Color.green);
		}
		accl.setHorizontalAlignment(SwingConstants.CENTER);
		backp.add(accl);
	}
	
	/**
	 *  A method for setting this panel invisible
	 */
	private void disappear(){
		this.setVisible(false);
	}
	
	//inner class to implement head panel
	class HeadP extends HeadUI{
		/**
		 *  A method for setting action if back home button is pressed
		 */
		public void homeAct() {
			HomeUI h=new HomeUI();
			h.build(container);          //to home page
			disappear();
		}

		/**
		 *  A method for setting action if log out button is pressed
		 */
		public void loginAct() {
			LoginUI l=new LoginUI();
			l.build(container);          //to login page
			disappear();
		}
	}
}
