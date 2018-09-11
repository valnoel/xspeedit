package org.ouisncf.xspeedit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.ouisncf.xspeedit.Article.ArticleComparator;

/**
 * Implementation of the XSpeedIt robot
 *
 * @author Valentin Noel
 *
 */
public class Robot {

	/**
	 * Optimizes the articles order to fit to the box capacity, and minimize the number of
	 * used boxes.
	 *
	 * @param articles
	 * 	The articles list to process
	 * @return the optimized articles list for packaging
	 */
	public static List<Article> getOptimizedArticlesOrder(final List<Article> articles) {

		// Sort the articles by size
		final List<Article> sortedArticles = new ArrayList<Article>(articles);
		sortedArticles.sort(new ArticleComparator());

		// Reset managed articles list
		final List<Article> optimizedArticles = new ArrayList<Article>();

		while(!sortedArticles.isEmpty()) {

			// Get the biggest article of the sorted list and move it the new list
			final Article biggestArticle = sortedArticles.remove(sortedArticles.size() - 1);
			optimizedArticles.add(biggestArticle);

			// Parse the remaining article sizes to find the article that fits the most to
			// the remaining space left by the previous biggest article into the box.
			final int remaingSize = Box.MAX_CAPACITY - biggestArticle.getSize();
			Article previousArticle = null;
			for (Article article : sortedArticles) {
				if(article.getSize() > remaingSize) {
					if(previousArticle != null) {
						// An article fits the remaining space in box capacity, let's move
						// it from the sorted list to the optimized list!
						sortedArticles.remove(previousArticle);
						optimizedArticles.add(previousArticle);
					}
					break;
				}
				previousArticle = article;
			}
		}
		return optimizedArticles;
	}

	/**
	 * List of the output filled boxes
	 */
	private final List<Box> boxes;

	/**
	 * Robot constructor
	 */
	public Robot() {
		this.boxes = new ArrayList<>();
	}

	/**
	 * Processes the input articles packaging into boxes.
	 *
	 * @param articles
	 * 	Input articles to be packed into boxes
	 */
	public void processPackaging(final List<Article> articles) {
		Objects.requireNonNull(articles);

		boxes.clear();

		Box box = null;
		for(final Article article : articles) {
			if(box == null) {
				box = new Box();
			}
			if(article.getSize() + box.getContainedSize() > Box.MAX_CAPACITY) {
				boxes.add(box);
				box = new Box();
			}
			box.add(article);
		}
		boxes.add(box);
	}

	/**
	 * Formats the sizes of the articles contained into the output boxes as a character string
	 * and returns it as a {@link String}.
	 *
	 * @return the output boxes content as a {@link String}
	 */
	public String getStringContent() {
		final StringBuilder content = new StringBuilder();
		for (Iterator<Box> iterator = boxes.iterator(); iterator.hasNext();) {
			content.append(iterator.next().getStringContent());
			if(iterator.hasNext()) {
				content.append("/");
			}
		}
		return content.toString();
	}

	/**
	 * Number of output boxes getter
	 * @return the number of filled boxes
	 */
	public int getNumberOfUsedBoxes() {
		return this.boxes.size();
	}

}
