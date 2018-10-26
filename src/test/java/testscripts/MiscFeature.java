package testscripts;

import org.testng.annotations.Test;
import testutils.TestBase;

public class MiscFeature extends TestBase {

    @Test
    public void scrollMenuForOptions() {

        launchApp().continueAsAGuest()
                .clickMenuForMoreOptions()
                .clickContactUsMenu();



    }

}
