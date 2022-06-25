package UI.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider()
    public Object[][] userCredentialsDataProvider() {
        return new Object[][]{{"tomsmith", "wqerty1233", "Your password is invalid!\n×"},
                {"tomsmith1", "SuperSecretPassword!", "Your username is invalid!\n×"},
                {"tomsmith1", "SuperSecretPassword!1", "Your username is invalid!\n×"}}
                ;
    }
}
