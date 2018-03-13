//new class(){};
//new intf(){};

DialogInterface.OnClickListener listener =
  new DialogInterface.OnClickListener(){
  public void onClick(DialogInterface dialog, int which){
  }
  };



class Hoge implements DialogInterface.OnClickListener{
  public void onClick(DialogInterface dialog, int which){
  }
}
DialogInterface.OnClickListener listener = new Hoge();



