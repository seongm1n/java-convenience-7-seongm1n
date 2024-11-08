package store.model;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final String startDate;
    private final String endDate;

    public Promotion(String name, int buy, int get, String startDate, String endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
