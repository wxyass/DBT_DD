package et.tsingtaopad.listviewintf;

import android.view.View;

/**
 * Created by yangwenmin on 2018/4/10.
 */

public abstract  class ILongClick implements View.OnLongClickListener{
    @Override
    public boolean onLongClick(View v) {
        listViewItemLongClick((Integer) v.getTag(), v);
        return false;
    }

    public abstract void listViewItemLongClick(int position, View v);
}
