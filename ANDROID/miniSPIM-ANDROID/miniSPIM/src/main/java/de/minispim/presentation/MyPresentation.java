package de.minispim.presentation;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;

import de.minispim.R;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

/**
 * Created by Bene on 06.01.17.
 */

/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * <p>
 * A {@link android.app.Presentation} used to demonstrate interaction between primary and
 * secondary screens.
 * </p>
 * <p>
 * It displays the name of the display in which it has been embedded (see
 * {@link android.app.Presentation#getDisplay()}) and exposes a facility to change its
 * background color and display its text.
 * </p>
 */
public class MyPresentation extends Presentation {

    private LinearLayout mLayout;
    private ImageView mImageView;

    public MyPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the custom layout
        setContentView(R.layout.presentation_layout);

        // Get the Views
        mLayout = (LinearLayout) findViewById(R.id.display_layout);
        mImageView = (ImageView) findViewById(R.id.imageView);

        setColor(RED);
    }

    /**
     * Set the background color of the layout and display the color as a String.
     *
     * @param color The background color
     */
    public void setColor(int color) {
        mLayout.setBackgroundColor(color);
        mImageView.setImageDrawable(null);

    }


    /**
     * Set the background image of the layout
     *
     * @param mImage Background Image
     */
    public void setDrawable(Drawable mImage) {
        // set image to ImageView
        mLayout.setBackgroundColor(BLACK);
        mImageView.setImageDrawable(mImage);
    }


    /**
     * Set the background image of the layout
     *
     * @param mBitmap Background Image
     */
    public void setBitmap(Bitmap mBitmap) {
        // set image to ImageView
        mLayout.setBackgroundColor(BLACK);
        mImageView.setImageBitmap(mBitmap);
    }
}
