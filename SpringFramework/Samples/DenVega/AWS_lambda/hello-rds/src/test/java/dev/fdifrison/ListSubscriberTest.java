package dev.fdifrison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListSubscriberTest {

    private ListSubscriber sut;

    @BeforeEach
    void setUp() {
        // in production, we would probably have a docker instance of the db to test
        DataSourceProperties db = new DataSourceProperties(
                "awspgdb.cuvvtod3ubgs.eu-central-1.rds.amazonaws.com",
                5432,
                "newsletter",
                "fdifrison",
                "123qweasd");

        sut = new ListSubscriber(db);
    }

    @Test
    public void numOfSubscribers() {
        List<String> emails = sut.handleRequest();
        assertEquals(4, emails.size());
    }

}