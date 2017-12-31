package cyy_IM_protocol;

public class GPG_factory {

    public String log_on(String email) {
        return "publicKey and privateKey";
    }
    public String get_public_key(String email) {
        return "public_key";
    }
    public String encrypte(String public_key, String content) {
        if(true) {
            return "encrypted string";
        }else {
            return null;
        }
    }

    public String Reget_private_key(String email, String passord) {
        return "private_key";
    }

    public String decrypte(String private_key) {
        return "original content";
    }
}
