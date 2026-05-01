public class MainTest {
    public static void main(String[] args) {
        Grafo<String> miGrafo = new Grafo<>();

        // 1. CARGA EL ARCHIVO
        miGrafo.cargarArchivo("datos/grafo_disjunto.json");

        // 2. PRUEBA DE CONEXIÓN
        // Intentamos ir de A hasta Y
        System.out.println("Buscando camino de A a Y:");
        Cola<String> camino = miGrafo.minCamino("A", "Y");

        //Comprobamos si hay camino con nuestro metodo
        boolean conectado = hayCamino(miGrafo, "A", "Y");

        if (conectado) {
            System.out.println("El grafo es conexo.");
        } else {
            System.out.println("El grafo no es conexo");
        }
        //Búsqueda del fisico:
        Grafo<String> Grafo1=new Grafo<>();
        Grafo1.cargarArchivo("datos/info_nobel.json");
        String fisico="persona:Albert Einstein";
        Grafo1.imprimirGrafo();
        Grafo1.buscarFisicoMismaCiudad(fisico);
        Grafo1.listarLugaresNacimientoNobelistas();
    }
    public static boolean hayCamino(Grafo<String> g, String origen, String destino) {
        Cola<String> resultado = g.minCamino(origen, destino);
        //Comprobamos si nos devolvió algo con contenido:
        return resultado != null && !resultado.isEmpty();
    }
}
