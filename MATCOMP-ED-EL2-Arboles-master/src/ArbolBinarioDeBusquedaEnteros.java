public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {
    public ArbolBinarioDeBusquedaEnteros(){
        super();
    }
    public int getSuma() {
        return sumarNodos(this);
    }
    private int sumarNodos(ArbolBinarioDeBusqueda<Integer> nodo) {
        if (nodo == null || nodo.dato == null) {
            return 0;
        }
        // sumamos el dato de este nodo
        int sumaTotal = nodo.dato;

        // sumamos los hijos si existen
        if (nodo.getSubArbolIzquierda() != null) {
            sumaTotal += sumarNodos(nodo.getSubArbolIzquierda());
        }
        if (nodo.getSubArbolDerecha() != null) {
            sumaTotal += sumarNodos(nodo.getSubArbolDerecha());
        }

        return sumaTotal;
    }
}

