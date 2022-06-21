package numbers.helper;

import numbers.enumerator.Properties;

import static numbers.enumerator.Properties.*;


public class PropertyOppositeFinder {
    public static Properties find(Properties originalProperty) {
        return switch (originalProperty) {
            case EVEN -> ODD;
            case ODD -> EVEN;
            case DUCK -> SPY;
            case SPY -> DUCK;
            case SQUARE -> SUNNY;
            case SUNNY -> SQUARE;
            default -> null;
        };
    }
}
