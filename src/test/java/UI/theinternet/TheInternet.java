package UI.theinternet;

import UI.BaseTest;
import UI.dataproviders.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.theinternet.TheInternetLoginPage;
import testdata.User;

public class TheInternet extends BaseTest {

    // valid login: tomsmith
    // valid password: SuperSecretPassword!

    @Test(description = "Error message should appears if user enter invalid credentiols", dataProvider = "userCredentialsDataProvider", dataProviderClass = DataProviders.class)
    public void errorMessageShouldAppears(String login, String password, String errormessage) {
        User user = new User(login, password);
        TheInternetLoginPage theInternetLoginPage = new TheInternetLoginPage(driver);
        theInternetLoginPage.navigate();
        theInternetLoginPage.login(user);
        Assert.assertEquals(theInternetLoginPage.getErrorMessage(), errormessage);
        }
}

//    @Test
//    public void arrayTest() {
//        int[][] array = new int[4][6];
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                array[i][j] = i + j;
//            }
//        }
//        print(arrayTest();
//    }
//
//    private void print() {
//        for (int row = 0; row < arr.length; row++)//Cycles through rows
//        {
//            for (int col = 0; col < arr[row].length; col++)//Cycles through columns
//            {
//                System.out.printf("%5d", arr[row][col]); //change the %5d to however much space you want
//            }
//            System.out.println(); //Makes a new row
//        }
//This allows you to print the array as matrix


        // 1, 2, 3, 4, 5  - нульовий рядок
        // 6, 7, 8, 9  -перший рядок
        // 11, 14, 17, 88
        // і вкожного елемента є номе стовпця 14 = 3.2


