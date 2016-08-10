package benmu.com.bansheeview.provide;

import android.content.Context;

/**
 * Created by Carry on 16/8/9.
 */
public interface Provide {

    Context getContext(Object o);
    Object findView(Object o,int resId);
}
