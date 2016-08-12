/**
 *  A class for condition if user forget password
 *
 *  @author    Kexin Zhu
 *  @date	   15th May,2014
 */

package UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Function.CustMan;
import Function.Subscriber;

public class ForgpwUI extends JPanel{

	private int width=800;                                       //width of this panel
	private int height=500;		                                 //height of this panel
	private JFrame container;                                    //frame of the UI
	private JTextField nametf,phonetf;
	private NoticeUI n;
	
	public void build(JFrame contf){
		container=contf;
		//set content panel attributes
		this.setLayout(null);//default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);
		//add head panel to this panel
		HeadP headp=new HeadP();
		headp.setLocation(0, 0);
		headp.build();
		headp.dislog();
		headp.dishome();
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
		
		//label panel*****************************************************************
		JPanel labelp=new JPanel();
		labelp.setSize(220, 100);
		labelp.setLocation(85,60);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLayout(new GridLayout(2,1));
		contp.add(labelp);
		//member name label		
		JLabel namel = new JLabel("Member's name:");
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(namel);
		//phone number label			
		JLabel phonel = new JLabel("Phone number:");
		phonel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(phonel);
		//****************************************************************************
		//item panel******************************************************************
		JPanel itemp = new JPanel();
		itemp.setLayout(new GridLayout(2,1));
		itemp.setLocation(305,60);
		itemp.setSize(380,100);
		contp.add(itemp);
		//text field that get the name			
		nametf=new JTextField();
		itemp.add(nametf);
		//text field that get the phone number
		phonetf=new JTextField();
		itemp.add(phonetf);
		//****************************************************************************
		//back button*****************************************************************
		JButton back= new JButton("Back");
		back.setLocation(140, 220);
		back.setSize(100,30);
		back.setBackground(new Color(135,206,235));
		back.addActionListener(new BackListener());
		contp.add(back);
		//*****************************************************************************	
		//submit button****************************************************************
		JButton submit= new JButton("Submit");
		submit.setLocation(515, 220);
		submit.setBackground(new Color(218,112,214));
		submit.setSize(100,30);
		submit.addActionListener(new SubmitListener());
		contp.add(submit);
	}
	
	/**
	 *  A method for setting this panel invisible
	 */
	private void disappear(){
		this.setVisible(false);
	}
	
	//inner class to implement head panel
	class HeadP extends HeadUI{
		public void homeAct() {}
		public void loginAct() {}
	}
	
	//inner class to take action when back button is pressed
	class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			LoginUI l=new LoginUI();
			l.build(container);
			disappear();
		}
	}
	
	//inner class to take action when submit button is pressed
	class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			boolean ifok=true;
			long phonum=0;
			String[] result=null;
			//check if information is complete
			if(nametf.getText().length()==0||phonetf.getText().length()==0){
				n=new NoticeUI("Please complete the information!");
				ifok=false;
			}
			//if OK then check if the phone number is a number
			if(ifok){
				try{
					phonum=Long.parseLong(phonetf.getText());
				}catch(Exception e){
					n=new NoticeUI("The phone number must be a number!");
					phonetf.setText("");
					ifok=false;
				}
			}
			//if OK then get information of the user
			if(ifok){
				CustMan c=new CustMan();
				result=c.query(nametf.getText());
			}
			//if OK and user is not exist
			if(ifok&&(result==null)){
				n=new NoticeUI("The user name is not exist!");
				nametf.setText("");
				ifok=false;
			}
			//If OK and phone number is wrong
			if(ifok&&(phonum!=Long.parseLong(result[3]))){
				n=new NoticeUI("The phone number is not correct!");
				phonetf.setText("");
				ifok=false;
			}
			//if OK then change the password
			if(ifok){
				String pw1="";
				String pw2="";
				pw1 = JOptionPane.showInputDialog("Enter the new password(press left button if ok).");
				pw2 = JOptionPane.showInputDialog("Enter the new password again(press left button if ok).");
				if(pw1!=null&&pw1.equals(pw2)){
					CustMan cus=new CustMan();
					cus.chanPw(Integer.parseInt(result[0]), pw1);    //change password
					//to use home interface
					UserhomeUI u=new UserhomeUI();
					u.build(container);
					u.setName(nametf.getText());
					disappear();
				}else{
					n=new NoticeUI("Password change failed!");
				}
			}
		}
	}
}
