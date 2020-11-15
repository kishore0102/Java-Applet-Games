import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class chatapp_client2{
	JFrame frame;
	JPanel mainPanel;
	JTextArea txtArea;
	JTextField txtField;
	Socket sock;
	InputStreamReader isReader;
	BufferedReader reader;
	PrintWriter writer;
	String outgoing_msg;
	String name;
	String incoming_msg;
	JTextField nameField,ipField,portField;
	JButton sendButton;
	JButton nameButton;
	JButton endButton;

	public static void main(String[] args){
		chatapp_client2 client=new chatapp_client2();
		client.gui();
	}
	public void gui(){
		frame=new JFrame("ChatApp");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		mainPanel=new JPanel();
		
		txtField=new JTextField(20);
		txtField.setEditable(false);
		txtField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					send();
			}}
		);

		ipField=new JTextField(10);
		ipField.setEditable(true);
		ipField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(ipField.getText().isEmpty()&&portField.getText().isEmpty()&&nameField.getText().isEmpty()){
				
					}else{ 
						makeConnection();
					}
				}
			}
		);

		portField=new JTextField(10);
		portField.setEditable(true);
		portField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(ipField.getText().isEmpty()&&portField.getText().isEmpty()&&nameField.getText().isEmpty()){
				
					}else{ 
						makeConnection();
					}
				}
			}
		);
		
		nameField=new JTextField(10);
		nameField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(ipField.getText().isEmpty()&&portField.getText().isEmpty()&&nameField.getText().isEmpty()){
				
					}else{ 
						makeConnection();
					}
				}
			}
		);
		txtArea=new JTextArea(15,25);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setEditable(false);
		JScrollPane scroller=new JScrollPane(txtArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		sendButton=new JButton("Send");
		sendButton.addActionListener(new sendButtonListener());
		sendButton.setEnabled(false);
		nameButton=new JButton("Connect");
		nameButton.addActionListener(new nameButtonListener());

		endButton=new JButton("End Connection");
		endButton.addActionListener(new endButtonListener());
		endButton.setEnabled(false);

		mainPanel.add(ipField);
		mainPanel.add(portField);
		mainPanel.add(nameField);
		mainPanel.add(nameButton);
		mainPanel.add(scroller);
		mainPanel.add(txtField);
		mainPanel.add(sendButton);
		mainPanel.add(endButton);
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		
		frame.setSize(300,400);
		frame.setVisible(true);
	}
	public class nameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(ipField.getText().isEmpty()&&portField.getText().isEmpty()&&nameField.getText().isEmpty()){
				
			}else{ 
				makeConnection();
			}
		}
	}
	public class endButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			txtArea.append("Connection Terminated\n\n");
			Reconnect();
		}
	}
	
	public class sendButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ex){
			send();
		}
	}
	private void setUpNetwork(String ipaddr,String port){
		try{
			sock=new Socket(ipaddr,Integer.parseInt(port));
			isReader=new InputStreamReader(sock.getInputStream());
			reader=new BufferedReader(isReader);
			writer=new PrintWriter(sock.getOutputStream());
			System.out.println("Connected to Server...\n");
			txtArea.append("Connected to Server...\n\n");
			endButton.setEnabled(true);
		}catch(Exception e){
			txtArea.append("Error, Try again...\n\n");
			Reconnect();
			e.printStackTrace();
		}
	}
	public class IncomingReader implements Runnable{
		public void run(){
			String msg;
			try{
				while((msg=reader.readLine())!=null){
					System.out.println("reading... "+msg);
					incoming_msg=decodeMsg(msg);
					txtArea.append(incoming_msg);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}//inner class
	public String encodeMsg(String message){
		String msg;
		msg=name+" : "+message;
		return msg;
	}
	public String decodeMsg(String message){
		String[] msg;
		String inmsg;
		msg=message.split(" : ");
		if(msg[0].equals(name)){
			msg[0]="Me";
		}else{

		}
		inmsg=msg[0]+" : "+msg[1]+"\n";
		return inmsg;
	}
	public void send(){
		try{
			outgoing_msg=encodeMsg(txtField.getText());
			writer.println(outgoing_msg);
			writer.flush();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		txtField.setText("");
		txtField.requestFocus();
	}
	public void makeConnection(){
		String ipaddr,port;
		ipaddr=ipField.getText();
		port=portField.getText();
		name=nameField.getText();
		ipField.setEditable(false);
		portField.setEditable(false);
		nameField.setEditable(false);
		nameButton.setEnabled(false);
		txtField.setEditable(true);
		sendButton.setEnabled(true);
		setUpNetwork(ipaddr,port);
		Thread myThread=new Thread(new IncomingReader());
		myThread.start();
		txtField.requestFocus();
	}
	public void Reconnect(){
		ipField.setEditable(true);
		portField.setEditable(true);
		nameField.setEditable(true);
		ipField.setText("");
		portField.setText("");
		nameField.setText("");
		nameButton.setEnabled(true);
		txtField.setEditable(false);
		sendButton.setEnabled(false);
		endButton.setEnabled(false);
	}
}