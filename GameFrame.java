import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
public class GameFrame extends JFrame implements ActionListener
{
	JPanel BP;
	JPanel OP;
	JSplitPane splitPane;
	String Player = "X";
	Font fo = new Font(Font.SERIF,Font.BOLD,100);
	JButton Board[][] = new JButton[3][3];
	JTextArea area;
	JButton clear,home,reset;
	public static int countx = 0;
	public static int counto = 0;

	public GameFrame()
	{
		super("XO Game");
		BP = new JPanel();
		OP = new JPanel();

		setLocation(270,20);
		setSize(700,700);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));

		BP.setBackground(Color.blue);
		BP.setLayout(new GridLayout(3,3,4,4));
		BP.setBorder(new EmptyBorder(10,10,10,10));

		OP.setBackground(Color.blue);
		OP.setLayout(new GridLayout(10,10,20,20));
		OP.setBorder(new EmptyBorder(20,20,20,20));

		clear = new JButton("Play Again");
		reset = new JButton("Reset Wons");
		home = new JButton("Home");

		area = new JTextArea("Player X: "+countx+" \nPlayer O: "+counto+" ");
		area.setFont(new Font(Font.DIALOG_INPUT,Font.ITALIC,16));
		area.setEnabled(false);
		area.setBackground(Color.blue.darker());
		area.setForeground(Color.blue);

		clear.addActionListener(this);
		home.addActionListener(this);
		reset.addActionListener(this);

		InitializeBoard();
		OP.add(clear);
		OP.add(reset);
		OP.add(home);
		OP.add(area);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, BP,OP);
		splitPane.setEnabled(false);
		splitPane.setDividerLocation(700);
		add(splitPane);
	}
	public void InitializeBoard()
	{
		int j = 1;
		for(int i=0; i<Board.length; i++)
		{
			for(int k=0; k<Board[i].length; k++)
			{
				Board[i][k] = new JButton(i+j+k+"");
				Board[i][k].setFont(fo);
				Board[i][k].setBackground(Color.blue.darker());
				Board[i][k].setForeground(Color.white);
				BP.add(Board[i][k]);
				Board[i][k].addActionListener(this);
			}
			j+=2;
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg)
	{
		for(int i=0; i<Board.length; i++)
		{
			for(int k=0; k<Board[i].length; k++)
			{
				if(arg.getSource() == Board[i][k])
				{
					if(!Board[i][k].getText().equals("X") && !Board[i][k].getText().equals("O"))
					{	Board[i][k].setText(Player);
						if (checkForWin()) {
							if(Player.equals("X"))
							countx+=1;
							if(Player.equals("O"))
							counto+=1;
							splitPane.setDividerLocation(500);
							Enabled();
							JOptionPane.showMessageDialog(this,"The Player \" "+Player+" \" is winner! Congrats!");
							area.setText("Player X: "+countx+" \nPlayer O: "+counto+" ");
						}
						else if(Overfilling())
						{
							splitPane.setDividerLocation(500);
							Enabled();
							JOptionPane.showMessageDialog(this,"We have a drawn");
							area.setText("Player X: "+countx+" \nPlayer O: "+counto+" ");
						}
						ChangePlayer();
					}
				}
			}
		}
		if(arg.getSource() == home)
		{
			dispose();
			new Frist().setVisible(true);
		}
		if(arg.getSource() == clear)
		{
			setVisible(false);
			new GameFrame().setVisible(true);
		}
		if(arg.getSource() == reset)
		{
			countx = 0;
			counto = 0;
			area.setText("Player X: "+countx+" \nPlayer O: "+counto+" ");
		}
	}
	public void Enabled()
	{
		for(int i=0; i<Board.length; i++)
		{
			for(int k=0; k<Board[i].length; k++)
			{
				Board[i][k].setEnabled(false);
			}
		}
	}

	public void ChangePlayer()
	{
		if(Player == "X")
		Player = "O";
		else
		Player = "X";
	}
	private boolean Overfilling(){
		boolean full= true;
		for(int i=0; i<Board.length; i++){
			for(int k=0; k<Board[i].length; k++){
				if((!Board[i][k].getText().equals("X")) && (!Board[i][k].getText().equals("O")))
				full = false;
			}
		}
		return full;
	}

	private boolean checkForWin(){
		  return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}
	private boolean checkRowsForWin() {
		for (int i = 0; i < Board.length; i++) {
			if (Board[i][0].getText().equals(Board[i][1].getText()) && Board[i][1].getText().equals(Board[i][2].getText()))
			return true;
		}
		return false;
	}
	private boolean checkColumnsForWin() {
		for (int i = 0; i < Board.length; i++) {
			if (Board[0][i].getText().equals(Board[1][i].getText()) && Board[1][i].getText().equals(Board[2][i].getText()))
			return true;
		}
		return false;
	}
	private boolean checkDiagonalsForWin() {
			return ((Board[0][0].getText().equals(Board[1][1].getText()) && Board[1][1].getText().equals(Board[2][2].getText())) ||
					(Board[2][0].getText().equals(Board[1][1].getText()) && Board[1][1].getText().equals(Board[0][2].getText())));
	}
}