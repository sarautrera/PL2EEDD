import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaTest {

    @Test
    void push() {
        Cola<String> miCola=new Cola<>();

        miCola.push("a");
        miCola.push("b");
        miCola.push("c");

        assertNotNull(miCola,"La cola no deberia estar vacía");
        assertEquals("a", miCola.pop(), "Deberia coincidir con a");
        assertEquals("b", miCola.pop(), "Deberia coincidir con b");
        assertEquals("c", miCola.pop(), "Deberia coincidir con c");
    }

    @Test
    void pop() {
        Cola<String> miCola=new Cola<>();

        miCola.push("a");
        miCola.push("b");
        miCola.push("c");

        assertNotNull(miCola,"La cola no deberia estar vacía");
        assertEquals("a", miCola.pop(), "Deberia coincidir con a");
        assertEquals("b", miCola.pop(), "Deberia coincidir con b");
        assertEquals("c", miCola.pop(), "Deberia coincidir con c");

        assertTrue(miCola.isEmpty(), "La cola debería quedar vacía tras los pops");
    }

    @Test
    void isEmpty() {
        Cola<String> miCola = new Cola<>();

        assertTrue(miCola.isEmpty(), "Una cola nueva debe estar vacía");

        miCola.push("Dato");
        assertFalse(miCola.isEmpty(), "Tras un push la cola no debe estar vacía");

        miCola.pop();
        assertTrue(miCola.isEmpty(), "Despues de pop, marcar true en isEmpty");
    }
}