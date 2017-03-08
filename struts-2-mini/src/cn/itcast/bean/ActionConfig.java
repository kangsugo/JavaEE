package cn.itcast.bean;

import java.util.List;
import java.util.Map;

public class ActionConfig {
	private String name;
	private String clazz;
	private String method;
	private Map<String,ResultConfig> results;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, ResultConfig> getResults() {
		return results;
	}
	public void setResults(Map<String, ResultConfig> results) {
		this.results = results;
	}
	
	
}
