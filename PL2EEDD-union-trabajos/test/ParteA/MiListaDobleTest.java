package ParteA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiListaDobleTest {

    private MiListaDoble<Integer> lista;

    @BeforeEach
    void setUp() {
        // Inicializamos una nueva lista antes de cada prueba
        lista = new MiListaDoble<>();
    }

    @Test
    void agregar() {
        assertTrue(lista.estaVacia(), "La lista debería estar vacía al inicializar");

        lista.agregar(10);

        assertFalse(lista.estaVacia(), "La lista no debería estar vacía tras añadir un elemento");
        assertEquals(1, lista.size(), "El tamaño de la lista debería ser 1");
        assertEquals(10, lista.getDatoEn(0), "El elemento en la posición 0 debería ser 10");

        lista.agregar(20);

        assertEquals(2, lista.size(), "El tamaño de la lista debería ser 2");
        assertEquals(20, lista.getDatoEn(1), "El elemento en la posición 1 debería ser 20");
    }

    @Test
    void agregarTodo() {
        MiListaDoble<Integer> otraLista = new MiListaDoble<>();
        otraLista.agregar(100);
        otraLista.agregar(200);

        lista.agregarTodo(otraLista);

        assertEquals(2, lista.size(), "El tamaño de la lista debería ser 2");
        assertEquals(100, lista.getDatoEn(0), "El elemento en la posición 0 debería ser 100");
        assertEquals(200, lista.getDatoEn(1), "El elemento en la posición 1 debería ser 200");
    }

    @Test
    void getDatoEn() {
        lista.agregar(5);
        lista.agregar(15);
        lista.agregar(25);

        assertEquals(5, lista.getDatoEn(0));
        assertEquals(15, lista.getDatoEn(1));
        assertEquals(25, lista.getDatoEn(2));
    }

    @Test
    void obtenerSumaEnteros() {
        // Probamos con una lista vacía
        assertEquals(0, lista.obtenerSumaEnteros(), "La suma de una lista vacía debería ser 0");

        // Probamos con elementos numéricos
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        assertEquals(60, lista.obtenerSumaEnteros(), "La suma debería ser 10 + 20 + 30 = 60");
    }

    @Test
    void estaVacia() {
        assertTrue(lista.estaVacia(), "La lista debería estar vacía");

        lista.agregar(42);

        assertFalse(lista.estaVacia(), "La lista no debería estar vacía tras añadir elementos");
    }

    @Test
    void size() {
        assertEquals(0, lista.size(), "El tamaño inicial debería ser 0");

        lista.agregar(1);
        lista.agregar(2);

        assertEquals(2, lista.size(), "El tamaño debería ser 2");
    }

    @Test
    void testToString() {
        // Lista vacía
        assertEquals("[]", lista.toString(), "La representación de la lista vacía debería ser '[]'");

        // Lista con elementos
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        assertEquals("[1, 2, 3]", lista.toString(), "La representación de la lista debería ser '[1, 2, 3]'");
    }
}