package com.ggbwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.ggbwallet.app.interact.ExportWalletInteract;
import com.ggbwallet.app.interact.FetchWalletsInteract;
import com.ggbwallet.app.service.KeyService;

import javax.inject.Inject;

public class BackupKeyViewModelFactory implements ViewModelProvider.Factory {
    private final KeyService keyService;
    private final ExportWalletInteract exportWalletInteract;
    private final FetchWalletsInteract fetchWalletsInteract;

    @Inject
    public BackupKeyViewModelFactory(
            KeyService keyService,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        this.keyService = keyService;
        this.exportWalletInteract = exportWalletInteract;
        this.fetchWalletsInteract = fetchWalletsInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BackupKeyViewModel(
                keyService,
                exportWalletInteract,
                fetchWalletsInteract);
    }
}
