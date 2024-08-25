package uniandes.dpoo.estructuras.logica;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
    	Collection<String> valores = mapaCadenas.values();

        List<String> listaValores = new ArrayList<>(valores);

        Collections.sort(listaValores);
        return listaValores;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	Set<String> llavesSet = mapaCadenas.keySet();
        
        List<String> llavesLista = new ArrayList<>(llavesSet);
        
        Collections.sort(llavesLista);
        
        Collections.reverse(llavesLista);
        
        return llavesLista;
    }
    

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if (mapaCadenas.isEmpty()) {
            return null;
        }

        String primera = null;

        // Itera sobre los valores del mapa
        for (String valor : mapaCadenas.values()) {
            // Si es el primer valor o el valor actual es menor que el mínimo registrado
            if (primera == null || valor.compareTo(primera) < 0) {
                primera = valor;
            }
        }

        return primera;
    }
    

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	if (mapaCadenas.isEmpty()) {
            return null;
        }

        Collection<String> valores = mapaCadenas.values();
        
        System.out.println("Valores en el mapa: " + valores);
        
        if (valores.isEmpty()) {
            return null;
        }

        return Collections.max(valores);
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	Set<String> llaves = mapaCadenas.keySet();
        List<String> llavesMayusculas = new ArrayList<>();
        for (String llave : llaves) {
            llavesMayusculas.add(llave.toUpperCase());
        }

        return llavesMayusculas;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
        return mapaCadenas.size();
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String cadenaInvertida = new StringBuilder(cadena).reverse().toString();
        mapaCadenas.putIfAbsent(cadenaInvertida, cadena);
    }


    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	for (Iterator<Map.Entry<String, String>> iterator = mapaCadenas.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> entry = iterator.next();

            if (entry.getValue().equals(valor)) {
                iterator.remove();
            }
        }
    }
    

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	mapaCadenas.clear();
        
        for (Object obj : objetos) {

            String cadena = obj.toString();
            String cadenaInvertida = new StringBuilder(cadena).reverse().toString();

            mapaCadenas.put(cadenaInvertida, cadena);
        }
    }
    

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> mapaTemporal = new HashMap<>();

    	Map<String, String> nuevoMapa = new HashMap<>();
        
        // Iterar sobre el mapa actual
        for (Map.Entry<String, String> entrada : mapaCadenas.entrySet()) {
            // Obtener la llave actual y convertirla a mayúsculas
            String llaveMayusculas = entrada.getKey().toUpperCase();
            // Obtener el valor asociado a la llave
            String valor = entrada.getValue();
            // Colocar la nueva entrada en el nuevo mapa
            nuevoMapa.put(llaveMayusculas, valor);
        }
        
        // Reemplazar el mapa antiguo con el nuevo mapa
        mapaCadenas = nuevoMapa;
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	 List<String> listaArreglo = List.of(otroArreglo);
    	    
    	    // Verificar si todos los elementos del arreglo están en los valores del mapa
    	    for (String valor : listaArreglo) {
    	        if (!mapaCadenas.containsValue(valor)) {
    	            return false;
    	        }
    	    }
    	    
    	    return true;
    	}
    }