package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gamePackage.GameWindow;
import gui.FrameWithFlash;
import gui.LoginScreen;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import communicationPackage.ReaderThread;

public class Main {

	private static Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;
	private static FrameWithFlash login;
	private static ReaderThread r;
	
	public Main(){
		try{
			socket = new Socket("localhost", 12345);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			r =new ReaderThread(in);
			r.start();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
	}
	
	public static void sendMessage(String message){
		out.println(message);
		out.flush();
	}
	public static void main(String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Main();
			login = new FrameWithFlash();

        } catch (Exception ex) {
            //DO NOTHING
        }
        
	}
	
	
	
	public static void shutDown(){
		
		ThreadGroup rootGroup = Thread.currentThread( ).getThreadGroup( );
		ThreadGroup parentGroup;
		while ( ( parentGroup = rootGroup.getParent() ) != null ) {
		    rootGroup = parentGroup;
		}
		
		Thread[] threads = new Thread[ rootGroup.activeCount() ];
		while ( rootGroup.enumerate( threads, true ) == threads.length ) {
		    threads = new Thread[ threads.length * 2 ];
		}
		
		for(Thread thread: threads){
			//System.out.println(thread.toString());
			thread.interrupt();
		}
		

	}
	
}
