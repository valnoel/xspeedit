package org.ouisncf.xspeedit;

import java.util.Arrays;
import java.util.List;

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

	@Test
	public void comparator() {
		List<Article> articles = Arrays.asList(new Article(1),
				new Article(6),
				new Article(3));
		articles.sort(new Article.ArticleComparator());
		Assert.assertEquals(1, articles.get(0).getSize());
		Assert.assertEquals(3, articles.get(1).getSize());
		Assert.assertEquals(6, articles.get(2).getSize());
	}

	@Test
	public void listMethods() {
		List<Article> articles = Arrays.asList(new Article(1),
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

		Assert.assertEquals("163841689525773", Article.getListStringContent(articles));
	}

}
