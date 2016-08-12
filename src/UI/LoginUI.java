/**
 *  A class for login(both staff and customer)
 *
 *  @author    Chunzi Wu
 *  @date	   12th May,2014
 */

package UI;

import javax.swing.*;

import Function.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JPanel{

	private int width=800;                       //width of this panel
	private int height=500;		                 //height of this panel
	private JFrame container;                    //frame of the UI
	private JTextField nametf;                   //user name text field
	private JTextField pwtf;                     //password text field
	private Login login;
	private NoticeUI n;
	
	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;
		//set content panel attributes
		this.setLayout(null);//default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);
		
		login=new Login();
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
		JPanel contp = new JPanel();
		contp.setSize(width, 440);
		contp.setLocation(0,60);
		contp.setBackground(Color.pink);
		contp.setLayout(null);
		this.add(contp);
		//window panel*********************************************************************
		JPanel wind = new JPanel();
		wind.setBackground(new Color(178,34,34));
		wind.setSize(width/3,150);                         //set size of window panel
		wind.setLocation(width/3,height/4);                //set window panel's location
		wind.setLayout(new GridLayout(5,1));               //set the type of layout
		contp.add(wind);                                   //add window panel to content panel
		//login label
		JLabel log = new JLabel("Login");
		Font font = new Font("Default",Font.PLAIN,30);
		log.setFont(font);
		log.setForeground(Color.white);
		log.setHorizontalAlignment(SwingConstants.CENTER); //let the "login" in the center
		wind.add(log);
		//************************************************************************************
		//name panel
		JPanel namep = new JPanel();
		namep.setBackground(new Color(255,215,0));
		namep.setLayout(null);
		wind.add(namep);
		JLabel namel = new JLabel("User name:");
		namel.setLocation(0,0);
		namel.setSize(width/9,30);
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		namep.add(namel);
		//user name
		nametf = new JTextField();
		nametf.setLocation(width/9,0);
		nametf.setSize(2*width/9,30);
		namep.add(nametf);
		//*************************************************************************************
		//password panel***********************************************************************
		JPanel pwp = new JPanel();
		pwp.setBackground(new Color(255,93,67));
		pwp.setLayout(null);
		wind.add(pwp);
		JLabel pwl = new JLabel("Password:");
		pwl.setLocation(0,0);
		pwl.setSize(width/9,30);
		pwl.setHorizontalAlignment(SwingConstants.CENTER);
		pwp.add(pwl);
		//password
		pwtf = new JPasswordField();
		pwtf.setLocation(width/9,0);
		pwtf.setSize(2*width/9,30);
		((JPasswordField) pwtf).setEchoChar('*');
		pwp.add(pwtf);
		//************************************************************************************
		//submit button***********************************************************************
		JButton sub = new JButton("Submit");
		sub.setBackground(new Color(218,112,214));
		sub.addActionListener(new SubmitListener());
		wind.add(sub);
		//************************************************************************************
		//forget password button**************************************************************
		JButton forg= new JButton("Forget Password?");
		forg.setBackground(new Color(199,21,125));
		forg.addActionListener(new ForgetListener());
		wind.add(forg);
	}
    
	/**
	 *  A method for setting this panel invisible
	 */
	private void disappear(){
		this.setVisible(false);
	}
	
	//inner class to take action when submit button is pressed
	class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String name=nametf.getText();
			String pw=pwtf.getText();
			//check if input something
			if(name.length()==0||pw.length()==0){
				n=new NoticeUI("Please enter username and password!");
			}else{
				login.setName(name);                    //get user name
				login.setPw(pw);                        //get password
				if(login.checkAcc()){                   //check account
					if(login.who()){                    //if staff
						HomeUI h=new HomeUI();
						h.build(container);
						disappear();
					}else{                             //if customer
						UserhomeUI u=new UserhomeUI();
						u.build(container);
						u.setName(name);
						disappear();
					}
				}else{
					nametf.setText("");
					pwtf.setText("");
				}
			}
		}	
	}
	
	//inner class to take action when forget password button is pressed
	class ForgetListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			ForgpwUI f=new ForgpwUI();
			f.build(container);
			disappear();
		}
	}

	//inner class to implement head panel
	class HeadP extends HeadUI{
		public void homeAct() {}
		public void loginAct() {}
	}
	
	
}