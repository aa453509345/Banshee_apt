package benmu.com.bansheeview.provide;

import android.app.Activity;
import android.content.Context;
/**
 * Created by Carry on 16/8/9.
 */
public class FindProvide implements Provide {
    @Override
    public Context getContext(Object o) {
        return ((Activity)o);
    }

    @Override
    public Object findView(Object o, int resId) {
        return ((Activity)o).findViewById(resId);
    }
}
