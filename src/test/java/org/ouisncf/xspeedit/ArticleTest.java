package org.ouisncf.xspeedit;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link Article} class
 * @author Valentin Noel
 */
public class ArticleTest {

	@Test
	public void construction() {
		try {
			for (int i = 1; i <= 9; i++) {
				new Article(i);
			}
		} catch(Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void wrongSizeConstruction() {
		try {
			new Article(0);
			Assert.fail();
		} catch(Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		try {
			new Article(10);
			Assert.fail();
		} catch(Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void getSize() {
		final Article article = new Article(5);
		Assert.assertEquals(5, article.getSize());
	}

}
