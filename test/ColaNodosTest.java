import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColaNodosTest {

    // comprueba que al añadir nodos la cola los guarda correctamente
    @Test
    void encolar() {
        ColaNodos cola = new ColaNodos();
        Nodo n1 = new Nodo("nodo prueba");

        assertTrue(cola.estaVacia(), "la cola deberia estar vacia al principio");
        cola.encolar(n1);
        assertFalse(cola.estaVacia(), "la cola ya no deberia estar vacia");
    }

    // verifica que el primer nodo en entrar es el primero en salir (sistema fifo)
    @Test
    void desencolar() {
        ColaNodos cola = new ColaNodos();
        Nodo n1 = new Nodo("primero");
        Nodo n2 = new Nodo("segundo");

        cola.encolar(n1);
        cola.encolar(n2);

        // al sacar el primero, tiene que coincidir con n1
        Nodo sacado = cola.desencolar();
        assertEquals("primero", sacado.nombre, "deberia salir el primer nodo que entro");

        // si sacamos todos, el siguiente intento debe dar null
        cola.desencolar();
        assertNull(cola.desencolar(), "si la cola esta vacia debe devolver null");
    }

    // revisa si la cola detecta bien cuando no tiene elementos
    @Test
    void estaVacia() {
        ColaNodos cola = new ColaNodos();
        assertTrue(cola.estaVacia());

        cola.encolar(new Nodo("test"));
        assertFalse(cola.estaVacia());
    }
}