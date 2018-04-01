package utils;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

import java.util.ArrayList;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//https://github.com/ehcache/ehcache3/tree/master/demos/01-CacheAside/src/main/java/org/ehcache/demos/peeper

public class DataCache<T1, T2> {

	private Cache<T1, T2> cache;
	private static final Logger logger = LoggerFactory.getLogger(DataCache.class);
	private CacheManager cacheManager=null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataCache(Class c1, Class c2) {	
		Configuration xmlConfig = new XmlConfiguration(this.getClass().getResource("ehcache.xml"));
		cacheManager = newCacheManager(xmlConfig);
		cacheManager.init();

		cache = cacheManager.getCache("basicCache", c1, c2);
		
		logger.info("Cache setup is done");
	}

	public T2 getFromCache(T1 key) {
		return cache.get(key);
	}

	public ArrayList<T1> getKeysFromCache() {
		ArrayList<T1> keys = new ArrayList<T1>();
		cache.forEach((cur) -> keys.add(cur.getKey()));
		return keys;
	}

	public boolean contains(T1 key) {
		return cache.containsKey(key);
	}

	public void addToCache(T1 key, T2 data) {
		cache.put(key, data);
	}

	public void removeFromCache(T1 key) {
		cache.remove(key);
	}

	public void clearCache() {
		cache.clear();
	}
	
	public void close() {
		cacheManager.close();
	}
}