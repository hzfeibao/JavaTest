package test.cache;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class ColorCache {
	private static final CacheManager cacheManager = new CacheManager();
	private static final ColorDatabase colorDatabase = new ColorDatabase();
	
	public ColorCache() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Color getColor(String name){
		Element elem = getCache().get(name);
		if(elem == null){
			Color color = colorDatabase.getColor(name);
			if(color == null){
				return null;
			}
			getCache().put(elem = new Element(name, color));
		}
		return (Color)elem.getValue();
	}
	
	private Ehcache getCache(){
		return cacheManager.getEhcache("colors");
	}
	
	private Color getCachedColor(String name){
		Element elem = getCache().get(name);
		return elem != null ? (Color)elem.getValue():null;
	}
	
	public long getTTL(){
		return getCache().getCacheConfiguration().getTimeToLiveSeconds();
	}
	
	public long getTTI(){
		return getCache().getCacheConfiguration().getTimeToIdleSeconds();
	}
	
	public int getSize(){
		return getCache().getSize();
	}
	
	public String[] getColorNames(){
		Iterator<String> keys = getCache().getKeys().iterator();
		List<String> list = new ArrayList<String>();
		while(keys.hasNext()){
			String name = keys.next();
			if(getCachedColor(name) != null){
				list.add(name);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	public Color[] getColors(){
		Iterator<String> keys = getCache().getKeys().iterator();
		List<Color> list = new ArrayList<Color>();
		while(keys.hasNext()){
			String name = keys.next();
			if(getCachedColor(name) != null){
				list.add(getCachedColor(name));
			}
		}
		return list.toArray(new Color[list.size()]);
	}
	
	public static void main(String[] args){
		String[] cs = {"red", "blue", "green", "white", "gray", "orange", };
		ColorCache cc = new ColorCache();
		for(String str : cs){
			cc.getColor(str);
		}
		
		String[] names = cc.getColorNames();
		Color[] ls = cc.getColors();
		System.out.println("names");
		for(String str : names){
			System.out.println(str);
		}
		System.out.println("colors");
		for(Color l : ls){
			System.out.println(l.toString());
		}
	}
}
