package com.ggbwallet.app.router;

import android.app.Activity;
import android.content.Intent;

import com.ggbwallet.app.C;
import com.ggbwallet.app.ui.ImportWalletActivity;

public class ImportWalletRouter {

	public void openForResult(Activity activity, int requestCode) {
		Intent intent = new Intent(activity, ImportWalletActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		activity.startActivityForResult(intent, requestCode);
	}

	public void openWatchCreate(Activity activity, int requestCode) {
		Intent intent = new Intent(activity, ImportWalletActivity.class);
		intent.putExtra(C.EXTRA_STATE, "watch");
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		activity.startActivityForResult(intent, requestCode);
	}
}
