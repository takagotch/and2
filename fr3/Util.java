
public static Bitmpa data2bmp(byte[] data){
  return BitmapFactory.decodeByteArray(data, 0, data, length);
}

public static byte[] bmp2data(Bitmap src){
  ByteArrayOutputStream os = new ByteArrayOutputStream();
  src.compress(Bitmap.CompressFormat.JPEG, 100, os);
  return os.toByteArray();
}


