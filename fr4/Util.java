SimpleDateFormat df0 = new SimpleDateFormat(
  "EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
Date date = df0.parse(pubDate);

SimpleDateFormat df1 = new SimpleDateFormat(
  "yyyy/MM/dd HH:mm", Locale.US);
return df1.format(date);




