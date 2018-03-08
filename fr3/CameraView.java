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

pictureSize = getPictureSize(cameraInfo);

public void takePicture(){
  if(cameraDevice == null || !active) return;
  active = false;

  try{
    ImageReader reader = ImageReader.newInstance(
	pictureSize.getWidth(), pictureSize.getHeight(),
	ImageFormat.JPEG, 2);
    reader.setOnImageAvailableListener(){
      new ImageReader.OnImageAvailableListener(
      @Override
      public void onImageAvailable(ImageReader reader){
        Image image = null;
	try{
	  image = reader.acquireLatestImage();
	  byte[] data = image2data(image);

	  int pw = previewSize.getWidth();
	  int ph = pictureSize.getHeight();
	  int sw = Math.min(pw, ph);
	  int dw = sw;
	  int dh = sw*viewH/viewW;
	  Bitmap bmp = Util.data2bmp(data);
	  bmp = Util.cutBitmap(bmp, dw, dh);
	} catch(Exception e){
	  if(image != null) image.close();
	}
      }
    }, workHandler);
  } catch(CameraAccessException e){
      e.printStackTrace();
  }
}

private boolean front;

private String getCameraId(){
  try{
    int facing = (front)?
	   CameraCharacteristics.LENS_FACING_FRONT;
    	   CameraCharacteristics.LENS_FACING_BACK;

    for(String cameraId : manager.getCameraIdList()){
      CameraCharacteristics cameraInfo =
	      manager.getCameraCharacteristics(cameraId);
      if(cameraInfo.get(CameraCharacterisitcs.LENS_FACING)
	== facing){
	  return cameraId;
	}
    }
  } catch(Exception e){
    e.printStackTrace();
  }
  return null;
}




