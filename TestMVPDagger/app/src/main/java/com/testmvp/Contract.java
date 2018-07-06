package com.testmvp;

public class Contract {
    public interface ViewCallBacks {
        void showProgress();

        void hideProgress();

        void setText(String string);
    }

    public interface ModelCallBacks {
        interface OnFinishedListener {
            void onFinished(String string);
        }

        void getNextFruit(OnFinishedListener onFinishedListener);
    }

    public interface PresenterCallBacks {
        void onButtonClick();

        void onDestroy();
    }
}
