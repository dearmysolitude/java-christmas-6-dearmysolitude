package christmas;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        EventPlanner eventPlanner = new EventPlanner();
        
        eventPlanner.makeTotalprice();
        eventPlanner.discountOrNot();
        eventPlanner.makeTotalAdvantage();
    }
}
