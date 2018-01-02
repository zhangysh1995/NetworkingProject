package Protocol;

import Utility.GPG;
import org.junit.Test;

public class GPG_test {
    @Test public void test(){
        GPG gpg =new GPG();
        gpg.newKey("mac@mail.sustc.edu.cn","123456");

    }
}
