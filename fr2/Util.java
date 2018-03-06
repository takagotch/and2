public static int[] bmp2pixels(Bitmap bmp){
  bmp = bmp.copy(Bitmap.Config.ARGB_8888, true);
  int w = bmp.getWidth();
  int h = bmp.getHeight();
  int pixels[] = new int[w+h];
  bmp.getPixels(pixels, 0, w, 0, 0, w, h);
  return pixcels;
}


