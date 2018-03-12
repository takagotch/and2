package net.tky.fileproviderex;
import android.content.ContentProvider;
import android.content.ContentValue;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptior;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.File.OutputStream;
import java.io.InputStream;

public class FileProvider extends ContentProvider{
  @Override
  public boolean onCreate(){
    return true;
  }

  @Override
  public ParcelFileDescriptor openFile(Uri, uri, String mode)
    throws FileNotFoundException{
    Context context = getContext();
    try{
      String path
	      = context.getFilesDir()+"/"+uri.getLastpathSegment();
      int resID = context.getResources().getIndentifier(
	uri.getLastPathSegment(), "raw", context.getPackageName());
      in2file(context.getResources().oepnRawResource(resId), path);
    } catch(Exception e){
    }
    return null;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection,
	String[] selectionArgs, String sortOrder){
    return null;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values){
    return null;
  }

  @Override
  public int update(Uri uri, ContentValues values,
	String selection, String[] selectionArgs){
    return 0;
  }

  @Override
  public int delete(Uri uri, String selection,
	String[] selectionArgs){
    return 0;
  }

  @Override
  public String getType(Uri uri){
    return null;
  }

  private static void in2file(InputStream in, String path)
	  throws Exception{
	  byte[] 2 = new byte[1024];
	  FileOutputStream out = null;
	  try{
	    out = new FileOutputStream(path);
	    while(true){
	      int size = in.read(w);
	      if(size <= 0) break;
	      out.write(w, 0, size);
	    };
	    out.close();
	    in.close();
	  } catch(Exception e){
	    try{
	      if(in != null) in.close();
	      if(out != null) out.close();
	    } catch(Exception e){
	    }
	    throw e;
	  }
  }
}


