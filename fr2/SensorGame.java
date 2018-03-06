@Override
public void onCreate(Bundle bundle){
  super.onCreate(bundle);
  Util.setActivity(this);

  getWindow().clearFlags();

  RelativeLayout layout = new RelativeLayout(this);

  Point displaySize = Util.getDisplaySize();
  
}

