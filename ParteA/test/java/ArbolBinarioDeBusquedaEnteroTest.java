import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioDeBusquedaEnteroTest {

    private ArbolBinarioDeBusquedaEntero arbol;

    @BeforeEach
    void setUp() {
        // Inicializamos una nueva instancia antes de cada test
        arbol = new ArbolBinarioDeBusquedaEntero();
    }

    @Test
    void crearInstancia() {
        // Comprobamos que el método crea una instancia válida y que está vacía
        ArbolBinarioDeBusquedaEntero nuevaInstancia = arbol.crearInstancia();

        assertNotNull(nuevaInstancia, "La instancia no debería ser nula");
        assertTrue(nuevaInstancia.isVacio(), "La instancia creada debería estar vacía");
    }

    @Test
    void getSuma() {
        // 1. Caso de árbol vacío: la suma debe ser 0
        assertEquals(0, arbol.getSuma(), "La suma de un árbol vacío debe ser 0");

        // 2. Caso con un solo elemento (raíz)
        arbol.add(20);
        assertEquals(20, arbol.getSuma(), "La suma debe ser 20 al contener solo la raíz");

        // 3. Caso con varios elementos (raíz, hijo izquierdo, hijo derecho)
        arbol.add(10);
        arbol.add(30);

        // Suma esperada: 20 + 10 + 30 = 60
        assertEquals(60, arbol.getSuma(), "La suma total debe ser 60");
    }
}