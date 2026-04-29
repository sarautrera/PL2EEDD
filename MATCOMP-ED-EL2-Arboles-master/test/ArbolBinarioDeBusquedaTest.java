import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArbolBinarioDeBusquedaTest {

    private ArbolBinarioDeBusqueda<Integer> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ArbolBinarioDeBusqueda<>();
        // estructura para las pruebas
        arbol.add(50);
        arbol.add(25);
        arbol.add(75);
        arbol.add(10);
        arbol.add(30);
    }

    @Test
    void getGrado() {
        Assertions.assertEquals(2, arbol.getGrado());
        ArbolBinarioDeBusqueda<Integer> vacio = new ArbolBinarioDeBusqueda<>();
        Assertions.assertEquals(0, vacio.getGrado());
    }

    @Test
    void getAltura() {
        Assertions.assertEquals(3, arbol.getAltura());
    }

    @Test
    void getListaDatosNivel() {
        Comparable[] nivel2 = arbol.getListaDatosNivel(2);
        Assertions.assertNotNull(nivel2);
        Assertions.assertEquals(2, nivel2.length);
        Assertions.assertEquals(25, nivel2[0]);
        Assertions.assertEquals(75, nivel2[1]);
    }

    @Test
    void contarNodosEnNivel() {
        Assertions.assertEquals(1, arbol.contarNodosEnNivel(1));
        Assertions.assertEquals(2, arbol.contarNodosEnNivel(2));
        Assertions.assertEquals(2, arbol.contarNodosEnNivel(3));
    }

    @Test
    void isArbolHomogeneo() {
        // con 10, 25, 30, 50, 75 es homogéneo (nodos con 0 o 2 hijos)
        Assertions.assertTrue(arbol.isArbolHomogeneo());
        arbol.add(80); // 75 ahora solo tiene un hijo derecho
        Assertions.assertFalse(arbol.isArbolHomogeneo());
    }

    @Test
    void isArbolCasiCompleto() {
        Assertions.assertTrue(arbol.isArbolCasiCompleto());
    }

    @Test
    void getCantidadNodos() {
        Assertions.assertEquals(5, arbol.getCantidadNodos());
    }

    @Test
    void getCamino() {
        Comparable[] camino = arbol.getCamino(30);
        Assertions.assertNotNull(camino);
        Assertions.assertEquals(3, camino.length); // 50 -> 25 -> 30
        Assertions.assertEquals(50, camino[0]);
        Assertions.assertEquals(25, camino[1]);
        Assertions.assertEquals(30, camino[2]);
    }

    @Test
    void add() {
        arbol.add(100);
        Assertions.assertEquals(6, arbol.getCantidadNodos());
        // verificamos que esté en el subárbol derecho del 75
        Assertions.assertEquals(100, arbol.getSubArbolDerecha().getSubArbolDerecha().dato);
    }

    @Test
    void getSubArbolDerecha() {
        Assertions.assertNotNull(arbol.getSubArbolDerecha());
        Assertions.assertEquals(75, arbol.getSubArbolDerecha().dato);
    }

    @Test
    void getSubArbolIzquierda() {
        Assertions.assertNotNull(arbol.getSubArbolIzquierda());
        Assertions.assertEquals(25, arbol.getSubArbolIzquierda().dato);
    }
}