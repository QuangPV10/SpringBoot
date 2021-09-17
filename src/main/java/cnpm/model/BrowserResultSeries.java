package cnpm.model;

import java.util.List;

public class BrowserResultSeries {
    private String name;
    private List<BrowserResult> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BrowserResult> getData() {
        return data;
    }

    public void setData(List<BrowserResult> data) {
        this.data = data;
    }

}
