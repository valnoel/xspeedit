package org.ouisncf.xspeedit;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the XSpeedIt robot
 *
 * @author Valentin Noel
 *
 */
public class Robot {

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
