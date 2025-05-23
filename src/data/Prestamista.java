package data;

/**
 *
 * @author alber
 */
public class Prestamista {
    private String nombre;
    private int numPlazos;
    private double porcentajeInteres;
    /*He hecho el constructor usando setters que he modificado para que tenga una super validación y que tengan
    que hacer el prestamista con datos correctos si o si con buenos datos, aunque entre los métodos de validar y esto
    creo que me he acabado pasando (aquí y en la tienda)*/
    public Prestamista(String nombre, int numPlazos, double porcentajeInteres) throws Exception {
        this.setNombre(nombre);
        this.setNumPlazos(numPlazos);
        this.setPorcentajeInteres(porcentajeInteres);
    }

    public String getNombre() {
        return nombre;
    }
    //setters y metodos para validar info
    public void setNombre(String nombre) throws Exception {
        if(nombre != null || nombre.trim().isEmpty())
            this.nombre = nombre;
        else
            throw new Exception("El nombre no puede estar vacio");
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

    public void setNumPlazos(int numPlazos) throws Exception {
        if(numPlazos >= 4 && numPlazos <= 120){
            this.numPlazos = numPlazos;
        }else
            throw new Exception("El numero de meses de plazo debe estar entre 4 y 120");
    }

    public double getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(double porcentajeInteres) throws Exception {
        if(porcentajeInteres >= 0 && porcentajeInteres <= 100){
            this.porcentajeInteres = porcentajeInteres;
        }
        else
            throw new Exception("Las tasas de intereses deben estar entre el 0 y el 100");
    }
 
    private static boolean numPlazosEsValido(int numPlazos) throws Exception {
        if(numPlazos >= 4 && numPlazos <= 120){
            return true;
        }else
            throw new Exception("El numero de meses de plazo debe estar entre 4 y 120");
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
        return nombre + " | Numero de meses de plazo: " + numPlazos + " | Porcentaje de interes: " + porcentajeInteres + "%";
    }

}
