package br.com.tcc.authenticator.to;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupTO implements Serializable {

	private static final long serialVersionUID = 3830908931325144325L;
	
	private String name;
	
	private String decription;
	
	@JsonProperty("fl_active")
	private Boolean flActive;
	
	@JsonProperty("route")
	private List<RouteTO> routes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	public List<RouteTO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteTO> routes) {
		this.routes = routes;
	}

}
