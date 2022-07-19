package sportresults.model;

import lombok.Getter;

@Getter
public enum sportType {

    SWIMMING('s'),
    RUNNING('s'),
    POLE_VAULT('m'),
    HAMMER_THROWING('m');

    private final char measureUnit;

    sportType(char measureUnit) {
        this.measureUnit = measureUnit;
    }
}
