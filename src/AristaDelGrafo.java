public class AristaDelGrafo<T> {
    String predicado;
    T objeto;
    AristaDelGrafo<T> siguiente;

    public AristaDelGrafo(String predicado, T objeto){
        this.predicado=predicado;
        this.objeto=objeto;
        this.siguiente=null;
    }
}
