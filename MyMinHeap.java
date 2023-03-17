public class MyMinHeap {
    // Heap is where the data is stored
    private static String[] Heap;
    // Size is counts how many slots are filled in the array
    private static int size;
    // MaxSize counts maximum size of the array
    private static int maxsize;

    private static int head = 1;

    public MyMinHeap(int maxsize){
        this.maxsize = maxsize;
        this.size = 0;

        Heap = new String[this.maxsize+1];
    }
    public MyMinHeap(){
        this.maxsize = 31;
        this.size = 0;

        Heap = new String[this.maxsize+1];
    }

    public static void insert(String element){
        System.out.println("Inserting: " + element);
        if (size >= maxsize){
            System.out.println("Reached maximum Heap");
            return;
        }
        Heap[++size] = element;
        int current = size;
        if (Heap[parent(current)] == null){
            return;
        }
        
        while (Heap[parent(current)].compareTo(Heap[current]) > 0){
            swap(current, parent(current));
            current = parent(current);
            if (Heap[parent(current)] == null){
                return;
            }
        }

    }

    private static int parent(int pos) { return pos/2;};
    private static int leftChild(int pos) { return (2*pos);}
    private static int rightChild(int pos){ return (2*pos)+1;}
    private static boolean isLeaf(int pos){ return (pos > (size/2));}

    public static void print()
    {
        for (int i = 1; i <= size / 2; i++) {
 
            // Printing the parent and both childrens
            System.out.print(
                " PARENT : " + Heap[i]
                + " LEFT CHILD : " + Heap[2 * i]
                + " RIGHT CHILD :" + Heap[2 * i + 1]);
 
            // By here new line is required
            System.out.println();
        }
    }

    private static void swap(int one, int two){
        //System.out.println("Swapping:" + Heap[one] + " with " + Heap[two]);
        String temp;
        temp = Heap[one];
        Heap[one] = Heap[two];
        Heap[two] = temp;
    }

    private static void remove(int pos){}
}