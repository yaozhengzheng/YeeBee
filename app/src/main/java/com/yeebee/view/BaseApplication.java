package com.yeebee.view;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;
//import cn.jpush.android.api.JPushInterface;

//import com.baidu.frontia.FrontiaApplication;
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.location.LocationClientOption.LocationMode;
//import com.baidu.location.GeofenceClient;
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.mapapi.SDKInitializer;
//import com.dream.makerspace.Constants;
//import com.dream.makerspace.domain.MyAppData;
//import com.dream.makerspace.utils.SharedPreferenceUtil;
//import com.easemob.EMCallBack;
//import com.easemob.chat.EMChat;
//import com.easemob.chat.EMChatManager;
//import com.easemob.chat.EMChatOptions;
//import com.easemob.chatuidemo.DemoHXSDKHelper;
//import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
//import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
//import com.nostra13.universalimageloader.utils.StorageUtils;
//import com.tencent.mm.sdk.openapi.IWXAPI;
//import com.tencent.mm.sdk.openapi.WXAPIFactory;
;import com.yeebee.utils.SharedPreferenceUtil;

public class BaseApplication extends Application {
	
	public static String PhoneIMEI = "0";
	public static String device_model;
	public static String userId;
    private static String PREF_NAME = "creativelocker.pref";
	public static Context context;
	static Context _context;
	private static Handler handler;
	private LinkedList<Activity> activityList = new LinkedList<Activity>();
	public static BaseApplication instance;
	
//	public MyLocationListener mMyLocationListener;
//	public GeofenceClient mGeofenceClient;
	
	//定位
//	public LocationClient mLocationClient = null;
//	public BDLocationListener myListener = new MyLocationListener();
	public Vibrator mVibrator;
	public static double latitude;  //纬度
	public static double lontitude;  // 经度
	
	/**
	 * 默认标题壁纸
	 */
	public Bitmap mDefault_TitleWallpager;
	/**
	 * 标题壁纸名称
	 */
	public String[] mTitleWallpagersName;
	
	/**
	 * 标题壁纸缓存
	 */
	public HashMap<String, SoftReference<Bitmap>> mTitleWallpagersCache = new HashMap<String, SoftReference<Bitmap>>();

	/**
	 * 当前壁纸编号
	 */
	public int mWallpagerPosition = 0;
		
	
	private static final String TAG= BaseApplication.class.getSimpleName();

   
//    private ImageLoader imageLoader;
    
//    public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();

    SharedPreferenceUtil mSharedPreferenceUtil;
    
	
	public BaseApplication() {
	}

	public static BaseApplication getInstance() {
		if (null == instance) {
			instance = new BaseApplication();
		}
		return instance;
	}
	
	public static synchronized BaseApplication context() {
		return (BaseApplication) _context;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getBaseContext();
		_context = getApplicationContext();
		handler = new Handler();
		
		try {
			mTitleWallpagersName = getAssets().list("title_wallpager");
		} catch (IOException e) {
			e.printStackTrace();
		}

        mSharedPreferenceUtil = new SharedPreferenceUtil(this,"userInfo");
		userId = mSharedPreferenceUtil.getUserId();
        PhoneIMEI =mSharedPreferenceUtil.getIMEI(getBaseContext());
        device_model = Build.MODEL;
							
	}

	public static void showToast(final int resId) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public static void showToast(final String text) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public int getMemoryCacheSize(Context context) {
		int memoryCacheSize;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
			int memClass = ((ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE))
					.getMemoryClass();
			memoryCacheSize = (memClass / 8) * 1024 * 1024; // 1/8 of app memory
															// limit
		} else {
			memoryCacheSize = 2 * 1024 * 1024;
		}
		return memoryCacheSize;
	}
	
	/**
	 * 根据壁纸编号获取标题壁纸
	 */
	public Bitmap getTitleWallpager(int position) {
		try {
			String titleWallpagerName = mTitleWallpagersName[position];
			Bitmap bitmap = null;
			if (mTitleWallpagersCache.containsKey(titleWallpagerName)) {
				SoftReference<Bitmap> reference = mTitleWallpagersCache
						.get(titleWallpagerName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"title_wallpager/" + titleWallpagerName));
			mTitleWallpagersCache.put(titleWallpagerName,
					new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return mDefault_TitleWallpager;
		}
	}
	
	public static boolean get(String key, boolean defValue) {
		return getPreferences().getBoolean(key, defValue);
	}
	
	 public static SharedPreferences getPreferences() {
		SharedPreferences pre = context.getSharedPreferences(PREF_NAME,
			Context.MODE_MULTI_PROCESS);
		return pre;
	 }
}
