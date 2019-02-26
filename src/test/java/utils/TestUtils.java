package utils;

import com.shop.ssm.utils.Util;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2019/2/25.
 */
public class TestUtils {

    @Test
    public void testIsNull(){
        String a=null;
        String b="a";
        String c="c";
        Assert.assertTrue(Util.isNotNull(a,b,c));
    }
}
