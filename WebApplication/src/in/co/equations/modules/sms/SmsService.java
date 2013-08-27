package in.co.equations.modules.sms;

import com.google.appengine.api.datastore.Text;
import com.thenextpointer.exceptions.PreexistingEntityException;
import com.thenextpointer.db.PersistenceService;
import in.co.equations.modules.user.EqUser;
import in.co.equations.modules.user.EqUserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class SmsService {

    private PersistenceService persistenceService;
    private EqUserService eqUserService;
    private String username = "thenextpointer";
    //Todo: Replace with correct password before deploying to Production
    private String password = "PUT_THE_CORRECT_PASSWORD_HERE";
    private String urlServer = "http://www.yosms.in/pushsms.php";
    
    /*http://bulksms.gateway4sms.com/pushsms.php?username=devashishmamgain&password=your_password
    &sender=mysenderid&to=myrecipient1,myrecipient2,myrecipient3&message=Hello*/

    public SmsService() {
        
    }

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }
    
    public EqUserService getEqUserService() {
        return eqUserService;
    }

    public void setEqUserService(EqUserService eqUserService) {
        this.eqUserService = eqUserService;
    }

    public void save(Sms sms) throws PreexistingEntityException {
        sms.setDate(new Date());
        persistenceService.getCrudService().create(sms);
    }

    public String sendToGroup(Sms sms) throws PreexistingEntityException {
        if (sms.getMessage().contains("<") && sms.getMessage().contains(">")) {
            String smsReceivers = sms.getReceivers().getValue();
            String[] phoneNumbers = smsReceivers.split(",");

            for(String phoneNumber: phoneNumbers) {
                EqUser eqUser = eqUserService.getEqUserByPhoneNumber(phoneNumber.trim());
                String message = sms.getMessage();
                if (eqUser != null) {
                    message  = message.replace("<user.name>", eqUser.getName());
                } else {
                    message = message.replace("<user.name>", "Student");
                }
                
                Sms sms1 = new Sms();
                //sms1.setDate(sms.getDate());
                sms1.setMessage(message);
                sms1.setSenderId(sms.getSenderId());
                sms1.setReceivers(new Text(phoneNumber));
                send(sms1);                
            }
        } else {            
            send(sms);
        }

        save(sms);
        return "success";
    }

    public String send(Sms sms) {
         //Build parameter string
        String data = "username="+username+"&password="+password+"&sender="+sms.getSenderId()+"&to="+sms.getReceivers().getValue()+"&message=" + sms.getMessage();
        try {            // Send the request
            URL url = new URL(urlServer);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            //write parameters
            writer.write(data);
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return "error";
        } catch (IOException ex) {
            ex.printStackTrace();
            return "error";
        }
        return "success";
    }
}
