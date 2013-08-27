package in.co.equations.common.modules.channel;

/**
 *
 * @author devashish
 */
public class InstantMessage {

    public static enum TYPE {

        SMS_RECEIVED("S.R"), SMS_SENDING("S.S"), SMS_SENT_UPDATE("S.S.U"), 
        SMS_DELIVERED_UPDATE("S.D.U"), CONTACT_SYNC_COMPLETED("C.S.C"), 
        LOGOUT("L"), RENEW_OAUTH_TOKEN("R.O.T");
        
        private String value;

        private TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    };
    private TYPE type;
    private String message;
    private boolean notifyUser = true;

    public InstantMessage(TYPE type, String message) {
        this.type = type;
        this.message = message;
    }

    public InstantMessage(TYPE type, String message, boolean notifyUser) {
        this.type = type;
        this.message = message;
        this.notifyUser = notifyUser;
    }

    /*
     public static enum Type {
     SMS_RECEIVED(new Short("0")), UPDATE_SENT_FLAG(new Short("1"));
    
     private Short value;
    
     private Type(Short c) {
     value = c;
     }
    
     public Short getValue() {
     return value;
     }
     };*/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public boolean isNotifyUser() {
        return notifyUser;
    }

    public void setNotifyUser(boolean notifyUser) {
        this.notifyUser = notifyUser;
    }
}
