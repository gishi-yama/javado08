package com.example;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class OtherSamples {

	private CacheLoader<String, String> loader1;
	private CacheLoader<String, String> loader2;

	@Before
	public void setUp() {
		loader1 = new CacheLoader<String, String>() {
			@Override
			public String load(String key) throws Exception {
				if (!key.equals("exception")) {
					return key.toUpperCase();
				}
				throw new Exception("3文字以内にしてください");
			}
		};

		loader2 = CacheLoader.from((key) -> key.toUpperCase());
	}


	@Test
	public void getとgetUncheckedの使い分け() throws ExecutionException {
		LoadingCache<String, String> checkedCache = CacheBuilder.newBuilder()
			.build(loader1);
		assertThat(checkedCache.get("foo"), is("FOO"));

		LoadingCache<String, String> unCheckedCache = CacheBuilder.newBuilder()
			.build(loader2);
		assertThat(unCheckedCache.getUnchecked("foo"), is("FOO"));
	}

	@Test(expected = ExecutionException.class)
	public void getでexceptionがスローされる() throws ExecutionException {
		LoadingCache<String, String> checkedCache = CacheBuilder.newBuilder()
			.build(loader1);
		checkedCache.get("exception");
	}

	@Test
	public void 自分でエントリをputする() {
		Cache<String, Integer> origCache = CacheBuilder.newBuilder()
			.maximumSize(3)
			.build();

		origCache.put("foo", 1);
		origCache.put("bar", 2);
		origCache.put("baz", 3);
		origCache.put("hoge", 4);

		assertNull(origCache.getIfPresent("foo"));
		assertThat(origCache.getIfPresent("bar"), is(2));
		assertThat(origCache.getIfPresent("baz"), is(3));
		assertThat(origCache.getIfPresent("hoge"), is(4));

		assertThat(origCache.size(), is(3L));
		origCache.invalidateAll();
		assertThat(origCache.size(), is(0L));

	}
}
