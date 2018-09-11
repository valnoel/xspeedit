package org.ouisncf.xspeedit;

import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Definition class for articles
 *
 * @author Valentin Noel
 *
 */
public class Article {

	/**
	 * Article size comparator
	 */
	public static class ArticleComparator implements Comparator<Article> {

		@Override
		public int compare(Article article1, Article article2) {
			return article1.getSize() - article2.getSize();
		}

	}

	/**
	 * Computes the sum of the sizes of an articles list and returns the result.
	 *
	 * @param articles
	 * 	The articles list to process
	 * @return the articles total size
	 */
	public static int getListTotalSize(final List<Article> articles) {
		return articles.stream().mapToInt((a) -> a.getSize()).sum();
	}

	/**
	 * Formats the sizes of an articles list as a character string, and returns it
	 * as a {@link String}.
	 *
	 * @param articles
	 * 	The articles list to process
	 * @return the article sizes as a {@link String}
	 */
	public static String getListStringContent(final List<Article> articles) {
		return articles.stream().map((a) -> Integer.toString(a.getSize())).reduce("", String::concat);
	}

	/**
	 * Article minimum size value
	 */
	private static final int MIN_SIZE = 1;
	/**
	 * Article maximum size value
	 */
	private static final int MAX_SIZE = 9;


	/**
	 * Article size
	 */
	private int size;

	/**
	 * Article constructor
	 * @param size
	 * 	Initial article size
	 * @throws IllegalArgumentException
	 * 	If the initial size is out of min and max constraints
	 */
	public Article(final int size) {
		if(size > MAX_SIZE || size < MIN_SIZE) {
			throw new IllegalArgumentException("An article size should be between " + MIN_SIZE + " and " + MAX_SIZE);
		}
		this.size = size;
	}

	/**
	 * Size getter
	 * @return the article size
	 */
	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add(Article.class.getSimpleName() + ": @" + Integer.toHexString(hashCode()))
				.add("size: " + this.size)
				.toString();
	}

}
