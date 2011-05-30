// Jay Sheridan
// 7/4/2002

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;
import java.util.Date;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Dice extends Applet implements ActionListener
{
	private Button rollButton;
	private TextField fieldW, fieldX, fieldY, fieldZ, fieldAns;
	private TextArea rollList;
	private Label opening, D, total, rolls, plus, times;
	private Random rand = new Random(System.currentTimeMillis());
	
	public static void main(String[] args)
	{
		Frame f = new CloseableFrame();

		f.setTitle("Dice");
		f.setLayout(new FlowLayout());
		f.add(new Dice());
		f.pack();
		f.show();
	}
	
	public void init()
	{
		buildObjects();
		buildLayout();
		rollButton.addActionListener(this);
	}

	public Dice()
	{
		init();
	}
	
	private void buildObjects()
	{
		// Buttons
		rollButton = new Button("Roll");
		
		// Labels
		// (w D x + y) * z
		opening = new Label("(");
		D = new Label("d");
		plus = new Label("+");
		times = new Label(")   *");
		total = new Label("=  Total:");
		rolls = new Label("List of rolls:");
		
		
		// Text fields/areas
		fieldW = new TextField("1",3);
		fieldX = new TextField("6",3);
		fieldY = new TextField("0",3);
		fieldZ = new TextField("1",3);
		fieldAns = new TextField(5);
		fieldAns.setEditable(false);
		rollList = new TextArea("", 6, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
		rollList.setEditable(false);
		
	}
	
	private void buildLayout()
	{
		Panel toppanel = new Panel(new FlowLayout());
		Panel midpanel = new Panel(new FlowLayout());
		Panel bottompanel = new Panel(new BorderLayout());
		
		// (w D x + y) * z
		toppanel.add(opening);
		toppanel.add(fieldW);
		toppanel.add(D);
		toppanel.add(fieldX);
		toppanel.add(plus);
		toppanel.add(fieldY);
		toppanel.add(times);
		toppanel.add(fieldZ);
		toppanel.add(total);
		toppanel.add(fieldAns);
		
		midpanel.add(rollButton);
		
		bottompanel.add(rolls, "North");
		bottompanel.add(rollList, "South");
		
		setLayout(new BorderLayout());
		add(toppanel, "North");
		add(midpanel, "Center");
		add(bottompanel, "South");
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if( evt.getActionCommand() == "Roll")
		{ onRoll(); }
		else
		{}
	}
	
	public void onRoll()
	{
		// (w D x + y) * z
		int WData=0, XData=0, YData=0, ZData=0;
		int totRoll, curRoll;
		StringBuffer rollListData = new StringBuffer("");
		try
		{
			WData = Integer.parseInt(fieldW.getText());
			XData = Integer.parseInt(fieldX.getText());
			YData = Integer.parseInt(fieldY.getText());
			ZData = Integer.parseInt(fieldZ.getText());
		}
		catch(NumberFormatException e)
		{
			rollList.setText("Invalid data.");
			return;
		}
		totRoll=0;
		rollList.setText("<");
		for (int i=0; i<WData; i++)
		{
			curRoll = (int)(rand.nextDouble()*XData) + 1;
			totRoll = totRoll + curRoll;
			rollListData.append("<" + Integer.toString(curRoll) + "> ");
		}
		rollList.setText(rollListData.toString());
		totRoll = (totRoll + YData) * ZData;
		fieldAns.setText(Integer.toString(totRoll));
	}
}
