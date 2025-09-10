package Cartas;

import java.util.Random;

//import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jugador {
    private final int TOTAL_CARTAS =10;
    private final int SEPARACION = 40;
    private final int MARGEN =10;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random(); // todo jugador tiene su propia pseudosuerte

    private int puntajeCartasSobran = 0;

    public void repartir(){
        for(int i=0; i<TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);

        }
    }
   
    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN+TOTAL_CARTAS * SEPARACION;
        for (Carta carta: cartas ){
            carta.mostrar(pnl, posicion, MARGEN);
            posicion -= SEPARACION;

       }
     pnl.repaint();   
    }
    // el jugador sepa por eso va acá 
    public String getGrupos() {
        String resultado = "No se encontraron grupos"; 
        int[] contadorGrupo = new int[NombreCarta.values().length]; // arreglo para la mano
        for (Carta carta : cartas) {
            contadorGrupo[carta.getNombre().ordinal()]++;  // el ordinal busca el numero de cada carta
        }
        //Se verifican grupos
        boolean hayGrupos=false;
        for (int contador:contadorGrupo){
            if(contador>=2){
                hayGrupos=true;
                break; // salida no estructurada
            }
        }
        if (hayGrupos){  // para decir que es falso sería con ! es decir !hayGrupos
            resultado = "Se hallaron los siguientes grupos: \n";
            for (int i=0; i< contadorGrupo.length; i++) {
                int contador= contadorGrupo[i];
                if (contador >= 2 ) {
                    resultado += Grupos.values()[contador] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return resultado;
    } 
    // leer cartas
    public String getEscaleras(){
        String resultado = "No hay escaleras :( \n";
        int [][] contadores = new int[Pinta.values().length][NombreCarta.values().length];
        for (Carta carta: cartas ) {
            contadores[carta.getPinta().ordinal()][carta.getNombre().ordinal()]++;
        }
        // Buscar escaleras
        boolean hayEscaleras = false;  // bandera
        for (int pinta = 0; pinta < Pinta.values().length; pinta++) {
            int secuencia = 0;      // cartas consecutiva
            int comienzo = -1;      // no hay carta elegida -1
            
            for (int nombre = 0; nombre < NombreCarta.values().length; nombre++) {
                if (contadores[pinta][nombre] > 0) {
                    if (secuencia == 0)
                    comienzo = nombre;
                    secuencia++;
                } 
                else {
                    if (secuencia >= 3) {
                        if (!hayEscaleras) {
                            resultado = " \nEscaleras:\n";
                            hayEscaleras = true;
                        }
                        resultado += Grupos.values()[secuencia] + " de " + Pinta.values()[pinta] + 
                                " de " + NombreCarta.values()[comienzo] + " a " + 
                                NombreCarta.values()[comienzo + secuencia - 1] + "\n";
                    }
                    secuencia = 0;
                }
            }          
            
        }
        return resultado;  
    } 
    
    public String getCartaSobrante() {
        String resultado = "";
        puntajeCartasSobran = 0;
        // Contar grupos
        int[] contarNombre = new int[NombreCarta.values().length]; //Array [13]
        for (Carta carta : cartas) {
            contarNombre[carta.getNombre().ordinal()]++;  // suma por cada carta enocntrada
        }        
        // Detectar escaleras
        int[][] contadorPinta = new int[Pinta.values().length][NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadorPinta[carta.getPinta().ordinal()][carta.getNombre().ordinal()]++;
        }
        // se crea un boolean para verificar si hace parte de una escalera 
        boolean[][] esEscalera  = new boolean[Pinta.values().length][NombreCarta.values().length];

        for (int pinta = 0; pinta < Pinta.values().length; pinta++) {  // primero pinta para revisar por signo
            int conteo = 0;  // se hace un conteo para la secuencia de cartas 
            int comienzo = -1;  // se usa -1 (centinela) para decir q no esta en niguna posición aun 
        
            for (int nombre = 0; nombre < NombreCarta.values().length; nombre++) {
                if (contadorPinta[pinta][nombre] > 0) {  // existe carta (pinta y nombe específico)
                    if (conteo == 0) comienzo = nombre;  // si es así, comienzo por esa posición 
                    conteo++;       // conteo +=1
                } 
                else { 
                    if (conteo >= 3){
                        int ultimaPos = comienzo+conteo-1;
                        for(int pos = comienzo; pos <= ultimaPos; pos++) {
                            esEscalera[pinta][pos] = true;
                        }
                    }                       
                }
            }
        
        }
        // Encontrar cartas que sobran
        int cartasSobran = 0;
        for (Carta carta : cartas) {
            int indiceNombre = carta.getNombre().ordinal();
            int indicePinta = carta.getPinta().ordinal();
            
            if (contarNombre[indiceNombre] < 2 && !esEscalera[indicePinta][indiceNombre]) {
                if (cartasSobran==0) {
                    resultado = "Sobran:\n";
                }
                resultado += carta.getNombre() + " de " + carta.getPinta() + "\n";
                puntajeCartasSobran += carta.getValor();
                cartasSobran++;
            }
        }
        if (cartasSobran==0)
            resultado = "No sobran cartas";
        return resultado;
    }

    public int getPuntaje() {  
        return puntajeCartasSobran;
    }
}
    
        


    
    