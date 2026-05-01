package ParteB;

public class Arista {
    protected String predicado;
    protected Nodo destino;
    protected Arista siguiente;

    public Arista(String predicado, Nodo nodoDestino) {
        this.predicado = predicado;
        this.destino = nodoDestino;
        this.siguiente = null;
    }
}
