/**
 *  A class for complete subscriber's operation.
 *  buying books and borrowing books
 *
 *  @author    Jingzhi Liu
 *  @date	   17th May,2014
 */

package UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Function.CustMan;
import Function.Subscriber;

public class SubBBUI extends JPanel{
	
	private int width=800;                               //width of this panel
	private int height=440;	                             //width of this panel
	private JFrame container;                            //frame of the UI
	private JTextField searchFrame;
	private String bookname;
	private JLabel item1,item2;
	private NoticeUI n;
	private CustMan c;
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
		//search button************************************************************************
		JButton search = new JButton(new ImageIcon("src/search.jpg"));
		search.addActionListener(new SearchListener());
		search.setLocation(520,30);
		search.setSize(80,30);
		contp.add(search);
		//text field get user name
		searchFrame = new JTextField();
		searchFrame.setLocation(120,30);
		searchFrame.setSize(400,30);
		contp.add(searchFrame);
		//**************************************************************************************
		//label panel***************************************************************************
		JPanel labelp=new JPanel();
		labelp.setSize(200, 100);
		labelp.setLocation(100,100);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLayout(new GridLayout(2,1));
		contp.add(labelp);	
		//label for subscriber name
		JLabel namel = new JLabel("Member name:");
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(namel);	
		//label for subscriber account balance
		JLabel accl = new JLabel("Account balance:");
		accl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(accl);
		//***************************************************************************************
		//item panel*****************************************************************************
		JPanel itemp=new JPanel();
		itemp.setSize(400, 100);
		itemp.setLocation(300,100);
		itemp.setBackground(Color.white);
		itemp.setLayout(new GridLayout(2,1));
		contp.add(itemp);	
		//subscriber name
		item1 = new JLabel();
		item1.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item1);			
		//account balance
		item2 = new JLabel();
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item2);
		//*************************************************************************************
		//buy button***************************************************************************
		JButton buy = new JButton("BUY");
		buy.addActionListener(new BuyListener());
		buy.setBackground(new Color(135,206,235));
		buy.setLocation(200,230);
		buy.setSize(100,30);
		contp.add(buy);
		//*************************************************************************************	
		//borrow button************************************************************************
		JButton borrow = new JButton("BORROW");
		borrow.addActionListener(new BorrowListener());
		borrow.setBackground(new Color(218,112,214));
		borrow.setLocation(550,230);
		borrow.setSize(100,30);
		contp.add(borrow);	
	}
	
	/**
	 *  A method for setting the book name from other object
	 *  @param bookname  name of the book
	 */
	public void setBookname(String bookname){
		this.bookname=bookname;
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
	
	//inner class to take action when search button is pressed
	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(searchFrame.getText().length()!=0){   //check if input something
				c=new CustMan();
				if((result=c.query(searchFrame.getText()))==null){    //if user not exist
					n=new NoticeUI("The user name is not exist!");
				}else{
					item1.setText(result[1]);
					item2.setText(result[2]);
				}
			}else{
				n=new NoticeUI("Please enter the user name!");
			}
		}
	}
	
	//inner class to take action when buy button is pressed
	class BuyListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			boolean ifok=true;
			//check if there is a book chosen
			if(result==null){
				n=new NoticeUI("Invalid Buy!");
				ifok=false;
			}
			String value="";
			int number=0;
			//if OK, then get user input about book number
			if(ifok){
				value = JOptionPane.showInputDialog("How many book do you want to buy?(press left button if ok)");
				if(value==null){
					ifok=false;
				}
			}
			//if OK, check if book number is a number
			if(ifok){
				try{
					number=Integer.parseInt(value);
				}catch(Exception e){
					n=new NoticeUI("Invalid book number!");
					ifok=false;
				}
			}
			
			//if OK, check if number is positive and then buy book
			if(ifok&&(number>0)){
				Subscriber sub=new Subscriber();
				sub.buyBook(Integer.parseInt(result[0]), bookname, number);
				result=c.query(result[1]);
				item2.setText(result[2]);
			}
		}
	}

	//inner class to take action when borrow button is pressed
	class BorrowListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//check if user input something
			if(result==null){
				n=new NoticeUI("Invalid Borrow!");
			}else{
				//check if borrow
				int reponse=JOptionPane.showConfirmDialog(null, "Sure to borrow one?", "Query", JOptionPane.YES_NO_OPTION);
				if(reponse==0){
					Subscriber sub=new Subscriber();
					sub.borBook(Integer.parseInt(result[0]), bookname);
					result=c.query(result[1]);
					item2.setText(result[2]);
				}
			}
		}
	}

}
