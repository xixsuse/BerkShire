package com.dotgears.berkshire.activities;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.FacebookSdk;

/**
 * Created by My PC on 08/12/2015.
 */
public class SignIn extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }


//    private UiLifecycleHelper uihelper;
//
//    void showMsg(String string)
//    {
//        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
//    }
//
//
//    private Session.StatusCallback callback =new Session.StatusCallback()
//    {
//
//        @Override
//        public void call(Session session, SessionState state, Exception exception)
//        {
//
//            onSessionStateChange(session,state,exception);
//        }
//    };
//
//
//    void onSessionStateChange(Session session, SessionState state, Exception exception)
//    {
//        if (state.isOpened())
//        {
//            Log.i("facebook", "Logged in...");
//            Request.newMeRequest(session, new Request.GraphUserCallback()
//            {
//
//                @Override
//                public void onCompleted(GraphUser user, Response response)
//                {
//
//                    if(user!=null)
//                    {
//                        showMsg(user.getName());
//                        showMsg(user.getProperty("email")+"");
//                        showMsg(user.getProperty("gender")+"");
//                        showMsg(user.getId()+"");
//                    }
//                    else
//                    {
//                        showMsg("its null");
//                        showMsg(response.getError().getErrorMessage());
//                    }
//                }
//            }).executeAsync();
//
//        }
//        else if (state.isClosed())
//        {
//            Log.i("facebook", "Logged out...");
//        }
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        uihelper.onResume();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        uihelper.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        uihelper.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        uihelper.onDestroy();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        uihelper.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        uihelper =new UiLifecycleHelper(this,callback);
//        uihelper.onCreate(savedInstanceState);
//
//        ArrayList<String> permission =new ArrayList<String>();
//        permission.add("email");
//        permission.add("public_profile");
//        permission.add("user_friends");
//
//        LoginButton btn=(LoginButton)findViewById(R.id.fbbtn);
//        btn.setPublishPermissions(permission);
//
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.example.testing",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
}
