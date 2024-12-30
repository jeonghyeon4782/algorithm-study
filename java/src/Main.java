import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        ListIterator<Integer> iterator = list.listIterator();

        while(iterator.hasNext()) {
            int i = iterator.next();
            if (i == 2) iterator.remove();
            if (i == 3) iterator.add(10);
        }

        System.out.println(list);
    }
}
