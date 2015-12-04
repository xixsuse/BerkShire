package com.dotgears.berkshire.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dotgears.berkshire.R;
import com.dotgears.berkshire.fragments.BookYourRoom;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialize.util.UIUtils;

public class MainActivity extends AppCompatActivity {

    private AccountHeader headerResult;
    private Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
        lp.height = lp.height + UIUtils.getStatusBarHeight(this);
        toolbar.setLayoutParams(lp);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("BerkShire Hotel");

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.book_your_room).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.booking_history).withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName(R.string.invite_friend).withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem().withName(R.string.coupon).withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem().withName(R.string.feedback).withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem().withName(R.string.help).withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem().withName(R.string.contact).withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName(R.string.member),
                        new SecondaryDrawerItem().withName(R.string.login).withIcon(FontAwesome.Icon.faw_bullhorn),
                        new SecondaryDrawerItem().withName(R.string.sign_up).withIcon(FontAwesome.Icon.faw_bullhorn)
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Fragment objectFragment = null;
                        switch (((Nameable) iDrawerItem).getName().getText(MainActivity.this)) {

                            case "Book Your Room":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Booking History":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Invite Friend":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Coupon":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Feedback":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Help":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Login":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Sign Up":
                                objectFragment = new BookYourRoom();
                                break;
                            case "Contact":
                                objectFragment = new BookYourRoom();
                                break;

                        }
                        // update the main content by replacing fragments
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, objectFragment).commit();
                        // .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        return false;
                    }
                })
                .build();

        fillFab();
        loadBackdrop();
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("https://unsplash.it/600/300/?random").centerCrop().into(imageView);
    }

    private void fillFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_home).actionBar().color(Color.WHITE));
    }
}