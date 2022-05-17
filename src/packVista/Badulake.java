
package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packModelo.GestorTablero;
import packModelo.Tienda;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Badulake extends JFrame implements Observer {

	private JPanel contentPane;
	private Controler controler = null;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JButton btnCerrar;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JRadioButton rdMisil;
	private JRadioButton rdEscudo;
	private JRadioButton rdRadar;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnComprar;
	private JLabel lblSaldo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private int seleccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Badulake frame = new Badulake();
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
	public Badulake() {
		Tienda.getTienda().addObserver(this);
		seleccion = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		actualizar();
	}
	
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("EL BADULAKE");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnCerrar());
		}
		return panel;
	}
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar Tienda");
			btnCerrar.addActionListener(getControler());
		}
		return btnCerrar;
	}
	
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 2, 0, 0));
			panel_1.add(getPanel_2());
			panel_1.add(getPanel_3());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(0, 1, 0, 0));
			panel_2.add(getRdMisil());
			panel_2.add(getRdEscudo());
			panel_2.add(getRdRadar());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			panel_3.add(getPanel_4());
			panel_3.add(getPanel_5());
		}
		return panel_3;
	}
	private JRadioButton getRdMisil() {
		if (rdMisil == null) {
			rdMisil = new JRadioButton("Misil()");
			rdMisil.addActionListener(getControler());
			buttonGroup.add(rdMisil);
		}
		return rdMisil;
	}
	private JRadioButton getRdEscudo() {
		if (rdEscudo == null) {
			rdEscudo = new JRadioButton("Escudo()");
			rdEscudo.addActionListener(getControler());
			buttonGroup.add(rdEscudo);
		}
		return rdEscudo;
	}
	private JRadioButton getRdRadar() {
		if (rdRadar == null) {
			rdRadar = new JRadioButton("Radar()");
			rdRadar.addActionListener(getControler());
			buttonGroup.add(rdRadar);
		}
		return rdRadar;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.add(getLblSaldo());
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(null);
			panel_5.add(getBtnComprar());
		}
		return panel_5;
	}
	private JButton getBtnComprar() {
		if (btnComprar == null) {
			btnComprar = new JButton("Comprar");
			btnComprar.setBounds(64, 43, 85, 21);
			btnComprar.addActionListener(getControler());
		}
		return btnComprar;
	}
	private JLabel getLblSaldo() {
		if (lblSaldo == null) {
			lblSaldo = new JLabel("Tu saldo:");
			lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSaldo.setBounds(39, 22, 131, 47);
		}
		return lblSaldo;
	}
	
	private Controler getControler(){
		if(controler==null){
			controler = new Controler();
		}
		return controler;
	}

	public void actualizar(){
		int[] cants = Tienda.getTienda().getCantObj(0);
		int e = cants[0];
		int r = cants[1];
		int m = cants[2];
		int saldo = cants[3];
		lblSaldo.setText("Tu sald	o: "+saldo);
		rdEscudo.setText("Escudo(25) Tienes: "+e);
		rdRadar.setText("Radar(15) Tienes: "+r);
		rdMisil.setText("Misil(30) Tienes: "+m);
	}
	

	private class Controler implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnCerrar)){setVisible(false);}
			if(e.getSource().equals(rdMisil)){seleccion = 1;}
			if(e.getSource().equals(rdEscudo)){seleccion = 2;}
			if(e.getSource().equals(rdRadar)){seleccion = 3;}
			if(e.getSource().equals(btnComprar)){Tienda.getTienda().comprarObjeto(seleccion,0);}
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		int [] cants = (int[]) arg;
		int e = cants[0];
		int r = cants[1];
		int m = cants[2];
		int saldo = cants[3];
		lblSaldo.setText("Tu sald	o: "+saldo);
		rdEscudo.setText("Escudo(25) Tienes: "+e);
		rdRadar.setText("Radar(15) Tienes: "+r);
		rdMisil.setText("Misil(30) Tienes: "+m);
	}
}
