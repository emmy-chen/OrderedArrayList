import java.util.ArrayList;
public class NoNullArrayList<T> extends ArrayList<T> {

  public static void main(String[] args) {
  }

  public NoNullArrayList() {
    super();
  }

  public NoNullArrayList(int startingCapacity) {
    super(startingCapacity);
  }

  public boolean add(T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot add null to the ArrayList");
    }
    return super.add(value);
  }

  public void add(int index, T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot add null to the ArrayList");
    }
    super.add(index, value);
  }

  public T set(int i, T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot set value to null");
    }
    return super.set(i,value);
  }

}
