public class ArbolBinarioDeBusquedaEntero extends ArbolBinarioDeBusqueda<Integer> {

    // Crea una nueva instancia del árbol
    @Override
    public ArbolBinarioDeBusquedaEntero crearInstancia() {
        return new ArbolBinarioDeBusquedaEntero();
    }

    //Calcula la suma de todos los valores enteros almacenados en el árbol.
    public int getSuma() {
        if (isVacio()) {
            return 0;
        }
        int acumulador = 0;
        // Sumamos la raíz
        acumulador += getDatoRaiz();
        // Sumamos la parte izquierda
        ArbolBinarioDeBusquedaEntero subIzquierdo = (ArbolBinarioDeBusquedaEntero) getSubArbolIzquierda();
        acumulador += subIzquierdo.getSuma();

        // Sumamos la parte derecha
        ArbolBinarioDeBusquedaEntero subDerecho = (ArbolBinarioDeBusquedaEntero) getSubArbolDerecha();
        acumulador += subDerecho.getSuma();
        return acumulador;
    }
}