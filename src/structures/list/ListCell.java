package structures.list;

public class ListCell {

    ListCell next;
    Object value;

    ListCell(){
        this.next=null;
        this.value = null;
    }

    @Override
    public String toString() {
        return "ListCell{" +
                "next=" + next +
                ", value=" + value +
                '}';
    }
}
