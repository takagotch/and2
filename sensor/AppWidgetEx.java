package net.tky.appwidgtetex;
import android.appwidget.AppWidgetManager;
import android.appwidget.appWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class AppWidgetEx extends AppWidgetProvider{
  @Override
  public void onUpdate(Context context,
		  AppWidgetMangerappWidgetManger, int[] appWidgetIds){
    Intent intent = new Intent(context, AppWidgetService.class);
    context.startService(intent);
  }
}

