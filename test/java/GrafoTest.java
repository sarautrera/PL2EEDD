import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    @Test
    void add() {
        Grafo<String> miGrafo = new Grafo<>();

        miGrafo.add("Persona:Albert Einstein", "premio", "1921");

        Nodo<String> nodoEncontrado = miGrafo.getSujeto("Persona:Albert Einstein");

        assertNotNull(nodoEncontrado, "El nodo debería existir en el grafo tras usar add");
        assertEquals("Persona:Albert Einstein", nodoEncontrado.sujeto, "El nombre del sujeto no coincide");
    }

    @Test
    void getSujeto() {
        Grafo<String> miGrafo = new Grafo<>();
        miGrafo.add("Persona:Albert Einstein", "premio", "1921");
        Nodo<String> nodoEncontrado = miGrafo.getSujeto("Persona:Albert Einstein");

        assertNotNull(nodoEncontrado, "El nodo debería existir en el grafo");
        assertEquals("Persona:Albert Einstein", nodoEncontrado.sujeto, "El nombre del sujeto no coincide");

        // Buscamos algo en un grafo vacío
        Nodo<String> resultado = miGrafo.getSujeto("Sujeto inexistente");
        assertNull(resultado, "El método debería devolver null si el sujeto no existe");

    }

    @Test
    void minCamino() {
        Grafo<String> miGrafo = new Grafo<>();
        miGrafo.add("A", "conecta", "B");
        miGrafo.add("B", "conecta", "C");

        Cola<String> camino=miGrafo.minCamino("A","C");

        //Verificamos
        assertEquals("A", camino.pop());
        assertEquals("B", camino.pop());
        assertEquals("C", camino.pop());

    }

    @Test
    void agregarALista() {
        Grafo<String> miGrafo = new Grafo<>();
        ListaEnlazadaSimple<String> miLista = null;

        miLista = miGrafo.agregarALista(miLista, "A", null);

        // Verificamos que no es null y que contiene el dato
        assertNotNull(miLista, "La lista no debería estar vacía");
        assertEquals("A", miLista.nodo, "El primer nodo debería ser 'A'");

        // Añadimos un segundo elemento (debe quedar al frente)
        miLista = miGrafo.agregarALista(miLista, "B", "A");

        assertEquals("B", miLista.nodo, "El nuevo nodo debería ser 'B'");

        // Verificamos que 'B' apunta a 'A' (el anterior)
        assertNotNull(miLista.siguiente, "El siguiente nodo no debería ser null");
        assertEquals("A", miLista.siguiente.nodo, "El nodo siguiente debería ser 'A'");

    }

    @Test
    void estaEnLista() {
        Grafo<String> miGrafo = new Grafo<>();
        ListaEnlazadaSimple<String> miLista = null;

        miLista = miGrafo.agregarALista(miLista, "B", "A");
        miLista = miGrafo.agregarALista(miLista, "A", null);

        boolean resultado = miGrafo.estaEnLista(miLista, "A");
        boolean noEncontrado = miGrafo.estaEnLista(miLista, "Z");

        // Verificamos el resultado
        assertTrue(resultado, "A debe estar en la cola");
        assertFalse(noEncontrado, "Z no debe estar");
    }

    @Test
    void cargarArchivo() {
        Grafo<String> miGrafo = new Grafo<>();
        miGrafo.cargarArchivo("datos/info_nobel.json");

        // Comprobamos que un sujeto que sabemos que está en el JSON existe
        Nodo<String> nodo = miGrafo.getSujeto("persona:Albert Einstein");
        assertNotNull(nodo, "El archivo debería haber cargado a Albert Einstein");
        assertEquals("persona:Albert Einstein", nodo.sujeto);
    }

    @Test
    void buscarFisicoMismaCiudad() {
        Grafo<String> miGrafo=new Grafo<>();
        miGrafo.add("Einstein", "nace_en", "Ulm");
        miGrafo.add("Heisenberg", "nace_en", "Wurzburg");
        miGrafo.add("Planck", "nace_en", "Ulm");

        // Buscamos físicos en "Ulm"
        Cola<String> listaUlm = miGrafo.buscarFisicoMismaCiudad("Einstein");

        // Verificamos (deberían salir Einstein y Planck)
        assertNotNull(listaUlm, "La lista no debería ser null");

        // Comprobamos contenido
        assertEquals("Planck", listaUlm.pop());
        assertEquals("Einstein", listaUlm.pop());

    }

    @Test
    void listarLugaresNacimientoNobelistas() {
        Grafo<String> miGrafo = new Grafo<>();

        // 1. Añadimos datos de prueba
        miGrafo.add("Einstein", "nace_en", "Ulm");
        miGrafo.add("Planck", "nace_en", "Kiel");
        miGrafo.add("Heisenberg", "nace_en", "Wurzburg");

        // 2. Ejecutamos el método
        Cola<String> listaLugares = miGrafo.listarLugaresNacimientoNobelistas();

        // 3. Verificamos
        assertNotNull(listaLugares, "La lista no debería ser null");

        // Como tu método agregarALista inserta al principio, el último añadido (Wurzburg) estará primero
        assertEquals("Wurzburg", listaLugares.pop());
        assertEquals("Kiel", listaLugares.pop());
        assertEquals("Ulm", listaLugares.pop());
    }
    }
