package iv1350.view;

import iv1350.Observer;

class TotalRevenueView {
    TotalRevenueView(Observer<Integer> revenueObservable) {
	    revenueObservable.addListener(this::updateRevenue);
    }

    private void updateRevenue(Integer newRevenue) {
	    System.out.printf("Revenue is now %d¤\n", newRevenue);
    }
}
