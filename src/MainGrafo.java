public class MainGrafo {
    public static void main(String[] args) {
        System.out.println("--- RESOLUCIÓN DE CONSULTAS ---");

        GrafoConocimiento grafo = new GrafoConocimiento();

        // CARGA DE DATOS
        grafo.añadir(new Tripleta("persona:Albert Einstein", "premio:Nobel", "1921"));
        grafo.añadir(new Tripleta("persona:Albert Einstein", "nace_en", "lugar:Ulm"));
        grafo.añadir(new Tripleta("persona:Albert Einstein", "profesion", "tipo:Fisico"));

        grafo.añadir(new Tripleta("persona:Hermann Anselm", "nace_en", "lugar:Ulm"));
        grafo.añadir(new Tripleta("persona:Hermann Anselm", "profesion", "tipo:Fisico"));

        grafo.añadir(new Tripleta("persona:Marie Curie", "premio:Nobel", "1903"));
        grafo.añadir(new Tripleta("persona:Marie Curie", "nace_en", "lugar:Varsovia"));

        // Físico famoso en la misma ciudad que Einstein
        System.out.println("\n Un físico nacido en la misma ciudad de Einstein es: ");
        String resultadoFisico = grafo.buscarFisicoMismaCiudad("persona:Albert Einstein");
        System.out.println(resultadoFisico);

        // Añadir a Antonio y listar nacimientos de premios Nobel
        System.out.println("\n Lista de lugares de nacimiento de premios Nobel con Antonio en incluido en la lista: ");
        grafo.añadir(new Tripleta("persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros"));

        // Recorremos los nodos usando los nuevos getters
        String[] todosLosNodos = grafo.getNodos();
        Tripleta[] todasLasAristas = grafo.getAristas();

        for (int i = 0; i < grafo.getNumeroNodos(); i++) {
            String nodoActual = todosLosNodos[i];
            boolean tieneNobel = false;
            String lugarNacimiento = null;

            // Buscamos las relaciones de este nodo
            for (int j = 0; j < grafo.getNumeroAristas(); j++) {
                if (todasLasAristas[j].sujeto.equals(nodoActual)) {
                    if (todasLasAristas[j].predicado.equals("premio:Nobel")) tieneNobel = true;
                    if (todasLasAristas[j].predicado.equals("nace_en")) lugarNacimiento = todasLasAristas[j].objeto;
                }
            }

            if (tieneNobel && lugarNacimiento != null) {
                System.out.println(nodoActual + " tiene un Nobel y nacio en " + lugarNacimiento);
            }
        }

        // Camino mínimo
        System.out.println("\n Camino mínimo entre Einstein y su ciudad:");
        MiListaDoble<String> camino = grafo.getCaminoMinimo("persona:Albert Einstein", "lugar:Ulm");
        System.out.println(camino.toString());

        // Comprobación de Grafos Disjuntos
        System.out.println("\n ¿El grafo es disjunto?: " + grafo.esDisjunto());
    }
}