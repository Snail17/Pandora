
package com.pandora.core.base;


import android.support.v4.app.Fragment;

import java.util.Stack;

/**
 * FragmentManager s
 */
public final class FragManager {
    private static Stack<Fragment> fragmentsStack;
    private  static volatile FragManager instance;

    private FragManager() {

    }

    /**
     * @return s
     */
    public static FragManager getAppManager() {
        if (instance == null) {
            synchronized (FragManager.class) {
                if (instance == null) {
                    instance = new FragManager();
                    instance.fragmentsStack = new Stack();
                }
            }

        }
        return instance;
    }

    /**
     * @param fragment s
     */
    public void addFragment(Fragment fragment) {
        if (fragmentsStack == null) {
            fragmentsStack = new Stack<Fragment>();
        }
        if (haseFragment(fragment)){
            removeFragment(fragment);
        }
        fragmentsStack.add(fragment);
    }

    /**
     * @return s
     */
    public Fragment currentFragment() {
        try {
            Fragment fragment = fragmentsStack.lastElement();
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param fragment s
     */
    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentsStack.remove(fragment);
            fragment = null;
        }
    }

    /**
     *
     * @param fragment s
     * @return s
     */
    public boolean haseFragment(Fragment fragment) {
        for (Fragment frag:fragmentsStack){
            if (frag.equals(fragment)){
                return true;
            }
        }
        return false;
    }

}