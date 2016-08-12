/**
 *  A class for customer management
 *
 *  @author    Jingzhi Liu
 *  @date	   13th May,2014
 */

package UI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Function.CustAcc;
import Function.CustMan;
import Function.Subscriber;
import Helper.DBhelper;

public class CustmanUI extends JPanel{

	private final static String AUTH="authority.txt";                 //authority filename
	private DBhelper db;
	private int width=800;                                            //width of this panel
	private int height=440;	                                          //height of this panel
	private JFrame container;                                         //frame of the UI
	private NoticeUI n;
	private JTextField searchFrame,nametf,phonetf,moneytf;
	private JLabel item2,item4,fdatel,sdatel,fnamel,snamel;
	private CustMan c;
	private String[] result;
	private JButton reset,add,fret,sret;
	private CustAcc acc;
	private Subscriber sub;
	
	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;
		
		this.setLayout(null);//default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);
		
		c=new CustMan();
		acc=new CustAcc();
		sub=new Subscriber();
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

		//content panel*****************************************************
		JPanel contp=new JPanel();
		contp.setSize(width, 440);
		contp.setLocation(0,60);
		contp.setBackground(Color.pink);
		contp.setLayout(null);
		this.add(contp);
		//search button	
		JButton search = new JButton(new ImageIcon("src/search.jpg"));
		search.addActionListener(new SearchListener());
		search.setLocation(600,10);
		search.setSize(80,30);
		contp.add(search);
		//search text field, get the user name
		searchFrame = new JTextField();
		searchFrame.setLocation(120,10);
		searchFrame.setSize(480,30);
		contp.add(searchFrame);
		//******************************************************************
		//label panel*******************************************************
		JPanel labelp=new JPanel();
		labelp.setSize(220, 150);
		labelp.setLocation(85,60);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLayout(new GridLayout(4,1));
		contp.add(labelp);
		//member name label
		JLabel namel = new JLabel("Member's name:");
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(namel);
		//ID label	
		JLabel idl = new JLabel("ID:");
		idl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(idl);
		//phone number label
		JLabel phonel = new JLabel("Phone number:");
		phonel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(phonel);
		//account balance label	
		JLabel accl = new JLabel("Account balance:");
		accl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(accl);
		//******************************************************************		  
		//item panel*******************************************************
		JPanel itemp = new JPanel();
		itemp.setLayout(new GridLayout(4,1));
		itemp.setLocation(305,60);
		itemp.setSize(380,150);
		contp.add(itemp);
		//edit user name 
		nametf=new JTextField();
		itemp.add(nametf);	
		//id 
		item2 = new JLabel();
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item2);
		//edit phone number 
		phonetf=new JTextField();
		itemp.add(phonetf);
		//account panel
		JPanel accp = new JPanel();
		accp.setLayout(null);
		itemp.add(accp);
		//reset account balance button
		reset= new JButton("Reset");
		reset.setSize(80, 25);
		reset.setLocation(20,5);
		reset.setBackground(new Color(255,93,67));
		reset.addActionListener(new ResetListener());
		reset.setVisible(false);
		accp.add(reset);
		//account balance
		item4 = new JLabel();
		item4.setSize(110, 30);
		item4.setLocation(105,5);
		item4.setHorizontalAlignment(SwingConstants.CENTER);
		accp.add(item4);
		//top-up button
		add= new JButton("re-charge");
		add.setBackground(new Color(255,215,0));
		add.setSize(100, 25);
		add.setLocation(220,5);
		add.addActionListener(new AddListener());
		add.setVisible(false);
		accp.add(add);
		//money text field
		moneytf=new JTextField();
		moneytf.setSize(60, 25);
		moneytf.setLocation(320,5);
		moneytf.setVisible(false);
		accp.add(moneytf);
		//******************************************************************
		//two button********************************************************
		//delete button
		JButton cancel= new JButton("Delete U");
		cancel.setBackground(new Color(255,93,67));
		cancel.setLocation(140, 220);
		cancel.setSize(100,30);
		cancel.addActionListener(new CancelListener());
		contp.add(cancel);
		//save button
		JButton save= new JButton("Save");
		save.setLocation(515, 220);
		save.setSize(100,30);
		save.setBackground(new Color(218,112,214));
		save.addActionListener(new SaveListener());
		contp.add(save);
		//********************************************************************
		//column panel 1******************************************************
		JPanel colp1 = new JPanel();
		colp1.setLocation(85,270);
		colp1.setSize(80,120);
		colp1.setLayout(new GridLayout(3,1));
		colp1.setBackground(new Color(34,139,34));
		contp.add(colp1);
		//no. label
		JLabel nol = new JLabel("NO.");
		nol.setHorizontalAlignment(SwingConstants.CENTER);
		colp1.add(nol);
		//first book label
		JLabel fl = new JLabel("1.");
		fl.setHorizontalAlignment(SwingConstants.CENTER);
		colp1.add(fl);
		//second book label
		JLabel sl = new JLabel("2.");
		sl.setHorizontalAlignment(SwingConstants.CENTER);
		colp1.add(sl);
		//***********************************************************************
		//column panel 2*********************************************************
		JPanel colp2 = new JPanel();
		colp2.setLocation(165,270);
		colp2.setSize(110,120);
		colp2.setLayout(new GridLayout(3,1));
		colp2.setBackground(new Color(245,222,125));
		contp.add(colp2);
		//date label
		JLabel datel = new JLabel("DATE");
		datel.setHorizontalAlignment(SwingConstants.CENTER);
		colp2.add(datel);
		//first book rent date label
		fdatel = new JLabel();
		fdatel.setHorizontalAlignment(SwingConstants.CENTER);
		colp2.add(fdatel);
		//second book rent date label
		sdatel = new JLabel();
		sdatel.setHorizontalAlignment(SwingConstants.CENTER);
		colp2.add(sdatel);
		//***********************************************************************
		//column panel 3*********************************************************
		JPanel colp3 = new JPanel();
		colp3.setLocation(275,270);
		colp3.setSize(170,120);
		colp3.setLayout(new GridLayout(3,1));
		colp3.setBackground(new Color(245,222,125));
		contp.add(colp3);
		//book label
		JLabel bookl = new JLabel("BOOK");
		bookl.setHorizontalAlignment(SwingConstants.CENTER);
		colp3.add(bookl);
		//first book name label
		fnamel=new JLabel();
		fnamel.setHorizontalAlignment(SwingConstants.CENTER);
		colp3.add(fnamel);
		//second book name label
		snamel=new JLabel();
		snamel.setHorizontalAlignment(SwingConstants.CENTER);
		colp3.add(snamel);
		//********************************************************************************
		//button base panel
		JPanel basep = new JPanel();
		basep.setLocation(425,270);
		basep.setSize(260,120);
		basep.setBackground(new Color(245,222,125));
		basep.setLayout(null);
		contp.add(basep);
		//return first book button
		fret= new JButton(new ImageIcon("src/return.jpg"));
		fret.setLocation(45,45);
		fret.setSize(90,25);
		fret.addActionListener(new FretListener());
		fret.setVisible(false);
		basep.add(fret);
		//return second book button
		sret = new JButton(new ImageIcon("src/return.jpg"));
		sret.setLocation(45,85);
		sret.setSize(90,25);
		sret.addActionListener(new SretListener());
		sret.setVisible(false);
		basep.add(sret);
		//********************************************************************
   }

	//inner class to take action when search button is pressed
	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//check if user has input something
			if(searchFrame.getText().length()!=0){
				result=c.query(searchFrame.getText());
				//check if user is exist
				if(result==null){
					n=new NoticeUI("The user is not exist!");
				}else{
					print();
				}
			}else{
				n=new NoticeUI("Please enter the user name!");
			}
		}
	}
	
	/**
	 *  A method for display the information
	 *  about this user
	 */
	private void print(){
		nametf.setText(result[1]);  //name
		item2.setText(result[0]);   //id
		phonetf.setText(result[3]); //phone number
		item4.setText(result[2]);   //account balance
		add.setVisible(true);       //re-charge button
		moneytf.setVisible(true);   //re-charge money
		//query authority information
		db=new DBhelper();
		String[] res=db.query(AUTH, null, 1);
		//check if reset button should appear
		if(res[0].equals("1")&&(Double.parseDouble(result[2])<0)){
			reset.setVisible(true);
		}
		//if borrow the first book
		if(!result[4].equals("0")){
			fdatel.setText(result[7]);  //first book rented date
			fnamel.setText(result[5]);  //first book name
			fret.setVisible(true);
		}
		//if borrow the second book
		if(!result[9].equals("0")){
			sdatel.setText(result[12]);  //second book rented date
			snamel.setText(result[10]);  //second book name
			sret.setVisible(true);       
		}
	}

	/**
	 *  A method for clearing the information
	 *  about this user
	 */
	private void clearcont(){
		nametf.setText("");
		item2.setText("");
		phonetf.setText("");
		item4.setText("");
		fdatel.setText("");
		fnamel.setText("");
		fret.setVisible(false);
		sdatel.setText("");
		snamel.setText("");
		sret.setVisible(false);
		reset.setVisible(false);
		add.setVisible(false);
		moneytf.setVisible(false);
	}
	
	//inner class to take action when cancel button is pressed
	class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//check if there is a user chosen now
			if(item2.getText().length()!=0){
				c.delete(item2.getText());     //delete the user
				clearcont();                   //clear the content displayed
			}else{
				n=new NoticeUI("Invalid cancel!");
			}
		}
	}
	
	//inner class to take action when save button is pressed
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			boolean ifsave=true;
			//check if there is something to save
			if(item2.getText().equals("")){
				n=new NoticeUI("Invalid save!");
				ifsave=false;
			}
			//if OK
			if(ifsave){
				//if user name need to be changed
				if(!nametf.getText().equals(result[1])){
					c.chanName(result[1],nametf.getText());
				}
				//if phone number need to be changed 
				if(!phonetf.getText().equals(result[3])){
					c.chanPhone(item2.getText(), phonetf.getText());
				}
				clearcont();
			}
		}
	}
	
	//inner class to take action when reset button is pressed
	class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			c.resAcc(Integer.parseInt(result[0]));         //reset account
			reset.setVisible(false);                       //set reset invisible
			result=c.query(Integer.parseInt(result[0]));   //query again and then show
			print();
		}
	}
	
	//inner class to take action when re-charge button is pressed
	class AddListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			double money=0;
			boolean ifadd=true;
			//check if money is a number
			try{
				money=Double.parseDouble(moneytf.getText());
			}catch(Exception e){
				n=new NoticeUI("The money adding must be a number!");
				ifadd=false;
			}
			//if OK
			if(ifadd){
				//check if money is positive
				if(money>=0){
					acc.addAcc(Integer.parseInt(result[0]), money);
					result=c.query(Integer.parseInt(result[0]));
					//check if the money is positive now and set reset invisible
					if(Double.parseDouble(result[2])>0){
						reset.setVisible(false);
					}
					print();
				}else{
					n=new NoticeUI("The money adding must be positive!");
				}
			}
			moneytf.setText("");
		}
	}
	
	//inner class to take action when first return book button is pressed
	class FretListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			sub.retBook(Integer.parseInt(result[0]), result[5], true);
			fret.setVisible(false);
			fdatel.setText("");  //first book rented date
			fnamel.setText("");  //first book name
			result=c.query(Integer.parseInt(result[0]));
			print();
		}
	}
	
	//inner class to take action when second return book button is pressed
	class SretListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			sub.retBook(Integer.parseInt(result[0]), result[10], false);
			sret.setVisible(false);
			sdatel.setText("");  //second book rented date
			snamel.setText("");  //second book name
			result=c.query(Integer.parseInt(result[0]));
			print();
		}
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
}