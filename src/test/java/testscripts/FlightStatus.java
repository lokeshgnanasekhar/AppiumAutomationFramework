package testscripts;

import org.testng.annotations.Test;
import testutils.TestBase;

public class FlightStatus extends TestBase {

    @Test
    public void findFlightStatus() {

        launchApp().continueAsAGuest()
                   .clickMenuForMoreOptions()
                   .clickFlightStatusMenu()
                   .setFlightDateAndNumber("Today","423")
                   .submitFlightDetails();


    }

}
