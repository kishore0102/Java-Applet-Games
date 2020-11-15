import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class chatapp_client{
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
	JTextField nameField;
	JButton sendButton;
	JButton nameButton;

	public static void main(String[] args){
		chatapp_client client=new chatapp_client();
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
		
		nameField=new JTextField(10);
		nameField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(nameField.getText().isEmpty()){
				
			}else{ 
				name=nameField.getText();
				nameField.setEditable(false);
				nameButton.setEnabled(false);
				txtField.setEditable(true);
				sendButton.setEnabled(true);
				setUpNetwork();
				Thread myThread=new Thread(new IncomingReader());
				myThread.start();
				txtField.requestFocus();
			}
			}}
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
		nameButton=new JButton("Set Name");
		nameButton.addActionListener(new nameButtonListener());

		mainPanel.add(nameField);
		mainPanel.add(nameButton);
		mainPanel.add(scroller);
		mainPanel.add(txtField);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		
		frame.setSize(300,400);
		frame.setVisible(true);
	}
	public class nameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(nameField.getText().isEmpty()){
				
			}else{ 
				name=nameField.getText();
				nameField.setEditable(false);
				nameButton.setEnabled(false);
				txtField.setEditable(true);
				sendButton.setEnabled(true);
				setUpNetwork();
				Thread myThread=new Thread(new IncomingReader());
				myThread.start();
			}
		}
	}
	public class sendButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ex){
			send();
		}
	}
	private void setUpNetwork(){
		try{
			sock=new Socket("qw7.0.0.1",5000);
			isReader=new InputStreamReader(sock.getInputStream());
			reader=new BufferedReader(isReader);
			writer=new PrintWriter(sock.getOutputStream());
			System.out.println("Connected to Server...\n");
			txtArea.append("Connected to Server...\n\n");
		}catch(Exception e){
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
}