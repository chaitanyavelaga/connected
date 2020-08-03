package com.chaitu.connected;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConnectedController {
	@Autowired
	private Map<String, Set<String>> cityConnectionMap;
	@GetMapping("/connected")
	public String connected(@RequestParam(value = "origin", required = false) String origin, @RequestParam(value = "destination", required = false) String destination) {
		Set<String> visited = new HashSet<String>();
		Stack<String> cities = new Stack<>();
		cities.push(origin);
		while(!cities.isEmpty()) {
			String city = cities.pop();
			if(city != null && !visited.contains(city)) {
				visited.add(city);
				if(city.equals(destination)) {
					return Connected.YES.getValue();
				}
				Set<String> neighbors = cityConnectionMap.get(city);
				if(neighbors != null) {
					for (String neighbor : neighbors) {
						if(!visited.contains(neighbor)) {
							cities.push(neighbor);
						}
					}
				}
			}
		}
		return Connected.NO.getValue();
	}
}
