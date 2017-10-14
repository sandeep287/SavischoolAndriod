package com.savitroday.savischools;


import com.google.firebase.messaging.RemoteMessage;
import com.savitroday.savischools.api.RestHelper;
import com.savitroday.savischools.di.module.AppModule;
import com.savitroday.savischools.di.module.ManagerModule;
import com.savitroday.savischools.di.module.NonAuthModule;
import com.savitroday.savischools.di.module.UserRestModule;
import com.savitroday.savischools.manager.AssignmentManager;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.manager.MyProfileManager;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.view.activity.LoginActivity;
import com.savitroday.savischools.view.activity.MainActivity;
import com.savitroday.savischools.view.fragment.DashboardFragment;
import com.savitroday.savischools.view.fragment.HistoryInvoiceFragment;
import com.savitroday.savischools.view.fragment.MessageOpenViewFragment;
import com.savitroday.savischools.view.fragment.MessagesFragment;
import com.savitroday.savischools.view.fragment.NotificationFragment;

import com.savitroday.savischools.view.fragment.NotificationMessageTabFragment;
import com.savitroday.savischools.view.fragment.ParentProfileFragment;
import com.savitroday.savischools.view.fragment.PendingInvoiceFragment;
import com.savitroday.savischools.view.fragment.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( //contains all other modules/dependencies
        modules = {
                          AppModule.class,
                          ManagerModule.class,
                          NonAuthModule.class,
                          UserRestModule.class
        }
)

public interface AppComponent {
    
    void inject(MyApplication application);
    void inject(NotificationMessageTabFragment notificationMessageTabFragment);
    void inject(NotificationFragment notificationFragment);
    void inject(RestHelper restHelper);
    void inject(ParentProfileFragment parentProfileFragment);
    void inject(MainActivity mainActivity);
    void inject(MessageOpenViewFragment messageOpenViewFragment);
    void inject(LoginActivity loginActivity);
    void inject(HistoryInvoiceFragment historyInvoiceFragment);
    
    void inject(DashboardFragment dashboardFragment);
    void inject(ProfileFragment profileFragment);
    void inject(PendingInvoiceFragment pendingInvoiceFragment);
    void inject(MessagesFragment messagesFragment);
    DashboardManager dashboardManager();
    MyProfileManager profileManager();
    
    InvoiceManager invoiceManager();
    
    AssignmentManager assignmentManager();
    
    NotificationManager notificationManager();
    
}