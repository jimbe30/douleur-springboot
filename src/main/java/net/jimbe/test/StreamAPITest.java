package net.jimbe.test;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamAPITest {
	
	public void test() {
		
		String[] liste = {"La voiture", "à", "Toto", "est", "vert", "chiasse"};
		
		Stream<String> stream = Stream.of(liste)
				.map(elem -> elem.concat("<br>"));
		
		String str = Stream.of(liste)
				.map(elem -> elem.concat("\r\n"))
				.reduce( (result, elem) -> result.concat(elem))
				.get();
		
		
		
		
		System.out.println(str); 
		
		System.out.println(Arrays.asList(stream.toArray()));
		
	}
	
	public static void main(String... args) {
		
		StreamAPITest t = new StreamAPITest();
		t.test();
	}
	

}
