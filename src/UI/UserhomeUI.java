/**
 *  A class for user home page interface
 *
 *  @author    Jingzhi Liu
 *  @date	   18th May,2014
 */

package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import Function.CustMan;

public class UserhomeUI extends JPanel{

	private int width=800;                                   //width of this panel
	private int height=500;		                             //height of this panel
	private JFrame container;                                //frame of the UI
	private String name;
	private JLabel item1,item2,item3,item4;
	private JPanel itemp1,itemp2,itemp3,itemp4;
	private JLabel fbook,sbook,fdate,sdate;
	private NoticeUI n;
	private String[] result;
	
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
		//add head panel to this panel
		HeadP headp=new HeadP();
		headp.setLocation(0, 0);
		headp.build();
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
		//***********************************************************************************
		//label panel************************************************************************
		JPanel labelp=new JPanel();
		labelp.setSize(200, 200);
		labelp.setLocation(100,30);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLayout(new GridLayout(4,1));
		contp.add(labelp);
		//set font
		Font font = new Font("Default",Font.PLAIN,20);
		//label for subscriber name
		JLabel namel = new JLabel("Member name:");
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		namel.setFont(font);
		labelp.add(namel);
		//label for subscriber ID
		JLabel idl = new JLabel("ID:");
		idl.setFont(font);
		idl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(idl);
		//label for subscriber phone number
		JLabel phonel = new JLabel("Phone number:");
		phonel.setFont(font);
		phonel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(phonel);
		//label for subscriber account balance
		JLabel accl = new JLabel("Account balance:");
		accl.setFont(font);
		accl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(accl);
		//*************************************************************************************
		//item panel***************************************************************************
		JPanel itemp=new JPanel();
		itemp.setSize(400, 200);
		itemp.setLocation(300,30);
		itemp.setBackground(new Color(245,222,125));
		itemp.setLayout(new GridLayout(4,1));
		contp.add(itemp);		
		//item for subscriber name
		item1 = new JLabel();
		item1.setFont(font);
		item1.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item1);		
		//item for subscriber ID
		item2 = new JLabel();
		item2.setFont(font);
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item2);		
		//item for subscriber phone number
		item3 = new JLabel();
		item3.setFont(font);
		item3.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item3);		
		//item for subscriber account balance
		item4 = new JLabel();
		item4.setFont(font);
		item4.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item4);
		//*************************************************************************************
		//title panel**************************************************************************
		JPanel titlep=new JPanel();
		titlep.setSize(200, 40);
		titlep.setLocation(300,230);
		titlep.setBackground(new Color(245,222,125));
		titlep.setLayout(new GridLayout(1,1));
		contp.add(titlep);
		//label for the title
		JLabel title = new JLabel("Book rented condition:");
		title.setFont(font);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		titlep.add(title);
		//rented book item1
		itemp1=new JPanel();
		itemp1.setSize(200, 40);
		itemp1.setLocation(200,270);
		itemp1.setBackground(new Color(245,222,125));
		itemp1.setLayout(new GridLayout(1,1));
		itemp1.setVisible(false);
		contp.add(itemp1);
		//*************************************************************************************
		//first book label*********************************************************************
		fbook= new JLabel();
		fbook.setFont(font);
		fbook.setHorizontalAlignment(SwingConstants.CENTER);
		itemp1.add(fbook);
		//rented book item2
		itemp2=new JPanel();
		itemp2.setSize(200, 40);
		itemp2.setLocation(400,270);
		itemp2.setBackground(new Color(245,222,125));
		itemp2.setLayout(new GridLayout(1,1));
		itemp2.setVisible(false);
		contp.add(itemp2);
		//first book rented date				
		fdate= new JLabel();
		fdate.setFont(font);
		fdate.setHorizontalAlignment(SwingConstants.CENTER);
		itemp2.add(fdate);
		//rented book item3
		itemp3=new JPanel();
		itemp3.setSize(200, 40);
		itemp3.setLocation(200,310);
		itemp3.setBackground(new Color(245,222,125));
		itemp3.setLayout(new GridLayout(1,1));
		itemp3.setVisible(false);
		contp.add(itemp3);
		//second book label		
		sbook= new JLabel();
		sbook.setFont(font);
		sbook.setHorizontalAlignment(SwingConstants.CENTER);
		itemp3.add(sbook);
		//rented book item4
		itemp4=new JPanel();
		itemp4.setSize(200, 40);
		itemp4.setLocation(400,310);
		itemp4.setBackground(new Color(245,222,125));
		itemp4.setLayout(new GridLayout(1,1));
		itemp4.setVisible(false);
		contp.add(itemp4);
		//second book rented date					
		sdate= new JLabel();
		sdate.setFont(font);
		sdate.setHorizontalAlignment(SwingConstants.CENTER);
		itemp4.add(sdate);
		//*************************************************************************************
		//change password button***************************************************************
		JButton chanpw = new JButton("Change Password");
		chanpw.addActionListener(new ChanpwListener());
		chanpw.setBackground(new Color(218,112,214));
		chanpw.setLocation(620,300);
		chanpw.setSize(150,30);
		contp.add(chanpw);	
	}
	
	/**
	 *  A method for print all the information about this user
	 */
	private void print(){
		CustMan c=new CustMan();
		result=c.query(name);
		if(result!=null){
			item1.setText(result[1]);                       //member name
			item2.setText(result[0]);                       //user ID
			item3.setText(result[3]);                       //phone number
			item4.setText(result[2]);                       //account balance
			if(!result[4].equals("0")){                     //if borrow the first book
				fbook.setText(result[5]);                   //first book name
				fdate.setText(result[7]);                   //first book rented date
				itemp1.setVisible(true);                    //set first book name visible
				itemp2.setVisible(true);                    //set first book rented date visible
			}
			if(!result[9].equals("0")){                     //if borrow the second book
				sbook.setText(result[10]);                  //second book name
				sdate.setText(result[12]);                  //second book rented date
				itemp3.setVisible(true);                    //set second book name visible
				itemp4.setVisible(true);                    //set second book rented date visible
			}
		}
	}

	//inner class to take action when change password button is pressed
	class ChanpwListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String pw1="";
			String pw2="";
			pw1 = JOptionPane.showInputDialog("Enter the new password(press left button if ok).");
			pw2 = JOptionPane.showInputDialog("Enter the new password again(press left button if ok).");
			//check if input is not empty and twice are the same
			if(pw1!=null&&pw1.equals(pw2)){
				CustMan cus=new CustMan();
				cus.chanPw(Integer.parseInt(result[0]), pw1);   //change password
			}else{
				n=new NoticeUI("Password change failed!");
			}
		}
	}
	
	public void setName(String name){
		this.name=name;
		print();
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
