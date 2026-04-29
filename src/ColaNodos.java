class ColaNodos {
    private class Elemento {
        Nodo nodo;
        Elemento siguiente;
        Elemento(Nodo n) { this.nodo = n; }
    }
    private Elemento primero, ultimo;

    public void encolar(Nodo n) {
        Elemento nuevo = new Elemento(n);
        if (ultimo != null) ultimo.siguiente = nuevo;
        ultimo = nuevo;
        if (primero == null) primero = nuevo;
    }

    public Nodo desencolar() {
        if (primero == null) return null;
        Nodo n = primero.nodo;
        primero = primero.siguiente;
        if (primero == null) ultimo = null;
        return n;
    }

    public boolean estaVacia() { return primero == null; }
}