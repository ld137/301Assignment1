public class MyMinHeap {
    // Heap is where the data is stored
    private static String[] Heap;
    // Size is counts how many slots are filled in the array
    private static int size;
    // MaxSize counts maximum size of the array
    private static int maxsize;

    private static int head = 1;

    public MyMinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;

        Heap = new String[this.maxsize + 1];
    }

    public MyMinHeap() {
        this.maxsize = 31;
        this.size = 0;

        Heap = new String[this.maxsize + 1];
    }

    private static int parent(int pos) {
        return pos / 2;
    };

    private static int leftChild(int pos) {
        return (2 * pos);
    }

    private static int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private static boolean isLeaf(int pos) {
        return (pos > (size / 2));
    }

    public static void print() {
        for (int i = 1; i <= size / 2; i++) {

            // Printing the parent and both childrens
            System.out.print("[" + i + "]" +
                    " PARENT : " + Heap[i]
                            + " LEFT CHILD : " + Heap[2 * i]
                            + " RIGHT CHILD :" + Heap[2 * i + 1]);

            // By here new line is required
            System.out.println();
        }
    }
    public String[] getArray(){
        return Heap;
    }
    public static void insert(String element) {
        System.out.println("Inserting: " + element);
        if (size >= maxsize) {
            System.out.println("Reached maximum Heap");
            return;
        }
        Heap[++size] = element;
        int current = size;
        if (Heap[parent(current)] == null) {
            return;
        }

        while (Heap[parent(current)].compareTo(Heap[current]) > 0) {
            replace(current, parent(current));
            current = parent(current);
            if (Heap[parent(current)] == null) {
                return;
            }
        }

    }
    public String remove()
    {
 
        String popped = Heap[1];
        Heap[1] = Heap[size--];
        minHeapify(1);
 
        return popped;
    }
    public static void minHeapify(int pos){
        if(!isLeaf(pos)){
            int swapPos= pos;
            // swap with the minimum of the two children
            // to check if right child exists. Otherwise default value will be '0'
            // and that will be swapped with parent node.
            if(rightChild(pos)<=size)
               swapPos = Heap[leftChild(pos)].compareTo(Heap[rightChild(pos)]) < 0 ?leftChild(pos):rightChild(pos);
            else
              swapPos= leftChild(pos);
             
            if(Heap[pos].compareTo(Heap[leftChild(pos)]) <0 || Heap[pos].compareTo(Heap[rightChild(pos)]) >0){
              replace(pos,swapPos);
              minHeapify(swapPos);
            }
             
          } 
    }

    public static void replace(int one, int two) {
        // System.out.println("Swapping:" + Heap[one] + " with " + Heap[two]);
        String temp;
        temp = Heap[one];
        Heap[one] = Heap[two];
        Heap[two] = temp;
    }

    public static String peek() {
        return Heap[0];
    }

    public static void load() {
    }

    public static void reheap() {
    }

}