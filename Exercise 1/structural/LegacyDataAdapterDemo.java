interface ModernDataFormat {
    String requestData(String startDate, String endDate);
}

class LegacyDataFormat {
    public String getOldData(String dateRange) {
        return "Legacy data for range: " + dateRange;
    }
}

class DataAdapter implements ModernDataFormat {
    private LegacyDataFormat legacySystem;

    public DataAdapter(LegacyDataFormat legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public String requestData(String startDate, String endDate) {
        String dateRange = startDate + " - " + endDate;
        return legacySystem.getOldData(dateRange);
    }
}

public class LegacyDataAdapterDemo {
    public static String clientCode(ModernDataFormat dataRequester, String startDate, String endDate) {
        return dataRequester.requestData(startDate, endDate);
    }

    public static void main(String[] args) {
        ModernDataFormat modernSystem = new ModernDataFormat() {
            @Override
            public String requestData(String startDate, String endDate) {
                return "Modern data from " + startDate + " to " + endDate;
            }
        };
        System.out.println(clientCode(modernSystem, "2023-01-01", "2023-12-31"));

        LegacyDataFormat legacySystem = new LegacyDataFormat();
        DataAdapter adapter = new DataAdapter(legacySystem);
        System.out.println(clientCode(adapter, "2023-01-01", "2023-12-31"));
    }
}
