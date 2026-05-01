package ParteB;

public class Nodo {
    protected String nombre;
    protected Arista primeraArista;
    protected Nodo siguiente;
    //auxiliares para algoritmo de camino
    protected boolean visitado;
    protected Nodo padre;

    public Nodo(String nombre){
        this.nombre = nombre;
        this.primeraArista = null;
        this.siguiente = null;
        this.visitado = false;
        this.padre = null;
    }
}
