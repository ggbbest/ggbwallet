package com.ggbwallet.app.di;

import com.ggbwallet.app.ui.ActivityFragment;
import com.ggbwallet.app.ui.AddTokenActivity;
import com.ggbwallet.app.ui.AdvancedSettingsActivity;
import com.ggbwallet.app.ui.AssetDisplayActivity;
import com.ggbwallet.app.ui.BackupKeyActivity;
import com.ggbwallet.app.ui.DappBrowserFragment;
import com.ggbwallet.app.ui.Erc20DetailActivity;
import com.ggbwallet.app.ui.FunctionActivity;
import com.ggbwallet.app.ui.GasSettingsActivity;
import com.ggbwallet.app.ui.HelpActivity;
import com.ggbwallet.app.ui.HomeActivity;
import com.ggbwallet.app.ui.ImportTokenActivity;
import com.ggbwallet.app.ui.ImportWalletActivity;
import com.ggbwallet.app.ui.MyAddressActivity;
import com.ggbwallet.app.ui.NewSettingsFragment;
import com.ggbwallet.app.ui.RedeemAssetSelectActivity;
import com.ggbwallet.app.ui.RedeemSignatureDisplayActivity;
import com.ggbwallet.app.ui.SelectNetworkActivity;
import com.ggbwallet.app.ui.SelectNetworkFilterActivity;
import com.ggbwallet.app.ui.SellDetailActivity;
import com.ggbwallet.app.ui.SendActivity;
import com.ggbwallet.app.ui.SplashActivity;
import com.ggbwallet.app.ui.TokenActivity;
import com.ggbwallet.app.ui.TokenDetailActivity;
import com.ggbwallet.app.ui.TokenFunctionActivity;
import com.ggbwallet.app.ui.TokenManagementActivity;
import com.ggbwallet.app.ui.TokenScriptManagementActivity;
import com.ggbwallet.app.ui.TransactionDetailActivity;
import com.ggbwallet.app.ui.TransferTicketActivity;
import com.ggbwallet.app.ui.TransferTicketDetailActivity;
import com.ggbwallet.app.ui.WalletActionsActivity;
import com.ggbwallet.app.ui.WalletConnectActivity;
import com.ggbwallet.app.ui.WalletConnectSessionActivity;
import com.ggbwallet.app.ui.WalletFragment;
import com.ggbwallet.app.ui.WalletsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = SplashModule.class)
	abstract SplashActivity bindSplashModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = AccountsManageModule.class)
	abstract WalletsActivity bindManageWalletsModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = ImportModule.class)
	abstract ImportWalletActivity bindImportWalletModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransactionDetailModule.class)
	abstract TransactionDetailActivity bindTransactionDetailModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = SendModule.class)
	abstract SendActivity bindSendModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = GasSettingsModule.class)
	abstract GasSettingsActivity bindGasSettingsModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = AddTokenModule.class)
	abstract AddTokenActivity bindAddTokenActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = RedeemSignatureDisplayModule.class)
	abstract RedeemSignatureDisplayActivity bindSignatureDisplayActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract AssetDisplayActivity bindAssetDisplayActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = SellDetailModule.class)
	abstract SellDetailActivity bindSellDetailsActivity();

	@FragmentScope
	@ContributesAndroidInjector(modules = NewSettingsModule.class)
	abstract NewSettingsFragment bindNewSettingsFragment();

	@FragmentScope
	@ContributesAndroidInjector(modules = ActivityModule.class)
	abstract ActivityFragment bindActivityFragment();

	@ActivityScope
	@ContributesAndroidInjector(modules = RedeemAssetSelectModule.class)
	abstract RedeemAssetSelectActivity bindRedeemTokenSelectActivity();

	@FragmentScope
	@ContributesAndroidInjector(modules = WalletModule.class)
	abstract WalletFragment bindWalletFragment();

	@ActivityScope
	@ContributesAndroidInjector(modules = HomeModule.class)
	abstract HomeActivity bindHomeActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = ImportTokenModule.class)
	abstract ImportTokenActivity bindImportTokenActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransferTicketDetailModule.class)
	abstract TransferTicketDetailActivity bindTransferTicketDetailActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransferTicketModule.class)
	abstract TransferTicketActivity bindTransferTicketActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = HelpModule.class)
	abstract HelpActivity bindHelpActivity();

	@FragmentScope
	@ContributesAndroidInjector(modules = DappBrowserModule.class)
	abstract DappBrowserFragment bindDappBrowserFragment();

	@ActivityScope
	@ContributesAndroidInjector(modules = Erc20DetailModule.class)
	abstract Erc20DetailActivity bindErc20DetailActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = WalletActionsModule.class)
	abstract WalletActionsActivity bindWalletActionsActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = BackupKeyModule.class)
	abstract BackupKeyActivity bindBackupKeyActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = MyAddressModule.class)
	abstract MyAddressActivity bindMyAddressActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract TokenFunctionActivity bindTokenFunctionActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract FunctionActivity bindFunctionActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract TokenDetailActivity bindTokenDetailActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract TokenActivity bindTokenActivity();

	@ContributesAndroidInjector(modules = SelectNetworkModule.class)
	abstract SelectNetworkActivity bindSelectNetworkActivity();

	@ContributesAndroidInjector(modules = SelectNetworkFilterModule.class)
	abstract SelectNetworkFilterActivity bindSelectNetworkFilterActivity();

	@ContributesAndroidInjector(modules = TokenManagementModule.class)
	abstract TokenManagementActivity bindTokenManagementActivity();

	@ContributesAndroidInjector(modules = AdvancedSettingsModule.class)
	abstract AdvancedSettingsActivity bindAdvancedSettingsActivity();

    @ActivityScope
	@ContributesAndroidInjector(modules = TokenScriptManagementModule.class)
	abstract TokenScriptManagementActivity bindTokenScriptManagementActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = WalletConnectModule.class)
	abstract WalletConnectActivity bindWalletConnectActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = WalletConnectModule.class)
	abstract WalletConnectSessionActivity bindWalletConnectSessionActivity();
}
