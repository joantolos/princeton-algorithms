package percolation;

import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {

    private final Percolation percolation = new Percolation(5);

    @Test
    public void shouldGetFlatIndexFromCoordinates() {
        Assert.assertEquals(Integer.valueOf(12), percolation.toIndex(3,2));
        Assert.assertEquals(Integer.valueOf(1), percolation.toIndex(1,1));
        Assert.assertEquals(Integer.valueOf(4), percolation.toIndex(1,4));
        Assert.assertEquals(Integer.valueOf(7), percolation.toIndex(2,2));
    }

}
