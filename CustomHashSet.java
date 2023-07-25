package Lesson5;

public class CustomHashSet<E> {
    private CustomHashMap<E, Object> map = new CustomHashMap<>();
    private static final Object PRESENT = new Object();

    public boolean add(E e) {return map.put(e, PRESENT)==null;}
    public boolean remove(E e) {return map.delete(e)!=null;}

}
