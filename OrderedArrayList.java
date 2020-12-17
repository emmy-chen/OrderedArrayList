import java.util.ArrayList;
public class OrderedArrayList<T extends Comparable<T>> extends NoNullArrayList<T> {

  public OrderedArrayList() {
    super();
  }

  public OrderedArrayList(int startingCapacity) {
    super(startingCapacity);
  }

  public void sort() {
    int size = size() - 1;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size - i; j++) {
        if (((this.get(j)).compareTo(this.get(j + 1))) > 0) {
          T temp = this.get(j);
          super.set(j, this.get(j + 1));
          super.set(j + 1, temp);
        }
      }
    }
  }

  public boolean add(T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot add null");
    }
    super.add(0, value);
    this.sort();
    /*
    if (size() == 0) {
      super.add(0, value);
    }
    this.sort();
    if (value.compareTo(this.get(size() / 2)) <= 0) {
      for (int i = 0; i < (size() / 2); i++) {
        if (value.compareTo(this.get(i)) <= 0) {
          super.add(i, value);
          return true;
        }
      }
    }
    else {
      for (int i = (size() / 2); i < size() ; i++) {
        if (value.compareTo(this.get(i)) <= 0) {
          super.add(i, value);
          return true;
        }
      }
    }
    */

    /*
    for (int i = 0; i < size(); i++) {
      if (value.compareTo(this.get(i)) <= 0) {
        super.add(i, value);
        return true;
      }
    }
    return true;
    super.add(0, value);
    return true;
    */
    return true;
  }

  public void add(int index, T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot add null to the ArrayList");
    }
    this.add(value);
  }

  public T set(int index, T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot set value to null");
    }
    T old = this.get(index);
    super.remove(index);
    this.add(value);
    return old;
  }

}
