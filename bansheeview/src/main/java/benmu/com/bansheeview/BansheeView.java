package benmu.com.bansheeview;

import android.app.Activity;

import java.util.HashMap;

import benmu.com.bansheeview.provide.FindProvide;
import benmu.com.bansheeview.provide.Provide;

/**
 * Created by Carry on 16/8/9.
 */
public class BansheeView {
    public static Provide activityProvide=new FindProvide();
    public static HashMap<String,Banshee> BansheeMap=new HashMap<>();
    private static final String Regex="$$Banshee";
    public static void inject(Activity activity){
        inject(activity,activity, activityProvide);
    }

    public static void inject(Object host,Object source,Provide provide){
        if(host==null){
            throw new IllegalArgumentException("host must not be null");
        }
        String clazzName=host.getClass().getName();
        Banshee banshee=BansheeMap.get(clazzName);
        if(banshee==null){
            try {
                Class aClass=Class.forName(clazzName+Regex);
                banshee=(Banshee)aClass.newInstance();
                BansheeMap.put(clazzName,banshee);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        banshee.inject(host,source,provide);

    }
}
