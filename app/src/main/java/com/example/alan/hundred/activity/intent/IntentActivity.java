package com.example.alan.hundred.activity.intent;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.activity.view.TextActivity;
import com.example.alan.hundred.util.L;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Function : Intent的使用　1.获取联系人列表　2.获取图库文件 3.打开摄像头读取图片信息
 * Author : Alan
 * Modify Date : 10/10/17
 * Issue : TODO
 * Whether solve :
 */

public class IntentActivity extends BaseHomeActivity {

    private TextView tv_show;
    private ImageView iv_photo;

    private final int REQUEST_CODE_CONTACT = 0;
    private final int REQUEST_CODE_IMAGE = 1;
    private final int REQUEST_CODE_IMAGE_RESULT = 2;
    private final int REQUEST_CODE_CAMERA = 3;

    private String picPath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_intent;
    }

    @Override
    public void initViews() {
        tv_show = (TextView) findViewById(R.id.tv_intent_show);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {

    }

    private String image_name = "intent.png";
    private File tempFile;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_intent_contact:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(ContactsContract.Contacts.CONTENT_URI);
                //    intent.setDataAndType(ContactsContract.Contacts.CONTENT_URI,"text");

                startActivityForResult(intent, REQUEST_CODE_CONTACT);
                break;
            case R.id.bt_intent_pic:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_PICK);
                intent1.setType("image/*");
                startActivityForResult(intent1, REQUEST_CODE_IMAGE);

                break;
            case R.id.bt_intent_camera:
                Intent intent2 = new Intent();
                intent2.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent2, REQUEST_CODE_CAMERA);
                break;
        }
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data        1.获取联系人的姓名和电话号码
     *                    姓名和ID是在Contact中
     *                    电话号码是丰Phone中
     *                    通过ID来查找电话号码　ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id
     *                    <p>
     *                    2.获取图片信息
     *
     */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CONTACT:

                    Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
                    if (cursor != null) {

                        if (cursor.moveToFirst()) {
                            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));                      //通过Cursor c获得联系人id

                            Cursor c2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                            //在ContactsContract.CommonDataKinds.Phone.CONTENT_URI里，
                            // 通过上面获得的联系人id获得一个新的Cursor c2
                            c2.moveToFirst();
                            String phone_num = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));   //通过Cursor c2获得联系人电话
                            //   String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            L.e(name + phone_num + phone_num.length());
                            c2.close();
                        }
                        cursor.close();
                    }
                    break;
                case REQUEST_CODE_IMAGE:

                    startPhotoZoom(data.getData());
//                    Uri uri = data.getData();
//                    String[] pojo = {MediaStore.Images.Media.DATA};
//
//                    @SuppressWarnings("deprecation")
//                    Cursor cursor2 = managedQuery(uri, pojo, null, null, null);
//                    if (cursor2 != null) {
//                        int index = cursor2
//                                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        cursor2.moveToFirst();
//                        String path = cursor2.getString(index);
//                        L.e(path);
//                        if (path != null) {
//                            picPath = path;
//                        }
//
//                        cursor2.close();
//                    }
                    break;
                case REQUEST_CODE_IMAGE_RESULT:
                    L.e("request result");

                    Bitmap bitmap = data.getExtras().getParcelable("data");

                    saveBitmap(bitmap);
                    iv_photo.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_CAMERA:
//                    Bitmap bitmap1 = data.getExtras().getParcelable("data");
//                    iv_photo.setImageBitmap(bitmap1);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;

            }
        }
    }


    //裁剪
    private void startPhotoZoom(Uri uri) {
        L.e("startZoom");
        if (uri == null) {
            L.d("uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_RESULT);
    }

    /**
     * 将图片保存到指定文件夹中
     * @param bitmap
     */
    public void saveBitmap(Bitmap bitmap) {

        tempFile = new File(Environment.getExternalStorageDirectory(), image_name);
        L.e(tempFile.getAbsolutePath());

        if (tempFile.exists()) {
            tempFile.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
