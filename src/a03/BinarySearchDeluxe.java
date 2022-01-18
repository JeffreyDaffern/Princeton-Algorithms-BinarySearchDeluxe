package a03;
import java.util.Comparator;

/**
 * A searching algorithm that allows the user to find the first and last index of a search term.
 * @author jeffd
 *
 */
public class BinarySearchDeluxe
{
	/**
	 * Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
	 * 
	 * @param <Key> the type of the term
	 * @param a the array to be searched
	 * @param key term being searched
	 * @param comparator comparator that determines the compare
	 * @return first index of the key or -1 if not found
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		if (a == null || key == null || comparator == null) throw new NullPointerException("The arguments cannot be null.");

		int low = 0;
		int high = a.length - 1;

		while (low <= high)
		{
			int mid = (low + high) / 2;
			if (comparator.compare(key, a[mid]) > 0)
				low = mid + 1;
			else if (comparator.compare(key, a[mid]) < 0)
				high = mid - 1;
			else
			{
				while (mid > 0 && comparator.compare(a[mid], a[mid - 1]) == 0)
				{
					mid--;
				}
				return mid;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
	 * 
	 * @param <Key> the type of the term
	 * @param a the array to be searched
	 * @param key term being searched
	 * @param comparator comparator that determines the compare
	 * @return last index of the key or -1 if not found
	 */
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		if (a == null || key == null || comparator == null) throw new NullPointerException("The arguments cannot be null.");
		
		int low = 0;
		int high = a.length - 1;

		while (low <= high)
		{
			int mid = (low + high) / 2;
			if (comparator.compare(key, a[mid]) > 0)
				low = mid + 1;
			else if (comparator.compare(key, a[mid]) < 0)
				high = mid - 1;
			else
			{
				while (mid < high && comparator.compare(a[mid], a[mid + 1]) == 0)
				{
					mid++;
				}
				return mid;
			}
		}
		return -1;
	}
}
