package bt.cbs.zrr.global.enumeration;

public enum ApplicationStatusCode {
    SUBMITTED("S", "SUBMITTED"),
    REVIEWED("R", "REVIEWED"),
    RETURNED("T", "RETURNED"),
    RE_SUBMITTED("O", "RE_SUBMITTED"),
    APPROVED("A", "APPROVED"),
    ACTIVE("O", "ACTIVE"),
    INACTIVE("O", "INACTIVE");

    private String value;
    private String text;

    ApplicationStatusCode(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String find(String value) {
        if (value.equals(ApplicationStatusCode.SUBMITTED.getValue())) {
            return ApplicationStatusCode.SUBMITTED.getText();
        }
        if (value.equals(ApplicationStatusCode.REVIEWED.getValue())) {
            return ApplicationStatusCode.REVIEWED.getText();
        }
        if (value.equals(ApplicationStatusCode.RETURNED.getValue())) {
            return ApplicationStatusCode.RETURNED.getText();
        }
        if (value.equals(ApplicationStatusCode.APPROVED.getValue())) {
            return ApplicationStatusCode.APPROVED.getText();
        }
        if (value.equals(ApplicationStatusCode.RE_SUBMITTED.getValue())) {
            return ApplicationStatusCode.RE_SUBMITTED.getText();
        }
        if (value.equals(ApplicationStatusCode.ACTIVE.getValue())) {
            return ApplicationStatusCode.ACTIVE.getText();
        }
        if (value.equals(ApplicationStatusCode.INACTIVE.getValue())) {
            return ApplicationStatusCode.INACTIVE.getText();
        }
        return null;
    }

}
