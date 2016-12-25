package com.progress.mohak.primercopy;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    ViewPager pager;
    pagerAdap adap;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);

        adap = new pagerAdap(getSupportFragmentManager());
        adap.addFrag(frag3.newInstance("", ""));
        adap.addFrag(frag2.newInstance("", ""));
        adap.addFrag(frag1.newInstance("", ""));

        pager.setAdapter(adap);
        pager.setBackgroundColor(getResources().getColor(R.color.frag3));


        final Integer[] colors = {getResources().getColor(R.color.frag3), getResources().getColor(R.color.frag2), getResources().getColor(R.color.frag1)};
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adap.getCount() - 1) && position < (colors.length - 1)) {

                    pager.setBackgroundColor((Integer) new ArgbEvaluator().evaluate(positionOffset, colors[position], colors[position + 1]));

                } else {

                    pager.setBackgroundColor(colors[colors.length - 1]);

                }

            }


            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pager.setPageTransformer(true, new ViewPager.PageTransformer() {
                    @Override
                    public void transformPage(View view, float position) {


                        img1 = (ImageView) view.findViewById(R.id.img1);
                        img2 = (ImageView) view.findViewById(R.id.img2);
                        img3 = (ImageView) view.findViewById(R.id.img3);


                        img4 = (ImageView) view.findViewById(R.id.img4);
                        img5 = (ImageView) view.findViewById(R.id.img5);
                        img6 = (ImageView) view.findViewById(R.id.img6);


                        img7 = (ImageView) view.findViewById(R.id.img7);
                        img8 = (ImageView) view.findViewById(R.id.img8);
                        img9 = (ImageView) view.findViewById(R.id.img9);


                        int pageWidth = view.getWidth();
                        int pageHeight = view.getHeight();

                        float ratio = (float) pageWidth / pageHeight;

                        Log.d(TAG, "val " + ratio);

                        if (position < -1) { // [-Infinity,-1)

                            // This page is way off-screen to the left.

                        } else if (position <= 1) { // [-1,1]


                            if (position > 0 && position <= 1) {

                                if (img1 != null && img2 != null && img3 != null) {

                                    img1.setTranslationY(-(pageWidth * (1 - position) / 3* ratio));
                                    img2.setTranslationX(pageWidth * (1 - position) / 8 * ratio);
                                    img3.setTranslationY(pageWidth * (1 - position) / 3 * ratio);

                                }

                                if (img4 != null && img5 != null && img6 != null) {

                                    img5.setTranslationX(pageWidth * (1 - position) / 20);
                                    img5.setTranslationY(pageWidth * (1 - position) / 20);
                                    img6.setTranslationX(pageWidth * (1 - position) / 10);
                                    img6.setTranslationY(pageWidth * (1 - position) / 10);
                                    img4.setRotation(-6 * (1 - position));
                                    img5.setRotation(-2 * (1 - position));

                                }


                            } else if (position <= 0 && position > -1) {

                                if (img1 != null && img2 != null && img3 != null) {

                                    position = -position;
                                    img1.setTranslationY(-(pageWidth * (1 - position) / 3 * ratio));
                                    img2.setTranslationX(pageWidth * (1 - position) / 8 * ratio);
                                    img3.setTranslationY(pageWidth * (1 - position) / 3 * ratio);

                                }

                                if (img7 != null && img8 != null && img9 != null) {

//
                                    img9.setTranslationY(pageWidth * (position) / 2);
                                    img8.setTranslationY(pageWidth * (position) / 3);
                                    img7.setTranslationY(pageWidth * (position) / 4);

                                    img9.setTranslationX(pageWidth * (position) / 2);
                                    img8.setTranslationX(pageWidth * (position) / 3);
                                    img7.setTranslationX(pageWidth * (position) / 4);


                                    img9.setRotation(4 * (position * 10));
                                    img8.setRotation(2 * (position * 10));
                                }

                            }


                        } else {

                            // (1,+Infinity]
                            // This page is way off-screen to the right.

                        }
                    }
                }

        );


    }

    public class pagerAdap extends FragmentStatePagerAdapter {

        ArrayList<Fragment> fragments = new ArrayList<>();

        public pagerAdap(FragmentManager fm) {
            super(fm);
        }

        void addFrag(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
