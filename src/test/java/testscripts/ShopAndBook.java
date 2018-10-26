package testscripts;

import org.testng.annotations.DataProvider;
import testutils.TestBase;
import org.testng.annotations.Test;


public class ShopAndBook extends TestBase {

    @Test
    public void BookAFlight() {

        launchApp().continueAsAGuest()
                    .setTripType("oneway")
                    .setOrigin("JFK")
                    .setDestination("ORD")
                    .selectDate()
                    .searchFlight()
                    .scrollToViewDetails("");

    }

}
