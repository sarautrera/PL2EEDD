public class GrafoConocimiento {
    private String[] nodos = new String[100];
    private Tripleta[] aristas = new Tripleta[100];
    private int numeroNodos = 0;
    private int numeroAristas = 0;

    //GETS
    public String[] getNodos() { return nodos; }
    public Tripleta[] getAristas() { return aristas; }
    public int getNumeroNodos() { return numeroNodos; }
    public int getNumeroAristas() { return numeroAristas; }

    //Añade una nueva relación
    public void añadir(Tripleta tripleta) {
        if (numeroAristas >= aristas.length) {
            redimensionarAristas();
        }
        aristas[numeroAristas++] = tripleta;
        registrarNodo(tripleta.sujeto);
        registrarNodo(tripleta.objeto);
    }

    private void registrarNodo(String nombre) {
        for (int i = 0; i < numeroNodos; i++) {
            if (nodos[i].equals(nombre)) return;
        }
        if (numeroNodos >= nodos.length) {
            redimensionarNodos();
        }
        nodos[numeroNodos++] = nombre;
    }

    private int getIndiceNodo(String nombre) {
        for (int i = 0; i < numeroNodos; i++) {
            if (nodos[i].equals(nombre)) return i;
        }
        return -1;
    }

    // Camino más corto entre un nodo de inicio y uno de fin.
    public MiListaDoble<String> getCaminoMinimo(String inicio, String fin) {
        // Obtiene posiciones y prepara las variables de resultado
        int posicionIn = getIndiceNodo(inicio);
        int posicionFin = getIndiceNodo(fin);
        MiListaDoble<String> caminoResultado = new MiListaDoble<>();

        if (posicionIn == -1 || posicionFin == -1) return caminoResultado;

        // Inicializa estructuras (visitados y registro de padres)
        boolean[] visitados = new boolean[numeroNodos];
        int[] padres = new int[numeroNodos];
        for (int i = 0; i < numeroNodos; i++) padres[i] = -1;

        ColaGrafo<Integer> cola = new ColaGrafo<>();
        cola.encolar(posicionIn);
        visitados[posicionIn] = true;

        boolean encontrado = false;

        // Recorrer el grafo nivel por nivel (Búsqueda en anchura)
        while (!cola.estaVacia()) {
            int actual = cola.desencolar();

            // Si llegamos al destino, detenemos la búsqueda
            if (actual == posicionFin) {
                encontrado = true;
                break;
            }

            // Explorar los vecinos del nodo actual
            for (int i = 0; i < numeroAristas; i++) {
                if (aristas[i].sujeto.equals(nodos[actual])) {
                    int vecino = getIndiceNodo(aristas[i].objeto);
                    if (vecino != -1 && !visitados[vecino]) {
                        visitados[vecino] = true;
                        padres[vecino] = actual;
                        cola.encolar(vecino);
                    }
                }
            }
        }

        // Reconstruir y devolver el camino si se alcanzó el destino
        if (encontrado) { reconstruirCamino(padres, posicionFin, caminoResultado); }
        return caminoResultado;
    }

    private void reconstruirCamino(int[] padres, int actual, MiListaDoble<String> lista) {
        if (actual == -1) return;
        reconstruirCamino(padres, padres[actual], lista);
        lista.agregar(nodos[actual]);
    }

    // Verifica si el grafo está compuesto por partes desconectadas entre sí.
    public boolean esDisjunto() {
        if (numeroNodos == 0) return false;

        // Empieza por el primer nodo
        boolean[] visitados = new boolean[numeroNodos];
        ColaGrafo<Integer> cola = new ColaGrafo<>();
        cola.encolar(0);
        visitados[0] = true;
        int alcanzados = 1;

        // Recorrer el grafo en todas direcciones (tratándolo como no dirigido)
        while (!cola.estaVacia()) {
            int actual = cola.desencolar();
            for (int i = 0; i < numeroAristas; i++) {
                int vecino = -1;
                if (aristas[i].sujeto.equals(nodos[actual])) vecino = getIndiceNodo(aristas[i].objeto);
                else if (aristas[i].objeto.equals(nodos[actual])) vecino = getIndiceNodo(aristas[i].sujeto);

                if (vecino != -1 && !visitados[vecino]) {
                    visitados[vecino] = true;
                    cola.encolar(vecino);
                    alcanzados++;
                }
            }
        }

        // Comparar nodos para saber si quedó alguno aislado
        return alcanzados < numeroNodos;
    }

    public String buscarFisicoMismaCiudad(String personaReferencia) {
        String ciudad = "";

        // Encontrar la ciudad de nacimiento de la persona de referencia
        for (int i = 0; i < numeroAristas; i++) {
            if (aristas[i].sujeto.equals(personaReferencia) && aristas[i].predicado.equals("nace_en")) {
                ciudad = aristas[i].objeto;
                break;
            }
        }

        // Si no se sabe dónde nació, no se puede continuar
        if (ciudad.equals("")) return "No encontrado";

        // Buscar en todos los demás nodos para encontrar coincidencias
        for (int i = 0; i < numeroNodos; i++) {
            if (nodos[i].equals(personaReferencia)){
                continue; // Saltar a la propia persona de referencia
            }

            boolean esFisico = false;
            boolean mismaCiudad = false;

            // Revisar las relaciones del nodo actual
            for (int j = 0; j < numeroAristas; j++) {
                if (aristas[j].sujeto.equals(nodos[i])) {
                    if (aristas[j].predicado.equals("profesion") && aristas[j].objeto.equals("tipo:Fisico")){
                        esFisico = true;
                    }
                    if (aristas[j].predicado.equals("nace_en") && aristas[j].objeto.equals(ciudad)){
                        mismaCiudad = true;
                    }
                }
            }

            // Retornar el nombre si cumple ambas condiciones
            if (esFisico && mismaCiudad){
                return nodos[i];
            }
        }
        return "No encontrado";
    }

    private void redimensionarNodos() {
        String[] nuevo = new String[nodos.length * 2];
        for (int i = 0; i < nodos.length; i++) nuevo[i] = nodos[i];
        nodos = nuevo;
    }

    private void redimensionarAristas() {
        Tripleta[] nuevo = new Tripleta[aristas.length * 2];
        for (int i = 0; i < aristas.length; i++) nuevo[i] = aristas[i];
        aristas = nuevo;
    }
}