/**
 *  A class for registration
 *
 *  @author    Chunzi Wu,Jingzhi Liu
 *  @date	   13th May,2014
 */

package UI;

import javax.swing.*;

import Function.Registration;
import UI.HomeUI.HeadP;

import java.awt.*;
import java.awt.event.*;

public class RegUI extends JPanel{

	private int width=800;                               //width of this panel
	private int height=440;	                             //height of this panel
	private JFrame container;                            //frame of the UI
	private JTextField nametf; 
	private JTextField pwtf1;
	private JTextField pwtf2;
	private JTextField paidtf;
	private JTextField phonetf;
	private NoticeUI n;
	private Registration reg;
	
	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;
		//set content panel attributes
		this.setLayout(null);                        //default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);
		
		reg=new Registration();
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
		//label panel*********************************************************************
		JPanel labelf = new JPanel();
		labelf.setLayout(new GridLayout(5,1));
		labelf.setBackground(new Color(245,222,125));
		labelf.setLocation(180,90);
		labelf.setSize(180,125);
		contp.add(labelf);
		//name label
		JLabel namel = new JLabel("name");
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		labelf.add(namel);
		//password 1 label
		JLabel pwl1 = new JLabel("password");
		pwl1.setHorizontalAlignment(SwingConstants.CENTER);
		labelf.add(pwl1);
		//password 2 label
		JLabel pwl2 = new JLabel("password again");
		pwl2.setHorizontalAlignment(SwingConstants.CENTER);
		labelf.add(pwl2);
		//paid money label
		JLabel paidl = new JLabel("pre-paid (>15)");
		paidl.setHorizontalAlignment(SwingConstants.CENTER);
		labelf.add(paidl);
		//phone number label
		JLabel phonel = new JLabel("phone number");
		phonel.setHorizontalAlignment(SwingConstants.CENTER);
		labelf.add(phonel);
		//*********************************************************************************
		//text field panel*****************************************************************
		JPanel textf = new JPanel();
		textf.setLayout(new GridLayout(5,1));
		textf.setLocation(360,90);
		textf.setSize(240,125);
		contp.add(textf);
		//text field get user name
		nametf = new JTextField();
		textf.add(nametf);
		//text field get first password
		pwtf1 = new JPasswordField();
		((JPasswordField) pwtf1).setEchoChar('*');
		textf.add(pwtf1);
		//text field get second password
		pwtf2 = new JPasswordField();
		((JPasswordField) pwtf2).setEchoChar('*');
		textf.add(pwtf2);
		//text field get paid money
		paidtf = new JTextField();
		textf.add(paidtf);
		//text field get phone number
		phonetf = new JTextField();
		textf.add(phonetf);
		//****************************************************************************
		//submit button***************************************************************
		JButton sub = new JButton("Submit");
		sub.addActionListener(new SubmitListener());
		sub.setBackground(new Color(218,112,214));
		sub.setSize(150,40);
		sub.setLocation(450,240);
		contp.add(sub);
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
			h.build(container);
			disappear();
		}

		/**
		 *  A method for setting action if log out button is pressed
		 */
		public void loginAct() {
			LoginUI l=new LoginUI();
			l.build(container);
			disappear();
		}	
	}

	//inner class to take action when submit button is pressed
	class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String name=nametf.getText();
			String pw1=pwtf1.getText();
			String pw2=pwtf2.getText();
			String paid=paidtf.getText();
			String phone=phonetf.getText();
			//check if information is complete
			if(name.length()==0||pw1.length()==0||paid.length()==0||phone.length()==0||pw2.length()==0){
				n=new NoticeUI("Please complete the information!");
			}else{
				//get information into the system
				if(reg.getName(name)&&reg.getPw(pw1, pw2)&&reg.getPhone(phone)&&reg.getMoney(paid)){
					reg.addAcc();
				}
				//clear
				nametf.setText("");	
				pwtf1.setText("");	
				pwtf2.setText("");	
				paidtf.setText("");	
				phonetf.setText("");	
			}
		}
	}
}