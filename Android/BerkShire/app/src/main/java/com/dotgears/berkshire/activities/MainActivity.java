package com.dotgears.berkshire.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

//import com.mikepenz.materialdrawer.app.drawerItems.CustomPrimaryDrawerItem;
//import com.mikepenz.materialdrawer.app.drawerItems.CustomUrlPrimaryDrawerItem;
//import com.mikepenz.materialdrawer.app.drawerItems.OverflowMenuDrawerItem;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private IProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BerkShire");

        // Create a few sample profile
        profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile));

        // Create the AccountHeader
        buildHeader(false, savedInstanceState);

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.book_your_room).withIcon(FontAwesome.Icon.faw_home),
//                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withDescription("This is a description").withIcon(FontAwesome.Icon.faw_eye),
//                        new CustomUrlPrimaryDrawerItem().withName(R.string.drawer_item_fragment_drawer).withDescription(R.string.drawer_item_fragment_drawer_desc).withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460"),
                        new SectionDrawerItem().withName(R.string.invite_friend),
                        new SecondaryDrawerItem().withName(R.string.coupon).withIcon(FontAwesome.Icon.faw_cart_plus),
                        new SecondaryDrawerItem().withName(R.string.feedback).withIcon(FontAwesome.Icon.faw_database).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.help).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.contact).withSelectedIconColor(Color.RED).withIconTintingEnabled(true).withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.member).withIcon(FontAwesome.Icon.faw_question).withEnabled(false)
                )
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        MainActivity.this.finish();
                        return false;
                    }
                })
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName(R.string.sign_in).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10),
                        new SecondaryDrawerItem().withName(R.string.sign_up).withIcon(FontAwesome.Icon.faw_github)
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
                            case "Sign In":

                                objectFragment = new BookYourRoom();
                                Intent itSignIn = new Intent(getApplicationContext(),SignIn.class);
                                startActivity(itSignIn);
                                break;
                            case "Sign Up":
                                objectFragment = new BookYourRoom();
                                Intent itSignOut = new Intent(getApplicationContext(),SignUp.class);
                                startActivity(itSignOut);
                                break;
                            case "Contact":
                                objectFragment = new BookYourRoom();
                                break;

                        }
                        // update the main content by replacing fragments
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_container, objectFragment).commit();
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();


    }

    /**
     * small helper method to reuse the logic to build the AccountHeader
     * this will be used to replace the header of the drawer with a compact/normal header
     *
     * @param compact
     * @param savedInstanceState
     */
    private void buildHeader(boolean compact, Bundle savedInstanceState) {
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(compact)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman").withEmail("batman@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile));
                            if (headerResult.getProfiles() != null) {
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}