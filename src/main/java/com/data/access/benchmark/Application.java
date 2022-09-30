package com.data.access.benchmark;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}
}