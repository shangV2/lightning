package com.lightning.datacenter.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	@Test
	public void testConversion() {
		
		List<String> words3 = new ArrayList<String>();
		words3.add("test");
		
		System.out.println(words3.getClass().getName());
		
		List<String> words = Arrays.asList(new String[] { "hello", "test" });
		for ( String w : words ) {
			System.out.println(w);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
		Gson gson = new Gson();
		String text = gson.toJson(words);
		System.out.println(text);
		
		System.out.println("=============================================================");
		
		Gson gson2 = new Gson();
//		List<String> word2 = (List<String>)gson2.fromJson(text, ArrayList.class);
		
		List<String> word2 = gson2.fromJson(text, new TypeToken<List<String>>() {}.getType());
		
		for ( String w : word2  ) {
			System.out.println(w);
		}
		
	}
	
}
