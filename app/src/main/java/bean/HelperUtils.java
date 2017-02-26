package bean;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import com.example.boylw789.edubao.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HelperUtils {

    public static SpannableStringBuilder highlight(Context context,
                                                   String text, String target) {

        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(ContextCompat.getColor(context,
                    R.color.backgroud_title));
            spannable.setSpan(span, m.start(), m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }
}
