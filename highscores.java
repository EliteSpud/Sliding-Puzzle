import javax.swing.*;
public class highscores extends JFrame
{
	JPanel pnl = new JPanel(null);
	JTable tbl = new JTable(20,2);
	public void run()
	{
		this.setSize(400,500);
		this.setLocation(600,300);
		this.setTitle("Highscores");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createObjects();
		
		this.add(pnl);
		this.setVisible(true);
	}
	public void createObjects()
	{
		tbl.setSize(300,450);
		tbl.setLocation(0,0);
		tbl.setRowHeight(20);
		//tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		//tbl.getColumnModel().getColumn(1).setPreferredWidth(50);
		pnl.add(tbl);
	}
}