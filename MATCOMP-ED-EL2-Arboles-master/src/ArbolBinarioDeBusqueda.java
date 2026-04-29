public class ArbolBinarioDeBusqueda<T extends Comparable<T>>{
    protected T dato;
    protected ArbolBinarioDeBusqueda<T> derecha;
    protected   ArbolBinarioDeBusqueda<T> izquierda;
    //inicializamos el arbol vacio
    public ArbolBinarioDeBusqueda(){
        this.dato=null;
        this.derecha=null;
        this.izquierda=null;
    }
    //PREGUNTAS
    public int getGrado(){
        //como esta vacio el grado es 0
        if (this.dato==null){
            return 0;
        }
        //calculamos el grado del nodo actual
        int gradoActual=0;
        if (this.derecha!=null) gradoActual++;
        if (this.izquierda!=null) gradoActual++;
        //como es un arbol binario el maximo grado es 2
        if (gradoActual==2){
            return 2;
        }
        //si es menor que dos, miramos los subarboles para ver si alguno tiene un grado mayor
        int gradoSubarboles = 0;
        if (this.izquierda != null) {
            gradoSubarboles = Math.max(gradoSubarboles, this.izquierda.getGrado());
        }
        if (this.derecha != null && gradoSubarboles < 2) {
            gradoSubarboles = Math.max(gradoSubarboles, this.derecha.getGrado());
        }
        //devolvemos el maximo grado
        return Math.max(gradoActual, gradoSubarboles);
    }
    public int getAltura(){
        if (this.dato==null) return 0;//si el arbol esta vacio su altura es 0
        //si solo tiene un nodo su altura es 1
        if(this.izquierda==null && this.derecha==null) return 1;
        //si no estan vacios calculamos la altura de cada rama
        int alturaIzq=(this.izquierda!=null)?this.izquierda.getAltura():0;
        int alturaDer=(this.derecha!=null)?this.derecha.getAltura():0;
        //sumamos 1 porque hay que contar el nivel actual y tomamos el camino mas largo
        return 1 + Math.max(alturaDer, alturaIzq);
    }

    public T[] getListaDatosNivel(int nivelDestino) {
        int tam = contarNodosEnNivel(nivelDestino);
        T[] resultado = (T[]) new Comparable[tam];
        int[] indice = {0};
        llenarNivel(resultado, indice, nivelDestino);
        return resultado;
    }
    //funciones necesarias para que funcione getListaDatosNivel
    public int contarNodosEnNivel(int nivel) {
        //bucle recursivo para ir recorriendo en arbol ver su tamaño
        if (this.dato == null || nivel < 1) return 0;
        if (nivel == 1) return 1;
        int nodosIzq = (this.izquierda != null) ? this.izquierda.contarNodosEnNivel(nivel - 1) : 0;
        int nodosDer = (this.derecha != null) ? this.derecha.contarNodosEnNivel(nivel - 1) : 0;
        return nodosIzq + nodosDer;
    }
    public void llenarNivel(Object[] array, int[] indice, int nivelActual) {
        //bucle recursivo para meter los datos en el nivel que queramos
        if (this.dato == null || nivelActual < 1) return;
        if (nivelActual == 1) {
            array[indice[0]++] = this.dato;
        } else {
            if (this.izquierda != null) this.izquierda.llenarNivel(array, indice, nivelActual - 1);
            if (this.derecha != null) this.derecha.llenarNivel(array, indice, nivelActual - 1);
        }
    }

    public Boolean isArbolHomogeneo(){
        //si el arbol esta vacio, por definicion es homogeneo
        if (this.dato==null) return true;
        //si solamente tiene un nodo(no hijos), tambien es homogeneo
        if (this.derecha==null&&this.izquierda==null) return true;
        //es homogeneo solo si tiene dos hijos, y estos tambien son homogeneos
        if (this.derecha!=null&&this.izquierda!=null){
            return this.izquierda.isArbolHomogeneo() && this.derecha.isArbolHomogeneo();
        }
        //si no cumple estas tres condiciones no es homogeneo
        return false;
    }
    public Boolean isArbolCasiCompleto() {
        int numeroNodos = this.getCantidadNodos();
        return verificarCasiCompleto(0, numeroNodos);
    }
    private boolean verificarCasiCompleto(int indiceActual, int numeroNodos) {
        // si el nodo está vacío, es una rama válida
        if (this.dato == null) {
            return true;
        }

        // si el índice asignado es mayor o igual al número de nodos, significa que hay un "hueco" antes de este nodo.
        if (indiceActual >= numeroNodos) {
            return false;
        }

        // verificamos recursivamente los hijos
        boolean izqValido = true;
        boolean derValido = true;

        if (this.izquierda != null) {
            izqValido = this.izquierda.verificarCasiCompleto(2 * indiceActual + 1, numeroNodos);
        }

        // Si el izquierdo da falso, no hace falta mirar el derecho
        if (!izqValido) return false;

        if (this.derecha != null) {
            derValido = this.derecha.verificarCasiCompleto(2 * indiceActual + 2, numeroNodos);
        }

        return derValido;
    }
    //metodo auxiliar para contar nodos
    public int getCantidadNodos() {
        if (this.dato == null) return 0;
        int count = 1;
        if (this.izquierda != null) count += this.izquierda.getCantidadNodos();
        if (this.derecha != null) count += this.derecha.getCantidadNodos();
        return count;
    }

    public T[] getCamino(T objetivo) {
        //calculamos la profundidad para saber el tamaño del array
        int profundidad = 0;
        ArbolBinarioDeBusqueda<T> actual = this;

        //contamos para dimensionar el array
        while (actual != null && actual.dato != null) {
            profundidad++;
            int comp = objetivo.compareTo(actual.dato);
            if (comp == 0) break;
            actual = (comp < 0) ? actual.izquierda : actual.derecha;
        }

        T[] camino = (T[]) new Comparable[profundidad];
        actual = this;
        int i = 0;

        //llenamos el array
        while (i < profundidad) {
            camino[i++] = actual.dato;
            int comp = objetivo.compareTo(actual.dato);
            if (comp == 0) break;
            actual = (comp < 0) ? actual.izquierda : actual.derecha;
        }

        return camino;
    }


    //OPERACIONES

    public void add(T nuevoDato){
        //el arbol esta vacio
        if(this.dato==null){
            this.dato=nuevoDato;
            return;
        }
        //comparamos el nuevo dato con el dato del nodo actual
        int comparar= nuevoDato.compareTo(this.dato);

        if (comparar<0){
            //como el nuevo dato es menor, va a la zquierda
            if(this.izquierda==null) {
                this.izquierda = new ArbolBinarioDeBusqueda<T>();
            }
            this.izquierda.add(nuevoDato);
        }
        else if (comparar>0){
            //como el nuevo dato es mayor, va a la derecha
            if(this.derecha==null) {
                this.derecha = new ArbolBinarioDeBusqueda<T>();
            }
            this.derecha.add(nuevoDato);
        }
        //si comparar==0 el dato ya existe
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        return this.derecha;
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        return this.izquierda;
    }
}
