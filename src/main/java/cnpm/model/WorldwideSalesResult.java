package cnpm.model;

import java.util.List;


public class WorldwideSalesResult {
    private String title;
    private List<String> categories;
    private List<SalesResult> series;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SalesResult> getSeries() {
        return series;
    }

    public void setSeries(List<SalesResult> series) {
        this.series = series;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
