package CS146S21.SinaKalantar.Project1;

/**
 * @author Sina Kalantar
 * @param <T>
 * @version 1.1
 *
 * Class below is the class Node where the data has been initialized in the constructor. setNext, getNext, and getData
 * methods have been defined.
 *
 */

class Node<T> {

    public T data;

    Node<T> next;


    public Node(T data) {

        this.data = data;
    }

    public void setNext(Node<T> next) {

        this.next = next;
    }

    public Node<T> getNext() {

        return next;
    }

    public T getData() {
        return data;
    }


}

