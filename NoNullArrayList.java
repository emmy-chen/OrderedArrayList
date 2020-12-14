import java.util.ArrayList;
public class NoNullArrayList<T> extends ArrayList<T> {
  private ArrayList<T> default;
  private int startingCapacity;

  public NoNullArrayList() {
    super();
  }

  public NoNullArrayList(int startingCapacity) {
    super(startingCapacity);
  }

  /*

  public boolean add(String elment) {
    if (element == null) {
      throw new IllegalArgumentException("a null value cannot be added to a NoNullArrayList");
    }
  }

  public void add(int index) {

  }

  public String set(int index, String value) {

  }

  */

}
