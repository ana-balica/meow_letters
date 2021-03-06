package anabalica.github.io.meowletters.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TableRow;

import anabalica.github.io.meowletters.R;

/**
 * CustomTableRow is used to equally distribute N number of rows inside a
 * SquareTableLayout, where N is SquareTableLayout custom attribute `rowsCount`.
 * Since the height of the parent view is set dynamically, the weight has no
 * effect on child elements of SquareTableLayout. The following is achieved by
 * setting the height of the CustomTableRow to 1/rowsCount of its width.
 *
 * @author Ana Balica
 */
public class CustomTableRow extends TableRow {
    private int columnsCount;

    public CustomTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomTableRow, 0, 0);

        try {
            columnsCount = attributes.getInteger(R.styleable.CustomTableRow_columnsCount, 0);
        } finally {
            attributes.recycle();
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        SquareTableLayout parent = (SquareTableLayout) getParent();
        final int rowsCount = parent.getRowsCount();
        final int width = getMeasuredWidth();
        setMeasuredDimension(width, width / rowsCount);
    }

    public int getColumnsCount() {
        return columnsCount;
    }
}
