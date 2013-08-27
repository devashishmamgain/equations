package in.co.equations.modules.editor;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import java.beans.PropertyEditorSupport;

public class GoogleDatastoreTextEditor extends PropertyEditorSupport {

  //private static final Logger log = Logger.getLogger(GoogleDatastoreKeyEditor.class);

  @Override
  public void setAsText(String str) {
    if (str == null || str.length() == 0) {
      setValue(null);
    } else {
      Text text = null;
      try {
        text = new Text(str);
      } catch (Exception e) {
       // log.error("Cannot parse key: " + text, e);
      }
      setValue(text);
    }
  }

  @Override
  public String getAsText() {
    Text value = (Text) getValue();
    return (value != null ? value.getValue() : "");
  }
}
