package et.tsingtaopad.view;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**   
 * 项目名称：AndroidSoldier
 * 类名称：MyGridView</br>   
 * 类描述：自定义GridView</br>   
 * 类详细描述: 在ScrollView中嵌套GridView</br>
 * 创建人：ywm</br>
 * 创建时间：2013-02-24 上午8:54:04</br>   
 * 修改人：Administrator</br>
 * 修改时间：2013-02-24 上午8:54:04</br>   
 * 修改备注：</br>   
 * @version</br>    
 *    
 */ 
public class MyGridView extends GridView { 

    public MyGridView(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 

    public MyGridView(Context context) { 
        super(context); 
    } 

    public MyGridView(Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
    } 

    @Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

        int expandSpec = MeasureSpec.makeMeasureSpec( 
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
        super.onMeasure(widthMeasureSpec, expandSpec); 
    } 
} 