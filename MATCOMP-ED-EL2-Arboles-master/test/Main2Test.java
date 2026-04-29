import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Main2Test {

    // comprueba que la funcion de suma manual funciona correctamente con un arbol pequeño
    @Test
    void sumarEnMain() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        // si el arbol esta vacio la suma deberia ser cero
        assertEquals(0, Main2.sumarEnMain(null), "la suma de un arbol nulo debe ser cero");

        // añadimos unos pocos numeros: 10 de raiz, 5 a la izquierda y 15 a la derecha
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);

        // la suma total deberia ser 30
        int resultado = Main2.sumarEnMain(arbol);
        assertEquals(30, resultado, "la suma de 10, 5 y 15 deberia ser 30");
    }
}