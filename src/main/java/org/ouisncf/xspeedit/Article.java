package org.ouisncf.xspeedit;

import java.util.StringJoiner;

/**
 * Definition class for articles
 *
 * @author Valentin Noel
 *
 */
public class Article {

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
