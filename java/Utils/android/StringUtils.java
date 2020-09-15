import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Patterns;

import java.util.List;

/**
 * Utils
 *
 * @author
 * @date
 */
public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmail(final @NonNull CharSequence str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static boolean isEmpty(final @Nullable String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isPresent(final @Nullable String str) {
        return !isEmpty(str);
    }

    public static final String toString(List<String> list, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (String item : list) {
            if (!first) {
                stringBuilder.append(separator);
            } else {
                first = false;
            }
            stringBuilder.append(item);
        }
        return stringBuilder.toString();
    }

    public static final CharSequence parseHtml(String htmlString) {

        if (htmlString == null) {
            return null;
        }

        htmlString = htmlString.replace("\n", "<br/>");

//        CharSequence sequence = Html.fromHtml(htmlString);
//        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
//        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
//        for(URLSpan span : urls) {
//            makeLinkClickable(strBuilder, span);
//        }
//        return strBuilder;

        return Html.fromHtml(htmlString);
    }

    //    protected static void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
//        int start = strBuilder.getSpanStart(span);
//        int end = strBuilder.getSpanEnd(span);
//        int flags = strBuilder.getSpanFlags(span);
//        ClickableSpan clickable = new ClickableSpan() {
//            public void onClick(View view) {
//                // Do something with span.getURL() to handle the link click...
//            }
//        };
//        strBuilder.setSpan(clickable, start, end, flags);
//        strBuilder.removeSpan(span);
//    }

}
