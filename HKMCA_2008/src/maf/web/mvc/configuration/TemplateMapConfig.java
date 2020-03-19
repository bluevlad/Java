package maf.web.mvc.configuration;

public class TemplateMapConfig {
    private String name;
    private String uri;
    
    public TemplateMapConfig() {}
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUri() {
        return uri;
    }
}