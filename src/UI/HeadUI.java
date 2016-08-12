/**
 *  A class for building the head panel
 *
 *  @author    Kexin Zhu
 *  @date	   13th May,2014
 */

package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class HeadUI extends JPanel{

	private int width=800;                                      //width of this panel
	private int height=60;	                                    //height of this panel
	private JButton logout;            
	private JButton tohome;
	
	public void build(){
		//set header panel attributes
		this.setSize(width, height);
		this.setBackground(new Color(245,222,179));
		this.setLayout(null);
		//set logout button
		logout = new JButton(new ImageIcon("src/log.jpg"));
		logout.setLocation(80, 10);
		logout.setSize(118,34);
		logout.addActionListener(new LoginListener());
		this.add(logout);
		//set application name
		JLabel headl = new JLabel();
		String appname="Comic Book Store System";
		headl.setText(appname);
		Font font = new Font("Default",Font.PLAIN,30);
		headl.setFont(font);
		headl.setLocation(200, 10);
		headl.setSize(400,40);
		headl.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(headl);
		//set logout button
		tohome = new JButton(new ImageIcon("src/home.jpg"));
		tohome.setLocation(620, 10);
		tohome.setSize(118,34);
		tohome.addActionListener(new HomeListener());
		this.add(tohome);
	}
	
	/**
	 *  A method for making logout button visible
	 */
	public void enlog(){
		logout.setVisible(true);
	}
	
	/**
	 *  A method for making logout button invisible
	 */
	public void dislog(){
		logout.setVisible(false);
	}
	
	/**
	 *  A method for making tohome button visible
	 */
	public void enhome(){
		tohome.setVisible(true);
	}
	
	/**
	 *  A method for making tohome button visible
	 */
	public void dishome(){
		tohome.setVisible(false);
	}
	
	/**
	 *  A method for subclass to implement the action of home button
	 */
	public abstract void homeAct();
	
	/**
	 *  A method for subclass to implement the action of logout button
	 */
	public abstract void loginAct();
	
	//inner class to take action when home button is pressed
	class HomeListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			homeAct();
		}
	}
	
	//inner class to take action when login button is pressed
	class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			loginAct();
		}
	}
}
