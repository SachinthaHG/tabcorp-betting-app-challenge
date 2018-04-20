package au.com.tabcorp.core.models;

import java.util.List;

public class Report<T> {
    private T reports;

    public T getReports() {
        return reports;
    }

    public void setReports(T reports) {
        this.reports = reports;
    }
}
