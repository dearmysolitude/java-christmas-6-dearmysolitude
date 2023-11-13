package christmas.Enum;

public enum Badge {
    STAR,
    TREE,
    SANTA;
    
    public static Badge badgeOf(Integer totalAdvantage) {
        Badge badge;
        badge = isItStar(totalAdvantage);
        if(badge == null) {
            badge = isItTree(totalAdvantage);
        }
        if(badge == null) {
            badge = isItSANTA(totalAdvantage);
        }
        return badge;
    }
    private static Badge isItStar(Integer totalAdvantage) {
        if(totalAdvantage >= 5000 && totalAdvantage < 10000) {
            return STAR;
        }
        return null;
    }
    private static Badge isItTree(Integer totalAdvantage) {
        if(totalAdvantage >= 10000 && totalAdvantage < 20000) {
            return TREE;
        }
        return null;
    }
    private static Badge isItSANTA(Integer totalAdvantage) {
        if(totalAdvantage > 20000) {
            return SANTA;
        }
        return null;
    }

}
