package senney.java.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * List：单列有序集合，元素可以重复
 * 常用实现类： ArrayList, LinkedList
 * @author Lanme
 *
 */
public class ListDemo {
	@Test
	public void arrayList_test() {
		List<String> str_list = new ArrayList<String>();
		str_list.add("中国");
		str_list.add("英国");
		str_list.add("美国");
		str_list.add("法国");
		str_list.add("俄国");

		System.out.print("原始数据：");
		for (String s : str_list) {
			System.out.print(s+"\t");
		}
		System.out.println();
		System.out.println("str_list.set(2, \"德国\") = " + str_list.set(2, "德国"));
		System.out.println("str_list.get(3) = " + str_list.get(3));
		System.out.println("str_list.contains(\"中国\") = " + str_list.contains("中国"));

		for (String s : str_list) {
			System.out.print(s+"\t");
		}
	}

	@Test
	public void linkedList_test() {
		List<String> str_list = new LinkedList<String>();
		str_list = Arrays.asList(new String[] { "中国", "英国", "美国", "美国", "俄国" });
		System.out.print("原始数据：");
		for (String s : str_list) {
			System.out.print(s+"\t");
		}
		System.out.println();
		System.out.println("str_list.set(2, \"德国\") = " + str_list.set(2, "德国"));
		System.out.println("str_list.get(3) = " + str_list.get(3));
		System.out.println("str_list.contains(\"中国\") = " + str_list.contains("中国"));

		for (String s : str_list) {
			System.out.print(s+"\t");
		}
	}
}
