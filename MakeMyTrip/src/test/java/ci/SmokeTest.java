package ci;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest {

    @Test(groups = "ci")
    public void ciSmokeTest() {
        System.out.println("✅ CI smoke test executed successfully");
        Assert.assertTrue(true, "CI sanity check passed");
    }
}

