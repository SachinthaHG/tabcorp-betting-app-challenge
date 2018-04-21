package au.com.tabcorp.core.models;

/**
 * model class for a report
 */
public class Report<T> {
    private T reports;

    public T getReports() {
        return reports;
    }

    public void setReports(T reports) {
        this.reports = reports;
    }
}
