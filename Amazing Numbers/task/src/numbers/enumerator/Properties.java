package numbers.enumerator;

public enum Properties {
    EVEN("even"),
    ODD("odd"),
    BUZZ("buzz"),
    DUCK("duck"),
    PALINDROMIC("palindromic"),
    GAPFUL("gapful"),
    SPY("spy"),
    SQUARE("square"),
    SUNNY("sunny"),
    JUMPING("jumping"),
    HAPPY("happy"),
    SAD("sad");
    private String propertyName;


    Properties (String propertyName) {
        this.propertyName = propertyName;
    }


    public String getPropertyName() {
        return this.propertyName;
    }

    public Properties getPropertiesByName(String propertyName) {
        for (Properties value : Properties.values()) {
            if (value == Properties.valueOf(propertyName)) {
                return value;
            }
        }
        return null;
    }

    public Boolean isProperty(String propertyName) {
        for (Properties value : Properties.values()) {
            if (value == Properties.valueOf(propertyName)) {
                return true;
            }
        }
        return false;
    }
}
