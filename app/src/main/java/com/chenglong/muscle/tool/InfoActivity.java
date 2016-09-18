package com.chenglong.muscle.tool;

import com.chenglong.muscle.main.MyTipDB;
import com.chenglong.muscle.R;
import com.chenglong.muscle.util.MyScreenUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class InfoActivity extends Activity {

    private final static int introNum = 10;
    private TextSwitcher tvIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.info);
        TextView rightView = (TextView) findViewById(R.id.toolbar_title_right);
        rightView.setText("健身贴士");

       // TextView tvIntro = (TextView) findViewById(R.id.info_tv);
        tvIntro = (TextSwitcher) findViewById(R.id.info_tv);
        tvIntro.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(InfoActivity.this);
                tv.setTextSize(MyScreenUtil.sp2px(InfoActivity.this, 10));
                tv.getPaint().setFakeBoldText(true);
                return tv;
            }
        });
        tvIntro.setText(getIntro());
//        tvIntro.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                ((TextView) v).setText(getIntro());
//            }
//        });
    }

    public void click(View v)
    {
        if (null != tvIntro)
        tvIntro.setText(getIntro());
    }

    protected String getIntro() {
        // TODO Auto-generated method stub
        int rand = (int) (Math.random() * introNum + 1);
        MyTipDB db = new MyTipDB();
        String intro = db.query(rand);
        if (intro.isEmpty()) {
            intro = "不要在露肉的季节选择逃避 ，在奋斗的年龄选择安逸";
        }
        db.close();

        return "【健康小贴士】" + intro + "【点击文字可浏览下一条】";
    }
}
