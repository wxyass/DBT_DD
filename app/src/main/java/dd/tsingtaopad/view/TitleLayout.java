package dd.tsingtaopad.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dd.tsingtaopad.R;


/**
 * Created by yangwenmin on 2017/12/12.
 * 导航页顶部标题栏(快速导航,退出)
 */
@SuppressLint("HandlerLeak")
public class TitleLayout extends RelativeLayout implements OnClickListener {

	private ImageView banner_im_menu;
	private ImageView banner_person_img;
	private TextView banner_person_tv;
	private TextView banner_welcome_tv;
	private TextView visitTermNumTv;
	private TextView planTermNumTv;
	private TextView banner_progress_tv;
	private ProgressBar banner_plan_pb;
	private Button banner_exit_bt;
	public ProgressDialog mProgressDialog;
	private final int QUERY_ISALLEMPTY = 1;
	public static final int UPLOAD_DATA = 2;
	public static final int LOGIN_OUT = 3;
	public static final int LOGIN_OUT_FAIL = 4;
	private String errmsg = null;
	private boolean isUpLoadingError = false;
	private int exitCount = 0; // 记录退出的时候 已经更新多少个任务 ，通过任务量来判断是否已经全部更新完毕
	private final String TAG = "TitleLayout";

	public TitleLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TitleLayout(Context context) {
		super(context);
		init();

	}

	private void init() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.banner, this);
		banner_im_menu = (ImageView) findViewById(R.id.banner_im_menu);
		banner_person_img = (ImageView) findViewById(R.id.banner_person_img);
		banner_person_tv = (TextView) findViewById(R.id.banner_person_tv);
		banner_welcome_tv = (TextView) findViewById(R.id.banner_welcome_tv);
		visitTermNumTv = (TextView) findViewById(R.id.banner_tv_visitnum);//拜访的数据
		planTermNumTv = (TextView) findViewById(R.id.banner_tv_plannum);//计划数量
		banner_progress_tv = (TextView) findViewById(R.id.banner_progress_tv);
		banner_plan_pb = (ProgressBar) findViewById(R.id.banner_plan_pb);
		banner_exit_bt = ((Button) findViewById(R.id.banner_exit_bt));

		banner_im_menu.setOnClickListener(this);
		banner_exit_bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.banner_im_menu) {
			Toast.makeText(getContext(),"popwindow",Toast.LENGTH_SHORT).show();
		} else if (i == R.id.banner_exit_bt){
			Toast.makeText(getContext(),"退出",Toast.LENGTH_SHORT).show();
		}

	}
}
