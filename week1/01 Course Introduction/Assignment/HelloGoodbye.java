import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class HelloGoodbye {

    public static void main(String[] args) {
        String firstName = args[0];
        String secondName = args[1];

        StdOut.print("Hello " + firstName + " and " + secondName + "\n");
        StdOut.print("Goodbye " + secondName + " and " + firstName + "\n");
    }
}
