package utils;
import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;
import static org.ehcache.config.units.MemoryUnit.MB;

import java.io.File;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.xml.XmlConfiguration;

public class MyCache {
	public static void main(String args[]) {
		MyCache me = new MyCache();
		me.MyCache2();
	}
	public void MyCache3() {
		/*long i = 10;
		PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder() 
				  .with(CacheManagerBuilder.persistence(new File("MyCache"))) 
				  .withCache("persistent-cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
				    ResourcePoolsBuilder.newResourcePoolsBuilder().disk(i, MemoryUnit.MB, true)) 
				  )
				  .build(true);*/
		PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				  .with(CacheManagerBuilder.persistence(new File("MyCache")))
				  .withCache("threeTieredCache",
				    CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
				      ResourcePoolsBuilder.newResourcePoolsBuilder()
				        .heap(1, EntryUnit.ENTRIES)
				        .offheap(1, MemoryUnit.MB)
				        .disk(20, MemoryUnit.MB, true)
				    )
				  ).build(true);
		Cache<Long, String> basicCache = persistentCacheManager.getCache("cache1", Long.class, String.class);
		//Cache<Long, String> basicCache = persistentCacheManager.createCache("cache1", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,ResourcePoolsBuilder.heap(10)));
		//basicCache.put(1L, "da one!");
		//basicCache.put(2L, "da two!");
		//basicCache.put(3L, "da three!");
		System.out.println(basicCache.containsKey(1L));
		System.out.println(basicCache.containsKey(2L));
		System.out.println(basicCache.containsKey(3L));
		persistentCacheManager.close();
	}

	public void MyCache2() {
		Configuration xmlConfig = new XmlConfiguration(this.getClass().getResource("ehcache.xml"));
		CacheManager cacheManager = newCacheManager(xmlConfig);
		cacheManager.init();

		Cache<String, String> basicCache = cacheManager.getCache("basicCache", String.class, String.class);

		basicCache.put("1L", "da one!");
		System.out.println(basicCache.get("1L"));
		
		cacheManager.close();
	}

	public void MyCache1() {
		try (CacheManager cacheManager = newCacheManagerBuilder()
				.withCache("basicCache", newCacheConfigurationBuilder(Long.class, String.class, heap(100).offheap(1, MB)))
				.build(true)) {
			Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);

			basicCache.put(1L, "da one!");
			System.out.println(basicCache.get(1L));

		}
	}
}
