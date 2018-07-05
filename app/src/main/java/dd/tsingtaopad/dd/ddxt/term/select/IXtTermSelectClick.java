package dd.tsingtaopad.dd.ddxt.term.select;

import android.view.View;  
import android.view.View.OnClickListener;  
  
//  
public abstract class IXtTermSelectClick implements OnClickListener {
    @Override  
    public void onClick(View v) {
        imageViewClick((Integer) v.getTag(), v);
    }  
  
    public abstract void imageViewClick(int position, View v);
  
}
