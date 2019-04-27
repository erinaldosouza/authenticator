package br.com.tcc.authenticator.to;

import java.io.Serializable;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteTO implements Serializable {

	private static final long serialVersionUID = -4240458056743056532L;
	
	@JsonProperty("app_name")
	private String appName;
	
	private String link;
	
	@JsonProperty("fl_active")
	private Boolean flActive;
	
	private String description;
	
	private HttpMethod authorization;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HttpMethod getAuthorization() {
		return authorization;
	}

	public void setAuthorization(HttpMethod authorization) {
		this.authorization = authorization;
	}

}
