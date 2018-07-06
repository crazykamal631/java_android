package com.testmvp;

public class PresenterImpl implements Contract.ModelCallBacks.OnFinishedListener,
        Contract.PresenterCallBacks {
    private Contract.ViewCallBacks mMainView;
    private Contract.ModelCallBacks mModel;

    public PresenterImpl(Contract.ViewCallBacks mMainView, Contract.ModelCallBacks mModel) {
        this.mMainView = mMainView;
        this.mModel = mModel;
    }

    @Override
    public void onFinished(String string) {
        if (mMainView != null) {
            mMainView.hideProgress();
            mMainView.setText(string);
        }
    }

    @Override
    public void onButtonClick() {
        if (mMainView != null) {
            mMainView.showProgress();
        }
        mModel.getNextFruit(this);
    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }
}
