package it.internalframe;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.login.ChangePassword;
import it.login.UserLogin;

import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;

public class UserHome extends JFrame {

	JDesktopPane jdpDesktop;
	static int openFrameCount = 0;
	public UserHome() {
		super("Gestione Magazzino");
		// Make the main window positioned as 50 pixels from each edge of the
		// screen.
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2,
				screenSize.height - inset * 2);
		// Add a Window Exit Listener
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// Create and Set up the GUI.
		jdpDesktop = new JDesktopPane();
		// A specialized layered pane to be used with JInternalFrames
		setContentPane(jdpDesktop);
		setJMenuBar(createMenuBar());
		// Make dragging faster by setting drag mode to Outline
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	
	
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Frame");
		menu.setMnemonic(KeyEvent.VK_N);
		JMenuItem menuItem = new JMenuItem("New IFrame");
		menuItem.setMnemonic(KeyEvent.VK_N);
		JMenu mnNewMenu = new JMenu("File");
    	menuBar.add(mnNewMenu);
    	JMenuItem menuItem2 = new JMenuItem("Logout");
    	mnNewMenu.add(menuItem2);
    	JMenuItem menuItem3 = new JMenuItem("Cambio Password");
    	mnNewMenu.add(menuItem3);
    	JMenu mnNewMenu1 = new JMenu("Prodotti");
    	menuBar.add(mnNewMenu1);
    	JMenuItem menuItem4 = new JMenuItem("Anagrafica");
    	mnNewMenu1.add(menuItem4);    	
    	JMenuItem menuItem5 = new JMenuItem("Giacenza");
    	mnNewMenu1.add(menuItem5); 
    	
    	menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ChangePassword bo = new ChangePassword(UserLogin.userSes());
                bo.setTitle("Cambio Password");
                bo.setVisible(true);              
            }
        });
    	
    	
    	menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(menuItem, "Sei Sicuro?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    UserLogin obj = new UserLogin();
                    obj.setTitle("Login");
                    obj.setVisible(true);
                }                
            }
        });
		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				createFrame();
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}
	protected void createFrame() {
		MyInternalFrame frame = new MyInternalFrame();
		frame.setVisible(true);
		// Every JInternalFrame must be added to content pane using JDesktopPane
		jdpDesktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
	}
	public static void main(String[] args) {
		UserHome frame = new UserHome();
		frame.setVisible(true);
	}
	class MyInternalFrame extends JInternalFrame {

		static final int xPosition = 30, yPosition = 30;
		public MyInternalFrame() {
			super("IFrame #" + (++openFrameCount), true, // resizable
					true, // closable
					true, // maximizable
					true);// iconifiable
			setSize(300, 300);
			// Set the window's location.
			setLocation(xPosition * openFrameCount, yPosition
					* openFrameCount);
		}
	}
}