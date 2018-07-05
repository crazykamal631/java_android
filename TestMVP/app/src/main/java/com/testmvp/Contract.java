package com.testmvp;

class Contract {
    interface ViewCallBacks {
        void showProgress();

        void hideProgress();

        void setText(String string);
    }

    interface ModelCallBacks {
        interface OnFinishedListener {
            void onFinished(String string);
        }

        void getNextFruit(OnFinishedListener onFinishedListener);
    }

    interface PresenterCallBacks {
        void onButtonClick();

        void onDestroy();
    }
}
