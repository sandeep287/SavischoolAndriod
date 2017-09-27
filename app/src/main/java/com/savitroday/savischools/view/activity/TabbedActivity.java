package com.savitroday.savischools.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.savitroday.savischools.R;
import com.savitroday.savischools.view.fragment.DashboardFragment;


public class TabbedActivity extends AppCompatActivity {
    
    private SlidingPaneLayout mSlidingLayout;
    private ListView mList;

    RelativeLayout relativeLayout1,relativeLayout2;
    View temp;
    private ActionBarHelper mActionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relativeLayout1=(RelativeLayout)findViewById(R.id.rlayoutman);
        relativeLayout2=(RelativeLayout)findViewById(R.id.rlayouttemp);
        temp=findViewById(R.id.rlayouttemp);
        relativeLayout1.setVisibility(View.INVISIBLE);
        relativeLayout2.addView(temp);
        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
        mList = (ListView) findViewById(R.id.left_pane);
        
        mSlidingLayout.setPanelSlideListener(new SliderListener());
//        mList.setAdapter(new ArrayAdapter(this,
//                                                         R.layout.simple_list_item, DataUnit.TITLES));
        mList.setOnItemClickListener(new ListItemClickListener());
        
        
        mActionBar = createActionBarHelper();
        mActionBar.init();
        mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new FirstLayoutListener());
        
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.flFragments, new DashboardFragment());
        transaction.commit();
    }
    
    
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 1) {
            fragmentManager.popBackStackImmediate();
        } else {
            finishAffinity();
        }
    }
    
    public class Handler {
        public void onSelectChildren() {
            //TODO: show popup here
            //showPopup();
        }
        
        public void openCategoryList() {
            
        }
        
        public void openActivityScreen() {
            
        }
    }
    
    /**
     * This list item click listener implements very simple view switching by
     * changing the primary content text. The slider is closed when a selection
     * is made to fully reveal the content.
     */
    private class ListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            
            mSlidingLayout.smoothSlideClosed();
        }
    }
    
    /**
     * This panel slide listener updates the action bar accordingly for each
     * panel state.
     */
    private class SliderListener extends
            SlidingPaneLayout.SimplePanelSlideListener {
        @Override
        public void onPanelOpened(View panel) {
            mActionBar.onPanelOpened();
        }
        
        @Override
        public void onPanelClosed(View panel) {
            mActionBar.onPanelClosed();
        }
    }
    
    /**
     * This global layout listener is used to fire an event after first layout
     * occurs and then it is removed. This gives us a chance to configure parts
     * of the UI that adapt based on available space after they have had the
     * opportunity to measure and layout.
     */
    private class FirstLayoutListener implements
            ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            mActionBar.onFirstLayout();
            mSlidingLayout.getViewTreeObserver().removeOnGlobalLayoutListener(
                    this);
        }
    }
    
    /**
     * Create a compatible helper that will manipulate the action bar if
     * available.
     */
    private ActionBarHelper createActionBarHelper() {
        
        return new ActionBarHelper();
        
    }
    
    /**
     * Stub action bar helper; this does nothing.
     */
    private class ActionBarHelper {
        public void init() {
        }
        
        public void onPanelClosed() {
        }
        
        public void onPanelOpened() {
        }
        
        public void onFirstLayout() {
        }
        
        public void setTitle(CharSequence title) {
        }
    }
    
}
