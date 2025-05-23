package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author alber
 */
//crearemos un set de producto donde guardaremos cualquier tipo de prestamista que hagamos
public class Banco {
    private final Set <Prestamista> prestamistas;

    public Banco() {
        prestamistas = new HashSet();
    }
    //altas bajas y modificaiones, la baja con iterator para evitarnos errores de concurrencia
    public boolean eliminarPrestamista(String nombre){
        Iterator<Prestamista> it = prestamistas.iterator();
        while(it.hasNext()){
            Prestamista p = it.next();
            if(p.getNombre().equalsIgnoreCase(nombre)){
                it.remove();
                return true;
            }
        }
        return false;
    }
    //todo sigue los mismos principios que el CRUD de la tienda
    public boolean modificarPlazosPorcentajesPrestamista(String nombre, double porcentajeInteres, int numPlazos) throws Exception{
        for (Prestamista prestamista : prestamistas) {
            if(prestamista.getNombre().equalsIgnoreCase(nombre)){
                prestamista.setNumPlazos(numPlazos);
                prestamista.setPorcentajeInteres(porcentajeInteres);
                return true;
            }
            
        }
        return false;
    }
    public boolean añadirPrestamista(String nombre, int numPlazos, double porcentajeInteres) throws Exception{
        if(Prestamista.prestamistaEsValido(numPlazos, porcentajeInteres)){
            prestamistas.add(new Prestamista(nombre,numPlazos,porcentajeInteres));
            return true;
        }
        else
            return false;
    }
 
 
    public Set<Prestamista> getPrestamistas() {
        return prestamistas;
    }
    /*Las siguientes funciones se encargan de cargar y guardar respectivamente
    la información de los listados en archivos.txt, de forma que tenemos persistencia de informacion al cerrar la app*/
    public void cargarPrestamistasDeArchivo(String ruta) throws FileNotFoundException, Exception{
        File archivo = new File(ruta);
        if(!archivo.exists()) return;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){
        String linea;
        while((linea = reader.readLine()) != null){
            String[] partes = linea.split(";");
            if(partes.length > 0){
                    String nombre = partes[0];
                    int numPlazos = Integer.parseInt(partes[1]);
                    double porcentajeInteres = Double.parseDouble(partes[2]);
                    prestamistas.add(new Prestamista(nombre,numPlazos,porcentajeInteres));
                }
            }
        }catch (IOException e){
            System.out.println("Error cargando productos: " + e.getMessage());
        }
    }
    
    public void guardarPrestamistasEnArchivo(String ruta){
        System.out.println("ENTRANDO A GUARDAR PRESTRAMISTAS");
        System.out.println("PRODUCTOS A GUARDAR: " + prestamistas.size());
        
        try(PrintWriter writer = new PrintWriter(new FileWriter(ruta))){
            for (Prestamista p : prestamistas) {
                    writer.println(p.getNombre() + ";" + p.getNumPlazos() + ";" + p.getPorcentajeInteres());
            }
        }
        catch(IOException e){
            System.out.println("Error al guardar productos" + e.getMessage());
        }
    }
    //hacemos un toString chulo con StringBuilder
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("En el banco tenemos los siguientes prestamistas: \n");
        for (Prestamista prestamista : prestamistas) {
            sb.append(prestamista.getNombre())
            .append(" | Meses de plazo: ")      
            .append(prestamista.getNumPlazos())
            .append(" | Porcentaje de interes: ")
            .append(prestamista.getPorcentajeInteres())
            .append("%");
            sb.append("\n");
        }
        return sb.toString();
    }
}