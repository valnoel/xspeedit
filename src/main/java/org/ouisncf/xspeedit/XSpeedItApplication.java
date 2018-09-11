package org.ouisncf.xspeedit;

import java.util.List;
import java.util.stream.Collectors;

/**
 * XSpeedIt application.
 *
 * Expected argument:
 * 	the input articles to process as a chain of size values.
 *
 * @author Valentin Noel
 *
 */
public class XSpeedItApplication {

	/**
	 * Display an usage help
	 */
	private static void displayUsage() {
		System.out.println("Please indicate the input articles to process as a chain of size values.");
		System.out.println("These size values must be included between 1 and 9.");
		System.out.println("For example: 163841689525773");
	}

	/**
	 * Application main method
	 *
	 * @param args
	 * 	Input article sizes
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to XSpeedIt!\n");
		if(args.length != 1) {
			displayUsage();
		}

		final List<Article> articles = args[0].chars().boxed()
				.map((c) -> Character.getNumericValue(c))
				.map((size) -> new Article(size))
				.collect(Collectors.toList());

		final Robot robot = new Robot();
		final int minBoxesNbResult = (int) Math.ceil((double)Article.getListTotalSize(articles) / Box.MAX_CAPACITY);

		System.out.println("Input articles: " + Article.getListStringContent(articles));
		System.out.println("Theorical minimum number of boxes: " + minBoxesNbResult);

		// Standard process
		System.out.print(" - Linear process:\t");
		robot.processPackaging(articles);
		System.out.println(robot.getStringContent() + " => Number of boxes: " + robot.getNumberOfUsedBoxes());

		// Optimized process
		System.out.print(" - Optimized process:\t");
		robot.processPackaging(Robot.getOptimizedArticlesOrder(articles));
		System.out.println(robot.getStringContent() + " => Number of boxes: " + robot.getNumberOfUsedBoxes());
	}

}
