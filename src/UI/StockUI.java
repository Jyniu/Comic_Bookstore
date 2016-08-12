/**
 *  A class for stock
 *
 *  @author    Chunzi Wu,Jingzhi Liu
 *  @date	   14th May,2014
 */

package UI;

import javax.swing.*;

import Function.Stock;

import java.awt.*;
import java.awt.event.*;

public class StockUI extends JPanel{

	private int width=800;                                         //width of this panel
	private int height=440;	                                       //height of this panel
	private JFrame container;                                      //frame of the UI
	private NoticeUI n;
	private JTextField nametf,authtf,gradetf,pricetf,numtf;
	private Stock stock;
	
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
		
		stock=new Stock();
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
		//label panel**************************************************************************
		JPanel labelp = new JPanel();
		labelp.setSize(180,125);
		labelp.setBackground(new Color(245,222,125));
		labelp.setLocation(180,150);
		labelp.setLayout(new GridLayout(5,1));
		contp.add(labelp);
		//book name label
		JLabel namel = new JLabel("book name");
		namel.setHorizontalAlignment(SwingConstants.CENTER);      //let the text in the center
		labelp.add(namel);
		//author label
		JLabel authorl = new JLabel("author");
		authorl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(authorl);
		//grade label
		JLabel gradel = new JLabel("grade");
		gradel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(gradel);
		//price label
		JLabel pricel = new JLabel("price");
		pricel.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(pricel);
		//number label
		JLabel numl = new JLabel("number");
		numl.setHorizontalAlignment(SwingConstants.CENTER);
		labelp.add(numl);
		//******************************************************************************
		//text field panel**************************************************************
		JPanel textf = new JPanel();
		textf.setLayout(new GridLayout(5,1));
		textf.setLocation(360,150);
		textf.setSize(240,125);
		contp.add(textf);
		//text field get book name
		nametf = new JTextField();
		textf.add(nametf);
		//text field get book author
		authtf = new JTextField();
		textf.add(authtf);
		//text field get book grade
		gradetf = new JTextField();
		textf.add(gradetf);
		//text field get book price
		pricetf = new JTextField();
		textf.add(pricetf);
		//text field get book number
		numtf = new JTextField();
		textf.add(numtf);
		//**************************************************************************
		//submit button*************************************************************
		JButton sub = new JButton("Submit");
		sub.addActionListener(new SubmitListener());
		sub.setSize(150,40);
		sub.setBackground(new Color(218,112,214));
		sub.setLocation(450,300);
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
			String author=authtf.getText();
			String grade=gradetf.getText();
			String price=pricetf.getText();
			String num=numtf.getText();
			//if input book name
			if(name.length()==0){
				n=new NoticeUI("Please enter the book name!");
			}else if(stock.getName(name)){               //if this book is a new book 
				if(author.length()!=0&&grade.length()!=0&&price.length()!=0&&num.length()!=0){
					if(stock.getPrice(price)&&stock.getNum(num)&&stock.getGrade(grade)){
						stock.getAuthor(author);
						stock.getId();
						stock.addNew();
						nametf.setText("");	
						authtf.setText("");	
						gradetf.setText("");
						pricetf.setText("");
						numtf.setText("");
					}
				}else{
					n=new NoticeUI("Please complete the information!");
				}
			}else{                                     //if this book is a old book 
				if(num.length()!=0){
					if(stock.getNum(num)){
						stock.addOld();
						nametf.setText("");	
						authtf.setText("");	
						gradetf.setText("");
						pricetf.setText("");
						numtf.setText("");
					}
				}else{
					n=new NoticeUI("Please enter the book number");
				}
			}
		}
	}
}