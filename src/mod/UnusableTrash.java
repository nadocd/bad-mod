package mod;

import arc.util.Log;

public class UnusableTrash {

    public static void loadWarnOnError(Runnable runnable, String info){
        try {
            runnable.run();
        } catch (Exception e) {
            Log.warn("Failed to load \""+info+"\"");
        }
    };
}
