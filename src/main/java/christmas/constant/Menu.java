package christmas.constant;

public enum Menu {
    SOUP("양송이수프", 6000, Sort.APITIZER),
    TAPAS("타파스", 5500, Sort.APITIZER),
    SALAD("시저샐러드", 8000, Sort.APITIZER),
    STEAK("티본스테이크", 55000, Sort.MAINDISH),
    RIB("바비큐립", 54000, Sort.MAINDISH),
    SEAFOODPASTA("해산물파스타", 35000, Sort.MAINDISH),
    CHRISTMASPASTA("크리스마스파스타", 25000, Sort.MAINDISH),
    CAKE("초코케이크", 15000, Sort.DESSERT),
    ICECREAM("아이스크림", 5000, Sort.DESSERT),
    COKE("제로콜라", 3000, Sort.DRINK),
    WINE("레드와인", 60000, Sort.DRINK),
    CHAMPAGNE("샴페인", 25000, Sort.DRINK);

    private final String foodName;
    private final int price;
    private final Sort sort;
    
    Menu(String foodName, int price, Sort sort) {
        this.foodName = foodName;
        this.price = price;
        this.sort = sort;
    }
    
    public String getfoodName() {
        return this.foodName;
    }
    public int getPrice() { return this.price; }
    public Sort getSort() { return this.sort; }
         
}
