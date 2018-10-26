package testscripts;

import org.testng.annotations.Test;
import testutils.TestBase;

public class EnrollmentFeature extends TestBase {

    @Test
    public void standardEnrollmentFromLoginScreen() {

        launchApp().tapJoinFlyingClub()
                    .fillJoinFlyingClubScreenDetails("test@testing.com","Pass1234")
                    .fillCreateYourProfileDetails("Peter" ,"Perk" , "Parker", "17", "October", "1988", "Female")
                    .fillContactInfoDetails("2017730284", true)
                    .fillAddressDetails("1701 somerset place","Apt 4","Somerset","Newport","40220")
                    .fillAccountSecurityDetails("What is the name of your first pet?","correct","What is your favorite foreign city?","Orlando");
    }

}
