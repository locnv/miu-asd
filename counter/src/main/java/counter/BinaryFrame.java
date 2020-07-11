package counter;

import java.awt.Font;

public class BinaryFrame extends javax.swing.JFrame implements FrameObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8566229815454944506L;
	javax.swing.JLabel JLabelCount = new javax.swing.JLabel();

	public BinaryFrame() {
		getContentPane().setLayout(null);
		setSize(300, 200);
		setVisible(false);
		getContentPane().add(JLabelCount);
		JLabelCount.setFont(new Font("Dialog", Font.BOLD, 36));
		JLabelCount.setBounds(48, 48, 170, 86);
		setTitle("Binary Frame");
		setCount(0);

		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event) {
			Object object = event.getSource();
			if (object == BinaryFrame.this)
				TextFrame_WindowClosing(event);
		}
	}

	void TextFrame_WindowClosing(java.awt.event.WindowEvent event) {
		dispose(); // dispose of the Frame.
	}

	public void setCount(int count) {
		JLabelCount.setText(toBinary(count));
	}
	
	private String toBinary(int number) {
		return Integer.toBinaryString(number);
	}
}
