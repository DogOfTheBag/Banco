package data;

/**
 *
 * @author alber
 */
public class Prestamista {
    private String nombre;
    private int numPlazos;
    private double porcentajeInteres;

    public Prestamista(String nombre, int numPlazos, double porcentajeInteres) {
        this.nombre = nombre;
        this.numPlazos = numPlazos;
        this.porcentajeInteres = porcentajeInteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPlazos() {
        return numPlazos;
    }
    
    public static boolean prestamistaEsValido(int numPlazos, double porcentajeInteres) throws Exception{
        if(numPlazosEsValido(numPlazos) && porcentajeInteresEsValido(porcentajeInteres))
            return true;
        else
            return false;
    }

    public boolean setNumPlazos(int numPlazos) throws Exception {
        if(numPlazos >= 4 && numPlazos <= 48){
            this.numPlazos = numPlazos;
            return true;
        }
        return false;
    }

    public double getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public boolean setPorcentajeInteres(double porcentajeInteres) throws Exception {
        if(porcentajeInteres >= 0 && porcentajeInteres <= 100){
            this.porcentajeInteres = porcentajeInteres;
            return true;
        }
        else
            throw new Exception("Las tasas de intereses deben estar entre el 0 y el 100");
    }
 
    private static boolean numPlazosEsValido(int numPlazos) throws Exception {
        if(numPlazos >= 4 && numPlazos <= 48){
            return true;
        }else
            throw new Exception("El numero de meses de plazo debe estar entre 4 y 48");
    }

    private static boolean porcentajeInteresEsValido(double porcentajeInteres) throws Exception {
        if(porcentajeInteres >= 0 && porcentajeInteres <= 100){
            return true;
        }
        else
            throw new Exception("Las tasas de intereses deben estar entre el 0 y el 100");   
    }

    @Override
    public String toString() {
        return "Prestamista: "  + nombre + " | numPlazos: " + numPlazos + " | porcentajeInteres: " + porcentajeInteres;
    }

}
