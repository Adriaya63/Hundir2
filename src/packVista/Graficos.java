package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packModelo.GestorTablero;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
 
public class Graficos extends JFrame implements Observer {
  
	private JPanel contentPane;
	private JPanel centro;
	private JLabel titulo;
	private JPanel pJugador;
	private JPanel pCPU;
	private JPanel crearTablero;
	private JPanel armas;
	private JPanel matriz1; 
	private JPanel matriz2;
	private JLabel jugador;
	private JLabel cpu;
	private ArrayList<JLabel> lJug;
	private ArrayList<JLabel> lCPU;
	private JPanel panel;
	private JLabel opTablero;
	private JRadioButton rbGabarra;
	private JRadioButton rbArriba;
	private JRadioButton rbTrainera;
	private JRadioButton rbVelero;
	private JRadioButton rbAbajo;
	private JRadioButton rbIzquierda;
	private JRadioButton rbDerecha;
	private JRadioButton rbTxintxorro;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private Controler controler = null;
	private Mouse mouse = null;
	private JPanel panel_1;
	private JButton bdColocar;
	private JButton bdConfirmar;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JRadioButton rdBomba;
	private JRadioButton rdMisil;
	private JButton bdDisparar;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JButton bdEscudo;
	private JButton bdRadar;

	// Atributos para actualizar
	private JLabel seleccionado;
	private ArrayList<Integer> lInd;
	private ArrayList<String> matrizJ;
	private ArrayList<String> matrizCPU;
	private int[] cant ;
	private ArrayList<Integer> lJA;
	private ArrayList<Integer> lCA;
	private ArrayList<Integer> lJB;
	private ArrayList<Integer> lCB;
	private ArrayList<Integer> lJE;
	private ArrayList<Integer> lCE;
	private ArrayList<Integer> lJH;
	private ArrayList<Integer> lCH;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graficos frame = new Graficos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Método para poner cordenadas en la primera fila y columna
	private void ponerCoordenadas() {
		int x,y;
		JLabel l1,l2;
		lJug.get(0).setVisible(false); //primera posición esta vacía
		lCPU.get(0).setVisible(false);
		x=0;
		for (y=1;y<11;y++) { //Primera fila de la matriz 1 y 2
			l1 = lJug.get(x*11+y);
			l1.setOpaque(false); //Transparente (Jugador)
			l1.setText(String.valueOf(y)); //Enumera las posiciomes del 1 al 10 en la primera fila
			l1.setBorder(BorderFactory.createEmptyBorder()); //Utiliza la clase BorderFactory para borrar los bordes y no ocupar espacio
			l1.setHorizontalAlignment(SwingConstants.CENTER); //Centrar los Strings numéricos
			l2 = lCPU.get(x*11+y);
			l2.setOpaque(false); 
			l2.setText(String.valueOf(y));
			l2.setBorder(BorderFactory.createEmptyBorder());
			l2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		y=0;
		for (x=1;x<11;x++) { //Lo mismo pero en horizontal
			l1 = lJug.get(x*11+y); //Multiplico por 11 para seleccionar la primera posicion de la siguiente fila
			l1.setOpaque(false);
			l1.setText(String.valueOf(x));
			l1.setBorder(BorderFactory.createEmptyBorder());
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			l2 = lCPU.get(x*11+y);
			l2.setOpaque(false);
			l2.setText(String.valueOf(x));
			l2.setBorder(BorderFactory.createEmptyBorder());
			l2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
	}
	private JLabel clb() { //Label vacío, con bordes blancos, fondo gris y una acción al clickear el label
		JLabel label1 = new JLabel("");
		label1.setBorder(BorderFactory.createLineBorder(Color.white));
		label1.setOpaque(true);
		label1.setBackground(Color.darkGray);
		label1.addMouseListener(getMouse());
		return label1;
		}

		//Método para colorear las posiciones de la matriz según si es agua o barco
	private void colorearMatriz(ArrayList<String> m, int t) { 
		if(t==1){ //opción 1 Jugador
			String c = "";
			for(int i=0;i<m.size();i++){
				c = m.get(i); //en c guardo el String en la posición de dicho Array ("a" o "b")
				if(c.equals("b")){lJug.get(i).setBackground(Color.red);} //barco
				if(c.equals("a")){lJug.get(i).setBackground(Color.blue);} //agua
			}
		}
		else if(t==2){ //opción 2 CPU
			String c = "";
			for(int i=0;i<m.size();i++){
				c = m.get(i);
				if(c.equals("b")){lCPU.get(i).setBackground(Color.red);} 
				if(c.equals("a")){lCPU.get(i).setBackground(Color.blue);}
			}
		}
	}

	private void colorearRadar(ArrayList<String> m, int pos, int t) {
		if (m.size()!=0){
			if(t==1){ //opción 1 Jugador
				if (m.get(0)=="b"){lCPU.get(pos-12).setBackground(Color.ORANGE);}
				if (m.get(1)=="b"){lCPU.get(pos-11).setBackground(Color.ORANGE);}
				if (m.get(2)=="b"){lCPU.get(pos-10).setBackground(Color.ORANGE);}
				if (m.get(3)=="b"){lCPU.get(pos-1).setBackground(Color.ORANGE);}
				lCPU.get(pos).setBackground(Color.green);
				if (m.get(5)=="b"){lCPU.get(pos+1).setBackground(Color.ORANGE);}
				if (m.get(6)=="b"){lCPU.get(pos+10).setBackground(Color.ORANGE);}
				if (m.get(7)=="b"){lCPU.get(pos+11).setBackground(Color.ORANGE);}
				if (m.get(8)=="b"){lCPU.get(pos+12).setBackground(Color.ORANGE);}
			}
			else if(t==2){ //opción 2 CPU
				if (m.get(0)=="b"){lJug.get(pos-12).setBackground(Color.ORANGE);}
				if (m.get(1)=="b"){lJug.get(pos-11).setBackground(Color.ORANGE);}
				if (m.get(2)=="b"){lJug.get(pos-10).setBackground(Color.ORANGE);}
				if (m.get(3)=="b"){lJug.get(pos-1).setBackground(Color.ORANGE);}
				lJug.get(pos).setBackground(Color.green);
				if (m.get(5)=="b"){lJug.get(pos+1).setBackground(Color.ORANGE);}
				if (m.get(6)=="b"){lJug.get(pos+10).setBackground(Color.ORANGE);}
				if (m.get(7)=="b"){lJug.get(pos+11).setBackground(Color.ORANGE);}
				if (m.get(8)=="b"){lJug.get(pos+12).setBackground(Color.ORANGE);}
			}
		}
	}

	private void  pintarGraficos() {
		colorearMatriz(matrizJ, 1);		
		colorearMatriz(matrizCPU, 2);
		rbGabarra.setText("Gabarra("+cant[0]+")");
		rbVelero.setText("Velero("+cant[1]+")");
		rbTrainera.setText("Trainera("+cant[2]+")");
		rbTxintxorro.setText("Txintxorro("+cant[3]+")");
		lJA.stream().forEach(l-> lCPU.get(l).setBackground(Color.CYAN));
		lCA.stream().forEach(l-> lJug.get(l).setBackground(Color.CYAN));
		lJB.stream().forEach(l-> lCPU.get(l).setBackground(Color.LIGHT_GRAY));
		lCB.stream().forEach(l-> lJug.get(l).setBackground(Color.LIGHT_GRAY));
		lJE.stream().forEach(l-> lCPU.get(l).setBackground(Color.WHITE));
		lCE.stream().forEach(l-> lJug.get(l).setBackground(Color.WHITE));
		lJH.stream().forEach(l-> lCPU.get(l).setBackground(Color.YELLOW));
		lCH.stream().forEach(l-> lJug.get(l).setBackground(Color.YELLOW));
		if(seleccionado!=null){seleccionado.setBackground(Color.GREEN);}

	}
	// private void colorearSelec(){
	// 	int n = lJug.indexOf(seleccionado);
	// 	if (n==-1){
	// 		n = lCPU.indexOf(seleccionado);
	// 		if(matrizCPU.get(n).equals("b")){lCPU.get(n).setBackground(Color.RED);}
	// 		if(matrizCPU.get(n).equals("a")){lCPU.get(n).setBackground(Color.BLUE);}
	// 	}else{
	// 		if(matrizJ.get(n).equals("b")){lJug.get(n).setBackground(Color.RED);}
	// 		if(matrizJ.get(n).equals("a")){lJug.get(n).setBackground(Color.BLUE);}
	// 	}
	// }
	
	
	/*private void colorearDisparo(ArrayList<String> ma1) {
		
		ArrayList<Integer> ld = GestorTablero.getGestorTablero().getLDisparos();
		
		String ca="";
		int i= ld.get(ld.size()-1);
		
		 //no lo guarda, solo comprueba los ifs de abajo
		
		
			ca = ma1.get(i);
		
		
			if(ca.equals("a")){
			ld.stream().forEach(l1 -> lJug.get(l1).setBackground(Color.black));
			
			}else if(ca.equals("b")) {
			ld.stream().forEach(l -> lJug.get(l).setBackground(Color.yellow));
			
		}
	} */
	
	
	//Labels de Jugador formando una matriz 11x11
	private void crearLabelsJugador() {
		int i,j;
		for(i=0; i<11;i++) {
			for(j=0; j<11;j++) {
				JLabel l = clb();
				matriz1.add(l,BorderLayout.CENTER, i*11+j);
				lJug.add(l);
			}
		}
	}

	//Labels de la CPU formando una matriz 11x11
	private void crearLabelsIA() {
		int i,j;
		for(i=0; i<11;i++) {
			for(j=0; j<11;j++) {
				JLabel l = clb();
				matriz2.add(l,BorderLayout.CENTER, i*11+j);
				lCPU.add(l);
			}
		}
	}
	
	public Graficos() {
		GestorTablero.getGestorTablero().addObserver(this);
		seleccionado = null;
		matrizJ = new ArrayList<String>();
		matrizCPU = new ArrayList<String>();
		cant = new int[4];
		lJug = new ArrayList<JLabel>();
		lCPU = new ArrayList<JLabel>();
		lInd = new ArrayList<Integer>(); 
		lJA = new ArrayList<Integer>();
		lCA = new ArrayList<Integer>();
		lJB = new ArrayList<Integer>();
		lCB = new ArrayList<Integer>();
		lJE = new ArrayList<Integer>();
		lCE = new ArrayList<Integer>();
		lJH = new ArrayList<Integer>();
		lCH = new ArrayList<Integer>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getCentro(), BorderLayout.CENTER);
		contentPane.add(gettitulo(), BorderLayout.NORTH);
		crearLabelsJugador();
		crearLabelsIA();
		ponerCoordenadas();
	}

	
	//panel central con un GridLayout de 2 filas y dos columnas
	//Fila 1 y Columna 1, Panel Jugador
	//Fila 1 Columna 2, Panel CPU
	//Fila 2 Columna 1, Panel crearTablero (Con los 8 radioButtons)
	//Fila 2 Columna 2, Panel armas (Boton de bdDisparar)
	private JPanel getCentro() {
		if (centro == null) {
			centro = new JPanel();
			centro.setLayout(new GridLayout(2, 2, 0, 0));
			centro.add(getPJugador());
			centro.add(getpCPU());
			centro.add(getCrearTablero());
			centro.add(getArmas());
		}
		return centro;
	}
	//Label Titulo
	private JLabel gettitulo() {
		if (titulo == null) {
			titulo = new JLabel("Hundir la gabarra");
			titulo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return titulo;
	}
	private JPanel getPJugador() {
		if (pJugador == null) {
			pJugador = new JPanel();
			pJugador.setLayout(new BorderLayout(0, 0));
			pJugador.add(getMatriz1());
			pJugador.add(getjugador(), BorderLayout.NORTH);
		}
		return pJugador;
	}
	private JPanel getpCPU() {
		if (pCPU == null) {
			pCPU = new JPanel();
			pCPU.setLayout(new BorderLayout(0, 0));
			pCPU.add(getMatriz2());
			pCPU.add(getcpu(), BorderLayout.NORTH);
		}
		return pCPU;
	}
	private JPanel getCrearTablero() {
		if (crearTablero == null) {
			crearTablero = new JPanel();
			crearTablero.setLayout(new BorderLayout(0, 0));
			crearTablero.add(getPanel(), BorderLayout.CENTER);
			crearTablero.add(getOpTablero(), BorderLayout.NORTH);
			crearTablero.add(getPanel_1(), BorderLayout.SOUTH);
		}
		return crearTablero;
	}
	private JPanel getArmas() {
		if (armas == null) {
			armas = new JPanel();
			armas.setLayout(new GridLayout(0, 1, 0, 0));
			armas.add(getPanel_2());
			armas.add(getPanel_3());
		}
		return armas;
	}
	//Panel tipo GridLayout con 11x11 posiciones (Jugador)
	private JPanel getMatriz1() {
		if (matriz1 == null) {
			matriz1 = new JPanel();
			matriz1.setLayout(new GridLayout(11, 11, 0, 0));
		}
		return matriz1;
	}
	//Panel tipo GridLayout con 11x11 posiciones (CPU)
	private JPanel getMatriz2() {
		if (matriz2 == null) {
			matriz2 = new JPanel();
			matriz2.setLayout(new GridLayout(11, 11, 0, 0));
		}
		return matriz2;
	}

	private JLabel getjugador() {
		if (jugador == null) {
			jugador = new JLabel("Tablero Jugador");
			jugador.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jugador;
	}
	private JLabel getcpu() {
		if (cpu == null) {
			cpu = new JLabel("Tablero CPU");
			cpu.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return cpu;
	}
	
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(4, 2, 0, 0));
			panel.add(getRbArriba());
			panel.add(getRbGabarra());
			panel.add(getRbAbajo());
			panel.add(getRbVelero());
			panel.add(getRbDerecha());
			panel.add(getRbTrainera());
			panel.add(getRbIzquierda());
			panel.add(getRbTxintxorro());
		}
		return panel;
	}
	private JLabel getOpTablero() {
		if (opTablero == null) {
			opTablero = new JLabel("Opciones Tablero");
			opTablero.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return opTablero;
	}
	
	//Radio Buttons
	
	private JRadioButton getRbGabarra() {
		if (rbGabarra == null) {
			rbGabarra = new JRadioButton("Gabarra(1)");
			rbGabarra.setHorizontalAlignment(SwingConstants.CENTER);
			rbGabarra.addActionListener(getControler());
			buttonGroup_1.add(rbGabarra);
		}
		return rbGabarra;
	}
	
	
	private JRadioButton getRbArriba() {
		if (rbArriba == null) {
			rbArriba = new JRadioButton("Arriba");
			rbArriba.setHorizontalAlignment(SwingConstants.CENTER);
			rbArriba.addActionListener(getControler());
			buttonGroup.add(rbArriba);
		}
		return rbArriba;
	}
	private JRadioButton getRbTrainera() {
		if (rbTrainera == null) {
			rbTrainera = new JRadioButton("Trainera(3)");
			rbTrainera.setHorizontalAlignment(SwingConstants.CENTER);
			rbTrainera.addActionListener(getControler());
			buttonGroup_1.add(rbTrainera);
		}
		return rbTrainera;
	}
	private JRadioButton getRbVelero() {
		if (rbVelero == null) {
			rbVelero = new JRadioButton("Velero(2)");
			rbVelero.setHorizontalAlignment(SwingConstants.CENTER);
			rbVelero.addActionListener(getControler());
			buttonGroup_1.add(rbVelero);
		}
		return rbVelero;
	} 
	private JRadioButton getRbAbajo() {
		if (rbAbajo == null) {
			rbAbajo = new JRadioButton("Abajo");
			rbAbajo.setHorizontalAlignment(SwingConstants.CENTER);
			rbAbajo.addActionListener(getControler());
			buttonGroup.add(rbAbajo);
		}
		return rbAbajo;
	}
	private JRadioButton getRbIzquierda() {
		if (rbIzquierda == null) {
			rbIzquierda = new JRadioButton("Izquierda");
			rbIzquierda.setHorizontalAlignment(SwingConstants.CENTER);
			rbIzquierda.addActionListener(getControler());
			buttonGroup.add(rbIzquierda);
		}
		return rbIzquierda;
	}
	private JRadioButton getRbDerecha() {
		if (rbDerecha == null) {
			rbDerecha = new JRadioButton("Derecha");
			rbDerecha.setHorizontalAlignment(SwingConstants.CENTER);
			rbDerecha.addActionListener(getControler());
			buttonGroup.add(rbDerecha);
		}
		return rbDerecha;
	}
	private JRadioButton getRbTxintxorro() {
		if (rbTxintxorro == null) {
			rbTxintxorro = new JRadioButton("Txintxorro(4)");
			rbTxintxorro.setHorizontalAlignment(SwingConstants.CENTER);
			rbTxintxorro.addActionListener(getControler());
			buttonGroup_1.add(rbTxintxorro);
		}
		return rbTxintxorro;
	}
	//panel con los botones de Colocar y Confirmar
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getBdColocar());
			panel_1.add(getBdConfirmar());
		}
		return panel_1;
	} //Botón colocar
	private JButton getBdColocar() {
		if (bdColocar == null) {
			bdColocar = new JButton("Colocar");
			bdColocar.addActionListener(getControler());
		}
		return bdColocar;
	} //Botón confirmar
	private JButton getBdConfirmar() {
		if (bdConfirmar == null) {
			bdConfirmar = new JButton("Confirmar");
			bdConfirmar.addActionListener(getControler());
		}
		return bdConfirmar;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(1, 0, 0, 0));
			panel_2.add(getPanel_4());
			panel_2.add(getPanel_5());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getbdDisparar());
			panel_3.add(getBdEscudo());
			panel_3.add(getBdRadar());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(2, 2, 0, 0));
			panel_4.add(getRdBomba());
			panel_4.add(getRdMisil());
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
		}
		return panel_5;
	}
	private JRadioButton getRdBomba() {
		if (rdBomba == null) {
			rdBomba = new JRadioButton("Bomba");
			rdBomba.addActionListener(getControler());
			buttonGroup_2.add(rdBomba);
		}
		return rdBomba;
	}
	private JRadioButton getRdMisil() {
		if (rdMisil == null) {
			rdMisil = new JRadioButton("Misil");
			rdMisil.addActionListener(getControler());
			buttonGroup_2.add(rdMisil);
		}
		return rdMisil;
	}
	private JButton getbdDisparar() {
		if (bdDisparar == null) {
			bdDisparar = new JButton("Disparar");
			bdDisparar.addActionListener(getControler());
		}
		return bdDisparar;
	}
	private JButton getBdEscudo() {
		if (bdEscudo == null) {
			bdEscudo = new JButton("Escudo");
			bdEscudo.addActionListener(getControler());
		}
		return bdEscudo;
	}
	private JButton getBdRadar() {
		if (bdRadar == null) {
			bdRadar = new JButton("Radar");
			bdRadar.addActionListener(getControler());
		}
		return bdRadar;
	}

	private Controler getControler(){
		if(controler==null){
			controler = new Controler();
		}
		return controler;
	}
	

	private class Controler implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(rbArriba)){GestorTablero.getGestorTablero().setDireccion("Arriba");}
			if(e.getSource().equals(rbAbajo)){GestorTablero.getGestorTablero().setDireccion("Abajo");}
			if(e.getSource().equals(rbDerecha)){GestorTablero.getGestorTablero().setDireccion("Derecha");}
			if(e.getSource().equals(rbIzquierda)){GestorTablero.getGestorTablero().setDireccion("Izquierda");}
			if(e.getSource().equals(rbGabarra)){GestorTablero.getGestorTablero().setTipoBarco(1);}
			if(e.getSource().equals(rbVelero)){GestorTablero.getGestorTablero().setTipoBarco(2);}
			if(e.getSource().equals(rbTrainera)){GestorTablero.getGestorTablero().setTipoBarco(3);}
			if(e.getSource().equals(rbTxintxorro)){GestorTablero.getGestorTablero().setTipoBarco(4);}
			if(e.getSource().equals(rdBomba)){GestorTablero.getGestorTablero().cambiarArma(0);}
			if(e.getSource().equals(rdMisil)){GestorTablero.getGestorTablero().cambiarArma(1);}
			if(e.getSource().equals(bdColocar)){GestorTablero.getGestorTablero().visualizarBarco();}
			if(e.getSource().equals(bdConfirmar)){GestorTablero.getGestorTablero().colocarBarco();}
			if(e.getSource().equals(bdDisparar)) {GestorTablero.getGestorTablero().turno(1);}//turno(1)
			if(e.getSource().equals(bdEscudo)) {GestorTablero.getGestorTablero().turno(2);}
			if(e.getSource().equals(bdRadar)) {GestorTablero.getGestorTablero().turno(3);}
		}
	}

	private Mouse getMouse(){
		if(mouse==null){
			mouse = new Mouse();
		}
		return mouse;
	}	

	private class Mouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int ind = lJug.indexOf((JLabel) e.getComponent()); /* indice del que ha creado ese evento, te devuelve la pos en la que has clickado*/
			if (ind == -1) {
				ind = lCPU.indexOf((JLabel) e.getComponent());
				GestorTablero.getGestorTablero().setIndSelect(ind, 2);//panel 2;
			}
			else {GestorTablero.getGestorTablero().setIndSelect(ind, 1);} 
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}

	@Override
	public void update(Observable o, Object arg) {
		lCPU.stream().forEach(l->l.setBackground(Color.DARK_GRAY));
		if (arg instanceof Object[]){
			Object[] upd = (Object[]) arg;
			if (upd[0] instanceof Integer){
				int n = (int) upd[0];
				System.out.println("Indice: "+n);
				// if(seleccionado!=null&&matrizCPU!=null&&matrizJ!=null){colorearSelec();}
				if(seleccionado!=null){seleccionado.setBackground(Color.DARK_GRAY);}

				if(n==1){
					if((int)upd[1]==1){seleccionado=lJug.get((int)upd[2]);}
					else{seleccionado=lCPU.get((int)upd[2]);}
				}
				if(n==3){
					seleccionado = null;
					matrizJ = (ArrayList<String>) upd[1];
					cant = (int[]) upd[2];
				}
				if(n==4){
					seleccionado = null;
					matrizCPU = (ArrayList<String>) upd[1];
				}
				if(n==5){
					seleccionado = null;
					ArrayList<Integer> lA = (ArrayList<Integer>) upd[1];
					if((int)upd[2]==1){lJA = lA;}
					if((int)upd[2]==2){lCA = lA;}
				}
				if(n==6){
					seleccionado = null;
					ArrayList<Integer> lB = (ArrayList<Integer>) upd[1];
					if((int)upd[2]==1){lJB = lB;}
					if((int)upd[2]==2){lCB = lB;}
				}
				if(n==7){
					seleccionado = null;
					ArrayList<Integer> lE = (ArrayList<Integer>) upd[1];
					if((int)upd[2]==1){lJE = lE;}
					if((int)upd[2]==2){lCE = lE;}
				}
				if(n==8){
					seleccionado = null;
					ArrayList<Integer> lH = (ArrayList<Integer>) upd[1];
					if((int)upd[2]==1){lJH = lH;}
					if((int)upd[2]==2){lCH = lH;}
				}
				pintarGraficos();
				if(n==10){
					int t = (int) upd[1];
					int pos = (int) upd[2];
					ArrayList<String> m = (ArrayList<String>) upd[3];
					colorearRadar(m, pos, t);
				}
				if(n==2){
					seleccionado = null;
					lJug.forEach(l->l.setBackground(Color.DARK_GRAY));
					lInd = (ArrayList<Integer>) upd[1];
					if(lInd.size()>0){lInd.forEach(l->lJug.get(l).setBackground(Color.GREEN));}
				}
			}
		}

		

		


		

		/*Primera prueba del viernes por la ma�ana*/
		
		/*ArrayList<String> m1 = GestorTablero.getGestorTablero().getMatrizJ();
		lD.stream().filter(ma -> m1.get(lD.size()-1).equals("a")).forEach(l -> lJug.get(1).setBackground(Color.black));*/
		
		/*Un avance que he hecho, lo unico que se actualizan todos a la vez y no se como hacer
		que se guarde a la hora de que sea agua en negro perpetuo y con barco lo mismo pero amarillo*/
		
		/*
		m = GestorTablero.getGestorTablero().getMatrizJ();
		colorearDisparo(m);
		*/
	}
	
	
}


