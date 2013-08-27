package in.co.equations.modules.editor;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.beans.PropertyEditorSupport;

public class GoogleDatastoreKeyEditor extends PropertyEditorSupport {

  //private static final Logger log = Logger.getLogger(GoogleDatastoreKeyEditor.class);

  @Override
  public void setAsText(String text) {
    if (text == null || text.length() == 0) {
      setValue(null);
    } else {
      Key key = null;
      try {
        key = KeyFactory.stringToKey(text);
      } catch (Exception e) {
       // log.error("Cannot parse key: " + text, e);
      }
      setValue(key);
    }
  }

  @Override
  public String getAsText() {
    Key value = (Key) getValue();
    return (value != null ? KeyFactory.keyToString(value) : "");
  }
}
