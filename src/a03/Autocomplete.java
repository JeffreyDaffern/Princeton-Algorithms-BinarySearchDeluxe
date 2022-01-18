package a03;

import java.util.Arrays;
import edu.princeton.cs.algs4.Quick;

/**
 * Searches an array to find possible matches to the current search term.
 * 
 * @author jeffd
 *
 */
public class Autocomplete
{
	private final Term[] terms;

	/**
	 * Initializes the data structure from the given array of terms.
	 * 
	 * @param terms the array to be searched
	 */
	public Autocomplete(Term[] terms)
	{
		if (terms == null)
			throw new NullPointerException("The array is null.");
		int length = terms.length;
		this.terms = new Term[length];

		for (int i = 0; i < length; i++)
		{
			this.terms[i] = terms[i];
		}
		Quick.sort(this.terms);
	}

	/**
	 * Returns all terms that start with the given prefix, in descending order of
	 * weight.
	 * 
	 * @param prefix the term being searched
	 * @return
	 */
	public Term[] allMatches(String prefix)
	{
		if (prefix == null)
			throw new NullPointerException("The prefix variable is null.");

		int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
				Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		int matchCount = numberOfMatches(prefix);
		Term[] matchingTerms = new Term[matchCount];

		if (firstIndex >= 0 && lastIndex >= 0)
		{
			for (int i = 0; i < matchCount; i++)
			{
				matchingTerms[i] = terms[firstIndex + i];
			}
			Arrays.sort(matchingTerms, Term.byReverseWeightOrder());
		}

		return matchingTerms;
	}

	/**
	 * Returns the number of terms that start with the given prefix.
	 * 
	 * @param prefix the term being searched
	 * @return the number of possible matching terms
	 */
	public int numberOfMatches(String prefix)
	{
		if (prefix == null)
			throw new NullPointerException("The prefix variable is null.");
		Term newTerm = new Term(prefix, 0);

		int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, newTerm, Term.byPrefixOrder(prefix.length()));

		if (firstIndex < 0)
			return 0;

		int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, newTerm, Term.byPrefixOrder(prefix.length()));

		return (lastIndex - firstIndex) + 1;
	}
}
