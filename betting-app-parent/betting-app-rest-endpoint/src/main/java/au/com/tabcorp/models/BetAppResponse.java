package au.com.tabcorp.models;

import java.util.List;

public class BetAppResponse<T> {
    List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
