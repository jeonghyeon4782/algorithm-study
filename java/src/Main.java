import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        System.out.println(deque.pollLast());
    }
}
