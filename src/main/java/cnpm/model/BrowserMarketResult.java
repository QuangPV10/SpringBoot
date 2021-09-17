package cnpm.model;

import java.util.List;


public class BrowserMarketResult {
    private String title;
    private List<BrowserResultSeries> series;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BrowserResultSeries> getSeries() {
        return series;
    }

    public void setSeries(List<BrowserResultSeries> series) {
        this.series = series;
    }
}
