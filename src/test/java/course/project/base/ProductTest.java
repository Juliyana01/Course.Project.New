package course.project.base;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends TestUtil{

    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage ProductPage = loginPage.login("standard_user", "secret_sauce");
        ProductPage.addToCartByProductName("backpack");

        Assert.assertEquals(ProductPage.getNumbersInTheCart(), 1, "Because we have added one item in the cart");
    }


}
