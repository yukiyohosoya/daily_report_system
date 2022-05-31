import java.io.FileInputStream;
import java.io.InputStream;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class test{
  static String JSON_FILE_NAME = "D:\\WORK\\JavaFirebaseSampleApp-2017-03-17.json";
  static String URL = "https://javafirebasesampleapp.firebaseio.com/";

  public static void main(String[] args) throws Exception
  {
    System.out.println("JavaFirebaseSampleApp 実行開始");

    InputStream stream_json = new FileInputStream(JSON_FILE_NAME);

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setServiceAccount(stream_json)
        .setDatabaseUrl(URL)
        .build();

    FirebaseApp.initializeApp(options);

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    reference.child("key1").setValue("こんにちは！");
    reference.child("key2").setValue("ファイアーベース");
    reference.child("key3").setValue("よろしくお願いします！");

    while(true)
    {
      System.out.println("実行中です。");
      Thread.sleep(5000);
    }
  }
}