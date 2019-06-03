package com.demo.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.widget.Toast;

public class CommonUtils {

    private static final String SHARED_PREFERENCE_DATA= "preference_data";

    public static int generateQuestionLayoutId(Context mContext){
        long id = getLongPreference(mContext,AppConstants.QUESTION_LAYOUT_ID);
        if (id!=0){
            id +=1;
            saveLongPreference(mContext,AppConstants.QUESTION_LAYOUT_ID,id);
        }else {
            id = System.currentTimeMillis()/3600000;
            saveLongPreference(mContext,AppConstants.QUESTION_LAYOUT_ID,id);
        }

        return (int)id;
    }

    public static void saveLongPreference(Context mContext,String key,long value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key,value);
        editor.apply();
    }
    public static long getLongPreference(Context mContext,String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_DATA,Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key,0);
    }
    public static void saveStringPreference(Context mContext,String key,String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getStringPreference(Context mContext,String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_DATA,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
    public static void saveBooleanPreference(Context mContext,String key,boolean value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static boolean getBooleanPreference(Context mContext,String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_DATA,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }


    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void showToast(Context mContext,String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NewApi")
    public static String getPath(@NonNull Activity context, @Nullable final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        // TODO : THIS VALUE GIVES NULL, content://com.android.shell.documents/document/bugreport%3Abugreport-2016-11-02-19-02-21.png
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(@NonNull Context context, @NonNull Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(@NonNull Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(@NonNull Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(@NonNull Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


}
