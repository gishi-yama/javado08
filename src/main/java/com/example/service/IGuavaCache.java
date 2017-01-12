package com.example.service;


import com.google.inject.ImplementedBy;

@ImplementedBy(GuavaCache.class)
public interface IGuavaCache {

	int getRandomInt();

	int getCachedRandomInt(String key);


}
