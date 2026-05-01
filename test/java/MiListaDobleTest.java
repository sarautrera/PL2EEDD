import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MiListaDobleTest {

    @Test
    void agregar() {
        MiListaDoble<String> lista = new MiListaDoble<>();
        lista.agregar("Primero");
        lista.agregar("Segundo");

        assertEquals(2, lista.size(), "El tamaño debería ser 2 tras agregar dos elementos.");
        assertEquals("Primero", lista.getDatoEn(0));
        assertEquals("Segundo", lista.getDatoEn(1));
    }

    @Test
    void agregarTodo() {
        MiListaDoble<Integer> lista1 = new MiListaDoble<>();
        lista1.agregar(1);
        lista1.agregar(2);

        MiListaDoble<Integer> lista2 = new MiListaDoble<>();
        lista2.agregar(3);
        lista2.agregar(4);

        lista1.agregarTodo(lista2);

        assertEquals(4, lista1.size(), "El tamaño total debería ser 4.");
        assertEquals(1, lista1.getDatoEn(0));
        assertEquals(4, lista1.getDatoEn(3));
    }

    @Test
    void getDatoEn() {
        MiListaDoble<String> lista = new MiListaDoble<>();
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");

        assertEquals("A", lista.getDatoEn(0));
        assertEquals("B", lista.getDatoEn(1));
        assertEquals("C", lista.getDatoEn(2));
    }

    @Test
    void obtenerSumaEnteros() {
        MiListaDoble<Integer> lista = new MiListaDoble<>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        // La suma debe ser 60
        assertEquals(60, lista.obtenerSumaEnteros(), "La suma de 10+20+30 debería ser 60.");
    }

    @Test
    void estaVacia() {
        MiListaDoble<Double> lista = new MiListaDoble<>();
        assertTrue(lista.estaVacia(), "La lista recién creada debería estar vacía.");

        lista.agregar(1.5);
        assertFalse(lista.estaVacia(), "La lista no debería estar vacía tras agregar un elemento.");
    }

    @Test
    void size() {
        MiListaDoble<String> lista = new MiListaDoble<>();
        assertEquals(0, lista.size());

        lista.agregar("Uno");
        assertEquals(1, lista.size());

        lista.agregar("Dos");
        assertEquals(2, lista.size());
    }

    @Test
    void testToString() {
        MiListaDoble<String> lista = new MiListaDoble<>();

        // Test lista vacía
        assertEquals("[]", lista.toString());

        // Test con elementos
        lista.agregar("Manzana");
        lista.agregar("Pera");
        assertEquals("[Manzana, Pera]", lista.toString());
    }
}