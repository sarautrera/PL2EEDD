package ParteA;
public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    private T valor;
    private ArbolBinarioDeBusqueda<T> izquierda, derecha;
    private boolean vacio = true;

    //Añade un nuevo elemento al árbol
    public void add(T nuevo) {
        if (vacio) {
            valor = nuevo;
            izquierda = crearInstancia();
            derecha = crearInstancia();
            vacio = false;
        }
        else {
            int comparacion = nuevo.compareTo(valor);
            if (comparacion < 0) izquierda.add(nuevo); //Si la comparación es negativa, significa que el valor nuevo es menor que el valor insertado en el árbol
            else if (comparacion > 0) derecha.add(nuevo); // El nuevo valor se posiciona a la derecha, debido a que los valores de mayor peso, por la definción del árbol, van a la derecha
        }
    }

    //Crea y retorna una nueva instancia vacía
    public ArbolBinarioDeBusqueda<T> crearInstancia() {
        return new ArbolBinarioDeBusqueda<>();
    }

    //Comprueba si el árbol actual (o el nodo raíz en el que nos encontramos) está vacío.
    public boolean isVacio() {
        return vacio;
    }

    //GETS
    public T getDatoRaiz() {
        return valor;
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        return izquierda;
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        return derecha;
    }
    public int getAltura() { // Número de niveles desde la raíz hasta la hoja más profunda.
        if (vacio) {
            return 0;
        }
        int alturaIzquierda = izquierda.getAltura();
        int alturaDerecha = derecha.getAltura();
        int mayor;
        if(alturaIzquierda > alturaDerecha) {
            mayor = alturaIzquierda;
        }
        else {
            mayor = alturaDerecha;
        }
        return 1 + mayor;
    }
    public MiListaDoble<T> getListaOrdenCentral() { //(inorden), obteniendo los elementos de menor a mayor
        MiListaDoble<T> lista = new MiListaDoble<>();
        if (!vacio) {
            lista.agregarTodo(izquierda.getListaOrdenCentral());
            lista.agregar(valor);
            lista.agregarTodo(derecha.getListaOrdenCentral());
        }
        return lista;
    }

    public MiListaDoble<T> getListaPreOrden() { //ORDEN: (raíz, izquierda, derecha)
        MiListaDoble<T> lista = new MiListaDoble<>();
        if (!vacio) {
            lista.agregar(valor);
            lista.agregarTodo(izquierda.getListaPreOrden());
            lista.agregarTodo(derecha.getListaPreOrden());
        }
        return lista;
    }
    public MiListaDoble<T> getListaPostOrden() { //ORDEN: (izquierda, derecha, raíz)
        MiListaDoble<T> lista = new MiListaDoble<>();
        if (!vacio) {
            lista.agregarTodo(izquierda.getListaPostOrden());
            lista.agregarTodo(derecha.getListaPostOrden());
            lista.agregar(valor);
        }
        return lista;
    }
    //Ruta completa de nodos desde la raíz hasta encontrar el elemento deseado.
    public MiListaDoble<T> getCamino(T objetivo) {
        MiListaDoble<T> caminoActual = new MiListaDoble<>();
        if (vacio) {
            return caminoActual;
        }
        caminoActual.agregar(valor);

        // Si el valor actual es el que buscamos
        if (valor.equals(objetivo)) {
            return caminoActual;
        }

        // Si no es el actual, buscamos en los hijos
        MiListaDoble<T> caminoDelSubArbol;
        // Decidimos si buscar por la izquierda o por la derecha según el valor
        if (objetivo.compareTo(valor) < 0) {
            caminoDelSubArbol = izquierda.getCamino(objetivo);  // El objetivo es menor
        }
        else {
            caminoDelSubArbol = derecha.getCamino(objetivo);  // El objetivo es mayor
        }

        //RESULTADO
        if (!caminoDelSubArbol.estaVacia()) {
            caminoActual.agregarTodo(caminoDelSubArbol);
            return caminoActual;
        }
        // Si el subárbol devolvió una lista vacía
        else {
            return new MiListaDoble<>();
        }
    }
}