private void loadMap(){
  
  imgMap = Util.res2bmp(R.drawable.map);
  mapData = Util.bmp2pixels(Util.res2bmp(R.drawable.map_data));

  Point startLT = new Point();
  Point startRB = new Point();
  for(int y = 0; y < H; y++){
    for(int x = 0; x < W; x++){
      if(mapData == Color.BLUE){
        if(startLT.x == 0 && startLT.y == 0){
	  startLT.x = x;
	  startLT.y = y;
	}else{
	  startRB.x = x;
	  startRB.y = y;
	}
      }
    }
  }
  ballX = startLT.x+(startRB.x-startLT.x)/2;
  ballY = startLT.y+(startRB.y-startLT.y)/2;
  ballVX = 0;
  ballVY = 0;
}

private void onTick(Graphics g){
  if(scene == S_PLAY){
    
    int data = mapData[(int)ballX+(int)ballY+W];
    if(data == Color.WHITE){
      scene = S_GAMEOVER;
    }else if(data == Color.RED) {
      scene = S_GOAL;
    }
  }
}


