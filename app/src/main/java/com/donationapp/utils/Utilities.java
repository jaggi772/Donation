package com.donationapp.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.content;

/**
 * Created by subir on 7/1/17.
 */

public class Utilities {


    static ProgressDialog progressDialog;


    public static void showProgressDialog(Context context, String title) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(title);
        progressDialog.setTitle("");
        progressDialog.show();

    }

    public static void dissmissDialog() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }


    /**
     * @param gsonData      data which is in form of json to convert in gson
     * @param yourPojoClass your ClassName[].class
     * @param <T>           pojo class in generic
     * @return
     */
    public static <T> List<T> convertingToGson(String gsonData, Class<T[]> yourPojoClass) {
        List<T> data = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        data = Arrays.asList(gson.fromJson(gsonData, yourPojoClass));

        return data;
    }

    /**
     * check for runtime perimission
     *
     * @param context
     * @param requestedPermissions
     * @return
     */
    public static boolean checkForRuntimePermission(FragmentActivity context, final String[] requestedPermissions) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;

        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(context, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(requestedPermissions, 10);
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * check for empty arraylist
     *
     * @param mlist
     * @param <T>
     * @return
     */
    public static <T> boolean isArrayListEmpty(List<T> mlist) {

        if (mlist == null) {
            return true;
        } else if (mlist.size() == 0) {
            return true;
        } else {
            return false;
        }

    }


    public static boolean isConnectedToInternet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static Typeface typedfile(Context context, String file) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), file);
        return typeface;
    }

    /**
     * Get the screen width.
     *
     * @param context
     * @return the screen width
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenWidth(Activity context) {

        Display display = context.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        }
        return display.getWidth();
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenHeight(Activity context) {

        Display display = context.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        }
        return display.getHeight();
    }

    public static void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if (items > 5) {
            x = items / 5;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }


    public void setFrameVisibility(final FrameLayout mFrameVisibility) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFrameVisibility.setVisibility(View.VISIBLE);
            }
        }, 1000);
    }

    static float preX = 0;
    static float preY = 0;

    public static boolean handleTouchEvent(MotionEvent ev, View view) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            preX = ev.getX();
            preY = ev.getY();
            return false;
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (Math.abs((preX - ev.getX())) > 8 || (Math.abs((preY - ev.getY())) > 8)) {
                preX = preY = 0;
                return false;
            } else {
                preX = preY = 0;
                return true;
            }

        }
        return false;
    }

    public static boolean isEmailValid(String mEmail) {
        if (mEmail.matches(Patterns.EMAIL_ADDRESS.pattern())) {
            return true;
        }
        return false;

    }

    public static void openGmailComposerIntent(Context context, String email, String subject, String body) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        final PackageManager pm = context.getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
        ResolveInfo best = null;
        for (final ResolveInfo info : matches)
            if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
                best = info;
        if (best != null)
            emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
        context.startActivity(emailIntent);

    }


    public static ArrayList<String> getGmailId(Context context) {
        ArrayList<String> accountsList = new ArrayList<>();

        //Getting all registered Google Accounts;
        try {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                return accountsList;
            }
            Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
            for (Account account : accounts) {
                accountsList.add(account.name);
            }
        } catch (Exception e) {
//            Log.i("Exception", "Exception:" + e);
        }

        return accountsList;
    }

}
