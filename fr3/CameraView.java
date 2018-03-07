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

previewSize = getPreviewSize(cameraInfo);


private void updateLayoutParams(){
  int srcW = Math.min(
       previewSize.getWidth(),
       previewSize.getHeight());
  int srcH = Math.max(
       previewSize.getWidth(),
       previewSize.getHeight());

  int dstW = viewW;
  int dstH = viewW*srcH/srcW;
  RelativeLayout.LayoutParams params;
  params = new RelativeLayout.LayoutParams(dstW, dstH);
  params.setMargins(0, (viewH-dstH)/2, 0, 0);
  setLayoutParams(params);
}

