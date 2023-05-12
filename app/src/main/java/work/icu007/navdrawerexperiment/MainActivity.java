package work.icu007.navdrawerexperiment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 实例化一个FloatingActionButton对象fab，若其不为空，则设置点击监听。
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        // 实例化 DrawerLayout 对象 drawer 与R.id.drawer_layout绑定
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 实例化ActionBarDrawerToggle对象 toogle
        /**
         * `ActionBarDrawerToggle` 是一个辅助类，它可用于在 `Toolbar` 上创建一个标准的抽屉式导航图标，
         * 并与 DrawerLayout 相关联。它可以让我们轻松实现滑动菜单与标题栏之间的联动效果。
         *
         * 以下是 `ActionBarDrawerToggle` 的一些重要方法：
         *
         * 1. `onDrawerOpened()` 和 `onDrawerClosed()`
         *
         *    `onDrawerOpened()` 和 `onDrawerClosed()` 方法分别在抽屉打开和关闭时被调用。
         *    可以通过扩展这些方法来处理特定情况下的行为，例如更新菜单或刷新 UI。
         *
         * 2. `onDrawerSlide()`
         *
         *    `onDrawerSlide()` 方法在抽屉移动时被调用，它使您可以在抽屉滑动时进行自定义操作，例如修改抽屉旁边的 UI。
         *
         * 3. `syncState()`
         *
         *    `syncState()` 方法将当前的抽屉状态与 `ActionBarDrawerToggle` 同步。
         *    应该在每次 Activity 启动时和配置更改时调用此方法。
         *
         * 4. `setDrawerIndicatorEnabled()`
         *
         *    `setDrawerIndicatorEnabled()` 方法用于启用或禁用汉堡包按钮（也称为三条横线），
         *    该按钮用于抽屉导航的触发。如果不想显示汉堡包按钮，则可以使用此方法禁用它。
         *
         * 5. `setHomeAsUpIndicator()`
         *
         *    `setHomeAsUpIndicator()` 方法用于设置自定义指示器，以替代默认的汉堡包图标。
         *    可以使用此方法来显示应用程序自己的标志或其他自定义图像。
         *
         * 总之，`ActionBarDrawerToggle` 是一个有用的辅助类，可简化与 DrawerLayout 和 Toolbar 相关的交互操作。
         * 如果应用程序需要滑动菜单和标题栏之间的联动效果，则可以考虑使用 `ActionBarDrawerToggle` 类。
         *
         * ActionBarDrawerToggle 实例化 需要传入三个参数： Activity实例 DrawerLayout对象和Toolbar 对象。
         * 这里的 R.string.navigation_drawer_open 和 R.string.navigation_drawer_close 是字符串资源，
         * 表示打开和关闭抽屉时显示的文本。这些字符串通常用于屏幕阅读器等辅助功能。
         */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (drawer != null) {
            // 将 ActionBarDrawerToggle 设置为 DrawerLayout 监听器
            // 以便在处理抽屉滑动事件时自动更新汉堡包按钮的状态
            drawer.addDrawerListener(toggle);
        }
        // 调用 ActionBarDrawerToggle 对象的 syncState() 方法，以确保抽屉的状态与按钮的状态正确匹配。
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    /**
     * Handles the Back button: closes the nav drawer.
     * 处理返回事件 关闭 nav drawer
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * Inflates the options menu.
     * 填充操作菜单
     * @param menu  Menu to inflate
     * @return      Returns true if menu is inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles a click on the Settings item in the options menu.
     * 处理在操作菜单的点击操作，点击到 settings时返回true。
     * @param item  Item in options menu that was clicked.
     * @return      Returns true if the item was Settings.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Handles a navigation drawer item click. It detects which item was
     * clicked and displays a toast message showing which item.
     * 处理抽屉式导航栏 点击事件，当某项被点击时显示toast消息。
     * @param item  Item in the navigation drawer
     * @return      Returns true after closing the nav drawer
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_camera:
                // Handle the camera import action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_camera));
                return true;
            case R.id.nav_gallery:
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_gallery));
                return true;
            case R.id.nav_slideshow:
                // Handle the slideshow action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_slideshow));
                return true;
            case R.id.nav_manage:
                // Handle the tools action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_tools));
                return true;
            case R.id.nav_share:
                // Handle the share action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_share));
                return true;
            case R.id.nav_send:
                // Handle the send action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_send));
                return true;
            default:
                return false;
        }
    }

    /**
     * Displays a toast message.
     * @param message   Message to display in toast
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}