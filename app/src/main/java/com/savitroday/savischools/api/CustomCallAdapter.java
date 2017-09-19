package com.savitroday.savischools.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Harshita Ahuja on 07/10/16.
 */

public class CustomCallAdapter {
    
    
    /**
     * A callback which offers granular callbacks for various conditions.
     */
    public interface CustomCallback<T> {
        /**
         * Called for [200, 300) responses.
         */
        void success(Response<T> response);
        
        void failure(ApiException e);
    }
    
    public interface CustomCall<T> {
        void cancel();
        
        void enqueue(CustomCallback<T> callback);
        
        CustomCall<T> clone();
        
        Response<T> execute() throws IOException;
    }
    
    
    public static class ErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
        
        private final Executor callbackExecutor;
        public Context context;
        
        public ErrorHandlingCallAdapterFactory(Executor callbackExecutor, Context context) {
            this.callbackExecutor = callbackExecutor;
            this.context = context;
        }
        
        @Override
        public CallAdapter<CustomCall<?>> get(Type returnType, Annotation[] annotations,
                                              Retrofit retrofit) {
            if (getRawType(returnType) != CustomCall.class) {
                return null;
            }
            if (!(returnType instanceof ParameterizedType)) {
                throw new IllegalStateException(
                                                       "MyCall must have generic type (e.g., MyCall<ResponseBody>)");
            }
            final Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
            // final Executor callbackExecutor = retrofit.callbackExecutor();
            return new CallAdapter<CustomCall<?>>() {
                @Override
                public Type responseType() {
                    return responseType;
                }
                
                @Override
                public <R> CustomCall<R> adapt(Call<R> call) {
                    return new MyCallAdapter<>(context, call, callbackExecutor);
                }
            };
        }
    }
    
    /**
     * Adapts a {@link Call} to {@link CustomCall}.
     */
    static class MyCallAdapter<T> implements CustomCall<T> {
        private final Call<T> call;
        private final Executor callbackExecutor;
        Context context;
        
        MyCallAdapter(Context context, Call<T> call, Executor callbackExecutor) {
            this.context = context;
            this.call = call;
            this.callbackExecutor = callbackExecutor;
        }
        
        @Override
        public void cancel() {
            call.cancel();
        }
        
        @Override
        public void enqueue(final CustomCallback<T> callback) {
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, final Response<T> response) {
                    // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                    // on that executor by submitting a Runnable. This is left as an exercise for the reader.
                    
                    //  int code = response.code();
                    // if (code >= 200 && code < 300) {
                    if (response.isSuccessful()) {
                        callbackExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.success(response);
                            }
                        });
                        
                    } else {
                        callbackExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.failure(ApiException.httpError(response.raw().request().url().toString(),
                                        response, context));
                            }
                        });
                        
                    }
                }
                
                @Override
                public void onFailure(Call<T> call, final Throwable t) {
                    // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                    // on that executor by submitting a Runnable. This is left as an exercise for the reader.
                    
                    if (t instanceof IOException) {
                        callbackExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.failure(ApiException.networkError((IOException) t, context));
                            }
                        });
                    } else {
                        callbackExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.failure(ApiException.unexpectedError(t, context));
                            }
                        });
                        
                    }
                }
            });
        }
        
        @Override
        public CustomCall<T> clone() {
            return new MyCallAdapter<>(context, call.clone(), callbackExecutor);
        }
        
        @Override
        public Response<T> execute() throws IOException {
            return call.execute();
        }
    }
    
    public static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());
        
        @Override
        public void execute(@NonNull Runnable r) {
            
            Log.d("TAG-------", "in MainThreadExecutor");
            handler.post(r);
        }
    }
}
