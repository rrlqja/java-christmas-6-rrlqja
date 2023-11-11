package christmas.domain;

public enum Badge {
    SANTA("산타"),
    TREE("트리"),
    START("별"),
    NONE("없음");

    private final String badge;

    Badge(String badge) {
        this.badge = badge;
    }

    public static Badge valueOfBenefit(Integer totalBenefit) {
        if (totalBenefit > 20000) {
            return SANTA;
        }
        if (totalBenefit > 10000) {
            return TREE;
        }
        if (totalBenefit > 5000) {
            return START;
        }
        return NONE;
    }

    public String getBadge() {
        return badge;
    }
}
