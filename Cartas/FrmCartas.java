package Cartas;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;

public class FrmCartas extends JFrame {
    private JPanel pnlJugador1, pnlJugador2;
    private Jugador Jugador1, Jugador2;
    JTabbedPane tpJugadores;

    public FrmCartas(){
        setSize(600,300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        pnlJugador1=new JPanel();
        pnlJugador1.setBackground(new Color(50,255,100));
        pnlJugador1.setLayout(null);
        pnlJugador2= new JPanel();
        pnlJugador2.setBackground(new Color(0,255,255));
        pnlJugador2.setLayout(null);

        tpJugadores = new JTabbedPane(); //corregir
        tpJugadores.addTab("tú", pnlJugador1);
        tpJugadores.addTab("oponente", pnlJugador2);
        tpJugadores.setBounds(10,40,550,200);
        getContentPane().add(tpJugadores);


        // Agregar los eventos
        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repartir();
                
            }
        });

        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }
        });

        // instanacias
        Jugador1 = new Jugador();
        Jugador2 = new Jugador();
        
    }

    private void repartir(){
        //pnlJugador1.removeAll(); limpiar panel
        //pnlJugador1.repaint();  repinta la carta paq salga enseguida
        Jugador1.repartir();
        Jugador2.repartir();
        //Carta cartaDelaSuerte = new Carta(new Random());
        //cartaDelaSuerte.mostrar(pnlJugador1, 10, 10);
        Jugador1.mostrar(pnlJugador1);
        Jugador2.mostrar(pnlJugador2);
    }
    private void verificar(){
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(null, Jugador1.getGrupos() + "\n\n" + Jugador1.getEscaleras() + Jugador1.getCartaSobrante()+Jugador1.getPuntaje());                
                break;        
            case 1:
                JOptionPane.showMessageDialog(null, Jugador2.getGrupos() + "\n\n" + Jugador2.getEscaleras()+ Jugador2.getCartaSobrante() + Jugador2.getPuntaje());
                break;
        }

    }
   
}



