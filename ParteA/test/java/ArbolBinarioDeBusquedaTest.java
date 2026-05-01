import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArbolBinarioDeBusquedaTest {

    private ArbolBinarioDeBusqueda<Integer> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ArbolBinarioDeBusqueda<>();
    }

    @Test
    void add() {
        assertTrue(arbol.isVacio());
        arbol.add(20);
        assertFalse(arbol.isVacio());
        assertEquals(20, arbol.getDatoRaiz());

        arbol.add(10);
        arbol.add(30);

        assertEquals(10, arbol.getSubArbolIzquierda().getDatoRaiz());
        assertEquals(30, arbol.getSubArbolDerecha().getDatoRaiz());
    }

    @Test
    void crearInstancia() {
        ArbolBinarioDeBusqueda<Integer> nuevaInstancia = arbol.crearInstancia();
        assertNotNull(nuevaInstancia);
        assertTrue(nuevaInstancia.isVacio());
    }

    @Test
    void isVacio() {
        assertTrue(arbol.isVacio());
        arbol.add(50);
        assertFalse(arbol.isVacio());
    }

    @Test
    void getDatoRaiz() {
        arbol.add(42);
        assertEquals(42, arbol.getDatoRaiz());
    }

    @Test
    void getSubArbolIzquierda() {
        arbol.add(20);
        assertNotNull(arbol.getSubArbolIzquierda());
        assertTrue(arbol.getSubArbolIzquierda().isVacio());

        arbol.add(10);
        assertFalse(arbol.getSubArbolIzquierda().isVacio());
        assertEquals(10, arbol.getSubArbolIzquierda().getDatoRaiz());
    }

    @Test
    void getSubArbolDerecha() {
        arbol.add(20);
        assertNotNull(arbol.getSubArbolDerecha());
        assertTrue(arbol.getSubArbolDerecha().isVacio());

        arbol.add(30);
        assertFalse(arbol.getSubArbolDerecha().isVacio());
        assertEquals(30, arbol.getSubArbolDerecha().getDatoRaiz());
    }

    @Test
    void getAltura() {
        assertEquals(0, arbol.getAltura());
        arbol.add(20);
        assertEquals(1, arbol.getAltura());
        arbol.add(10);
        assertEquals(2, arbol.getAltura());
        arbol.add(30);
        assertEquals(2, arbol.getAltura());
        arbol.add(5);
        assertEquals(3, arbol.getAltura());
    }

    @Test
    void getListaOrdenCentral() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);

        MiListaDoble<Integer> lista = arbol.getListaOrdenCentral();
        assertNotNull(lista);
    }

    @Test
    void getListaPreOrden() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);

        MiListaDoble<Integer> lista = arbol.getListaPreOrden();
        assertNotNull(lista);
    }

    @Test
    void getListaPostOrden() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);

        MiListaDoble<Integer> lista = arbol.getListaPostOrden();
        assertNotNull(lista);
    }

    @Test
    void getCamino() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);

        // Verificamos el camino hacia un nodo existente
        MiListaDoble<Integer> caminoExistente = arbol.getCamino(30);
        assertNotNull(caminoExistente);
        assertFalse(caminoExistente.estaVacia());

        // Verificamos el camino para un elemento que no está en el árbol
        MiListaDoble<Integer> caminoInexistente = arbol.getCamino(99);
        assertNotNull(caminoInexistente);
        assertTrue(caminoInexistente.estaVacia());
    }
}