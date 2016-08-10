package benmu.com.bansheeview;

import benmu.com.bansheeview.provide.Provide;

/**
 * Created by Carry on 16/8/9.
 */
public interface Banshee<T> {

    void inject(T host, Object source, Provide provide);
}
