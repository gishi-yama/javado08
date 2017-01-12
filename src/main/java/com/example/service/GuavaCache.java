package com.example.service;

import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Singleton;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Singleton
public class GuavaCache implements IGuavaCache {

	private Random random;

	public GuavaCache() {
		this.random = new Random();
	}

	@Override
	public int getRandomInt() {
		return random.nextInt(1000);
	}

	private final LoadingCache<String, Integer> randomIntCache =
		CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS)
			.build(new CacheLoader<String, Integer>() {
				@Override
				public Integer load(String key) throws Exception {
					return getRandomInt();
				}
			});


	@Override
	public int getCachedRandomInt(String key) {
		try {
			Stopwatch stopwatch = Stopwatch.createStarted();
			int i = randomIntCache.get(key);
			stopwatch.stop();
			System.out.println(stopwatch.toString());
			return i;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return Integer.MIN_VALUE;
		}
	}
}
