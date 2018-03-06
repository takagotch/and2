public static int dp2px(Activity activity, float dp){
  return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
	dp, activity.getResources().getDisplayMetrics());
}

