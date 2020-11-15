import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyServer2{
	JFrame frame;
	JTextArea txtArea;
	JButton connectButton;
	JTextField txtField;
	ArrayList incoming_stream;
	JScrollPane scroller;
	JPanel mainPanel;

	public class ClientHandler implements Runnable{
		Socket s;
		InputStreamReader isR;
		BufferedReader reader;
		public ClientHandler(Socket clientSocket){
			try{
				s=clientSocket;
				isR=new InputStreamReader(s.getInputStream());
				reader=new BufferedReader(isR);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void run(){
			String msg;
			try{
				while((msg=reader.readLine())!=null){
					txtArea.append(msg+"\n");
					tellEveryone(msg);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}//inner class

	public class setUpNetwork implements Runnable{
		int portno;
		public setUpNetwork(int p){
			portno=p;
		}
		public void run(){
			incoming_stream=new ArrayList();
		try{
			ServerSocket ss1=new ServerSocket(portno);
			txtArea.append("Checking port "+portno+"\n");
			while(true){
				Socket s1=ss1.accept();
				PrintWriter writer=new PrintWriter(s1.getOutputStream());
				incoming_stream.add(writer);
				txtArea.append("Connected to "+s1.getInetAddress().getHostName()+"\n");
				Thread myThread=new Thread(new ClientHandler(s1));
				myThread.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}//inner class

	public static void main(String[] args){
		MyServer2 server=new MyServer2();
		server.gui();
	}
	public void gui(){
		frame=new JFrame("ChatApp Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		mainPanel=new JPanel();

		txtField=new JTextField(7);
		txtField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					startConnection();
			}}
		);

		txtArea=new JTextArea(15,50);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setEditable(false);
		scroller=new JScrollPane(txtArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		txtArea.append("waiting"+"\n");

		connectButton=new JButton("Switch ON Server");
		connectButton.addActionListener(new connectButtonListener());

		mainPanel.add(txtField);
		mainPanel.add(connectButton);
		frame.getContentPane().add(BorderLayout.NORTH,mainPanel);
		frame.getContentPane().add(BorderLayout.CENTER,scroller);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	public class connectButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			startConnection();
		}
	}
	public void startConnection(){
		String port;
		int portno;
		if((port=txtField.getText())!=null){
			portno=Integer.parseInt(port);
				if((portno>4999)&&(portno<20000)){
				connectButton.setEnabled(false);
				txtField.setEditable(false);
				Thread setnw=new Thread(new setUpNetwork(portno));
				setnw.start();
			}
		}
	}
	public void tellEveryone(String msg){
		Iterator it=incoming_stream.iterator();
		while(it.hasNext()){
			try{
				PrintWriter writer=(PrintWriter) it.next();
				writer.println(msg);
				writer.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	}//outer class