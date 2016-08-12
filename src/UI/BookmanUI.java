/**
 *  A class for manage book information
 *
 *  @author    Chunzi Wu,Jingzhi Liu
 *  @date	   16th May,2014
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Function.BookMan;

public class BookmanUI extends JPanel{

	private int width=800;                                     //width of this panel
	private int height=440;	                                   //height of this panel
	private JFrame container;                                  //frame of the UI
	private NoticeUI n;
	private JTextField searchFrame,gradetf,pricetf;            
	private JLabel item1,item2,item5;
	private BookMan b;
	private String[] result;

	/**
	 *  A method for building the frame,head panel and content panel
	 *  @param contf  JFrame of the UI
	 */
	public void build(JFrame contf){
		container=contf;                                      //get frame
		//set content panel attributes
		this.setLayout(null);                                 //default the layout
		this.setBackground(Color.white);
		this.setSize(width,height);
		this.setLocation(0, 0);
		contf.getContentPane().add(this);
		
		b=new BookMan();
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
		//*******************************************************************************
		//set search button 
		JButton search = new JButton(new ImageIcon("src/search.jpg"));
		search.addActionListener(new SearchListener());
		search.setLocation(520,30);
		search.setSize(80,30);
		contp.add(search);
		//set search text field to get book name need to search
		searchFrame = new JTextField();
		searchFrame.setLocation(120,30);
		searchFrame.setSize(400,30);
		contp.add(searchFrame);
		//********************************************************************************
		//label panel
		JPanel labelp=new JPanel();
		labelp.setSize(200, 240);
		labelp.setLocation(100,80);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLayout(new GridLayout(5,1));
		contp.add(labelp);
		//*********************************************************************************
		//label for book name
		JLabel namel = new JLabel("Book name:");
		namel.setHorizontalAlignment(SwingConstants.CENTER);       //set the text in the center
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
		//label for book stock condition
		JLabel stockl = new JLabel("Stock/rent/all:");
		stockl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(stockl);
		//*******************************************************************************
		//item panel
		JPanel itemp=new JPanel();
		itemp.setSize(400, 240);
		itemp.setLocation(300,80);
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
		//grade that could be change by the store staff
		gradetf = new JTextField();
		itemp.add(gradetf);
		//price that could be change by the store staff
		pricetf = new JTextField();
		itemp.add(pricetf);
		//stock condition
		item5=new JLabel();
		item5.setHorizontalAlignment(SwingConstants.CENTER);
		itemp.add(item5);
		//*****************************************************************************
		//save button
		JButton save = new JButton("Save");
		save.addActionListener(new SaveListener());
		save.setBackground(new Color(218,112,214));
		save.setSize(150,30);
		save.setLocation(450,330);
		contp.add(save);
	}
	
	//inner class to take action when search button is pressed
	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(searchFrame.getText().length()!=0){            //check if user has input something
				result=b.query(searchFrame.getText());        //check if the book is on the list of the store
				if(result==null){
					n=new NoticeUI("The book is not on the list of our store!");
				}else{                                        //show information
					item1.setText(result[1]);
					item2.setText(result[2]);
					gradetf.setText(result[4]);
					pricetf.setText(result[3]);
					item5.setText(result[5] + "/" + result[6] + "/" + result[7]);
				}
			}else{
				n=new NoticeUI("Please enter the book name!");
			}
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
			h.build(container);                  //to home page
			disappear();
		}

		/**
		 *  A method for setting action if log out button is pressed
		 */
		public void loginAct() {
			LoginUI l=new LoginUI();
			l.build(container);                  //to login page
			disappear();
		}
	}
	
	//inner class to take action when save button is pressed
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			int id=0;
			boolean ifsave=true;
			//check if user has search some one first
			try{
				id=Integer.parseInt(result[0]);
			}catch(Exception e){
				n=new NoticeUI("Invalid save!");
				ifsave=false;
			}
			if(ifsave){
				if(!gradetf.getText().equals(result[4])){                    //check if grade is change, 
					b.chanGrade(id, gradetf.getText());                      //and save it if it is.
				}
				if(!pricetf.getText().equals(result[3])){                    //check if price is change,
					b.chanPrice(id, pricetf.getText());                      //and save it if it is.
				}
				//clear text field
				item1.setText("");
				item2.setText("");
				gradetf.setText("");
				pricetf.setText("");
				item5.setText("");
			}
		}
	}
}
