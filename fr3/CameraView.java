public CameraView(Context context){
  super(context);

  setSurfaceTextureListener(new TextureView.SurfaceTextureListener(){
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height){
      if(viewW == 0 && viewH == 0){
        viewW = width;
	viewH = height;
      }
      startCamera();
    }
    
    @Override
    public void onSufaceTextureSizeChanged(SurfaceTexture surface, int width, int height){
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface){
      stopCamera();
      retrun true;
    }
  });
}


