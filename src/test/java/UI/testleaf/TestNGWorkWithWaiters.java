package UI.testleaf;

import UI.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.testleafPages.AppearTestleafPage;
import pages.testleafPages.DesapperTestleafPage;
import pages.testleafPages.TextChengeTestleafPage;
public class TestNGWorkWithWaiters extends BaseTest {

    @Test
    public void checkThatElementDisapper() {
        DesapperTestleafPage desapperTestleafPage = new DesapperTestleafPage(driver);
        desapperTestleafPage.navigate();
        Assert.assertTrue(desapperTestleafPage.checkIsDisplayed(), "The element isn't displayed after opening the page");
        Assert.assertTrue(desapperTestleafPage.waiteThatElementHidden(), "Button isn't hidden");
    }

    @Test
    public void checkThatElementAppear() {
        AppearTestleafPage appearTestleafPage = new AppearTestleafPage(driver);
        appearTestleafPage.navigate();
        Assert.assertFalse(appearTestleafPage.checkIsDisplayed(), "Element is displayed after opening the page");
        appearTestleafPage.waiteThatElementIsDisplayed();
        Assert.assertTrue(appearTestleafPage.checkIsDisplayed(), "The element isn't shown");
    }

    @Test
    public void CheckThatTextOnTheButtonChenged() {
        TextChengeTestleafPage textChengeTestleafPage = new TextChengeTestleafPage(driver);
        textChengeTestleafPage.navigate();
        Assert.assertEquals(textChengeTestleafPage.getStartTextOnTheButton(), "I'm going to change text!!");
        Assert.assertTrue(textChengeTestleafPage.waitIfTextIsDisplayed());
    }

}



