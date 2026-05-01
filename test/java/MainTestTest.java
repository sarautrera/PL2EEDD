import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTestTest {
    @Test
    public void testConectividadYBusqueda() {
        // 1. Setup: Crear y cargar
        Grafo<String> miGrafo = new Grafo<>();
        miGrafo.cargarArchivo("datos/grafo_disjunto.json");

        // 2. Comprobar conectividad
        // assertFalse verifica que la condición sea falsa; si no, el test falla
        assertFalse(hayCamino(miGrafo, "A", "Y"), "El grafo es disjunto, no debería haber camino entre A y Y");

        // 3. Probar búsqueda de físico
        miGrafo.cargarArchivo("datos/info_Nobel.json");
        String fisico = "persona:Albert Einstein";
        miGrafo.buscarFisicoMismaCiudad(fisico);

        miGrafo.imprimirGrafo();
        miGrafo.buscarFisicoMismaCiudad(fisico);
        miGrafo.listarLugaresNacimientoNobelistas();

    }

    // Método auxiliar
    public boolean hayCamino(Grafo<String> g, String origen, String destino) {
        Cola<String> resultado = g.minCamino(origen, destino);
        return resultado != null && !resultado.isEmpty();

    }
}