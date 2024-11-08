package store.model;

import camp.nextstep.edu.missionutils.DateTimes;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private String startDate;
    private String endDate;

    public Promotion(String name, int buy, int get, String startDate, String endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
