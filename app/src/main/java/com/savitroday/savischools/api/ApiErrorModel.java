package com.savitroday.savischools.api;

/**
 * Created by harshitaahuja.
 */
public class ApiErrorModel {

    public ApiErrorModel(String errorMessage){
        this.errorMessage = errorMessage;
        this.errorSource = "";
    }

    public void prepareApiErrorMessage() {
        if(this.error != null && this.error.length() > 0) {
            this.errorMessage = this.getErrorMessage();
            this.errorSource = this.error;
        }
    }

    public ApiErrorModel(ApiErrorModel errorModel){
        if(errorModel.error!=null && errorModel.error.length()>0)
        this.errorMessage = errorModel.getErrorMessage();
        this.errorSource = errorModel.error;
    }

    public String errorType;
    public String errorMessage;
    public String errorSource;

    //OAuth Keys
    public String error;
    public String error_description;

    public String getErrorMessage(){
//TODO:move to strings
        switch (this.error){
            case "unauthorized":
            case "invalid_grant":
                return "The User ID / Password you entered does not match our records. Please try again.";
            case "invalid_token":
                return "Your session has been expired.Please login again";
        }
        return "Unable to Authenticate\n Please wait a few minutes and try again.";
    }
}
