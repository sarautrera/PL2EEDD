public class ColaGrafo<T> {
    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo(T d) { this.dato = d; }
    }

    private Nodo frente; //Primer elemento de la cola
    private Nodo fin; //Último elemento de la cola

    public void encolar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (frente == null) {
            frente = fin = nuevo;
        }
        else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public T desencolar() {
        if (this.estaVacia()) {
            return null;
        }
        // Guardamos el dato que está en el frente para devolverlo al final
        T datoExtraido = frente.dato;
        // Avanzamos el puntero del frente al siguiente nodo
        frente = frente.siguiente;
        //Si después de avanzar, el frente es null, la cola se ha quedado vacía.
        if (frente == null) {
            fin = null;
        }
        return datoExtraido;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}
