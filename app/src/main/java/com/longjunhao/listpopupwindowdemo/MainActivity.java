package com.longjunhao.listpopupwindowdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import com.longjunhao.listpopupwindowdemo.databinding.ActivityMainBinding;


/**
 * @author Admitor
 * 本demo涉及用于调试Popupwindow、ListPopupwindow的阴影问题。用了dataBinding来绑定点击事件。
 * <p>
 * 1、Popupwindow
 * 优点：可以通过setBackgroundDrawable()来重新设置阴影。
 * 缺点：当AnchorView是可移动的，比如移动到屏幕的左右边界、左下角、右下角时，
 * Popupwindow的显示需要适配与AnchorView的相对位置。
 * <p>
 * 2、ListPopupwindow
 * 优点：当AnchorView是可移动的，比如移动到屏幕的左右边界、左下角、右下角时，ListPopupwindow会自适应显示位置
 * 不需要考虑显示位置。如果需要判断是否显示在AnchorView上面，则可以通过AnchorView在屏幕中的位置和
 * ListPopupwindow的高度是否超过屏幕来判断。
 * 缺点：可以通过setBackgroundDrawable(null)去掉阴影，但是如果想重新设置阴影，则不能用
 * android.widget.ListPopupWindow，只能用android.support.v7.widget.ListPopupWindow
 * 或者androidx.appcompat.widget.ListPopupWindow来设置新的阴影。同时还要在styles.xml中配置
 * listPopupWindowStyle属性，不然设置的阴影不生效。
 * <p>
 * 关于dataBinding的知识点：
 * DataBinding：
 * https://blog.csdn.net/huangbin123/article/details/105111818
 * https://www.jianshu.com/p/7de37ca38d0e
 * <p>
 * Lifecycle:
 * https://www.jianshu.com/p/78532ac86db6
 * Lifecycle中实现观察者，java7和java8不一样。
 * https://www.jianshu.com/p/b6fff29ab498
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    ActivityMainBinding mBind;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBind.setClick(this);
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                initPopupWindow(view);
                break;
            case R.id.btn_2:
                initListPopupWindow(view);
                break;
            case R.id.btn_3:
                initNewListPopupWindow(view);
                break;
            default:
                break;
        }
    }
    
    /**
     * Popupwindow的阴影，本demo中是没有阴影。可以通过setBackgroundDrawable()来重新设置。
     *
     * @param view
     */
    private void initPopupWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        //白色背景
        //popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //没有阴影的背景
        //popupWindow.setBackgroundDrawable(getDrawable(R.drawable.list_pop_bg_border));
        //有阴影的背景
        popupWindow.setBackgroundDrawable(getDrawable(R.drawable.list_pop_bg));
        popupWindow.showAsDropDown(view);
    }
    
    /**
     * 如果是android.widget.ListPopupWindow，只能通过setBackgroundDrawable(null)来去掉阴影，并不能
     * 设置新的阴影
     *
     * @param view
     */
    private void initListPopupWindow(View view) {
        final String[] itmes = {"第一个子项", "第二个子项", "第三个子项"};
        final android.widget.ListPopupWindow listPopupWindow = new android.widget.ListPopupWindow(MainActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, itmes));
        listPopupWindow.setWidth(388);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setAnchorView(view);
        listPopupWindow.setModal(true);
        //listPopupWindow.setBackgroundDrawable(null);
        //listPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_pop_bg_border));
        listPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_pop_bg));
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();
    }
    
    /**
     * 如果是android.support.v7.widget.ListPopupWindow或者androidx.appcompat.widget.ListPopupWindow
     * 可以通过setBackgroundDrawable(null)来去掉阴影，也可以通过setBackgroundDrawable()来设置新的阴影，
     * 但是需要在styles.xml中设置listPopupWindowStyle属性。
     *
     * @param view
     */
    private void initNewListPopupWindow(View view) {
        final String[] itmes = {"第一个子项", "第二个子项", "第三个子项"};
        final ListPopupWindow listPopupWindow = new ListPopupWindow(MainActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, itmes));
        listPopupWindow.setWidth(388);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setAnchorView(view);
        listPopupWindow.setModal(true);
        //listPopupWindow.setBackgroundDrawable(null);
        //listPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_pop_bg_border));
        listPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_pop_bg));
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();
    }
}