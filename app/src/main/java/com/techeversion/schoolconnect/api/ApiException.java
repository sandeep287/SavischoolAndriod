package com.techeversion.schoolconnect.api;

/**
 * Created by harshitaahuja.
 */

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.techeversion.schoolconnect.R;
import com.techeversion.schoolconnect.util.NetworkUtils;

import java.io.IOException;

import retrofit2.Response;


// This is RetrofitError converted to Retrofit 2
public class ApiException extends RuntimeException {
    private final String url;
    private final Response response;
    private final Kind kind;
    private ApiErrorModel errorModel;
    private Context context;
    
    public ApiException(Context context, String message, String url, Response response, Kind kind, Throwable
                                                                                                           exception,
                        boolean isNetworkError) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        if (isNetworkError) {
            this.errorModel = new ApiErrorModel(message);
        }
        this.context = context;
    }
    
    
    public static ApiException httpError(String url, Response response, Context context) {
        String message = response.code() + " " + response.message();
        return new ApiException(context, message, url, response, Kind.HTTP, null, false);
    }
    
    public static ApiException networkError(IOException exception, Context context) {
        if (NetworkUtils.isNetworkAvailable()) {
            return new ApiException(context, context.getString(R.string.timeout), null, null, Kind.NETWORK,
                                           exception, true);
        }
        return new ApiException(context, context.getString(R.string.internet_off), null, null, Kind.NETWORK,
                                       exception, true);
    }
    
    public static ApiException unexpectedError(Throwable exception, Context context) {
        return new ApiException(context, exception.getMessage(), null, null, Kind.UNEXPECTED, exception, false);
    }
    
    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }
    
    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }
    
    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
/*
    public <T> T getErrorBodyAs(Class<T> type, ResponseBody responseBody) throws IOException, RuntimeException {
        Gson gson = new Gson();
        ApiErrorModel errorModel = gson.fromJson(responseBody.toString(), type);
        Converter<ResponseBody, T> converter =  GsonConverterFactory.create().responseBodyConverter(type, new
        Annotation[0]);//MyApplication.getRestClient().getRetrofitInstance().responseBodyConverter(type, new
        Annotation[0]);
        try {
            return converter.convert(responseBody);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
*/
    public ApiErrorModel getErrorModel() {
        //TODO: Check for null if null Send a mock ApiErrorModel saying unexpected happen
        if (this.errorModel != null) {
            return errorModel;
        }
        if (response == null || response.errorBody() == null) {
            return new ApiErrorModel(context.getString(R.string.server_unreachable));
        }
        try {
            Gson gson = new Gson();
            ApiErrorModel apiErrorModel = gson.fromJson(response.errorBody().string(), ApiErrorModel.class);
            apiErrorModel.prepareApiErrorMessage();
            Log.d("APIException ------- : ", apiErrorModel.errorMessage + " " + apiErrorModel.errorSource);
            if ((apiErrorModel.errorMessage == null && apiErrorModel.errorSource == null) || (apiErrorModel
                                                                                                      .errorMessage
                                                                                                      .length() <= 0
                                                                                                      &&
                                                                                                      apiErrorModel
                                                                                                              .errorSource.length() <= 0)) {
                return new ApiErrorModel(apiErrorModel);
            }
            return apiErrorModel;
        } catch (Exception e1) {
            
            e1.printStackTrace();
            return new ApiErrorModel(context.getString(R.string.server_unreachable));
        }
    }
    
    /**
     * Identifies the event kind which triggered a {@link ApiException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}