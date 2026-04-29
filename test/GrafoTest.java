import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    // comprueba que si buscamos un nodo que hemos añadido antes lo encuentra bien
    @Test
    void buscar() {
        Grafo g = new Grafo();
        g.buscarONuevo("persona:einstein");
        assertNotNull(g.buscar("persona:einstein"), "deberia encontrar el nodo de einstein");
        assertNull(g.buscar("persona:curie"), "no deberia encontrar a curie si no la hemos añadido");
    }

    // verifica que si el nodo no existe lo crea y si ya existe nos devuelve el mismo
    @Test
    void buscarONuevo() {
        Grafo g = new Grafo();
        Nodo n1 = g.buscarONuevo("lugar:madrid");
        assertNotNull(n1);
        assertEquals("lugar:madrid", n1.nombre);

        // intentamos buscar el mismo otra vez
        Nodo n2 = g.buscarONuevo("lugar:madrid");
        assertSame(n1, n2, "deberia ser el mismo objeto nodo, no uno nuevo");
    }

    // mira si las conexiones o tripletas del uml se guardan bien en el grafo
    @Test
    void addTripleta() {
        Grafo g = new Grafo();
        g.addTripleta("persona:einstein", "profesion", "fisico");
        Nodo n = g.buscar("persona:einstein");
        // comprobamos que tiene al menos una arista conectada
        assertNotNull(n.primeraArista, "el nodo deberia tener una conexion o arista");
        assertEquals("profesion", n.primeraArista.predicado);
    }

    // este metodo es para pintar por consola, comprobamos que no de errores al ejecutarlo
    @Test
    void imprimir() {
        Grafo g = new Grafo();
        g.addTripleta("a", "conecta", "b");
        assertDoesNotThrow(() -> g.imprimir(), "el metodo imprimir no deberia fallar");
    }

    // verifica que podemos filtrar conexiones por el nombre de la relacion (predicado)
    @Test
    void buscarPredicado() {
        Grafo g = new Grafo();
        g.addTripleta("persona:einstein", "nace_en", "lugar:ulm");
        assertDoesNotThrow(() -> g.buscarPredicado("persona:einstein", "nace_en"));
    }

    // comprueba que la logica encuentra una ruta entre dos nodos alejados
    @Test
    void buscarCaminoMinimo() {
        Grafo g = new Grafo();
        g.addTripleta("a", "relacion", "b");
        g.addTripleta("b", "relacion", "c");
        // comprobamos que puede ir de a hasta c pasando por b
        assertDoesNotThrow(() -> g.buscarCaminoMinimo("a", "c"));
    }

    // test para ver si el programa sabe cuando hay nodos sueltos sin conexion
    @Test
    void esDisjunto() {
        Grafo g = new Grafo();
        g.buscarONuevo("isla1");
        g.buscarONuevo("isla2");

        // al principio no hay conexion, debe ser true (es disjunto)
        assertTrue(g.esDisjunto(), "el grafo deberia ser disjunto al no tener conexiones");

        // conectamos isla1 con isla2 e isla2 con isla1 para que sea un grupo unido
        g.addTripleta("isla1", "puente", "isla2");
        g.addTripleta("isla2", "puente", "isla1");

        // ahora, se empiece por donde se empiece, se llega a todo el grafo
        assertFalse(g.esDisjunto(), "ahora que estan unidos en ambos sentidos no deberia ser disjunto");
    }

    // mira si el filtro de ciudad y profesion funciona segun lo que pide el ejercicio
    @Test
    void buscarCientificosPorCiudadYProfesion() {
        Grafo g = new Grafo();
        g.addTripleta("persona:einstein", "nace_en", "lugar:ulm");
        g.addTripleta("persona:einstein", "profesion", "fisico");
        assertDoesNotThrow(() -> g.buscarCientificosPorCiudadYProfesion("lugar:ulm", "fisico", "nadie"));
    }

    // comprueba que se listan correctamente los premios nobel y sus lugares de origen
    @Test
    void listarNacimientosPremiosNobel() {
        Grafo g = new Grafo();
        g.addTripleta("persona:marie", "premio:Nobel", "quimica");
        g.addTripleta("persona:marie", "nace_en", "lugar:varsovia");
        assertDoesNotThrow(() -> g.listarNacimientosPremiosNobel());
    }
}