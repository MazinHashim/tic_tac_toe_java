import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Frist extends JFrame implements ActionListener
{
	JPanel face;
	JPanel south,label;
	JButton New,resu,exit;
	JLabel lab,hel;
	GameFrame G = new GameFrame();
	public Frist()
	{
		super("XO Game");
		setLocation(270,20);
		setSize(700,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));

		label = new JPanel(new BorderLayout());
		lab = new JLabel(new ImageIcon(getClass().getResource("XO.jpg")));

		hel = new JLabel("Play XO Game With Me!");
		hel.setFont(new Font(Font.SERIF,Font.CENTER_BASELINE,30));
		hel.setForeground(Color.cyan);
		label.setBackground(Color.blue);
		label.add(lab,BorderLayout.WEST);
		label.add(hel,BorderLayout.EAST);

		face = new JPanel(new BorderLayout());
		face.setBackground(Color.blue);
		face.setBorder(new EmptyBorder(10,10,100,10));
		add(face);

		south = new JPanel(new GridLayout(3,1,20,20));
		south.setBackground(Color.blue);
		south.setBorder(new EmptyBorder(10,250,10,250));
		face.add(south,BorderLayout.SOUTH);
		face.add(label,BorderLayout.NORTH);

		New = new JButton("New Game");
		New.setBackground(Color.white.darker());
		New.addActionListener(this);

		resu = new JButton("Resume");
		if(G.countx != 0 || G.counto != 0)
		resu.setEnabled(true);
		else
		resu.setEnabled(false);
		resu.setBackground(Color.white.darker());
		resu.addActionListener(this);

		exit = new JButton("Exit");
		exit.setBackground(Color.white.darker());
		exit.addActionListener(this);
		south.add(New);
		south.add(resu);
		south.add(exit);
	}
	public static void main(String[] args)
	{
		new Frist().setVisible(true);
	}
	public void actionPerformed(ActionEvent arg)
	{
		if(arg.getSource() == New)
		{
			dispose();
			G.countx = 0;
			G.counto = 0;
			G.setVisible(true);
			G.area.setText("Player X: "+G.countx+" \nPlayer O: "+G.counto+" ");
			JOptionPane.showMessageDialog(this,"Wellcome!\nStart game");
		}
		if(arg.getSource() == resu)
		{
			dispose();
			G.setVisible(true);
		}
		if(arg.getSource() == exit)
		{
			System.exit(0);
		}
	}
	}