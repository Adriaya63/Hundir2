package packVista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class PantallaFinal extends JFrame {
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaFinal frame = new PantallaFinal(2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaFinal(int g) {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getLblNewLabel(), BorderLayout.CENTER);
		if(g==2) {lblNewLabel.setText("JUGADOR HA GANADO");}
		else if(g==1) {lblNewLabel.setText("CPU HA GANADO");}
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("JUGADOR HA GANADO!!!!");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
			lblNewLabel.setForeground(Color.GREEN);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
}
