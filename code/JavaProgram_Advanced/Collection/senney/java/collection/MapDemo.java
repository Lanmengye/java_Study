package senney.java.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.junit.Test;

/**
 *  Map： 双列、无序、不重复集合
 *  常用实现类： HashMap, TreeMap
 * @author Lanme
 *
 */
public class MapDemo {
	@Test
	public void hashMap_test() {
		Map<String, Integer> country_map = new HashMap<String, Integer>();
		// 添加数据
		country_map.put("中国", 1);
		country_map.put("英国", 2);
		country_map.put("美国", 3);
		country_map.put("法国", 4);
		// 添加null键
		country_map.put(null, 5);
		// 添加null值
		country_map.put("德国", null);
		// 遍历map
		for (Entry<String, Integer> entry : country_map.entrySet()) {
			System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
		}
		// 添加重复键，将覆盖原值
		country_map.put("法国", 8);
		System.out.println("country_map.get(\"法国\") = " + country_map.get("法国"));
		// 根据键删除元素
		System.out.println("country_map.remove(null) = " + country_map.remove(null));

		// 遍历map
		for (String key : country_map.keySet()) {
			System.out.println("key = " + key + ", value = " + country_map.get(key));
		}
	}

	@Test
	public void treeMap_test() {
		Map<String, Integer> country_map = new TreeMap<String, Integer>();
		country_map.put("China", 1);
		country_map.put("America", 2);
		country_map.put("France", 3);
		country_map.put("UK", 4);
		country_map.put("China", 5); 

		System.out.println("country_map.get(\"China\") = " + country_map.get("China"));

		System.out.println("country_map.remove(\"France\") = " + country_map.remove("France"));

		for (Entry<String, Integer> entry : country_map.entrySet()) {
			System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
		}
	}
}
