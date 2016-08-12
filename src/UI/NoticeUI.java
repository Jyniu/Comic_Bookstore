/**
 *  A class for giving notice information
 *
 *  @author    Anni Piao
 *  @date	   17th May,2014
 */

package UI;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NoticeUI extends JDialog{
	
	/**
	 *  Constructor
	 *  @param str the message 
	 */
	public NoticeUI(String str){
		//set parameters
		this.setSize(400, 100);
		this.setTitle("Warning");
		this.setLocation(500,300);
		//content panel
		JPanel contp=new JPanel();
		contp.setLayout(null);
		contp.setSize(400, 100);
		this.add(contp);
		//message label
		JLabel l=new JLabel(str);
		l.setLocation(0, 0);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("Default",Font.PLAIN,20);
		l.setFont(font);
		l.setSize(400, 80);
		contp.add(l);
		//set visible
		this.setVisible(true);
	}
}
