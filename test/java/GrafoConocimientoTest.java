import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrafoConocimientoTest {

    private GrafoConocimiento grafo;

    // Se ejecuta antes de cada test para asegurar un entorno limpio
    @BeforeEach
    void setUp() {
        grafo = new GrafoConocimiento();
    }

    @Test
    void getNodos() {
        grafo.añadir(new Tripleta("Madrid", "es_capital_de", "España"));
        String[] nodos = grafo.getNodos();

        // Verificamos que el arreglo contenga los nodos añadidos
        assertEquals("Madrid", nodos[0]);
        assertEquals("España", nodos[1]);
        // Comprobamos que el resto del array inicial (tamaño 100) sea null
        assertNull(nodos[2]);
    }

    @Test
    void getAristas() {
        Tripleta t1 = new Tripleta("Einstein", "nace_en", "Ulm");
        grafo.añadir(t1);
        Tripleta[] aristas = grafo.getAristas();

        assertEquals(t1, aristas[0]);
        assertEquals("Einstein", aristas[0].sujeto);
        assertEquals("nace_en", aristas[0].predicado);
        assertEquals("Ulm", aristas[0].objeto);
    }

    @Test
    void getNumeroNodos() {
        assertEquals(0, grafo.getNumeroNodos(), "El grafo debe inicializarse con 0 nodos");

        grafo.añadir(new Tripleta("A", "relacion", "B"));
        assertEquals(2, grafo.getNumeroNodos());

        // Añadir una relación con un nodo ya existente no debería duplicarlo
        grafo.añadir(new Tripleta("B", "relacion", "C"));
        assertEquals(3, grafo.getNumeroNodos());
    }

    @Test
    void getNumeroAristas() {
        assertEquals(0, grafo.getNumeroAristas(), "El grafo debe inicializarse con 0 aristas");

        grafo.añadir(new Tripleta("A", "relacion", "B"));
        grafo.añadir(new Tripleta("B", "relacion", "C"));

        assertEquals(2, grafo.getNumeroAristas());
    }

    @Test
    void añadir() {
        Tripleta t1 = new Tripleta("Sol", "es_una", "Estrella");
        grafo.añadir(t1);

        // Verificamos que los contadores se actualicen correctamente
        assertEquals(1, grafo.getNumeroAristas());
        assertEquals(2, grafo.getNumeroNodos());

        // Verificamos la persistencia en las estructuras internas
        assertEquals("Sol", grafo.getNodos()[0]);
        assertEquals("Estrella", grafo.getNodos()[1]);
    }

    @Test
    void getCaminoMinimo() {
        grafo.añadir(new Tripleta("A", "conecta_con", "B"));
        grafo.añadir(new Tripleta("B", "conecta_con", "C"));
        grafo.añadir(new Tripleta("A", "conecta_con", "X")); // Camino alternativo

        MiListaDoble<String> camino = grafo.getCaminoMinimo("A", "C");
        assertNotNull(camino);

        // Verificación de camino inexistente
        MiListaDoble<String> caminoInexistente = grafo.getCaminoMinimo("C", "A"); // Grafo dirigido
        assertNotNull(caminoInexistente);
    }

    @Test
    void esDisjunto() {
        // Grafo inicial vacío (tu código devuelve false si hay 0 nodos)
        assertFalse(grafo.esDisjunto());

        // Grafo conectado
        grafo.añadir(new Tripleta("A", "conecta", "B"));
        grafo.añadir(new Tripleta("B", "conecta", "C"));
        assertFalse(grafo.esDisjunto(), "El grafo está conectado, no debe ser disjunto");

        // Hacemos el grafo disjunto añadiendo un subgrafo aislado
        grafo.añadir(new Tripleta("X", "conecta", "Y"));
        assertTrue(grafo.esDisjunto(), "El grafo tiene dos subgrafos desconectados, debe ser disjunto");
    }

    @Test
    void buscarFisicoMismaCiudad() {
        // Configuramos la persona de referencia
        grafo.añadir(new Tripleta("PersonaReferencia", "nace_en", "CiudadX"));

        // Configuramos a un Físico que nació en la misma ciudad
        grafo.añadir(new Tripleta("Fisico1", "nace_en", "CiudadX"));
        grafo.añadir(new Tripleta("Fisico1", "profesion", "tipo:Fisico"));

        // Configuramos a un Físico que nació en otra ciudad (para despistar)
        grafo.añadir(new Tripleta("Fisico2", "nace_en", "CiudadY"));
        grafo.añadir(new Tripleta("Fisico2", "profesion", "tipo:Fisico"));

        // Configuramos a alguien de la misma ciudad que NO es físico
        grafo.añadir(new Tripleta("Matematico", "nace_en", "CiudadX"));
        grafo.añadir(new Tripleta("Matematico", "profesion", "tipo:Matematico"));

        // Prueba de éxito
        String resultado = grafo.buscarFisicoMismaCiudad("PersonaReferencia");
        assertEquals("Fisico1", resultado, "Debería encontrar al físico de la misma ciudad");

        // Prueba de fallo (no hay otro físico en CiudadY para Fisico2)
        String resultadoFallido = grafo.buscarFisicoMismaCiudad("Fisico2");
        assertEquals("No encontrado", resultadoFallido);

        // Prueba de fallo (persona sin ciudad registrada)
        grafo.añadir(new Tripleta("PersonaSinCiudad", "es_amigo_de", "Alguien"));
        String resultadoSinCiudad = grafo.buscarFisicoMismaCiudad("PersonaSinCiudad");
        assertEquals("No encontrado", resultadoSinCiudad);
    }
}