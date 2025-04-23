package lv.rvt;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CartTest {

    /**
     * Promocode test
     */
    @Test
    public void promocodeTest()
    {
        assertEquals( 10.0d+"", Cart.promocode(100.0, 10)+"" );
        assertEquals( 15.0d+"", Cart.promocode(100.0, 15)+"" );
        assertEquals( 20.0d+"", Cart.promocode(100.0, 20)+"" );
    }

}
