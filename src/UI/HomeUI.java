/**
 *  A class for home page interface
 *
 *  @author    Chunzi Wu,Jingzhi Liu
 *  @date	   12th May,2014
 */

package UI;

import javax.swing.*;

import Function.BookMan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUI extends JPanel{

	private int width=800;                          //width of this panel
	private int height=440;	                        //height of this panel
	private JFrame container;                       //frame of the UI
	private JTextField searchFrame;
	private BookMan b;
	private NoticeUI n;
	
	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;
		//set content panel attributes
		this.setLayout(null);                          //default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);
		
		b=new BookMan();
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
		//set background picture
		final ImageIcon image = new ImageIcon("src/Background.jpg");
		//content panel
		JPanel contp = new JPanel(){
	        public void paint(Graphics g){
	            g.clearRect(0, 0, getSize().width, getSize().height);
	            image.paintIcon(this, g, 0, 0);
	            paintComponents(g);
	        }
	    };
		contp.setSize(width, 440);
		contp.setLocation(0,60);
		contp.setBackground(Color.pink);
		contp.setLayout(null);
		this.add(contp);
		//registration button
		JButton createNew = new JButton(new ImageIcon("src/CreatMem.jpg"));
		createNew.addActionListener(new CreateListener());
		createNew.setLocation(40,115);
		createNew.setSize(175,200);
		contp.add(createNew);
		//book management button
		JButton manageBook = new JButton(new ImageIcon("src/manage book.jpg"));
		manageBook.addActionListener(new BookListener());
		manageBook.setLocation(245,115);
		manageBook.setSize(120,200);
		contp.add(manageBook);
		//member management button
		JButton manageMem = new JButton(new ImageIcon("src/manage member.jpg"));
		manageMem.addActionListener(new MenListener());
		manageMem.setLocation(370,115);
		manageMem.setSize(120,200);
		contp.add(manageMem);
		//show balance button
		JButton showBalance = new JButton(new ImageIcon("src/ShowBalance.jpg"));
		showBalance.addActionListener(new ShowListener());
		showBalance.setLocation(550,115);
		showBalance.setSize(150,275);
		contp.add(showBalance);
		//stock button
		JButton stock = new JButton(new ImageIcon("src/stock.jpg"));
		stock.addActionListener(new StockListener());
		stock.setLocation(50,340);
		stock.setSize(400,40);
		contp.add(stock);
		//search button
		JButton search = new JButton(new ImageIcon("src/search.jpg"));
		search.addActionListener(new SearchListener());
		search.setLocation(610,40);
		search.setSize(80,30);
		contp.add(search);
		//search text field to get a book name
		searchFrame = new JTextField();
		searchFrame.setLocation(40,40);
		searchFrame.setSize(570,30);
		contp.add(searchFrame);
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

	//inner class to take action when create new member button is pressed
	class CreateListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			RegUI r=new RegUI();
			r.build(container);
			disappear();
		}
	}
	
	//inner class to take action when book management button is pressed
	class BookListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			BookmanUI b=new BookmanUI();
			b.build(container);
			disappear();
		}
	}
	
	//inner class to take action when customer management button is pressed
	class MenListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			CustmanUI m=new CustmanUI();
			m.build(container);
			disappear();
		}
	}
	
	//inner class to take action when show balance button is pressed
	class ShowListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			BalanceUI bal=new BalanceUI();
			bal.build(container);
			disappear();
		}
	}
	
	//inner class to take action when stock button is pressed
	class StockListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			StockUI s=new StockUI();
			s.build(container);
			disappear();
		}
	}
	
	//inner class to take action when search button is pressed
	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//check if input something
			if(searchFrame.getText().length()==0){
				n=new NoticeUI("Please enter the book name!");
			}else if(b.query(searchFrame.getText())==null){      //check if store has this book
				n=new NoticeUI("The store does not have this book!");
			}else{          
				BookUI book=new BookUI();                      
				book.build(container);   
				book.setName(searchFrame.getText()); 
				disappear();
			}
		}
	}
}
