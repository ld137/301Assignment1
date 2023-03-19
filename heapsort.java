import java.util.Arrays;

public class heapsort {
    public static void main(String[] args) {
        MyMinHeap heap = new MyMinHeap(31);
        heap.insert("The");
        heap.print();
        heap.insert("Quick");
        heap.print();
        heap.insert("Brown");
        heap.print();
        heap.insert("Fox");
        heap.print();
        heap.insert("Jumps");
        heap.print();
        heap.insert("Over");
        heap.print();
        heap.insert("A");
        heap.print();
        heap.insert("Lazy");
        heap.print();
        heap.insert("Dog");
        heap.print();
        String removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
        removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
        removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
        removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
        removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
        removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
        removed = heap.remove();
        System.out.println("Removed: " + removed);
        heap.print();
    }
}
