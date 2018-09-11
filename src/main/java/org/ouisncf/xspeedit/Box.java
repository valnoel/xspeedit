package org.ouisncf.xspeedit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Definition class for {@link Article} boxes
 *
 * @author Valentin Noel
 *
 */
public class Box {

	/**
	 * Box maximum capacity value
	 */
	public static final int MAX_CAPACITY = 10;

	/**
	 * List of articles contained into the box
	 */
	private final List<Article> articles;
	/**
	 * Sum of sizes of the articles contained into the box
	 */
	private int containedSize;

	/**
	 * Box constructor
	 */
	public Box() {
		this.articles = new ArrayList<>();
		this.containedSize = 0;
	}

	/**
	 * Contained size getter
	 * @return the total size of the contained articles
	 */
	public int getContainedSize() {
		return containedSize;
	}

	/**
	 * Adds an article to the box, and increments the contained size.
	 *
	 * @param article
	 * 	The {@link Article} instance to add to the {@link #articles} list
	 */
	public void add(final Article article) {
		Objects.requireNonNull(article);

		if(article.getSize() + this.containedSize > MAX_CAPACITY) {
			throw new RuntimeException("A box size capacity is " + MAX_CAPACITY);
		}

		this.articles.add(article);
		this.containedSize += article.getSize();
	}

	/**
	 * Formats the sizes of the articles contained into the box as a character string, and returns it
	 * as a {@link String}.
	 *
	 * @return the contained article sizes as a {@link String}
	 */
	public String getStringContent() {
		return articles.stream().map((a) -> Integer.toString(a.getSize())).reduce("", String::concat);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add(Box.class.getSimpleName() + ": @" + Integer.toHexString(hashCode()))
				.add("containedSize: " + this.containedSize)
				.add("articles: " + this.articles)
				.toString();
	}
}
