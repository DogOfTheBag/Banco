package data;

import GUI.VPal;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alber
 */
public class App extends Thread {

    /**
     * @param args the command line arguments
     ***********************************************NOTA PARA JAVI**************************************************
     * SI VES ESTE EJERCICIO PRIMERO MIRATE MEJOR LA TIENDA PARA EMPEZAR QUE AHI EXPLICO ALGO MÁS EL PROGRAMA
     ***********************************************NOTA PARA JAVI**************************************************
     */
    final boolean DEV_VERSION = false;
    final String NOM = "Banco Damtander";
    Banco banco;
    //No voy a explicar esto mucho porque es igual que la tienda
    //dev version para probar cosas, o abrir la ventana si es la versión final
    @Override
    public void run(){
        if(DEV_VERSION){
            banco = new Banco();
            try {
                this.crearDatosPrueba();
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.visualizarConsola();
            this.visualizarVentana();
        }else{
        /*creamos la tienda, cargamos los productos del archivo y activamos la ventana*/
        banco = new Banco();
           
            try {
                banco.cargarPrestamistasDeArchivo("res/prestamistas.txt");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        VPal v = new VPal(NOM, banco);
        v.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new App().start();
    }
//***********************************PARA PRUEBAS SOLO*******************************
    private void crearDatosPrueba() throws Exception {
        banco.cargarPrestamistasDeArchivo("res/prestamistas.txt");
        //banco.añadirPrestamista("Santander", 12, 25);
        //banco.añadirPrestamista("Bankia", 24, 12);
        //banco.añadirPrestamista("Caixa", 12, 13);
        //banco.añadirPrestamista("Sabadell", 18, 8);
    }

    private void visualizarConsola() {
        System.out.println("" + banco);
    }

    private void visualizarVentana() {
      //parent component, mensaje, titulo de la ventana, icono que sale
      JOptionPane.showMessageDialog(null, banco.toString(), NOM, 1);
    }
}
