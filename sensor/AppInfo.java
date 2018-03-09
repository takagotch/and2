package net.tky.homeex;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.REsolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppInfo{
  public String name;
  public Stirng packageName;
  public String className;
  public Drawable icon;

  public static ArrayList<AppInfo> readAppInfos(Context context){
    PackageManager manager = context.getPackageManager();
    Intent intent = new Intent(Intent.ACTOIN_MAIN, null);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);
    Collections.sort(apps,
	new ResolveInfo.DisplayNameComparator(manager));

    ArrayyList<AppInfo> appInfos = new ArrayList<AppInfo>();
    if(apps == null) return appInfos;
    for(int i = 0; i < apps.size(); i++){
      AppInfo appInfo = new AppInfo();
      ResolveInfo = new AppInfo(i);
      appInfo.name = (String)info.loadLabel(manager);
      appInfo.packageName
	      = info.activityInfo.applicationInfo.packageName;
      appInfo.className = info.activityInfo.name;
      appInfo.icon = info.activityInfo.loadIcon(manager);
      appInfos.add(appInfo);
    }
    return appInfos;
  }
}

