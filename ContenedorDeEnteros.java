//package practica1;

public class ContenedorDeEnteros {
    private Nodo primero;
    private int numElementos;

    private class Nodo {
        int contenido;
        Nodo next;
    }

    public ContenedorDeEnteros(){
        numElementos = 0;
    }

    public int cardinal() {
        return numElementos;
    }

    public boolean insertar(int num) {
        if (!buscar(num)) {
            Nodo aux = primero;
            Nodo nuevo = new Nodo();
            nuevo.contenido = num;
            nuevo.next = primero;
            primero = nuevo;
            numElementos++;
            return true;
        }
        return false;
    }
// Falla si el elemento a extraer es el primero, Arreglado
    public boolean extraer(int n){
        if(buscar(n)){
            if(primero.contenido == n){
                primero = primero.next;
                numElementos--;
                return true;
            }
            Nodo prev = primero;
            Nodo aux = primero.next;
            while(aux!=null){
                if (aux.contenido == n) {
                    prev.next = aux.next;
                    numElementos--;
                    return true;
                }
                prev = aux;
                aux = aux.next;
            }
        }
        return false;
    }

    public boolean buscar(int a){
        Nodo aux = primero;
        while(aux!=null){
            if (aux.contenido == a){
                return true;
            }
            aux = aux.next;
        }
        return false;
    }


    public int[] elementos(){
        int[] res= new int[numElementos];
        Nodo aux= primero;
        for(int i= 0; i<numElementos; i++){
            res[i]= aux.contenido;
            aux = aux.next;
        }
        return res;
    }

    public void vaciar(){
        primero = null;
        numElementos = 0;
    }
}