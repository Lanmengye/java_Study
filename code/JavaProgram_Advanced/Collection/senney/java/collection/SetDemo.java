package senney.java.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Set：单列无序集合，元素不重复 常用实现类：HashSet, TreeSet
 * 
 * @author Lanme
 *
 */
public class SetDemo {
	/**
	 * 不对存入的元素进行排序
	 */
	@Test
	public void hashSet_test() {
		Set<String> str_set = new HashSet<String>();
		str_set.add("aaa");
		str_set.add("ccc");
		str_set.add("bbb");
		str_set.add("ddd");
		str_set.add("ccc");
		str_set.add("cdb");
		System.out.print("原始数据：");
		for (String s : str_set) {
			System.out.print(s + "\t");
		}
		System.out.println();
		System.out.println("str_set.remove(\"bbb\") = " + str_set.remove("bbb"));
		System.out.println("str_set.contains(\"bbb\") = " + str_set.contains("bbb"));
		// 添加重复元素，返回false
		System.out.println("str_set.add(\"ddd\") = " + str_set.add("ddd"));
	}

	/**
	 * treeSet： 如果调用空的构造函数，则按照自然排序规则对元素进行排序， 否则按照传入构造函数的comparator对元素进行排序
	 */
	@Test
	public void treeSet_test() {
		Set<String> str_set = new TreeSet<String>();
		str_set.add("aaa");
		str_set.add("ccc");
		str_set.add("bbb");
		str_set.add("ddd");
		str_set.add("ccc");
		str_set.add("cdb");
		System.out.print("原始数据：");
		for (String s : str_set) {
			System.out.print(s + "\t");
		}
		System.out.println();
		System.out.println("str_set.remove(\"bbb\") = " + str_set.remove("bbb"));
		System.out.println("str_set.contains(\"bbb\") = " + str_set.contains("bbb"));
		// 添加重复元素，返回false
		System.out.println("str_set.add(\"ddd\") = " + str_set.add("ddd"));
	}
}
