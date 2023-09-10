package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    private T maxItem;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (comparator == null || this.isEmpty()) {
            return null;
        }

        maxItem = this.get(0);
        for (T currentItem : this) {
            if (comparator.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        comparator = c;
        return max();
    }
}
