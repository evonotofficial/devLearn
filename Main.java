package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author Evo
 *
 */
public class Main {

	private static Random ran = new Random(1000);

	public static void main(String arg[]) {
		try {
			lists();
			maps();
			sets();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//***************************************************************************
	public static void lists() throws InterruptedException {
		int limit = (int) 1e5;
		//Allows identical objects
		List<Integer> arrayList = new ArrayList<>(); //not synchronized - good for get(i) and add/delete from end only
		List<Integer> vectorList = new Vector<>(); //same as arrayList, but synchronized 
		List<Integer> linkedList = new LinkedList<>(); //not synchronized - good for add/delete from start/middle, slow when get(i)

		long time = System.currentTimeMillis();
		for (int i = 0; i < limit; i++) {
			arrayList.add(0, ran.nextInt());
		}
		System.out.println("Adding at index 0 in arrayList: " + (System.currentTimeMillis() - time) + "ms. Length: " + arrayList.size());
		time = System.currentTimeMillis();
		
		for (int i = 0; i < limit; i++) {
			linkedList.add(0, ran.nextInt());
			linkedList.add(0, ran.nextInt());
		}
		System.out.println("Adding at index 0 in linkedList: " + (System.currentTimeMillis() - time) + "ms. Length: " + linkedList.size());
		time = System.currentTimeMillis();
		
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < limit/2; i++) {
				vectorList.add(0, ran.nextInt());
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < limit/2; i++) {
				vectorList.add(0, ran.nextInt());
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Adding at index 0 in vectorList: " + (System.currentTimeMillis() - time) + "ms. Length: " + vectorList.size());
		time = System.currentTimeMillis();
	}
	
	//***************************************************************************
	public static void maps(){
		Map<Integer, String> hashMap = new HashMap<>();//key->map collection - fastest search, no order of the entries 
		Map<Integer, String> treeMap = new TreeMap<>();//sort the entries by key
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();//keep order of adding

		fillMaps(hashMap);
		printMap(hashMap);
		fillMaps(treeMap);
		printMap(treeMap);
		fillMaps(linkedHashMap);
		printMap(linkedHashMap);

	}
	
	public static void fillMaps(Map<Integer, String> hashMap){
		hashMap.put(3, "three");
		hashMap.put(4, "four");
		hashMap.put(2, "two");
		hashMap.put(1, "one");
		hashMap.put(5, "five");
	}
	public static void printMap(Map<Integer, String> map){
		for(Integer i: map.keySet()){
			System.out.print("[" + i + ": '" + map.get(i) + "'], ");
		}
		System.out.println();
	}
	
	//***************************************************************************
	public static void sets(){
		//* optimized for searching - contains()
		//* to work correctly with custom objects - override toString() and hashCode()
		//* not allowing identical objects - second identical is not added (no overriding the first one)
		Set<String> tempSet = new HashSet<>();//set get cool functionality like - 
		//hashSet.retainAll(tempSet); //keep all values in hashSet, which are found in tempSet
		//treeSet.removeAll(tempSet); //remove all values in hashSet, which are found in tempSet
		
		Set<String> hashSet = new HashSet<>(); //optimized for searching - contains()
		Set<String> treeSet = new TreeSet<>(); //sorted by the values
		Set<String> linkedHashSet = new LinkedHashSet<>(); //keeps order of adding

		fillSets(hashSet);
		printSet(hashSet);
		fillSets(treeSet);
		printSet(treeSet);
		fillSets(linkedHashSet);
		printSet(linkedHashSet);
		tempSet.add("7");
		tempSet.add("1");
		tempSet.add("4");
		System.out.print("tempSet: ");
		printSet(tempSet);

		System.out.print("retainAll(tempSet): ");
		hashSet.retainAll(tempSet); //keep all values in hashSet, which are found in tempSet
		printSet(hashSet);

		System.out.print("removeAll(tempSet): ");
		treeSet.removeAll(tempSet); //remove all values in hashSet, which are found in tempSet
		printSet(treeSet);
	}
	
	public static void fillSets(Set<String> set){
		set.add("4");
		set.add("2");
		set.add("6");
		set.add("1");
		set.add("3");
	}

	public static void printSet(Set<String> set){
		for(String s: set){
			System.out.print(s + ", ");
		}
		System.out.println();
	}
	
}