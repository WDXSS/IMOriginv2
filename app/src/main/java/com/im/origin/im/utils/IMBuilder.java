package com.im.origin.im.utils;

import android.content.Context;

import com.im.origin.three.observer.NIMInitManager;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.UIKitOptions;
import com.netease.nim.uikit.business.contact.core.query.PinYin;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;

/**
 * Created by zc on 2018/1/25.
 */

public class IMBuilder {
    private static final String TAG = "IMBuilder";
    private Context mContext;

    public IMBuilder(Context context) {
        this.mContext = context;
    }

    public void init(){
        // 4.6.0 开始，第三方推送配置入口改为 SDKOption#mixPushConfig，旧版配置方式依旧支持。
        NIMClient.init(this, getLoginInfo(), NimSDKOptionConfig.getSDKOptions(this));

        // 以下逻辑只在主进程初始化时执行
        if (NIMUtil.isMainProcess(mContext)) {

            // init pinyin
            PinYin.init(mContext);
            PinYin.validate();
            // 初始化UIKit模块
            initUIKit();
//            // 初始化消息提醒
//            NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
            // 云信sdk相关业务初始化
            NIMInitManager.getInstance().init(true);
            // 初始化音视频模块
//            initAVChatKit();
        }
//       日志
//        Crashlytics crashlyticsKit = new Crashlytics.Builder()
//                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
//                .build();
//
//        // Initialize Fabric with the debug-disabled crashlytics.
//        Fabric.with(this, crashlyticsKit);
    }

    public LoginInfo getLoginInfo() {
        return null;
    }

    private void initUIKit() {
        // 初始化
        NimUIKit.init(mContext, buildUIKitOptions());

        // 设置地理位置提供者。如果需要发送地理位置消息，该参数必须提供。如果不需要，可以忽略。
        NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // IM 会话窗口的定制初始化。
//        SessionHelper.init();

        // 聊天室聊天窗口的定制初始化。
//        ChatRoomSessionHelper.init();

        // 通讯录列表定制初始化
//        ContactHelper.init();

        // 添加自定义推送文案以及选项，请开发者在各端（Android、IOS、PC、Web）消息发送时保持一致，以免出现通知不一致的情况
        NimUIKit.setCustomPushContentProvider(new DemoPushContentProvider());

        NimUIKit.setOnlineStateContentProvider(new DemoOnlineStateContentProvider());
    }

    private UIKitOptions buildUIKitOptions() {
        UIKitOptions options = new UIKitOptions();
        // 设置app图片/音频/日志等缓存目录
        options.appCacheDir = NimSDKOptionConfig.getAppCacheDir(this) + "/app";
        return options;
    }

//    private void initAVChatKit() {
//        AVChatOptions avChatOptions = new AVChatOptions(){
//            @Override
//            public void logout(Context context) {
//                MainActivity.logout(context, true);
//            }
//        };
//        avChatOptions.entranceActivity = WelcomeActivity.class;
//        avChatOptions.notificationIconRes = R.drawable.ic_stat_notify_msg;
//        AVChatKit.init(avChatOptions);
//
//        // 初始化日志系统
//        LogHelper.init();
//        // 设置用户相关资料提供者
//        AVChatKit.setUserInfoProvider(new IUserInfoProvider() {
//            @Override
//            public UserInfo getUserInfo(String account) {
//                return NimUIKit.getUserInfoProvider().getUserInfo(account);
//            }
//
//            @Override
//            public String getUserDisplayName(String account) {
//                return UserInfoHelper.getUserDisplayName(account);
//            }
//        });
//        // 设置群组数据提供者
//        AVChatKit.setTeamDataProvider(new ITeamDataProvider() {
//            @Override
//            public String getDisplayNameWithoutMe(String teamId, String account) {
//                return TeamHelper.getDisplayNameWithoutMe(teamId, account);
//            }
//
//            @Override
//            public String getTeamMemberDisplayName(String teamId, String account) {
//                return TeamHelper.getTeamMemberDisplayName(teamId, account);
//            }
//        });
//    }
}
