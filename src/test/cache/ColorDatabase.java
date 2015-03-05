package test.cache;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorDatabase {
	private static final Map<String, Color> colorMap = new HashMap<String, Color>();

	static {
		colorMap.put("red", Color.red);
		colorMap.put("blue", Color.blue);
		colorMap.put("green", Color.green);
		colorMap.put("white", Color.white);
		colorMap.put("black", Color.black);
		colorMap.put("lightGray", Color.lightGray);
		colorMap.put("gray", Color.gray);
		colorMap.put("darkGray", Color.darkGray);
		colorMap.put("pink", Color.pink);
		colorMap.put("orange", Color.orange);
		colorMap.put("yellow", Color.yellow);
		colorMap.put("magenta", Color.magenta);
		colorMap.put("cyan", Color.cyan);
	}

	public ColorDatabase() {
		/**/
	}

	/**
	 * Simulates retrieving expensive object from SOR.
	 */
	public Color getColor(String name) {
		Color color = colorMap.get(name);
		if (color == null) {
			return null;
		}
		try {
			Thread.sleep(3000);
		} catch (Exception e) {/**/
		}
		return color;
	}
}
