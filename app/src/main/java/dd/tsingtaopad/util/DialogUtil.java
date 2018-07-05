package dd.tsingtaopad.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import dd.tsingtaopad.R;


public class DialogUtil {




    /**
     * 查询等待提示框
     * 
     * @param context
     * @param msgId     提示信息资源ID,为0时不显示提示信息
     * @return
     */
    public static AlertDialog progressDialog(Context context, int msgId) {

        AlertDialog dialog = new AlertDialog.Builder(context).setCancelable(false).create();
        View view  = LayoutInflater.from(context).inflate(R.layout.login_progress, null);
        //View view = context.getLayoutInflater().inflate(R.layout.login_progress,null);
        TextView msgTv = (TextView)view.findViewById(R.id.textView1);
        if (msgId == 0) {
            msgTv.setVisibility(View.GONE);
        } else {
            msgTv.setText(context.getString(msgId, ""));
        }
        dialog.setView(view, 0, 0, 0, 0);
        return dialog;
    }
}
