/**
 * MyMinHeap is a class for implementing a min-heap of Strings.
 * A min-heap is a binary tree where each node is smaller than its children.
 * This implementation uses an array to store the elements of the heap.
 */
public class MyMinHeap {

    // The array to store the elements of the heap.
    private String[] Heap;

    // The current size of the heap.
    private int size;

    // The maximum size of the heap.
    private int maxsize;

    private boolean empty = true;

    /**
     * 
     * Constructs a new MyMinHeap object with the specified maximum size.
     * 
     * @param maxsize the maximum size of the heap
     */
    public MyMinHeap(int maxsize) {
        this.maxsize = maxsize;
        Heap = new String[maxsize + 1];
        size = 0;
    }

    public int getSize(){
        return size;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    /**
     * 
     * Swaps the elements at the specified positions in the heap array.
     * 
     * @param pos     the position of the first element to swap
     * @param swapPos the position of the second element to swap
     */
    private void swap(int pos, int swapPos) {
        String temp = Heap[pos];
        Heap[pos] = Heap[swapPos];
        Heap[swapPos] = temp;
    }

    /**
     * 
     * Returns the minimum element of the heap without removing it.
     * 
     * @return the minimum element of the heap
     */
    public String peek() {
        return Heap[1];
    }

    /**
     * 
     * Inserts the specified value into the heap.
     * 
     * @param value the value to insert
     */
    public boolean insert(String value) {
        if (size >= maxsize) {
            //System.err.println("Heap is full");
            return false;
        }
        size++;
        Heap[size] = value;
        int current = size;
        while (current > 1 && Heap[current].compareTo(Heap[parent(current)]) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
        empty = false;
        return true;
    }

    /**
     * 
     * Removes and returns the minimum element of the heap.
     * 
     * @return the minimum element of the heap
     */
    public String remove() {
        if (size <= 0) {
            //System.err.println("Heap is empty");
            empty = true;
            return null;
        }
        String min = Heap[1];
        Heap[1] = Heap[size];
        size--;
        minHeapify(1);
        return min;
    }

    /**
     * 
     * Maintains the heap property of a min-heap starting from a given position. The
     * method assumes that the left and right subtrees of the input position already
     * satisfy the heap property. If the input position does not satisfy the heap
     * property, it is swapped with the swapPos child until the heap property is
     * restored.
     * 
     * @param pos the position from which to start maintaining the heap property
     */
    private void minHeapify(int pos) {
        int left = leftChild(pos);
        int right = rightChild(pos);
        int swapPos = pos;
        if (left <= size && Heap[left].compareTo(Heap[swapPos]) < 0) {
            swapPos = left;
        }
        if (right <= size && Heap[right].compareTo(Heap[swapPos]) < 0) {
            swapPos = right;
        }
        if (swapPos != pos) {
            swap(pos, swapPos);
            minHeapify(swapPos);
        }
    }

    /**
     * Replaces a node in the heap with a new value. The old value is located by
     * searching for it in the heap array. The node is then removed by swapping it
     * with the root and then deleting the root. The new value is inserted into the
     * heap by calling insert(). If the new value is smaller than its parent, it
     * will be swapped upward until the heap property is restored. If the new value
     * is larger than its parent or is now the root of the heap, minHeapify() is
     * called to restore the heap property.
     * 
     * @param oldVal the value to be replaced
     * @param newVal the new value to insert into the heap
     */
    public void replace(String oldVal, String newVal) {
        int index = -1;
        for (int i = 1; i <= size; i++) {
            if (Heap[i].equals(oldVal)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.err.println(oldVal + " not found in Heap");
        }
        Heap[index] = newVal;
        while (index > 1 && Heap[index].compareTo(Heap[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
        if (index == 1 || Heap[index].compareTo(Heap[parent(index)]) >= 0) {
            minHeapify(index);
        }
    }

    /**
     * Loads the input array into the heap. This function replaces the current heap
     * content with the input array content. The size of the heap is set to the
     * maximum
     * size of the heap. If the input array contains more elements than the maximum
     * size of the heap, only the first maxsize elements will be loaded.
     * 
     * @param input the array of strings to be loaded into the heap
     */
    public void load(String[] input) {
        maxsize = (input.length > 31) ? (31 + 1) : (input.length + 1);
        Heap = new String[maxsize];
        size = 0;
        for (int i = 1; i < maxsize; i++) {
            Heap[i] = input[i-1];
            size++;
        }
        minHeapify(1);
    }

    /**
     * Prints the heap in a tree-like structure. Each line represents a node in the
     * tree
     * and its parent and children. The root node is at index 1, its left child is
     * at index 2,
     * and its right child is at index 3. The second level of the tree starts at
     * index 4, and so on.
     */
    public void print() {
        System.out.println("-----------------------");
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(
                    " PARENT : " + Heap[i]
                            + " LEFT CHILD : " + Heap[2 * i]
                            + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }

    public boolean isEmpty(){
        return empty;
    }
}