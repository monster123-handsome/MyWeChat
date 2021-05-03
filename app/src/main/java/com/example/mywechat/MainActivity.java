package com.example.mywechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment mlayout= new WeChat_BlankFragment();
    private Fragment mTab02 = new friend_BlankFragment();
    private Fragment mTab03 = new find_BlankFragment();
    private Fragment mTab04 = new settings_BlankFragment();


    private LinearLayout mTabWeChat;
    private LinearLayout mTabFrd;
    private LinearLayout mTabFind;
    private LinearLayout mTabSettings;

    private ImageButton mImgWeChat;
    private ImageButton mImgFrd;

    private ImageButton mImgFind;
    private ImageButton mImgSettings;

    private FragmentManager fm;
    private ListView lv;
    int [] imgSrcs={R.drawable.jj,R.drawable.lyr,R.drawable.sl,R.drawable.syq,R.drawable.yz,R.drawable.zly};
    String []names={"姐姐","林允儿","孙俪","宋雨琦","杨紫","赵丽颖",};
    String []intros={"我好想你呀，臭弟弟","今晚几点看电影呀","不回我消息","跑男录制好累","张一山肯定没你帅臭弟弟","离婚了有点伤心",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
        initEvent();
        selectFragment(0);
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content, mlayout);
        transaction.add(R.id.id_content, mTab02);
        transaction.add(R.id.id_content, mTab03);
        transaction.add(R.id.id_content, mTab04);
        transaction.commit();
    }

    private void resetImgs() {
        mImgWeChat.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgFind.setImageResource(R.drawable.tab_address_normal);
        mImgSettings.setImageResource(R.drawable.tab_settings_normal);

    }

    private void initView() {
        mTabWeChat = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabFind = (LinearLayout) findViewById(R.id.id_tab_contact);
        mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);

        mImgWeChat = (ImageButton) findViewById(R.id.id_tab_WeChat_img);
        mImgFrd = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mImgFind = (ImageButton) findViewById(R.id.id_tab_contact_img);
        mImgSettings = (ImageButton) findViewById(R.id.id_tab_settings_img);
        lv=(ListView)findViewById(R.id.lv);
        List<Map<String,Object>>userList=new ArrayList<>();
        for (int i=0;i<imgSrcs.length;i++){
            HashMap user=new HashMap();
            user.put("header",imgSrcs[i]);
            user.put("name",names[i]);
            user.put("intro",intros[i]);
            userList.add(user);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,
                userList,
                R.layout.tab01,
                new String[]{"header","name","intro"},
                new int[]{R.id.iv,R.id.tv1,R.id.tv2});
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?>parent,View view,int position,long id) {
                      Map user=(Map) parent.getItemAtPosition(position);
                    String name=(String)user.get("name");
                    Toast.makeText(MainActivity.this,"你的选择是："+name,Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void selectFragment(int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        //把图片设置为亮的
        //设置内容区域
        switch (i) {
            case 0:
                Log.d("setSelect", "1");
                transaction.show(mlayout);
                mImgWeChat.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(mTab02);
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                transaction.show(mTab03);
                mImgFind.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void initEvent() {
        mTabWeChat.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabFind.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);
    }

    private void hideFragment(FragmentTransaction transaction) {
        transaction.hide(mlayout);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);

    }

    @Override
    public void onClick(View v) {
        Log.d("onClick", "1");
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                Log.d("onClick", "2");
                selectFragment(0);
                break;
            case R.id.id_tab_frd:
                selectFragment(1);
                break;
            case R.id.id_tab_contact:
                selectFragment(2);
                break;
            case R.id.id_tab_settings:
                selectFragment(3);
                break;
            default:
                break;
        }
    }
}