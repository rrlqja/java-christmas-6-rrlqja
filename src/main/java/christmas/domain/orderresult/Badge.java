package christmas.domain.orderresult;

public enum Badge {
    SANTA("산타"),
    TREE("트리"),
    STAR("별"),
    NONE("없음");

    private static final int SANTA_THRESHOLD = 20000;
    private static final int TREE_THRESHOLD = 10000;
    private static final int STAR_THRESHOLD = 5000;
    private final String badge;

    Badge(String badge) {
        this.badge = badge;
    }

    public static Badge valueOfBenefit(Integer totalBenefit) {
        if (totalBenefit > SANTA_THRESHOLD) {
            return SANTA;
        }
        if (totalBenefit > TREE_THRESHOLD) {
            return TREE;
        }
        if (totalBenefit > STAR_THRESHOLD) {
            return STAR;
        }
        return NONE;
    }

    public String getBadge() {
        return badge;
    }
}
