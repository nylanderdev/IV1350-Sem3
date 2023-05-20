package iv1350.view;

import iv1350.Observer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

class TotalRevenueFileOutput {
    private PrintStream output;

    TotalRevenueFileOutput(String filePath, Observer<Integer> revenueObservable) throws IOException {
        File logFile = new File(filePath);
        logFile.createNewFile();
        output = new PrintStream(logFile);
	    revenueObservable.addListener(this::updateRevenue);
    }

    private void updateRevenue(Integer newRevenue) {
	    output.printf("Revenue is now %d¤\n\n", newRevenue);
    }
}
