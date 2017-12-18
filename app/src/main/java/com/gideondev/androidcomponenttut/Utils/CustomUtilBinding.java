package com.gideondev.androidcomponenttut.Utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

/**
 * Created by ${ENNY} on 12/4/2017.
 */

public class CustomUtilBinding {

    public CustomUtilBinding() {
    }

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("greetings")
    public static void setName(TextView view, String text) {
        view.setText("Welcome, " + text);
    }

    @BindingAdapter({ "font" })
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }

    @BindingAdapter("htmlText")
    public static void setHtmlText(TextView textView, String htmlText) {
        if (htmlText == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            //noinspection deprecation
            textView.setText(Html.fromHtml(htmlText));
        }
    }
}
