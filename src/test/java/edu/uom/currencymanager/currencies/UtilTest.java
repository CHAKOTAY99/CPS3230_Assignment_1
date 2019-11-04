package edu.uom.currencymanager.currencies;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void testFormat(){
        // Setup
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,##0.00");
        // Exercise
        String result = decimalFormat.format(1);
        // Verify
        assertEquals("1.00", result);
    }

    @Test
    public void testApplyFormat(){
        // Exercise
        String result = Util.formatAmount(1);
        // Verify
        assertEquals("1.00", result);
    }
}
