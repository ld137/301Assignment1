public class heapsort {
    public static void main(String[] args) {
        basicTest();
    }

    public static void basicTest(){
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
        System.out.println("-----------------------");
        System.out.println("Replacing 'Lazy' with 'Incompentent'");
        heap.replace("Lazy", "Incompentent");
        heap.print();
        System.out.println("-----------------------");
        String removed = heap.remove();
        while(removed != null){
            System.out.println(removed);
            removed = heap.remove();
        }
    }

    public void CreateRuns(int length){

    }
}
