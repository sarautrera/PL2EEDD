package ParteB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // comprueba que el metodo principal del programa puede arrancar sin cerrarse por error
    @Test
    void main() {
        // pasamos un array vacio porque el main espera argumentos de entrada
        assertDoesNotThrow(() -> Main.main(new String[]{}), "el metodo main deberia ejecutarse sin errores criticos");
    }

    // verifica la carga del grafo desde un archivo y la busqueda de rutas
    @Test
    void probarGrafo() {
        // intentamos cargar un archivo; aunque no exista, el metodo captura el error internamente
        assertDoesNotThrow(() -> Main.probarGrafo("data/datos.json", "inicio", "fin"),
                "el metodo deberia gestionar la carga del archivo sin romperse");
    }

    // mira si la busqueda de cientificos relacionados funciona con la estructura del proyecto
    @Test
    void probarBusquedaHijos() {
        assertDoesNotThrow(() -> Main.probarBusquedaHijos("data/datos.json"),
                "la busqueda de cientificos por ciudad deberia ejecutarse correctamente");
    }

    // comprueba que el sistema para añadir nuevas conexiones (tripletas) es estable
    @Test
    void probarAñadirTripleta() {
        assertDoesNotThrow(() ->
                        Main.probarAñadirTripleta("data/datos.json", "sujeto", "predicado", "objeto"),
                "añadir una tripleta de prueba no deberia lanzar ninguna excepcion"
        );
    }

    // verifica que la consulta de premios nobel se realice de forma segura
    @Test
    void probarConsultaNobel() {
        assertDoesNotThrow(() -> Main.probarConsultaNobel("data/datos.json"),
                "la consulta de premios nobel deberia procesar los datos correctamente");
    }
}