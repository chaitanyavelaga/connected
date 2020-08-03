package com.chaitu.connected;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class Application {
	
	@Value("classpath:city.txt")
    private Resource res;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Bean(name = "cityConnectionMap")
    public Map<String, Set<String>> getCityConnectionMap() throws IOException {
		Map<String, Set<String>> cityConnectionMap = new HashMap<>();
		List<String> lines;
		lines = Files.readAllLines(Paths.get(res.getURI()),
				StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] words = line.split(", ");
			String city1 = words[0];
			String city2 = words[1];
			Set<String> city1Neighbors = cityConnectionMap.getOrDefault(city1, new HashSet<String>());
			city1Neighbors.add(city2);
			cityConnectionMap.put(city1, city1Neighbors);
			Set<String> city2Neighbors = cityConnectionMap.getOrDefault(city2, new HashSet<String>());
			city2Neighbors.add(city1);
			cityConnectionMap.put(city2, city2Neighbors);
		}
		return cityConnectionMap;
    }
	
	
}
