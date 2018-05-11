package personal.rowan.sandbox.ui.base;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Rowan Hall
 */

public abstract class BaseActivity
        extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String title, String message) {
        mProgressDialog = ProgressDialog.show(this, title, message, true);
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    protected void setToolbar(Toolbar toolbar, String title) {
        setToolbar(toolbar, title, false);
    }

    protected void setToolbar(Toolbar toolbar, String title, boolean setUpButton) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        setTitle(title);
        if(setUpButton && actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }
    }

    // Override this method for specific onUpPressed behavior
    protected void onUpPressed() {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onUpPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
