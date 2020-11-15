import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyServer{
	JFrame frame;
	JTextArea txtArea;
	JButton connectButton;
	ArrayList incoming_stream;
	JScrollPane scroller;

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
		public void run(){
			incoming_stream=new ArrayList();
		try{
			ServerSocket ss1=new ServerSocket(5000);
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
		MyServer server=new MyServer();
		server.gui();
	}
	public void gui(){
		frame=new JFrame("ChatApp Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

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

		frame.getContentPane().add(BorderLayout.NORTH,connectButton);
		frame.getContentPane().add(BorderLayout.CENTER,scroller);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	public class connectButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			connectButton.setEnabled(false);
			Thread setnw=new Thread(new setUpNetwork());
			setnw.start();
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