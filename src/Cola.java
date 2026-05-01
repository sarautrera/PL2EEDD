public class Cola<T> {
    protected Nodo<T> ultimo;
    protected Nodo<T> primero;
    protected int tamaño;

    public Cola() {
        this.ultimo = null;
        this.tamaño=0;
    }

    //Añadir elementos
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        tamaño++;
    }

    //Quitar elementos

    public T pop() {
        if (isEmpty()) return null;
        T dato = primero.sujeto;
        primero = primero.siguiente;

        if (primero == null) {
            ultimo = null;
        }
        tamaño--;
        return dato;
    }

    public boolean isEmpty() {
        return ultimo == null;
    }

}
