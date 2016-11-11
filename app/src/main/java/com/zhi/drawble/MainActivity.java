package com.zhi.drawble;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener , View.OnClickListener{

    private ImageView mIvBitmap;
    private ImageView mIvLayers;
    private ImageView mIvLevers;
    private ImageView mIvScale;
    private CheckBox mCbNinePatch;
    private TextView mTvShow9;
    private CheckBox mCbLayers;
    private CheckBox mCbStates;
    private Button mBtnLever6;
    private Button mBtnLever14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvBitmap = (ImageView) findViewById(R.id.iv_bitmap);
        mIvLayers = (ImageView) findViewById(R.id.iv_layers);
        mIvScale = (ImageView) findViewById(R.id.iv_scale);
        mCbNinePatch = (CheckBox) findViewById(R.id.cb_nine_patch);
        mTvShow9 = (TextView) findViewById(R.id.tv_show9);
        mIvLevers = (ImageView) findViewById(R.id.iv_levers);
        mCbLayers = (CheckBox) findViewById(R.id.cb_layers);
        mCbStates = (CheckBox) findViewById(R.id.cb_states);
        mBtnLever6 = (Button) findViewById(R.id.btn_lever_6);
        mBtnLever14 = (Button) findViewById(R.id.btn_lever_14);

        mBtnLever6.setOnClickListener(this);
        mBtnLever14.setOnClickListener(this);
        mCbNinePatch.setOnCheckedChangeListener(this);
        mCbLayers.setOnCheckedChangeListener(this);
        mCbStates.setOnCheckedChangeListener(this);
        showBitmap();   // imageView 显示bitmap图像
        showScale();
    }

    /**
     * 在imageView中显示bitmap图像（本例用的本地资源文件中的图片)
     * 一般应用是需要从网络上获取成byte[]数组，用BitmapFactory.decodeByteArray(byte[] data, int offset, int length）获取bitmap
     */
    private void showBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_rank);
        mIvBitmap.setImageBitmap(bitmap);
    }

    /**
     * 显示缩放后的图片
     * 注意：默认缩放的lever为0，会导致不显示图片，因此这里把它设置为1
     */
    private void showScale(){
        Drawable drawable = mIvScale.getDrawable();
        drawable.setLevel(1);
        mIvScale.setImageDrawable(drawable);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_nine_patch:
                showNinePatch(isChecked);
                break;
            case R.id.cb_layers:
                showLayerList(isChecked);
                break;
            case R.id.cb_states:
                mCbStates.setChecked(isChecked);
                break;
        }
    }

    /**
     * 显示.9图，它的特点是只会拉伸图片的指定位置
     */
    private void showNinePatch(boolean isChecked) {
        if (isChecked) {
            mTvShow9.setText(".9图你看我输入这么多文字三角形会不会变形");
        } else {
            mTvShow9.setText(".9图");
        }
    }

    /**
     * 显示层布局，并更换某层的图片
     * @param isChecked  点击就切换
     */
    private void showLayerList(boolean isChecked) {
        LayerDrawable layerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.layers);
        Drawable drawable;
        if (isChecked) {
            drawable = getResources().getDrawable(R.mipmap.ic_launcher);
            layerDrawable.setDrawableByLayerId(R.id.user_image, drawable);
        } else {
            drawable = getResources().getDrawable(R.mipmap.user);
            layerDrawable.setDrawableByLayerId(R.id.user_image, drawable);
        }
        mIvLayers.setImageDrawable(layerDrawable);
    }

    @Override
    public void onClick(View v) {
        Drawable drawable = mIvLevers.getDrawable();
        switch (v.getId()){
            case R.id.btn_lever_6:
                drawable.setLevel(6);
                mIvLevers.setImageDrawable(drawable);
                break;
            case R.id.btn_lever_14:
                drawable.setLevel(14);
                mIvLevers.setImageDrawable(drawable);
                break;
        }
    }
}