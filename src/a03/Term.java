package a03;

import java.util.Comparator;

/**
 * Represents a search term that includes a query and how frequently that term
 * is searched.
 * 
 * @author jeffd
 *
 */
public class Term implements Comparable<Term>
{
	private String query;
	private double weight;

	/**
	 * Initializes a term with the given query string and weight.
	 * 
	 * @param query  the search term
	 * @param weight the frequency the term is searched
	 */
	public Term(String query, double weight)
	{
		if (query == null)
			throw new NullPointerException("The query cannot be null.");
		if (weight < 0)
			throw new IllegalArgumentException("The weight cannot be negative.");

		this.query = query;
		this.weight = weight;
	}

	/**
	 * Compares the terms in descending order by weight.
	 * 
	 * @return
	 */
	public static Comparator<Term> byReverseWeightOrder()
	{
		return new ReverseWeightOrder();
	}

	/**
	 * Compares the terms in descending order by weight.
	 * 
	 * @author jeffd
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term>
	{
		@Override
		public int compare(Term a, Term b)
		{
			return Double.valueOf(b.weight).compareTo(Double.valueOf(a.weight));
		}
	}

	/**
	 * Compares the terms in lexicographic order but using only the first r
	 * characters of each query.
	 * 
	 * @param r the length of the string being searched
	 * @return
	 */
	public static Comparator<Term> byPrefixOrder(int r)
	{
		if (r < 0)
			throw new IllegalArgumentException("r cannot be negative.");
		return new PrefixOrder(r);
	}

	/**
	 * Compares the terms in lexicographic order but using only the first r
	 * characters of each query.
	 * 
	 * @author jeffd
	 *
	 */
	public static class PrefixOrder implements Comparator<Term>
	{
		private int r;

		/**
		 * Constructor to initialize the fields.
		 * 
		 * @param r length of the string being searched
		 */
		public PrefixOrder(int r)
		{
			this.r = r;
		}

		/**
		 * Compares the query fields of 2 Term objects.
		 */
		@Override
		public int compare(Term a, Term b)
		{
			return a.query.substring(0, a.query.length() < r ? a.query.length() : r)
					.compareTo(b.query.substring(0, b.query.length() < r ? b.query.length() : r));
		}
	}

	/**
	 * Compares the query fields of 2 Term objects.
	 */
	@Override
	public int compareTo(Term that)
	{
		return this.query.compareTo(that.query);
	}

	public String toString()
	{
		return this.weight + "\t" + this.query;
	}
}