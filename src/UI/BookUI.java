/**
 *  A class for displaying book information and 
 *  providing book operation on this kind of book
 *
 *  @author    Jingzhi Liu
 *  @date	   16th May,2014
 */

package UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Function.BookMan;
import Function.Customer;

public class BookUI extends JPanel{

	private int width=800;                                          //width of this panel
	private int height=440;	                                        //height of this panel
	private JFrame container;                                       //frame of the UI
	private JLabel item1,item2,item3,item4,item5;
	private BookMan b;
	private String[] result;
	private String name;
	private JTextField numtf,montf;
	private NoticeUI n;
	private JDialog user;
	
	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;                                        //get frame
		//set content panel attributes
		this.setLayout(null);                                   //default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);

		b=new BookMan();
		
		HeadP headp=new HeadP();
		headp.setLocation(0, 0);
		headp.build();
		this.add(headp);
		
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
		labelp.setSize(200, 240);
		labelp.setLocation(100,60);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLayout(new GridLayout(5,1));
		contp.add(labelp);		
		//label for book name
		JLabel namel = new JLabel("Book name:");
		namel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(namel);
		//label for book author	
		JLabel authl = new JLabel("Author:");
		authl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(authl);
		//label for book grade			
		JLabel gradel = new JLabel("Grade:");
		gradel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(gradel);
		//label for book price			
		JLabel pricel = new JLabel("Price:");
		pricel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(pricel);
		//label for book stock			
		JLabel stockl = new JLabel("Stock/rent/all:");
		stockl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(stockl);		
		//**************************************************************************
		//item panel****************************************************************
		JPanel itemp=new JPanel();
		itemp.setSize(400, 240);
		itemp.setLocation(300,60);
		itemp.setBackground(Color.white);
		itemp.setLayout(new GridLayout(5,1));
		contp.add(itemp);		
		//book name
		item1 = new JLabel();
		item1.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item1);		
		//author
		item2 = new JLabel();
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item2);		
		//grade
		item3 = new JLabel();
		item3.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item3);		
		//price
		item4 = new JLabel();
		item4.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item4);
		//stock/rent/all
		item5=new JLabel();
		item5.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item5);
		//*************************************************************************
		//user button**************************************************************
		JButton user = new JButton("User");
		user.addActionListener(new UserListener());
		user.setBackground(new Color(135,206,235));
		user.setLocation(100,300);
		user.setSize(200,30);
		contp.add(user);
		//subscriber button
		JButton subscriber = new JButton("Subscriber");
		subscriber.addActionListener(new SubscriberListener());
		subscriber.setBackground(new Color(218,112,214));
		subscriber.setLocation(300,300);
		subscriber.setSize(400,30);
		contp.add(subscriber);
		
	}
	
	/**
	 *  A method for setting the book name 
	 *  @param bname  name of the book
	 */
	public void setName(String bname){
		name=bname;
		print();
	}
	
	/**
	 *  A method for displaying all
	 *  information of this book in item panel
	 */
	private void print(){
		result=b.query(name);
		item1.setText(result[1]);
		item2.setText(result[2]);
		item3.setText(result[4]);
		item4.setText(result[3] + " £");
		item5.setText(result[5] + "/" + result[6] + "/" + result[7]);
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
	
	//inner class to take action when user button is pressed
	class UserListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			userDia();	 
		}
	}
	
	/**
	 *  A method for building a dialog that get user
	 *  input about book number and paid money
	 */
	private void userDia(){
		user=new JDialog();
		user.setSize(400, 300);
		user.setTitle("Welcome!");
		user.setLocation(450,300);
		//content panel
		JPanel contentp=new JPanel();
		contentp.setLayout(null);
		contentp.setSize(400, 300);
		contentp.setBackground(new Color(245,222,125));
		user.add(contentp);
		//string buy label
		JLabel buy = new JLabel("Buy");
		buy.setSize(100, 50);
		buy.setLocation(50,25);
		buy.setHorizontalAlignment(SwingConstants.CENTER);
		contentp.add(buy);
		//get book number text field	
		numtf = new JTextField();
		numtf.setSize(100, 50);
		numtf.setLocation(150,25);
		contentp.add(numtf);
		//book name label
		JLabel bookname = new JLabel(name);
		bookname.setSize(100, 50);
		bookname.setLocation(250,25);
		bookname.setHorizontalAlignment(SwingConstants.CENTER);
		contentp.add(bookname);
		//pay label
		JLabel pay = new JLabel("Pay");
		pay.setSize(100, 50);
		pay.setLocation(50,100);
		pay.setHorizontalAlignment(SwingConstants.CENTER);
		contentp.add(pay);
		//get paid money text field
		montf = new JTextField();
		montf.setSize(100, 50);
		montf.setLocation(150,100);
		contentp.add(montf);
		//pound symbol text field
		JLabel pound= new JLabel("£");
		pound.setSize(100, 50);
		pound.setLocation(250,100);
		pound.setHorizontalAlignment(SwingConstants.CENTER);
		contentp.add(pound);
		//check button
		JButton check = new JButton("OK");
		check.addActionListener(new OkListener());
		check.setBackground(new Color(218,112,214));
		check.setLocation(150,200);
		check.setSize(100,50);
		contentp.add(check);
		
		user.setVisible(true);
		
	}
	
	//inner class to take action when OK button is pressed
	class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			int number=0;
			double money=0;
			boolean ifok=true;
			//check if book number is entered
			if(numtf.getText().length()==0){
				n=new NoticeUI("Please enter the number of book!");
				ifok=false;
			}else if(montf.getText().length()==0){      //check if money is entered
				n=new NoticeUI("Please pay the money!");
				ifok=false;
			}
			//check if book number is a number
			if(ifok){
				try{
					number=Integer.parseInt(numtf.getText());
				}catch(Exception e){
					n=new NoticeUI("Invalid number!");
					numtf.setText("");
					ifok=false;
				}
			}
			//check if the number is no less than one
			if(ifok&&(number<0)){
					n=new NoticeUI("Can't buy less than one book!!");
					numtf.setText("");
					ifok=false;
			}
			//check if the money is a number
			if(ifok){
				try{
					money=Double.parseDouble(montf.getText());
				}catch(Exception e){
					n=new NoticeUI("Invalid money!");
					montf.setText("");
					ifok=false;
				}
			}
			//check if the money is enough
			double price=Double.parseDouble(result[3]);
			if(ifok&&(money<((double)(number))*price)){	
				n=new NoticeUI("Money is not enough!");
				montf.setText("");
				ifok=false;
			}	
			//if all check is pass
			if(ifok){
				Customer cus=new Customer();
				if(cus.buyBook(0, name, number)){                 //buy book by cash
					user.setVisible(false);
					print();                                      //show
					double total=((double)(number))*price;        //total price
					DecimalFormat format = new DecimalFormat( "######.00 ");   //set the decimal format to avoid error
					n=new NoticeUI("Your change is " + "£ " + format.format(money-total));  //show change
				}
			}
		}
	}

	//inner class to take action when subscriber button is pressed
	class SubscriberListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			SubBBUI sub=new SubBBUI();
			sub.build(container);
			sub.setBookname(name);
			disappear();
		}
	}
	
}
