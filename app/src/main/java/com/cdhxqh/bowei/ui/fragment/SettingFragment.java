package com.cdhxqh.bowei.ui.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.MaterialInfoDao;
import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.Dao.WorkerInfoDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.AppManager;
import com.cdhxqh.bowei.ui.activity.About_us_Activity;
import com.cdhxqh.bowei.ui.activity.MipcaActivityCapture;
import com.cdhxqh.bowei.ui.activity.SettingDownloadActivity;
import com.cdhxqh.bowei.utils.Utils;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 设置fragment
 */
public class SettingFragment extends Fragment {


    private static final String TAG = "SettingFragment";
    /**
     * 数据下载*
     */
    RelativeLayout dataRelativeLayout;
    /**
     * 清除缓存*
     */
    RelativeLayout clearRelativeLayout;
    /**
     * 网络设置*
     */
    RelativeLayout workRelativeLayout;
    /**
     * 版本更新*
     */
    RelativeLayout updateRelativeLayout;
    /**
     * 关于我们*
     */
    RelativeLayout usRelativeLayout;


    /**
     * 缓存大小*
     */
    TextView cacheSize;


    /** 自定义进度条 **/
    private ProgressDialog progressDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.setting_layout, container, false);
        findById(view);
        return view;
    }


    /**
     * 初始化界面控件*
     */
    private void findById(View view) {
        dataRelativeLayout = (RelativeLayout) view.findViewById(R.id.upload_data_id);
        clearRelativeLayout = (RelativeLayout) view.findViewById(R.id.clear_cache_id);
        workRelativeLayout = (RelativeLayout) view.findViewById(R.id.network_id);
        updateRelativeLayout = (RelativeLayout) view.findViewById(R.id.update_id);
        usRelativeLayout = (RelativeLayout) view.findViewById(R.id.about_us_id);


        cacheSize = (TextView) view.findViewById(R.id.cache_size_id);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        dataRelativeLayout.setOnClickListener(dataRelativeLayoutOnClickListener);

        clearRelativeLayout.setOnClickListener(clearRelativeLayoutOnClickListener);

        updateRelativeLayout.setOnClickListener(updateRelativeLayoutOnClickListener);

        usRelativeLayout.setOnClickListener(usRelativeLayoutOnClickListener);


        cacheSize.setText(getDataFileSize() + "/M");


    }

    private View.OnClickListener dataRelativeLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), SettingDownloadActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener usRelativeLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), About_us_Activity.class);
            startActivity(intent);
        }
    };


    private View.OnClickListener clearRelativeLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };


    /**版本更新**/

    private View.OnClickListener updateRelativeLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createProgressDialog("正在检测更新，请耐心等候...");
            setForceUpdate();
        }
    };


    /**
     * 弹出对话框*
     */

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("提示");
        builder.setMessage("清除缓存将删除本地所有工单信息，确定要清除缓存吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                ClearCache();
//                if (clearCache()) {
//                    cacheSize.setText(getDataFileSize());
                    Toast.makeText(getActivity(), "清除成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "清除失败", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    /**
     * 获取数据库文件的大小*
     */
    private String getDataFileSize() {

        boolean isSdCard = Utils.isSdCard();
        String file;
        if (isSdCard) {
            file = Constants.PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        } else {
            file = Constants.NOT_SDCARD_PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        }
        File dir = new File(file);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        double size = Utils.getDirSize(dir);

        //保留2位小数
        DecimalFormat df = new DecimalFormat("######0.00");
        String size1 = df.format(size);

        return size1;
    }


    /**
     * 清除缓存*
     */
    private boolean clearCache() {
        boolean isSdCard = Utils.isSdCard();
        String file;
        if (isSdCard) {
            file = Constants.PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        } else {
            file = Constants.NOT_SDCARD_PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        }

        return Utils.deleteFile(file);
    }

    /**
     * 清除缓存*
     */
    private void ClearCache() {
        new OrderMainDao(getActivity()).deleteall();
        new MaterialInfoDao(getActivity()).deleteall();
        new OrderTaskDao(getActivity()).deleteall();
        new WorkerInfoDao(getActivity()).deleteall();
    }

    /**
     * 手动强制更新
     */
    private void setForceUpdate() {
        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus,
                                         UpdateResponse updateInfo) {
                closeDialog();
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent
                                .showUpdateDialog(getActivity(), updateInfo);
                        break;
                    case UpdateStatus.No: // has no update
                        Toast.makeText(getActivity(), "未发现新版本，当前安装的已是最新版本",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        Toast.makeText(getActivity(), "没有wifi连接， 只在wifi下更新",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.Timeout: // time out
                        Toast.makeText(getActivity(), "更新超时,请检查网络",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        UmengUpdateAgent.forceUpdate(getActivity());
    }


    /** 创建数据加载的对话框 **/
    private void createProgressDialog(String str) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(str);
        progressDialog.setProgressStyle(R.style.CustomProgressDialog);
        progressDialog.show();
    }

    /** 关闭对话框 **/
    private void closeDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
