package com.facerec.tasol.mvcexample.presenter;

/**
 * Created by anupamchugh on 11/08/17.
 */

public interface MainContract {

    interface MainView {


    }

    interface GetQuoteInteractor {
        interface OnFinishedListener {
            void onFinished(String string);
        }

        void getNextQuote(OnFinishedListener onFinishedListener);
    }

    interface GetUserModel{
        interface OnDataPushListener{
            void onPushed(String message);
        }
    }

    interface Presenter {
        void onButtonClick();

        void onDestroy();
    }
}
