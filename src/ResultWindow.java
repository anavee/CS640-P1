import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class to output text results to a window
 */
public class ResultWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JScrollPane scroller;
	private JTextArea output;
	private JButton pause;
	private boolean isPaused = false;
	private boolean writeToFile = false;
	private BufferedWriter writer = null;

	/**
	 * Constructor
	 */
	public ResultWindow(boolean writeToDisk) {
		initComponents();
		
		writeToFile = writeToDisk;
		if (writeToDisk) {
			try {
				writer = new BufferedWriter(new FileWriter("resultOutput.txt", false));
				writer.write("======== Start Output ========");
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
		scroller = new JScrollPane();
		output = new JTextArea();
		pause = new JButton("Pause");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		pause.setPreferredSize(new Dimension(200, 80));
		pause.addActionListener(this);
		output.setColumns(20);
		output.setRows(5);
		scroller.setViewportView(output);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup().addComponent(pause, 
								GroupLayout.PREFERRED_SIZE, 90, 
								GroupLayout.PREFERRED_SIZE)).addGroup(
						layout.createSequentialGroup().addComponent(scroller,
								GroupLayout.PREFERRED_SIZE, 800,
								GroupLayout.PREFERRED_SIZE))));

		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.LEADING,
				layout.createParallelGroup().addComponent(pause,
						GroupLayout.PREFERRED_SIZE, 25,
						GroupLayout.PREFERRED_SIZE)).addGroup(
				layout.createSequentialGroup().addComponent(scroller,
						GroupLayout.PREFERRED_SIZE, 400,
						GroupLayout.PREFERRED_SIZE)));

		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		this.isPaused = !this.isPaused;
		if (this.isPaused)
			this.pause.setText("Continue");
		else
			this.pause.setText("Pause");
	}

	/**
	 * Appends text to the results window
	 * 
	 * @param text - the text to output
	 */
	public void updateText(String text) {
		if (!isPaused) {
			output.append(text + "\n");
			output.setCaretPosition(output.getDocument().getLength());
		}
		
		if (writeToFile)
			this.outputToFile(text);
	}
	
	private void outputToFile(String text) {
		try {
			writer = new BufferedWriter(new FileWriter("resultOutput.txt", true));
			writer.write(text);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}
}