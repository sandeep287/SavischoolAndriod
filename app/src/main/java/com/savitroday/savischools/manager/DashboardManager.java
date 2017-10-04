package com.savitroday.savischools.manager;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.util.TinyDB;

import java.util.ArrayList;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Response;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

public class DashboardManager {
    public static final String TAG = "StudentManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private ArrayList<TaskCompletionSource> taskList = new ArrayList<>();
    private static Dashboard dashboard;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public DashboardManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getDashboardTask() {
        
        final TaskCompletionSource<Dashboard> task = new TaskCompletionSource<Dashboard>();
        
        if (dashboard != null && !clearCache && !updateInProgress) {
            task.trySetResult(dashboard);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            String userID = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
            
            userRestService.getDashboard(schoolId, parentId, userID).enqueue(new CustomCallAdapter
                                                                                         .CustomCallback<Dashboard>() {
                @Override
                public void success(Response<Dashboard> response) {
                    dashboard = response.body();
                    task.setResult(dashboard);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(dashboard);
                    }
                    taskList.clear();
                    updateInProgress = false;
                    clearCache = false;
                }
                
                @Override
                public void failure(ApiException e) {
                    task.setError(e);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setError(new Exception(e.getMessage()));
                    }
                    taskList.clear();
                    updateInProgress = false;
                }
            });
        }
        return task.getTask();
    }
    
    public void setDefaultStudent(Student student) {
        for (Student student1 : dashboard.listStudentModel) {
            if (student1.studentId.equals(student.studentId)) {
                student1.isdefault = true;
            } else {
                student1.isdefault = false;
            }
            EventManager.getInstance().postEventName(Event.DASHBOARD_UPDATED);
        }
    }
    
    public Task getStudentProfileTask(String studentId) {
        
        final TaskCompletionSource<Student> task = new TaskCompletionSource<Student>();
        
        if (dashboard != null && dashboard.listSchoolMessagesModel != null) {
            Student student = getStudentWithId(studentId);
            if (student != null) {
                task.trySetResult(student);
                return task.getTask();
            }
        }
        
        String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        
        userRestService.getStudentProfile(schoolId, parentId).enqueue(new CustomCallAdapter
                                                                                  .CustomCallback<Student>() {
            @Override
            public void success(Response<Student> response) {
                Student studentProfile = response.body();
                task.setResult(studentProfile);
                
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
                
            }
        });
        
        return task.getTask();
    }
    
    public Student getStudentWithId(String studentId) {
        for (Student student : dashboard.listStudentModel) {
            if (student.studentId.equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    
}
