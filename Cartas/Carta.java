package Cartas;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Carta {

    private int indice;

    public Carta(Random r) {
        indice = r.nextInt(52)+1;

    }
    public void mostrar(JPanel pnl, int x, int y) {
        String archivoImagen = "Imagen/CARTA"+indice+".JPG";
        ImageIcon imgCarta= new ImageIcon(getClass().getResource(archivoImagen));
        JLabel lblCarta = new JLabel();
        lblCarta.setIcon(imgCarta);
        lblCarta.setBounds(x,y,imgCarta.getIconWidth(), imgCarta.getIconHeight());
        
        lblCarta.addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent me ) {
                JOptionPane.showMessageDialog(null, getNombre()+ " de " + getPinta());

            }
        });

        pnl.add(lblCarta);
        //return lblCarta;
    }  

    // reconocer la carta --> signo. 
    public Pinta getPinta() {
        if (indice<=13)
            return Pinta.TREBOL;
        else if(indice<=26)
            return Pinta.PICA;
        else if(indice<=39)
            return Pinta.CORAZÓN;
        else
            return Pinta.DIAMANTE;
    }
    public NombreCarta getNombre(){
        int residuo = indice % 13;
        int posicion = residuo == 0 ? 12 : residuo-1;
        return NombreCarta.values()[posicion];
    
    }
    public int getValor() {
        int residuo = indice % 13;
        if (residuo == 0) return 10; // KING
        if (residuo == 1) return 10; // AS  
        if (residuo >= 11) return 10; // JACK, QUEEN
        return residuo; // DOS=2, TRES=3
    }
}
