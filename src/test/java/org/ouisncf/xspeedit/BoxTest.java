package org.ouisncf.xspeedit;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link Box} class
 * @author Valentin Noel
 */
public class BoxTest {

	@Test
	public void sizeCapacity() {
		final Box box = new Box();
		Assert.assertEquals(0, box.getContainedSize());
		box.add(new Article(4));
		box.add(new Article(5));
		try {
			box.add(new Article(5));
			Assert.fail();
		} catch(Exception e) {
			Assert.assertTrue(e instanceof RuntimeException);
		}
	}

	@Test
	public void stringContent() {
		final Box box = new Box();
		Assert.assertEquals(0, box.getContainedSize());
		box.add(new Article(4));
		box.add(new Article(5));
		Assert.assertEquals("45", box.getStringContent());
	}

}
