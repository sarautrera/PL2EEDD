import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArbolBinarioDeBusquedaEnterosTest {

    @Test
    void getSuma() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        //arbol vacío
        Assertions.assertEquals(0, arbol.getSuma(), "La suma de un árbol vacío debe ser 0");

        // arbol con un solo nodo
        arbol.add(50);
        Assertions.assertEquals(50, arbol.getSuma(), "La suma con un solo nodo debe ser su propio valor");

        //arbol con varios nodos
        arbol.add(25);
        arbol.add(75);
        arbol.add(10);
        arbol.add(30);

        //calculo: 50 + 25 + 75 + 10 + 30 = 190
        Assertions.assertEquals(190, arbol.getSuma(), "La suma total de los nodos debería ser 190");

        //valores negativos
        ArbolBinarioDeBusquedaEnteros arbolNegativo = new ArbolBinarioDeBusquedaEnteros();
        arbolNegativo.add(10);
        arbolNegativo.add(-5);
        arbolNegativo.add(15);

        //calculo: 10 + (-5) + 15 = 20
        Assertions.assertEquals(20, arbolNegativo.getSuma(), "Debe sumar correctamente valores negativos");
    }
}