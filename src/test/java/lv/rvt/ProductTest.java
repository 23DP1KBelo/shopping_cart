package lv.rvt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProductTest {

    /**
     * Get product name test
     */
    @Test
    public void getNameTest()
    {
        Products product = new Products("Test product");
        assertEquals( "Test product", product.getName());

        Products product2 = new Products("Test product2");
        assertEquals( "Test product2", product2.getName());

        Products product3 = new Products("Test product3");
        assertEquals( "Test product3", product3.getName());
    }

    /**
     * Get total price of product list
     * 
     */
    @Test
    public void priceOfproductTest()
    {
        assertEquals( 100.0d+"", Products.priceOfproduct(10.0d, 10)+"");
    }

    /**
     * Get product price
     * 
     */
    @Test
    public void getPriceTest()
    {
        Products product = new Products("Test category", "Test product", 30.0d, "100");
        assertEquals( 30.0d+"", product.getPrice()+"");
    }


    /**
     * Get product weight test
     * 
     */
    @Test
    public void getWeightTest()
    {
        Products product = new Products("Test category", "Test product", 30.0d, "100");
        assertEquals( "100", product.getWeight());
    }

}