package com.morega.batterymanager.UI.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.morega.batterymanager.Info.BatteryInfo;
import com.morega.batterymanager.Info.BatterySipper;
import com.morega.batterymanager.R;
import com.morega.batterymanager.Utils.ActivityUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class FindFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final int REFRESH_COMPLETE = 0X110;
    private static final String SCHEME = "package";
    /**
     * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.1及之前版本)
     */
    private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
    /**
     * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.2)
     */
    private static final String APP_PKG_NAME_22 = "pkg";
    /**
     * InstalledAppDetails所在包名
     */
    private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
    /**
     * InstalledAppDetails类名
     */
    private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";
    private final int PROGRESS_DIALOG_ID = 1;
    private customAdapter adapter;
    private BatteryInfo info;
    private ProgressDialog progressDialog;
    private List<BatterySipper> mList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.ranking_list)
    ListView list;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        onCreateDialog(PROGRESS_DIALOG_ID);
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面
    }
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.ranking_list,container,false);

        ButterKnife.inject(this, view);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_ly);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        adapter = new customAdapter();
        list.setAdapter(adapter);
        info = new BatteryInfo(getActivity());
        getBatteryStats();


        return view;
    }

    private void getBatteryStats() {
        getActivity().showDialog(PROGRESS_DIALOG_ID);
        new Thread() {
            public void run() {
                mList = info.getBatteryStats();
                mHandler.sendEmptyMessage(1);
            }
        }.start();
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    if(getActivity().isFinishing())
                        return;
                    progressDialog.dismiss();
                    adapter.setData(mList);
                    break;
                case  REFRESH_COMPLETE:
                    info = new BatteryInfo(getActivity());
                    getBatteryStats();
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PROGRESS_DIALOG_ID:
                if (progressDialog==null){
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("正在玩命加载~");
                    return progressDialog;
                }
        }
        return null;
    }

    @Override
    public void onRefresh()
    {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
    class customAdapter extends BaseAdapter {
        private List<BatterySipper> list;
        private LayoutInflater inflater;

        public customAdapter() {
            inflater = LayoutInflater.from(getActivity());
        }

        public void setData(List<BatterySipper> list) {
            this.list = list;

            for (int i = list.size() - 1; i >= 0; i--) {
                final BatterySipper sipper = list.get(i);
                String name = sipper.getName();
                if (name == null) {
                    Drawable icon = sipper.getIcon();
                    switch (sipper.getDrainType()) {
                        case CELL:
                            name = getString(R.string.power_cell);
                            icon = getResources().getDrawable(R.drawable.ic_settings_cell_standby);
                            break;
                        case IDLE:
                            name = getString(R.string.power_idle);
                            icon = getResources().getDrawable(R.drawable.ic_settings_phone_idle);
                            break;
                        case BLUETOOTH:
                            name = getString(R.string.power_bluetooth);
                            icon = getResources().getDrawable(R.drawable.ic_settings_bluetooth);
                            break;
                        case WIFI:
                            name = getString(R.string.power_wifi);
                            icon = getResources().getDrawable(R.drawable.ic_settings_wifi);
                            break;
                        case SCREEN:
                            name = getString(R.string.power_screen);
                            icon = getResources().getDrawable(R.drawable.ic_settings_display);
                            break;
                        case PHONE:
                            name = getString(R.string.power_phone);
                            icon = getResources().getDrawable(R.drawable.ic_settings_voice_calls);
                            break;
                        case KERNEL:
                            name = getString(R.string.process_kernel_label);
                            icon = getResources().getDrawable(R.drawable.ic_power_system);
                            break;
                        case MEDIASERVER:
                            name = getString(R.string.process_mediaserver_label);
                            icon = getResources().getDrawable(R.drawable.ic_power_system);
                            break;
                        default:
                            break;
                    }

                    if (name != null) {
                        sipper.setName(name);
                        if (icon == null) {
                            PackageManager pm = getActivity().getPackageManager();
                            icon = pm.getDefaultActivityIcon();
                        }
                        sipper.setIcon(icon);
                    } else {
                        list.remove(i);
                    }
                }
            }
            notifyDataSetInvalidated();
        }
        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public BatterySipper getItem(int position) {
            return list == null ? null : list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = inflater.inflate(R.layout.list_items, null);
                holder.appIcon = (ImageView) convertView.findViewById(R.id.appIcon);
                holder.appName = (TextView) convertView.findViewById(R.id.appName);
                holder.textProgress = (TextView) convertView.findViewById(R.id.txtProgress);
                holder.progress = (ProgressBar) convertView.findViewById(R.id.progress);
                holder.button = (TextView) convertView.findViewById(R.id.racking_button);
                holder.applicationDescribe = (TextView) convertView.findViewById(R.id.application_describe);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            BatterySipper sipper = getItem(position);
            holder.appName.setText(sipper.getName());
            holder.appIcon.setImageDrawable(sipper.getIcon());

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInstalledAppDetails(getActivity(), getItem(position).getPackageName());
                }
            });
            if (sipper.getSystemType() == BatteryInfo.SystemType.SYSTEM){
                holder.button.setText("查看");
                holder.button.setBackgroundColor(Color.parseColor("#8cc814"));
            }
            if (sipper.getSystemType() == BatteryInfo.SystemType.APP
                    && ActivityUtils.isRunningApp(getActivity(), sipper.getPackageName())){
                holder.button.setText("关闭");
                holder.button.setBackgroundColor(Color.parseColor("#fdae47"));
            }
            if (sipper.getSystemType() == BatteryInfo.SystemType.APP
                    && !ActivityUtils.isRunningApp(getActivity(), sipper.getPackageName())){
                holder.button.setText("不活跃");
                holder.button.setBackgroundColor(Color.parseColor("#a0a0a0"));

            }
            double percentOfTotal = sipper.getPercentOfTotal();
            holder.textProgress.setText(format(percentOfTotal));
            holder.progress.setProgress((int) percentOfTotal);
            if (sipper.getSystemType() == BatteryInfo.SystemType.SYSTEM){
                if (percentOfTotal>=20){
                    holder.applicationDescribe.setText("虽然耗电巨大，但是很重要噢！~");
                }else {
                    holder.applicationDescribe.setText("系统应用，关闭需谨慎。");
                }
            }
            if (sipper.getSystemType() == BatteryInfo.SystemType.APP){
                if (percentOfTotal>=10){
                    holder.applicationDescribe.setText("耗电达人，杀死它杀死它！");
                }else {
                    holder.applicationDescribe.setText("一个平凡的应用。");
                }
            }
            if (sipper.getSystemType() == BatteryInfo.SystemType.APP
                    && !ActivityUtils.isRunningApp(getActivity(), sipper.getPackageName())){
                holder.applicationDescribe.setText("它曾经耗电过，后来它死了……");
            }
            return convertView;
        }
    }

    class Holder{
        ImageView appIcon;
        TextView appName;
        TextView textProgress;
        ProgressBar progress;
        TextView button;
        TextView applicationDescribe;
    }

    private String format(double size) {
        return String.format("%1$.2f%%", size);
        // return new BigDecimal("" + size).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
    /**
     * 调用系统InstalledAppDetails界面显示已安装应用程序的详细信息。 对于Android 2.3（Api Level
     * 9）以上，使用SDK提供的接口； 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）。
     *
     * @param context
     *
     * @param packageName
     *            应用程序的包名
     */
    public static void showInstalledAppDetails(Context context, String packageName) {
        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
            // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
            final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22
                    : APP_PKG_NAME_21);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME,
                    APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }
        context.startActivity(intent);
    }
}
