package et.tsingtaopad.dd.dddaysummary.pagesliding;

import android.content.Context;
import android.view.WindowManager;

public class DimenUtil {

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
}
