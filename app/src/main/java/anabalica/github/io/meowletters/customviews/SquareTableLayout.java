package anabalica.github.io.meowletters.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

/**
 * Custom square TableLayout, i.e. the height is set equal to the width
 * of the layout.
 *
 * @author Ana Balica
 */
public class SquareTableLayout extends TableLayout {

    public SquareTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
