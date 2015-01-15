package anabalica.github.io.meowletters.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TableRow.LayoutParams;

import java.util.HashMap;

import anabalica.github.io.meowletters.R;

/**
 * SquareButton width and height are set according to the CustomTableRow width
 * and custom attribute columnsCount, by dividing CustomTableRow width to the
 * columnsCount.
 *
 * @author Ana Balica
 */
public class SquareButton extends Button {
    public static final int THRESHOLD_TABLETS = 600;
    public static final int SMALL_MARGIN = 2;
    public static final int AVERAGE_MARGIN = 4;

    private int margin;
    private Integer row;
    private Integer column;
    private HashMap<String, Integer> position = new HashMap<>(2);
    LayoutParams params = new LayoutParams();

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        margin = computeMargin(context);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.SquareButton, 0, 0);

        try {
            row = attributes.getInteger(R.styleable.SquareButton_row, 0);
            column = attributes.getInteger(R.styleable.SquareButton_column, 0);
        } finally {
            attributes.recycle();
        }
        position.put("row", row);
        position.put("column", column);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        CustomTableRow parent = (CustomTableRow) getParent();
        final int parentWidth = parent.getMeasuredWidth();
        final int columnsCount = parent.getColumnsCount();
        final int width = (parentWidth / columnsCount) - margin * 2;

        setMeasuredDimension(width, width);
        params.setMargins(margin, margin, margin, margin);
        setLayoutParams(params);
    }

    /**
     * Compute the margin of a single square button in the runtime according to the device width
     *
     * @param context Context object
     * @return int margin in px units
     */
    private int computeMargin(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dpWidth = Math.round(displayMetrics.widthPixels / displayMetrics.density);

        int dpMargin = 0;
        if (dpWidth < THRESHOLD_TABLETS) {
            dpMargin = SMALL_MARGIN;
        } else {
            dpMargin = AVERAGE_MARGIN;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpMargin, getResources().getDisplayMetrics());
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public HashMap<String, Integer> getPosition() {
        return position;
    }
}
