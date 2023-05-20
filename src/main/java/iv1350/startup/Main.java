package iv1350.startup;

import iv1350.controller.Controller;
import iv1350.integration.*;
import iv1350.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * The class responsible for setting up and starting the application
 */
public class Main {
    /**
     * Sets up relevant dependencies, then hands control to the view
     * @param args Command-line arguments, unused
     */
    public static void main(String[] args) {
        boolean logToFile = true;
        File errorLog = new File("./log.txt");
        try {
            errorLog.createNewFile();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            logToFile = false;
        }
        if (logToFile) {
            try {
                System.setErr(new PrintStream(errorLog));
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.err);
            }
        }

        DiscountDAO discountDAO = new MockDiscountDAO();
        ItemDAO itemDAO = new MockItemDAO();
        SaleDAO saleDAO = new MockSaleDAO();
        DAOCollection daoCollection = new DAOCollection(discountDAO, itemDAO, saleDAO);
        DAOCollection.setSingleton(daoCollection);

        Controller controller = new Controller();
        View view = new View();
        view.run(controller);
    }
}
