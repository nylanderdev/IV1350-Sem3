package iv1350.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MockItemDaoTests {
    private final static int VALID_ID = 2;
    private final static int INVALID_ID = 3;
    private MockItemDAO dao;
    @BeforeEach
    void setupDao() {
        dao = new MockItemDAO();
    }
    @Test
    void invalidIdThrowsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> dao.fetchItemById(INVALID_ID));
    }
    @Test
    void validIdDoesNotThrowException() {
        assertDoesNotThrow(() -> dao.fetchItemById(VALID_ID));
    }
    @Test
    void databaseFailureIdThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class,
                () -> dao.fetchItemById(DatabaseFailureException.DATABASE_FAILURE_TRIGGER_ID));
    }
}
