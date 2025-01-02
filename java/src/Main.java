import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Main {
    public static void main(String[] args) {
       int a = 10;
       long b = (long)1000000000 + 1000000000 * 100000;

       if (a > b) System.out.println("a가 더 큼");
       else System.out.println("b가 더 크거나 같음");
    }
}
