public class createRuns {
    public static void main(String[] args) {
        int heapSize = 0;
        try{
            if(args.length>0){
                heapSize = Integer.parseInt(args[0]); // Saves the specified heapsize into heapSize int
            }
            else{
                System.out.println("Error: Unspecified heap size"); // Specifies error and exits program
                System.exit(0);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Error: Invalid integer " + args[0]); // If integer is incorrectly formated exits program
        }

        if(heapSize == 0){
            System.out.println("Error: Insufficient heap size"); // Specifies error and exits program
            System.exit(0);
        }

       MyMinHeap initialHeap =  new MyMinHeap(heapSize);
       MyMinHeap secondHeap =  new MyMinHeap(heapSize);

       MyMinHeap sortedHeap =  new MyMinHeap(heapSize * 2 - 1);


        while(!initialHeap.peek().isEmpty() || !secondHeap.peek().isEmpty()){
            if(initialHeap.peek().compareTo(secondHeap.peek()) < 0){
            // Initial heap value goes next
            sortedHeap.insert(initialHeap.peek());
            initialHeap.remove();
            }
            else if(initialHeap.peek().compareTo(secondHeap.peek()) > 0){
            sortedHeap.insert(secondHeap.peek());
            secondHeap.remove();
            }
        }
       
        
    }
}
