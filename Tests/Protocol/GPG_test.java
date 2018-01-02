package Protocol;

import Utility.GPG;
import org.junit.Test;

public class GPG_test {
    @Test public void test(){
        GPG gpg =new GPG();
        //gpg.newKey("mac@mail.sustc.edu.cn","123456");
        String encryted = GPG.Encrypt("hello world","mac@mail.sustc.edu.cn","mac@mail.sustc.edu.cn","123456");
        System.out.println(encryted);
        System.out.println(GPG.Decrypt(encryted));

    }
}
