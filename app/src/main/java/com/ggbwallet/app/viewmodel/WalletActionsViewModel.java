package com.ggbwallet.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ggbwallet.app.C;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import com.ggbwallet.app.entity.ErrorEnvelope;
import com.ggbwallet.app.entity.Wallet;
import com.ggbwallet.app.interact.DeleteWalletInteract;
import com.ggbwallet.app.interact.ExportWalletInteract;
import com.ggbwallet.app.interact.FetchWalletsInteract;
import com.ggbwallet.app.router.HomeRouter;

public class WalletActionsViewModel extends BaseViewModel {
    private final static String TAG = WalletActionsViewModel.class.getSimpleName();

    private final HomeRouter homeRouter;
    private final DeleteWalletInteract deleteWalletInteract;
    private final ExportWalletInteract exportWalletInteract;
    private final FetchWalletsInteract fetchWalletsInteract;

    private final MutableLiveData<Integer> saved = new MutableLiveData<>();
    private final MutableLiveData<Boolean> deleted = new MutableLiveData<>();
    private final MutableLiveData<ErrorEnvelope> exportWalletError = new MutableLiveData<>();
    private final MutableLiveData<ErrorEnvelope> deleteWalletError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isTaskRunning = new MutableLiveData<>();

    WalletActionsViewModel(
            HomeRouter homeRouter,
            DeleteWalletInteract deleteWalletInteract,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        this.deleteWalletInteract = deleteWalletInteract;
        this.exportWalletInteract = exportWalletInteract;
        this.fetchWalletsInteract = fetchWalletsInteract;
        this.homeRouter = homeRouter;
    }

    public LiveData<ErrorEnvelope> exportWalletError() {
        return exportWalletError;
    }

    public LiveData<ErrorEnvelope> deleteWalletError() {
        return deleteWalletError;
    }

    public LiveData<Boolean> deleted() {
        return deleted;
    }

    public LiveData<Integer> saved() {
        return saved;
    }

    public LiveData<Boolean> isTaskRunning() {
        return isTaskRunning;
    }

    public void deleteWallet(Wallet wallet) {
        isTaskRunning.postValue(true);
        disposable = deleteWalletInteract
                .delete(wallet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDelete, this::onDeleteWalletError);
    }

    private void onDeleteWalletError(Throwable throwable) {
        isTaskRunning.postValue(false);
        deleteWalletError.postValue(
                new ErrorEnvelope(C.ErrorCode.UNKNOWN, TextUtils.isEmpty(throwable.getLocalizedMessage())
                        ? throwable.getMessage() : throwable.getLocalizedMessage()));
    }

    private void onDelete(Wallet[] wallets) {
        isTaskRunning.postValue(false);
        deleted.postValue(true);
    }

    public void storeWallet(Wallet wallet) {
        isTaskRunning.postValue(true);
        disposable = fetchWalletsInteract.storeWallet(wallet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onStored, this::onError);
    }

    private void onStored(Wallet wallet) {
        isTaskRunning.postValue(false);
        Log.d(TAG, "Stored " + wallet.address);
        saved.postValue(1);
    }

    @Override
    protected void onError(Throwable throwable) {
        isTaskRunning.postValue(false);
        super.onError(throwable);
    }

    public void showHome(Context context) {
        homeRouter.open(context, true);
    }
}
