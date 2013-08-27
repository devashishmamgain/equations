package in.co.equations.modules.session;

import in.co.equations.modules.user.EqUser;
import java.io.Serializable;

/**
 *
 * @author devashish
 */
public class UserSession implements Serializable {
    
    private EqUser eqUser;

    public EqUser getEqUser() {
        return eqUser;
    }

    public void setEqUser(EqUser eqUser) {
        this.eqUser = eqUser;
    }
}
