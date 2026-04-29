public class Grafo {
    private Nodo primero;

    public Grafo(){
        this.primero=null;
    }

    // recorre la lista de nodos para ver si existe uno con el nombre que buscamos
    public Nodo buscar(String nombre){
        Nodo actual=primero;
        while(actual!=null){
            if(actual.nombre.equals(nombre)){
                return actual;
            }
            actual=actual.siguiente;
        }
        return null;
    }

    // busca un nodo y si no lo encuentra lo crea y lo añade al principio de la lista
    public Nodo buscarONuevo(String nombre){
        Nodo actual=primero;
        while(actual!=null){
            if(actual.nombre.equals(nombre)){
                return actual;
            }
            actual=actual.siguiente;
        }
        Nodo nuevo= new Nodo(nombre);
        nuevo.siguiente=primero;
        primero=nuevo;
        return nuevo;
    }

    // conecta dos nodos mediante una relacion o arista usando los nombres de los diagramas uml
    public void addTripleta(String sujeto, String predicado, String objeto) {
        Nodo nodoSujeto = buscarONuevo(sujeto);
        Nodo nodoObjeto = buscarONuevo(objeto);
        Arista nueva = new Arista(predicado, nodoObjeto);
        nueva.siguiente = nodoSujeto.primeraArista;
        nodoSujeto.primeraArista = nueva;
    }

    // muestra por pantalla todos los nodos y las conexiones que tienen cada uno
    public void imprimir(){
        Nodo actual=primero;
        while(actual!=null){
            System.out.println(actual.nombre+" ");
            Arista arista= actual.primeraArista;
            while(arista!=null){
                System.out.println("["+arista.predicado+" "+arista.destino.nombre+"]");
                arista=arista.siguiente;
            }
            System.out.println();
            actual=actual.siguiente;
        }
    }

    // busca un nodo especifico y filtra sus conexiones segun el tipo de relacion que pidamos
    public void buscarPredicado(String sujetoBuscado, String predicadoBuscado) {
        Nodo s = buscar(sujetoBuscado);
        Arista a = s.primeraArista;
        System.out.println("Resultados para " + sujetoBuscado + " con relación " + predicadoBuscado + ":");
        while (a != null) {
            if (a.predicado.equals(predicadoBuscado)) {
                System.out.println("- " + a.destino.nombre);
            }
            a = a.siguiente;
        }
    }

    // encuentra la ruta mas corta entre dos puntos usando una busqueda por niveles
    public void buscarCaminoMinimo(String nombreOrigen, String nombreDestino) {
        // pone todos los nodos como no visitados para empezar la busqueda de cero
        Nodo aux = primero;
        while (aux != null) {
            aux.visitado = false;
            aux.padre = null;
            aux = aux.siguiente;
        }

        // localiza los nodos de inicio y fin en el grafo
        Nodo inicio = buscar(nombreOrigen);
        Nodo fin = buscar(nombreDestino);

        if (inicio == null || fin == null) {
            System.out.println("El origen o el destino no existen.");
            return;
        }

        // usa una cola para ir explorando los nodos vecinos paso a paso
        ColaNodos cola = new ColaNodos();
        inicio.visitado = true;
        cola.encolar(inicio);

        boolean encontrado = false;
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();

            if (actual == fin) {
                encontrado = true;
                break;
            }

            Arista aristaAct = actual.primeraArista;
            while (aristaAct != null) {
                Nodo vecino = aristaAct.destino;
                if (!vecino.visitado) {
                    vecino.visitado = true;
                    vecino.padre = actual; // guarda el nodo anterior para reconstruir el camino
                    cola.encolar(vecino);
                }
                aristaAct = aristaAct.siguiente;
            }
        }

        // si llegamos al destino muestra la ruta completa
        if (encontrado) {
            imprimirCaminoRecursivo(fin);
            System.out.println();
        } else {
            System.out.println("No hay conexión entre los nodos.");
        }
    }

    // escribe el camino de forma ordenada yendo desde el final hacia atras
    private void imprimirCaminoRecursivo(Nodo n) {
        if (n == null) return;
        imprimirCaminoRecursivo(n.padre);
        System.out.print((n.padre == null ? "" : " -> ") + n.nombre);
    }

    // comprueba si hay partes del grafo que estan aisladas y no se pueden alcanzar
    public boolean esDisjunto() {
        if (primero == null) return false;

        // marca tdod como no visitado para contar cuantos nodos hay en total
        Nodo aux = primero;
        int totalNodos = 0;
        while (aux != null) {
            aux.visitado = false;
            totalNodos++;
            aux = aux.siguiente;
        }

        // recorre el grafo desde el primer nodo para ver a cuantos puede llegar
        ColaNodos cola = new ColaNodos();
        primero.visitado = true;
        cola.encolar(primero);
        int visitados = 0;

        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            visitados++;
            Arista a = actual.primeraArista;
            while (a != null) {
                if (!a.destino.visitado) {
                    a.destino.visitado = true;
                    cola.encolar(a.destino);
                }
                a = a.siguiente;
            }
        }

        // si el numero de nodos visitados es menor al total es que hay islas sueltas
        return visitados < totalNodos;
    }

    // filtra nodos que coincidan con una ciudad y profesion concretas sin contar a una persona
    public void buscarCientificosPorCiudadYProfesion(String ciudad, String profesion, String ignorarNombre) {
        Nodo temp = primero;

        while (temp != null) {
            // comprueba que no sea el nodo que queremos saltarnos
            if (!temp.nombre.equals(ignorarNombre)) {
                boolean naceEnCiudad = false;
                boolean esProfesion = false;

                // mira todas las etiquetas de las conexiones de cada nodo
                Arista a = temp.primeraArista;
                while (a != null) {
                    if (a.predicado.equals("nace_en") && a.destino.nombre.equals(ciudad)) {
                        naceEnCiudad = true;
                    }
                    if (a.predicado.equals("profesion") && a.destino.nombre.equals(profesion)) {
                        esProfesion = true;
                    }
                    a = a.siguiente;
                }

                // muestra el nombre si cumple los dos requisitos a la vez
                if (naceEnCiudad && esProfesion) {
                    System.out.println(temp.nombre);
                }
            }
            temp = temp.siguiente;
        }
    }

    // busca que nodos tienen la etiqueta de premio nobel y dice donde nacieron
    public void listarNacimientosPremiosNobel() {
        Nodo actual = primero;
        while (actual != null) {
            boolean tieneNobel = false;
            String lugarNacimiento = "desconocido";

            Arista a = actual.primeraArista;
            while (a != null) {
                if (a.predicado.equals("premio:Nobel")) {
                    tieneNobel = true;
                }
                if (a.predicado.equals("nace_en")) {
                    lugarNacimiento = a.destino.nombre;
                }
                a = a.siguiente;
            }

            if (tieneNobel) {
                System.out.println(actual.nombre + " nacio en: " + lugarNacimiento);
            }
            actual = actual.siguiente;
        }
    }
}