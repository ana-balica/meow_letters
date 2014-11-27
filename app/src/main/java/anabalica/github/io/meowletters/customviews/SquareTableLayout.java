package anabalica.github.io.meowletters.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TableLayout;

import anabalica.github.io.meowletters.R;

/**
 * Custom square TableLayout, i.e. the height is set equal to the width
 * of the layout.
 *
 * @author Ana Balica
 */
public class SquareTableLayout extends TableLayout {
    private int rowsCount;

    public SquareTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.SquareTableLayout, 0, 0);

        try {
            rowsCount = attributes.getInteger(R.styleable.SquareTableLayout_rowsCount, 0);
        } finally {
            attributes.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    public int getRowsCount() {
        return rowsCount;
    }
}
