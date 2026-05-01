package ParteA;
public class MiListaDoble<T> {

    private class NodoDE {
        T dato;
        NodoDE siguiente;
        NodoDE anterior;

        NodoDE(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private NodoDE cabeza; // Puntero que apunta al primer nodo de la lista
    private NodoDE cola; // Puntero que apunta al último nodo de la lista
    private int tamano = 0; // Contador para mantener el tamaño actual de la lista

    //Agrega un nuevo elemento al final de la lista.
    public void agregar(T elemento) {
        NodoDE nuevo = new NodoDE(elemento);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        }
        else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamano++;
    }

    //Agrega todos los elementos de otra lista al final de la lista actual.
    public void agregarTodo(MiListaDoble<T> otra) {
        if (otra == null || otra.estaVacia()) {
            return;
        }
        NodoDE puntero = otra.cabeza;
        while (puntero != null) {
            this.agregar(puntero.dato);
            puntero = puntero.siguiente;
        }
    }

    //GETS
    public T getDatoEn(int indice) {
        NodoDE puntero = cabeza;
        for (int i = 0; i < indice; i++) puntero = puntero.siguiente;
        return puntero.dato;
    }

    //Calcula la suma de todos los nodos.
    public int obtenerSumaEnteros() {
        int suma = 0;
        NodoDE puntero = cabeza;
        while (puntero != null) {
            // Usamos integer para "desbloquear" el valor numérico que está escondido dentro del nodo genérico.
            suma += (Integer) puntero.dato;
            puntero = puntero.siguiente;
        }
        return suma;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int size() {
        return tamano;
    }

    //TO STRING
    @Override
    public String toString() {
        if (cabeza == null) {
            return "[]";
        }
        String resultado = "[";
        NodoDE puntero = cabeza;
        while (puntero != null) {
            resultado += puntero.dato;
            if (puntero.siguiente != null) {
                resultado += ", ";
            }
            puntero = puntero.siguiente;
        }
        return resultado + "]";
    }
}