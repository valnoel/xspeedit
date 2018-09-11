package org.ouisncf.xspeedit;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link Robot} class
 * @author Valentin Noel
 */
public class RobotTest {

	private List<Article> articles = Arrays.asList(new Article(1),
			new Article(6),
			new Article(3),
			new Article(8),
			new Article(4),
			new Article(1),
			new Article(6),
			new Article(8),
			new Article(9),
			new Article(5),
			new Article(2),
			new Article(5),
			new Article(7),
			new Article(7),
			new Article(3));

	@Test
	public void getStringContent() {
		final Robot robot = new Robot();
		Assert.assertEquals("", robot.getStringContent());
	}

	@Test
	public void articlesOrderOptimization() {
		Assert.assertEquals("163841689525773", Article.getListStringContent(articles));
		Assert.assertEquals("918281737364655", Article.getListStringContent(Robot.getOptimizedArticlesOrder(articles)));
	}

	@Test
	public void packaging() {
		final Robot robot = new Robot();

		// standard process
		robot.processPackaging(articles);
		Assert.assertEquals(10, robot.getNumberOfUsedBoxes());
		Assert.assertEquals("163/8/41/6/8/9/52/5/7/73", robot.getStringContent());

		// sort articles for optimization before process
		robot.processPackaging(Robot.getOptimizedArticlesOrder(articles));
		Assert.assertEquals(8, robot.getNumberOfUsedBoxes());
		Assert.assertEquals("91/82/81/73/73/64/6/55", robot.getStringContent());
	}

}
