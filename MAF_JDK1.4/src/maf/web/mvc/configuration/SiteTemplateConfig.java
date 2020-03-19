package maf.web.mvc.configuration;

public class SiteTemplateConfig {
    private String site;
    private String uri;
    
    public SiteTemplateConfig() {}
    
    public void setSite(String name) {
        this.site = name;
    }
    public String getSite() {
        return site;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUri() {
        return uri;
    }
}